package shapes;
import java.awt.*;
import java.awt.geom.Line2D;


/**
 * Class that creates a Line object
 */

public class Line extends Shape {
    private double x1;
    private double y1;
    private double x2;
    private double y2;

    /**
     * Line constructor given the coordinates passed, in the format (x1,y1) (x2,y2)
     * @param x1 x coordinate of the first point
     * @param y1 y coordinate of the first point
     * @param x2 x coordinate of the second point
     * @param y2 y coordinate of the second point
     */
    public Line(Color color, boolean fill,double x1, double y1, double x2, double y2){
        super(color, fill);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    /**
     * Method for painting to panel using Graphics class
     * @param g graphics object for drawing
     */
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
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




}
