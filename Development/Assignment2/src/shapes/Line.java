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
     * Constructs a Line object given the pen color, fill color and, start and
     * end points coordinates
     * @param penColor color of the pen outline
     * @param fillColor // null
     * @param x1 x coordinate of the first point
     * @param y1 y coordinate of the first point
     * @param x2 x coordinate of the second point
     * @param y2 y coordinate of the second point
     * @throws ShapeException throws a shape exception if pen color is invalid or coordinates
     * are negative
     */

    public Line(Color penColor, Color fillColor,double x1, double y1, double x2, double y2) throws ShapeException {
        super(penColor, fillColor);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    /**
     * Constructs a Line object given the pen color and start point
     * @param penColor color of the pen outline
     * @param fillColor // null
     * @param x1 x coordinate of the start point
     * @param y1 y coordinate of the start point
     * @throws ShapeException throws a shape exception if pen color or coordinates are invalid
     */
    public Line(Color penColor, Color fillColor,double x1, double y1) throws ShapeException {
        super(penColor, fillColor);
        if( x1 < 0 || y1 < 0){
            throw new ShapeException("Coordinates cannot be negative");
        } else {
            this.x1 = x1;
            this.y1 = y1;
        }
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
     * @throws ShapeException throws shape exception if coordinates return are negative
     */
    @Override
    public double [] getCoordinates() throws ShapeException{
        double [] coord = {x1, y1, x2, y2};
        for (double v : coord) {
            if (v < 0) {
                throw new ShapeException("Coordinates found were negative");
            }
        }
        return coord;
    }

    /**
     * Gets the pen color of the line
     * @return pen color outline
     */
    @Override
    public Color getPenColor() {
        return this.penColor;
    }

    /**
     * Gets the fill color
     * @return null because line can't be filled
     */
    @Override
    public Color getFillColor() {
        return null;
    }

    /**
     * Set the start point of the line
     * @param x1 x coordinate of the start line
     * @param y1 y coordinate of the start line
     * @throws  ShapeException throws shape exception if coordinates are invalid
     */
    public void setStartPoint(double x1, double y1) throws ShapeException {
        if(x1 < 0|| y1 < 0){
            throw new ShapeException("Coordinates cannot be negative");
        } else {
            this.x1 = x1;
            this.y1 = y1;
        }
    }

    /**
     * Set the end point of the line
     * @param x2 x coordinate of the end point
     * @param y2 y coordinate of the end point
     * @throws ShapeException throws shape exception if coordinates are invalid
     */
    public void setEndPoint(double x2, double y2) throws ShapeException {
        if(x2 < 0 || y2 < 0){
            throw new ShapeException("Coordinate cannot be negative");
        } else {
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    /**
     * Sets the pen color of the line
     * @param penColor the color of the line to be set
     */
    public void setPenColor(Color penColor) throws ShapeException{
        if(penColor != null && penColor.getClass() != Color.class) {
            throw new ShapeException("Invalid pen color!");
        } else {
            this.penColor = penColor;
        }
    }

}
