/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shape.regular;

import java.awt.Point;
import shape.RegularStarPolygon;

/**
 *
 * @author Brb-PC
 */
public class Hexagon extends RegularStarPolygon{

    public Hexagon(Point center, double radius){
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
