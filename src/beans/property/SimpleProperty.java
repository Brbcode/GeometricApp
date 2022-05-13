package beans.property;


/**
 * Implementation of abstract class 'Property'
 * @author Bruno Garcia Tripoli
 * @param <T> - Value type of property
 */
public final class SimpleProperty<T> extends Property<T> {
    /**
     * Property constructor, set property initial value.
     * 
     * @param value - Avoid null values.
     */
   public SimpleProperty(T value){
       super(value);
   }

    
}
