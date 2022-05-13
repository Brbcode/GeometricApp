package shape.regular;

import java.awt.geom.Point2D;
import shape.RegularStarPolygon;

/**
 * RegularStarPolygon of 7 sides
 * 
 * @author Bruno Garcia Tripoli
 */
public class Heptagon extends RegularStarPolygon{

    public Heptagon(Point2D center, double radius){
        super(center,7,radius);
    }
    
    @Override
    public String getName() {
        return "Heptagon";
    }

    @Override
    public double getArea() {
        int e = (int) this.getEdgeLenght();
        return (7.0/4)*e*e*(1.0/Math.tan(Math.toRadians(180.0/7)));
    }
    
}
