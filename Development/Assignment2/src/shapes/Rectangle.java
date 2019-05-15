package shapes;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Rectangle implements ShapeInterface {
    private double tx;
    private double ty;
    private double bx;
    private double by;


    public Rectangle(double tx, double ty, double bx, double by ){
        this.tx = tx;
        this.ty = ty;
        this.bx = bx;
        this.by = by;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.draw(new Rectangle2D.Double(tx,ty, tx + bx, ty - by));
    }

    @Override
    public double[] getCoordinates() {
        double [] coord = {tx, ty, bx, by};
        return coord;
    }
}
