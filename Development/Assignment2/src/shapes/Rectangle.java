package shapes;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Rectangle extends ShapeInterface {
    private double tx;
    private double ty;
    private double bx;
    private double by;
    private Color color;

    public Rectangle(Color color, double tx, double ty, double bx, double by ){
        this.tx = tx;
        this.ty = ty;
        this.bx = bx;
        this.by = by;
        this.color = color;

    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(color);
        g2.draw(new Rectangle2D.Double(tx,ty,bx ,ty - (ty - by) ));
    }

    @Override
    public double[] getCoordinates() {
        double [] coord = {tx, ty, bx,by };
        return coord;
    }

}
