package beans.value;

/**
 * An ObservableValue is an entity that wraps a value and allows to observe the 
 * value for changes. 
 * 
 * @author Bruno Garcia Tripoli
 * @param <T> - Type of value that can be changed    
 */
public interface ObservableValue<T> {
    /**
     * Returns the current value of this ObservableValue
     * @return The current value
     */
    public T get();
    
    /**
     * Adds a ChangeListener which will be notified whenever the value of the 
     * ObservableValue changes. If the same listener is added more than once, 
     * then it will be notified more than once. That is, no check is made to 
     * ensure uniqueness.
     * 
     * Note that the same actual ChangeListener instance may be safely 
     * registered for different ObservableValues.    
     * 
     * @param listener - The listener to register
     * @throws NullPointerException - if the listener is null
     */
    public void addListener(ChangeListener<T> listener); 
    
    /**
     * Removes the given listener from the list of listeners, that are notified 
     * whenever the value of the ObservableValue changes.
     * 
     * If the given listener has not been previously registered 
     * (i.e. it was never added) then this method call is a no-op. If it had 
     * been previously added then it will be removed. If it had been added more 
     * than once, then only the first occurrence will be removed.
     * 
     * @param listener - The listener to remove
     * @return True if listener passed as argument has been removed successfully, false otherwise.
     */
    public boolean removeListener(ChangeListener<T> listener);
}
