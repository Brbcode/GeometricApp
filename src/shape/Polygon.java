/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shape;

import java.awt.Point;
import java.util.Arrays;
import java.util.Iterator;

/**
 *
 * @author Brb-PC
 */
public abstract class Polygon implements Shape,Iterable<Point>{
    protected Point[] points;
    
    public Polygon(Point f, Point s, Point t,Point... points){
        this.points = Arrays.asList(f,s,t,points).toArray(new Point[0]);
    }
    
    protected Polygon(Point[] points){
        this.points = points;
    }       
    
    public abstract double getArea();
    
    public final int getEdgeCount(){
        return points.length;
    }
        
    public void translate(int x,int y){
        for(Point p : points)
        {
            p.x += x;
            p.y += y;
        }
    }
    
    public void rotate(double degree){
        Point zero = new Point(0,0);
        double rad = Math.toRadians(degree);
        
        for(Point p : points)
        {
            double radius = zero.distance(p);
            rad += Math.atan2(p.x, p.y);
            int x,y;
            x = (int) (Math.cos(rad)*radius);
            y = (int) (Math.sin(rad)*radius);
            
        }
    }
    
    public void scale(double scale){
        for(Point p : points){
            p.x*=scale;
            p.y*=scale;
        }
    }

    @Override
    public Iterator<Point> iterator() {
        return Arrays.stream(points).iterator();
    }
    
}
