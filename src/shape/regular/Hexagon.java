
package shape.regular;

import java.awt.geom.Point2D;
import shape.RegularStarPolygon;

/**
 * RegularStarPolygon of 6 sides
 * 
 * @author Bruno Garcia Tripoli
 */
public class Hexagon extends RegularStarPolygon{

    public Hexagon(Point2D center, double radius){
        super(center,6,radius);
    }
    
    @Override
    public String getName() {
        return "Hexagon";
    }

    @Override
    public double getArea() {
        int e = (int) this.getEdgeLenght();
        return ((3*Math.sqrt(3))/2)*e*e;
    }
    
}
