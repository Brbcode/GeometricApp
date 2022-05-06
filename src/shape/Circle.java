package shape;

import java.awt.Point;
import java.awt.geom.Point2D;

/**
 *
 * @author Brb-PC
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
    
    public Point2D getCenter(){
        return center;
    }
    
    public double getRadius(){
        return radius;
    }

    @Override
    public String getName() {
        return "Circle";
    }
}
