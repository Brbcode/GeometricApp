package beans.property;

import beans.value.ChangeListener;
import beans.value.ObservableValue;
import beans.value.WritableValue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A Property is a variable wrapper that allows detect changes on hiself and 
 * trigger a change listener, also allows bind multiple property to auto update 
 * their value to match all values.
 * 
 * @author Bruno Garcia Tripoli
 * @param <T> - Value type of property
 */
public abstract class Property<T> implements ObservableValue<T>,WritableValue<T> {
    /**
     * Property value
     */
    private T value;
    /**
     * List of change listeners.
     */
    private List<ChangeListener<T>> listeners;
    /**
     * List of Properties attached to this Property
     */
    private Set<Property<T>> binds;
    
    /**
     * Property constructor, set property initial value.
     * 
     * @param value - Avoid null values.
     */
    public Property(T value){
        this.value = value;
        listeners = new ArrayList();
        binds = new HashSet();
    }

    @Override
    public void addListener(ChangeListener<T> listener) {
        listeners.add(listener);
    }

    @Override
    public boolean removeListener(ChangeListener< T> listener) {
        return listeners.remove(listener);
    }
    
    /**
     * Method to set internal value without trigger change listeners.
     * This method is dangerous be careful in its use.
     * @param value - Value to be set.
     */
    protected final void __set(T value){         
        this.value = value;
    }
    /**
     * Bind current property with another one.
     * The bind is bidirectional.
     * 
     * @param other - Other property to be bind.
     */
    public void bind(Property<T> other){
        binds.add(other);
        other.binds.add(this);
    }
    /**
     * Remove a existent bind if exist otherwise return false.
     * @param other - Other property to be unbind.
     * @return True if the bind has been removed sucessfully, false otherwise. 
     */
    public boolean unbind(Property<T> other){
        if(!binds.contains(other))
            return false;
        
        other.binds.remove(this);
        binds.remove(other);
        return true;
    }

    @Override
    public T get() {
        return value;
    }
    
    @Override
    public void set(T value){
        if(get() == value) return;
        
        for(var l : listeners)
            l.changed(this, get(), value);
                
        for(var prop : getTree(binds)){
            for(var l : prop.listeners)
                l.changed(this, prop.get(), value);
            
            prop.__set(value);
        }
        
        __set(value);
    }
    
    private Set<Property<T>> getTree(Collection<Property<T>> collection){
        Set<Property<T>> ret = new HashSet();
        for(var prop : collection)
        {
            ret.add(prop);
            appendTree(ret, prop.binds);
        }
        
        return ret;
    }
    
    private void appendTree(Set<Property<T>> ret, Collection<Property<T>> collection){
        for(var prop : collection){
            if(!ret.contains(prop))
            {
                ret.add(prop);
                appendTree(ret,prop.binds);
            }            
        }
    }
    
}
