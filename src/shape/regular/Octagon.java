/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shape.regular;

import java.awt.Point;
import java.awt.geom.Point2D;
import shape.RegularStarPolygon;

/**
 *
 * @author Brb-PC
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
