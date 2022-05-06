package shape;

import java.awt.Point;
import java.awt.geom.Point2D;

/**
 *
 * @author Brb-PC
 */
public abstract class RegularPolygon extends Polygon implements Circumscribed{
    private Point2D center;    
    
    protected RegularPolygon(Point2D center,int edge_count,double radius){
        super(__generate(center,edge_count,radius));
        
        this.center = center;
    }
    
    private static Point2D[] __generate(Point2D center,int edge_count,double radius){
        Point2D[] ret = new Point2D[edge_count];
        for(int i=0;i<edge_count;i++)
        {
            double rad = Math.toRadians((i*1.0/edge_count)*360);            
            double x,y;
            x = Math.cos(rad)*radius;
            y = Math.sin(rad)*radius;
            ret[i] = new Point2D.Double(x+center.getX(),y+center.getY());
        }
        return ret;
    }
        
    public double getEdgeLenght(){
        return points[0].distance(points[1]);
    }
    
    @Override
    public Point2D getCenter(){
        return center;
    }
    
    @Override
    public double getRadius(){
        return center.distance(points[0]);
    }

    @Override
    public void scale(double scale) {
        Point2D cc = new Point2D.Double(center.getX(),center.getY());
        this.translate(-cc.getX(),-cc.getY());
        super.scale(scale);
        this.translate(cc.getX(),cc.getY());
    }

    @Override
    public void rotate(double degree) {
        //Point cc = new Point(center.x,center.y);
        //super.translate(-cc.x,-cc.y);        
        //super.rotate(degree);
        //super.translate(cc.x,cc.y);
        double rad = Math.toRadians(degree);
        double cx = center.getX();
        double cy = center.getY();
                
        for(Point2D p : points)
        {            
            double px = p.getX();
            double py = p.getY();
            double cos = Math.cos(rad);
            double sin = Math.sin(rad);
            
            double x = cx + (px - cx)*cos - (py - cy)*sin;
            double y = cy + (px - cx)*sin + (py - cy)*cos;      
            p.setLocation(x, y);            
        }        
    }

    @Override
    public void translate(double x, double y) {        
        super.translate(x, y);
        center.setLocation(center.getX()+x, center.getY()+y);
    }
    
    
    
}
