package geometricapp;

import beans.property.BooleanProperty;
import beans.property.Property;
import beans.property.SimpleProperty;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import shape.Polygon;
import shape.RegularPolygon;
import shape.Shape;


/**
 * Canvas Component
 * Draw a shape, and allow rotate it.
 * Can draw circumscribed circle.
 * Can draw help lines (line between center and every vertex.
 * Can draw inscripted star shape.
 * 
 * @author Bruno García Trípoli
 */
public class Canvas extends javax.swing.JPanel{
    private Property<Shape> shapeProp;
    private Property<Double> size;
    private BooleanProperty drawCircumscribedCircle;
    private BooleanProperty drawCenter;
    private BooleanProperty drawHelp;
    private BooleanProperty drawStar;    
    private final double handleRadius = 5d;
    private boolean isHovered = false;
    private boolean isRotating = false;
    private boolean canRotate = false;
    private Thread hover_thread = null;
            
    /**
     * Create a new Canvas Component inside <wrapper>
     * @param wrapper - Parent component
     */
    public Canvas(JComponent wrapper){
        super();       
        this.setSize(wrapper.getWidth()-20, wrapper.getHeight()-20);
        this.setLocation(10, 10);
        wrapper.add(this);
        this.shapeProp = new SimpleProperty<Shape>(null);
        this.size = new SimpleProperty<Double>(1.0);
        this.drawCircumscribedCircle = new BooleanProperty(true);
        this.drawCenter = new BooleanProperty(true);
        this.drawHelp = new BooleanProperty(false);
        this.drawStar = new BooleanProperty(false);
        this.shapeProp.addListener((obs,ov,nv)->this.updateUI());
        this.drawCircumscribedCircle.addListener((obs,ov,nv)->this.updateUI());
        this.drawCenter.addListener((obs,ov,nv)->this.updateUI());
        this.drawHelp.addListener((obs,ov,nv)->this.updateUI());
        this.drawStar.addListener((obs,ov,nv)->this.updateUI());
        
        
        this.addMouseListener(new MouseListener(){
            private Point getMousePoint(){
                Point zero = new Point(0,0);
                Point pivot = getLocationOnScreen();
                Rectangle rect = getBounds();
                Point mouse = MouseInfo.getPointerInfo().getLocation();
                mouse.x -= pivot.x;
                mouse.x -= rect.width/2;
                mouse.y -= pivot.y;
                mouse.y -= rect.height/2; 
                
                return mouse;
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {   
                                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                Point mouse = getMousePoint();
                for(Point2D p : (RegularPolygon) shapeProp.get())
                {
                    if(mouse.distance(p)<handleRadius)
                        isRotating = true;
                    
                    break;
                }                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isRotating = false;
            }

            @Override
            public void mouseEntered(MouseEvent e) {                
                isHovered = true;
                hover_thread = new Thread(()->{
                    for(;isHovered;)
                    {
                        Point mouse = getMousePoint(); 
                        Point2D p = null;
                        for(var _p : (RegularPolygon) shapeProp.get())
                        {
                            p = _p;
                            break;
                        }
                                                
                        if(mouse.distance(p)<handleRadius){
                            setCursor(new Cursor(Cursor.MOVE_CURSOR));
                            canRotate = true;
                        }
                        else if(!isRotating){
                            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                            canRotate = false;
                        }
                        
                        if(isRotating)
                        {                            
                            double angle=Math.atan2(p.getX(),p.getY()) - Math.atan2(mouse.x, mouse.y);
                            var polygon = (RegularPolygon) shapeProp.get();
                            if(Math.abs(angle)>Math.PI)
                                angle = (angle>0?-1:1)*Math.PI;                            
                            
                            polygon.rotate( (angle>0)?0.5:-0.5 );                            
                        }
                        
                        updateUI();
                        
                        try {
                            Thread.sleep( (isRotating)?2:50);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Canvas.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                hover_thread.start();
            }

            @Override
            public void mouseExited(MouseEvent e) {                
                isHovered = false;
                isRotating = false;
            }
        
        });
    }
    /**
     * Get Shape Property
     * @return Shape Property
     */
    public Property<Shape> getShapeProperty(){return shapeProp;}
    /**
     * Get Shape size property [0,1]
     * @return Shape Size Property
     */
    public Property<Double> getShapeSizeProperty(){return size;}
    /**
     * Get Circumsribed Circle Draw Option Property
     * @return Property
     */
    public BooleanProperty drawCircumscribedCircle(){return drawCircumscribedCircle;}
    /**
     * Get Center Draw Option Property
     * @return Property
     */
    public BooleanProperty drawCenter(){return drawCenter;}
    /**
     * Get Help Lines Draw Option Property
     * @return Property
     */
    public BooleanProperty drawHelp(){return drawHelp;}
    /**
     * Get Inscripted Star Draw Option Property
     * @return Property
     */
    public BooleanProperty drawStar(){return drawStar;}
    
    
    /**
     * Override paint to draw shape.
     * 
     * @param g - Graphics
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(getWidth()/2, getHeight()/2);
        //g2d.rotate(Math.toRadians(-90));

        //if(shapeProp.get()== null) return;
        
        if(drawStar.get())
        {
            g2d.setColor(new Color(255,0,0,110));
            g2d.setStroke(new BasicStroke(1.0f,                  
                            BasicStroke.CAP_ROUND,    
                            BasicStroke.JOIN_ROUND));            
            
            RegularPolygon polygon = (RegularPolygon) shapeProp.get();
            List<Point2D> vertex = new ArrayList();
            for(Point2D p : polygon)
                vertex.add(p);
            
            for(int i = 0;i<vertex.size();i++)                    
            {
                Point2D p1 = vertex.get(i);
                Point2D p2 = vertex.get( (i+2)%vertex.size() );
                g2d.drawLine((int) p1.getX(),(int) p1.getY(),(int) p2.getX(),(int) p2.getY());
            }
            
        }
        
        if(shapeProp.get()!= null && shapeProp.get() instanceof RegularPolygon){
            RegularPolygon polygon = (RegularPolygon) shapeProp.get();                      
            
            Point2D fp = null,lp = null;
            g2d.setColor(new Color(187,187,187));
            g2d.setStroke(new BasicStroke(2.0f,                  
                            BasicStroke.CAP_ROUND,    
                            BasicStroke.JOIN_ROUND));
            
            for(Point2D p : polygon)
            {
                if(lp!=null)                    
                    g2d.drawLine((int) lp.getX(),(int) lp.getY(),(int) p.getX(),(int) p.getY());
                else
                    fp = p;                    
                
                lp = p;
            }
            g2d.drawLine((int) lp.getX(),(int) lp.getY(),(int) fp.getX(),(int) fp.getY());
            
            g2d.setColor(new Color(200,200,200,80));
            g2d.setStroke(new BasicStroke(1.0f,                  
                            BasicStroke.CAP_ROUND,    
                            BasicStroke.JOIN_ROUND)); 
            
            int ix = (int) (fp.getX() - this.handleRadius);
            int iy = (int) (fp.getY() - this.handleRadius);
            
            g2d.drawOval(ix, iy,(int) handleRadius*2, (int) handleRadius*2);
        }
        
        if(drawHelp.get() && shapeProp.get()!= null && shapeProp.get() instanceof RegularPolygon)
        {
            g2d.setColor(new Color(200,200,200,80));
            g2d.setStroke(new BasicStroke(1.0f,                  
                            BasicStroke.CAP_ROUND,    
                            BasicStroke.JOIN_ROUND)); 
            
            RegularPolygon polygon = (RegularPolygon) shapeProp.get();
            Point2D center = polygon.getCenter();
            for(Point2D p : polygon)
            {
                g2d.drawLine((int) center.getX(),(int) center.getY(),(int) p.getX(),(int) p.getY());
            }
        }
        
        
        
        if(drawCircumscribedCircle.get())
        {
            g2d.setColor(Color.red);
            g2d.setStroke(new BasicStroke(2.0f,                  
                            BasicStroke.CAP_ROUND,    
                            BasicStroke.JOIN_ROUND));            
            int radius = (int) (size.get()*this.getHeight())/2;  
            
            g2d.drawOval(-radius, -radius, radius*2, radius*2);
        }
        
        if(drawCenter.get())
        {
            g2d.setColor(new Color(200,200,200));
            g2d.setStroke(new BasicStroke(1.0f,                  
                            BasicStroke.CAP_ROUND,    
                            BasicStroke.JOIN_ROUND));            
            int size = 2;              
            g2d.drawLine(-size, size, size, -size);
            g2d.drawLine(size, size, -size, -size);
        }
        
        if( (canRotate || isRotating)  
                && shapeProp.get()!= null && shapeProp.get() instanceof RegularPolygon){
            g2d.setColor(new Color(200,200,200,80));
            g2d.setStroke(new BasicStroke(1.0f,                  
                            BasicStroke.CAP_ROUND,    
                            BasicStroke.JOIN_ROUND)); 
            
            RegularPolygon polygon = (RegularPolygon) shapeProp.get();
            var c = polygon.getCenter();
            Point2D p = null;
            for(Point2D _p : polygon)
            {
                p = new Point2D.Double(_p.getX()*1000,_p.getY()*1000);
                break;
            }
            
            g2d.drawLine((int) c.getX(), (int) c.getY(), 
                    (int) p.getX(), (int) p.getY());
        }
        
    }
       
}
