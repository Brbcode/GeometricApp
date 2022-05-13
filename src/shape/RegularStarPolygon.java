package shape;

import java.awt.geom.Point2D;

/**
 * Regular Star Polygon is a Regular Polygon that inside holds a star shape.
 * 
 * @author Bruno Garcia Tripoli
 */
public abstract class RegularStarPolygon extends RegularPolygon{
    protected RegularStarPolygon(Point2D center,int edge_count,double radius)
    {
        super(center,edge_count,radius);
    }
}
