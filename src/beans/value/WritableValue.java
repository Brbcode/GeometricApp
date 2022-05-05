package beans.value;

/**
 * A WritableValue is an entity that wraps a value that can be read and set. 
 * 
 * @author Bruno Garcia Tripoli
 * @param <T> The type of the wrapped value
 */
public interface WritableValue<T> {
    /**
     * Get the wrapped value
     * @return The current value
     */
    public T get();
    
    /**
     * Set the wrapped value.
     * 
     * @param value The new value
     */
    public void set(T value);
}
