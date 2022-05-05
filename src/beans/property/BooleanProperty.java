package beans.property;

/**
 *
 * @author Bruno Garcia Tripoli
 */
public final class BooleanProperty extends Property<Boolean>{
    
    public BooleanProperty(boolean value){
        super(value);
    }
    
    public void toggle(){
        set(!get());
    }
}
