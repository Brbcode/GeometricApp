/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shape.regular;

import java.awt.Point;
import java.awt.geom.Point2D;
import shape.RegularPolygon;

/**
 *
 * @author Brb-PC
 */
public class Triangle extends RegularPolygon{

    public Triangle(Point2D center, double radius)
    {
        super(center,3,radius);        
    }
    
    @Override
    public String getName() {
        return "Triangle";
    }

    @Override
    public double getArea() {
        int e = (int) this.getEdgeLenght();
        
        return (Math.pow(e, 2)*Math.sqrt(3))/4;
    }
    
}
