package shapes;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Rectangle extends Shape {
    private double tx;
    private double ty;
    private double bx;
    private double by;

    public Rectangle(Color color, Boolean fill, double tx, double ty, double bx, double by ){
        super(color, fill);
        this.tx = tx;
        this.ty = ty;
        this.bx = bx;
        this.by = by;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        Rectangle2D rect = new Rectangle2D.Double(tx,ty,bx - tx , by - ty );
        g2.draw(rect);

        //Check if fill is on
        if(fill){
            g2.setColor(fillColor);
            g2.fill(rect);
        }
    }


    @Override
    public double[] getCoordinates() {
        double [] coord = {tx, ty, bx,by };
        return coord;
    }

}
