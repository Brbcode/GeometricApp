package beans.property;

/**
 * BooleanProperty is a property that holds a boolean and allows toogle hiself.
 * 
 * @author Bruno Garcia Tripoli
 */
public final class BooleanProperty extends Property<Boolean>{
    
    public BooleanProperty(boolean value){
        super(value);
    }
    
    /**
     * Set the BooleanProperty value to the inverse of current value.
     * True → False
     * False → True
     */
    public void toggle(){
        set(!get());
    }
}
