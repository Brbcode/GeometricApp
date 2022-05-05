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
 *
 * @author Bruno Garcia Tripoli
 */
public abstract class Property<T> implements ObservableValue<T>,WritableValue<T> {
    private T value;
    private List<ChangeListener<T>> listeners;
    private Set<Property<T>> binds;
    
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
    
    protected final void __set(T value){         
        this.value = value;
    }
    
    public void bind(Property<T> other){
        binds.add(other);
        other.binds.add(this);
    }
    
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
