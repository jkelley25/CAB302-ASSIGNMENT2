package shapes;
import java.awt.*;
import java.awt.geom.Line2D;


/**
 * Class that creates a Line object
 */

public class Line extends AbstractShape {
    private double x1;
    private double y1;
    private double x2;
    private double y2;

    /**
     * Constructs a Line object given the pen color, fill color and end points coordinates
     * @param penColor
     * @param fillColor
     * @param x1 x coordinate of the first point
     * @param y1 y coordinate of the first point
     * @param x2 x coordinate of the second point
     * @param y2 y coordinate of the second point
     */
    public Line(Color penColor, Color fillColor,double x1, double y1, double x2, double y2){
        super(penColor, fillColor);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public Line(Color penColor, Color fillColor,double x1, double y1){
        super(penColor, fillColor);
        this.x1 = x1;
        this.y1 = y1;
    }

    /**
     * Method for painting to panel using Graphics class
     * @param g graphics object for drawing
     */
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(penColor);
        g2.draw(new Line2D.Double(x1 , y1, x2, y2));
    }

    /**
     * Gets the coordinates of the end points
     * @return coordinates of starting and end point of line
     */
    public double [] getCoordinates(){
        double [] coord = {x1, y1, x2, y2};
        return coord;
    }

    @Override
    public Color getPenColor() {
        return this.penColor;
    }

    @Override
    public Color getFillColor() {
        return this.fillColor;
    }

    public void setEndPoint(double x, double y){
        this.x2 = x;
        this.y2 = y;
    }

    public void setPenColor(Color penColor){
        this.penColor = penColor;
    }

}
