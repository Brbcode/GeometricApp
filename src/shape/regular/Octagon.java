
package shape.regular;

import java.awt.geom.Point2D;
import shape.RegularStarPolygon;

/**
 * RegularStarPolygon of 8 sides
 * 
 * @author Bruno Garcia Tripoli
 */
public class Octagon extends RegularStarPolygon{

    public Octagon(Point2D center, double radius){
        super(center,8,radius);
    }
    
    @Override
    public String getName() {
        return "Octagon";
    }

    @Override
    public double getArea() {
        int e = (int) this.getEdgeLenght();
        return 2*(1+Math.sqrt(2))*e*e;
    }
    
}
