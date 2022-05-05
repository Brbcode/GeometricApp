package geometricapp;

import beans.property.BooleanProperty;
import beans.property.Property;
import beans.property.SimpleProperty;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import shape.RegularPolygon;
import shape.Shape;


/**
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
            @Override
            public void mouseClicked(MouseEvent e) {                               
                updateUI();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }
        
        });
    }
    
    public Property<Shape> getShapeProperty(){return shapeProp;}
    public Property<Double> getShapeSizeProperty(){return size;}
    public BooleanProperty drawCircumscribedCircle(){return drawCircumscribedCircle;}
    public BooleanProperty drawCenter(){return drawCenter;}
    public BooleanProperty drawHelp(){return drawHelp;}
    public BooleanProperty drawStar(){return drawStar;}
    
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(getWidth()/2, getHeight()/2);
        g2d.rotate(Math.toRadians(-90));

        //if(shapeProp.get()== null) return;
        
        if(drawStar.get())
        {
            g2d.setColor(new Color(255,0,0,110));
            g2d.setStroke(new BasicStroke(1.0f,                  
                            BasicStroke.CAP_ROUND,    
                            BasicStroke.JOIN_ROUND));            
            
            RegularPolygon polygon = (RegularPolygon) shapeProp.get();
            List<Point> vertex = new ArrayList();
            for(Point p : polygon)
                vertex.add(p);
            
            for(int i = 0;i<vertex.size();i++)                    
            {
                Point p1 = vertex.get(i);
                Point p2 = vertex.get( (i+2)%vertex.size() );
                g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
            
        }
        
        if(shapeProp.get()!= null && shapeProp.get() instanceof RegularPolygon){
            RegularPolygon polygon = (RegularPolygon) shapeProp.get();                      
            
            Point fp = null,lp = null;
            g2d.setColor(new Color(187,187,187));
            g2d.setStroke(new BasicStroke(2.0f,                  
                            BasicStroke.CAP_ROUND,    
                            BasicStroke.JOIN_ROUND));
            
            for(Point p : polygon)
            {
                if(lp!=null)                    
                    g2d.drawLine(lp.x, lp.y, p.x, p.y);
                else
                    fp = p;                    
                
                lp = p;
            }
            g2d.drawLine(lp.x, lp.y, fp.x, fp.y);
        }
        
        if(drawHelp.get() && shapeProp.get()!= null && shapeProp.get() instanceof RegularPolygon)
        {
            g2d.setColor(new Color(200,200,200,80));
            g2d.setStroke(new BasicStroke(1.0f,                  
                            BasicStroke.CAP_ROUND,    
                            BasicStroke.JOIN_ROUND)); 
            
            RegularPolygon polygon = (RegularPolygon) shapeProp.get();
            Point center = polygon.getCenter();
            for(Point p : polygon)
            {
                g2d.drawLine(center.x, center.y, p.x, p.y);
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
        
        
        
    }
       
}
