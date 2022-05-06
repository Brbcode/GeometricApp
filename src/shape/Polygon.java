/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shape;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.Iterator;

/**
 *
 * @author Brb-PC
 */
public abstract class Polygon implements Shape,Iterable<Point2D>{
    protected Point2D[] points;
    
    public Polygon(Point2D f, Point2D s, Point2D t,Point2D... points){
        this.points = Arrays.asList(f,s,t,points).toArray(new Point2D[0]);
    }
    
    protected Polygon(Point2D[] points){
        this.points = points;
    }       
    
    public abstract double getArea();
    
    public final int getEdgeCount(){
        return points.length;
    }
        
    public void translate(double x,double y){
        for(Point2D p : points)
        {
            p.setLocation(p.getX()+x,p.getY()+y);
        }
    }
    
    public void rotate(double degree){        
        Point zero = new Point(0,0);
        double rad = Math.toRadians(degree);
        
        for(Point2D p : points)
        {            
            double radius = zero.distance(p);
            
            rad += Math.atan2(p.getX(), p.getY());
                        
            double x,y;
            x = Math.sin(rad)*radius;
            y = Math.cos(rad)*radius;
            p.setLocation(x, y);            
        }
    }
    
    public void scale(double scale){
        for(Point2D p : points){
            p.setLocation(p.getX()*scale,p.getY()*scale);
        }
    }

    @Override
    public Iterator<Point2D> iterator() {
        return Arrays.stream(points).iterator();
    }
    
}
