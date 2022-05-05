package shape;

import java.awt.Point;

/**
 *
 * @author Brb-PC
 */
public abstract class RegularPolygon extends Polygon implements Circumscribed{
    private Point center;    
    
    protected RegularPolygon(Point center,int edge_count,double radius){
        super(__generate(center,edge_count,radius));
        
        this.center = center;
    }
    
    private static Point[] __generate(Point center,int edge_count,double radius){
        Point[] ret = new Point[edge_count];
        for(int i=0;i<edge_count;i++)
        {
            double rad = Math.toRadians((i*1.0/edge_count)*360);            
            int x,y;
            x = (int) (Math.cos(rad)*radius);
            y = (int) (Math.sin(rad)*radius);
            ret[i] = new Point(x+center.x,y+center.y);
        }
        return ret;
    }
        
    public double getEdgeLenght(){
        return points[0].distance(points[1]);
    }
    
    @Override
    public Point getCenter(){
        return center;
    }
    
    @Override
    public double getRadius(){
        return center.distance(points[0]);
    }

    @Override
    public void scale(double scale) {
        Point cc = new Point(center.x,center.y);
        this.translate(-cc.x,-cc.y);
        super.scale(scale);
        this.translate(cc.x,cc.y);
    }

    @Override
    public void rotate(double degree) {
        Point cc = new Point(center.x,center.y);
        this.translate(-cc.x,-cc.y);
        super.rotate(degree);
        this.translate(cc.x,cc.y);
    }

    @Override
    public void translate(int x, int y) {        
        super.translate(x, y);
        center.x += x;
        center.y += y;
    }
    
    
    
}
