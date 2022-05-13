package beans.value;

/**
 * A ChangeListener is notified whenever the value of an ObservableValue changes. 
 * It can be registered and unregistered with ObservableValue.addListener
 * (ChangeListener) respectively ObservableValue.removeListener(ChangeListener)
 * 
 * The same instance of ChangeListener can be registered to listen to multiple 
 * ObservableValues.
 * 
 * @author Bruno Garcia Tripoli
 * @param <T> - Type of value that can be changed              
 */
public interface ChangeListener<T> {
    /**
     * This method needs to be provided by an implementation of ChangeListener. 
     * It is called if the value of an ObservableValue changes.
     * 
     * In general is is considered bad practice to modify the observed value in 
     * this method.
     * 
     * @param observable - The ObservableValue which value changed
     * @param oldValue - The old value
     * @param newValue - The new value 
     */
    void changed(ObservableValue<T> observable,T oldValue, T newValue);
}
