package shape.regular;

import java.awt.geom.Point2D;
import shape.RegularPolygon;

/**
 * RegularPolygon of 3 sides
 * 
 * @author Bruno Garcia Tripoli
 */
public class Triangle extends RegularPolygon{

    public Triangle(Point2D center, double radius)
    {
        super(center,3,radius);        
    }
    
    @Override
    public String getName() {
        return "Triangle";
    }

    @Override
    public double getArea() {
        int e = (int) this.getEdgeLenght();
        
        return (Math.pow(e, 2)*Math.sqrt(3))/4;
    }
    
}
