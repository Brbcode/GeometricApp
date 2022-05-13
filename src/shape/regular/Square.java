package shape.regular;

import java.awt.geom.Point2D;
import shape.RegularPolygon;

/**
 * RegularPolygon of 4 sides
 * 
 * @author Bruno Garcia Tripoli
 */
public class Square extends RegularPolygon{

    public Square(Point2D center, double radius){
        super(center,4,radius);
    }
    
    @Override
    public String getName() {
        return "Square";
    }

    @Override
    public double getArea() {
        int e = (int) this.getEdgeLenght();
        return e*e;
    }
    
}
