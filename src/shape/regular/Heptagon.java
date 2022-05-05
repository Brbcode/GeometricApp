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
public class Heptagon extends RegularStarPolygon{

    public Heptagon(Point center, double radius){
        super(center,7,radius);
    }
    
    @Override
    public String getName() {
        return "Heptagon";
    }

    @Override
    public double getArea() {
        int e = (int) this.getEdgeLenght();
        return (7.0/4)*e*e*(1.0/Math.tan(Math.toRadians(180.0/7)));
    }
    
}
