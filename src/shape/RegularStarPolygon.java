/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shape;

import java.awt.Point;
import java.awt.geom.Point2D;

/**
 *
 * @author Brb-PC
 */
public abstract class RegularStarPolygon extends RegularPolygon{
    protected RegularStarPolygon(Point2D center,int edge_count,double radius)
    {
        super(center,edge_count,radius);
    }
}
