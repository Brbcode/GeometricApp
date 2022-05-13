package shape;

import java.awt.Point;
import java.awt.geom.Point2D;

/**
 * Circle shape.
 * 
 * @author Bruno Garcia Tripoli
 */
public class Circle implements Shape{
    private Point2D center;
    private double radius;
    
    public Circle(double radius){
        this(new Point(0,0),radius);
    }
    
    public Circle(Point2D center, double radius){
        this.center = center;
        this.radius = radius;
    }
    /**
     * Retrives circle center
     * @return Point2D
     */
    public Point2D getCenter(){
        return center;
    }
    /**
     * Retrives circle radius
     * @return The radius
     */
    public double getRadius(){
        return radius;
    }

    @Override
    public String getName() {
        return "Circle";
    }
}
