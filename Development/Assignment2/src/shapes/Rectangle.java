package shapes;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Rectangle extends AbstractShape {
    private double tx;
    private double ty;
    private double bx;
    private double by;

    /**
     * Constructs a rectangle object given the pen color, fill color, top-left coordinate and
     * bottom right coordinate
     * @param penColor
     * @param fillColor
     * @param tx
     * @param ty
     * @param bx
     * @param by
     */
    public Rectangle(Color penColor, Color fillColor, double tx, double ty, double bx, double by ){
        super(penColor, fillColor);
        this.tx = tx;
        this.ty = ty;
        this.bx = bx;
        this.by = by;
    }

    public Rectangle(Color penColor, Color fillColor, double tx, double ty){
        super(penColor, fillColor);
        this.tx = tx;
        this.ty = ty;
    }

    /**
     * Method for painting to panel using Graphics class
     * @param g graphics object for drawing
     */
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(penColor);
        Rectangle2D rect = new Rectangle2D.Double(tx,ty,bx - tx , by - ty );
        g2.draw(rect);

        //Check if fill is on
        if(fillColor != null){
            g2.setColor(fillColor);
            g2.fill(rect);
        }
    }

    /**
     * Gets the coordinates of the top left and bottom right of the rectangle
     * @return double array containing corner coordinates of rectangle
     */
    @Override
    public double[] getCoordinates() {
        double [] coord = {tx, ty, bx,by };
        return coord;
    }

    public void setStartPoint(double x1, double y1){
        this.tx = x1;
        this.ty = y1;
    }
    public void setEndPoint(double x2, double y2){
        this.bx = x2;
        this.by = y2;
    }

    public void setPenColor(Color penColor){
        this.penColor = penColor;
    }

}
