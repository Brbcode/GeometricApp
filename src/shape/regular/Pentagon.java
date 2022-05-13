package shape.regular;


import java.awt.geom.Point2D;
import shape.RegularStarPolygon;

/**
 * RegularStarPolygon of 5 sides
 * 
 * @author Bruno Garcia Tripoli
 */
public class Pentagon extends RegularStarPolygon {
    
    public Pentagon(Point2D center, double radius){
        super(center,5,radius);
    }
    
    @Override
    public String getName() {
        return "Pentagon";
    }

    @Override
    public double getArea() {
        int e = (int) this.getEdgeLenght();
        return (1.0/4)*e*e*Math.sqrt(5*(5+2*Math.sqrt(5)));
    }
    
}
