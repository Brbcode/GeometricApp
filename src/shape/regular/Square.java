/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shape.regular;

import java.awt.Point;
import shape.RegularPolygon;

/**
 *
 * @author Brb-PC
 */
public class Square extends RegularPolygon{

    public Square(Point center, double radius){
        super(center,4,radius);
    }
    
    @Override
    public String getName() {
        return "Square";
    }

    @Override
    public double getArea() {
        int e = (int) this.getEdgeLenght();
        return e*e;
    }
    
}
