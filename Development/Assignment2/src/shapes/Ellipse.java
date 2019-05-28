package shapes;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ellipse extends AbstractShape {
    private double x1;
    private double y1;
    private double x2;
    private double y2;

    /**
     * Constructs an ellipse object given the pen color, fill color, and the top left and bottom right
     * of the rectangle in which the ellipse will fill
     * @param penColor
     * @param fillColor
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    public Ellipse(Color penColor, Color fillColor, double x1, double y1, double x2, double y2){
        super(penColor, fillColor);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public Ellipse(Color penColor, Color fillColor, double x1, double y1){
        super(penColor, fillColor);
        this.x1 = x1;
        this.y1 = y1;
    }

    /**
     * Method for painting to panel using Graphics class
     * @param g graphics object for drawing
     */
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(penColor);
        Ellipse2D ellipse = new Ellipse2D.Double(x1,y1,x2-x1,y2-y1);
        g2.draw(ellipse);

        //Check if fill is on
        if(fillColor != null){
            g2.setColor(fillColor);
            g2.fill(ellipse);
        }
    }

    /**
     * Gets the coordinates of the top left and bottom right of the rectangle in which the
     * ellipse resides in
     * @return double array containing corner coordinates of rectangle
     */
    @Override
    public double[] getCoordinates() {
        double [] coord = {x1,y1,x2,y2};
        return coord;
    }

    public void setEndPoint(double x, double y){
        this.x2 = x;
        this.y2 = y;
    }

    public void setPenColor(Color penColor){
        this.penColor = penColor;
    }
}
