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
public class Pentagon extends RegularStarPolygon {
    
    public Pentagon(Point center, double radius){
        super(center,5,radius);
    }
    
    @Override
    public String getName() {
        return "Pentagon";
    }

    @Override
    public double getArea() {
        int e = (int) this.getEdgeLenght();
        return (1.0/4)*e*e*Math.sqrt(5*(5+2*Math.sqrt(5)));
    }
    
}
