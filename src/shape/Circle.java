package shape;

import java.awt.Point;

/**
 *
 * @author Brb-PC
 */
public class Circle implements Shape{
    private Point center;
    private double radius;
    
    public Circle(double radius){
        this(new Point(0,0),radius);
    }
    
    public Circle(Point center, double radius){
        this.center = center;
        this.radius = radius;
    }
    
    public Point getCenter(){
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
