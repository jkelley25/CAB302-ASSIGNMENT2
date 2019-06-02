package shapes;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Class that creates a Line object
 */
public class Ellipse extends AbstractShape {
    private double x1;
    private double y1;
    private double x2;
    private double y2;

    /**
     * Constructs an ellipse object given the pen color, fill color, and the top left and bottom right
     * of the rectangle in which the ellipse will fill
     * @param penColor pen color outline of the ellipse
     * @param fillColor fill color of the ellipse
     * @param x1 x coordinate of the top left of the  rectangle container
     * @param y1 y coordiante of the top left of the rectangle container
     * @param x2 x coordinate of the bottom right of the rectangle container
     * @param y2 y coordinate of the bottom right of the rectangle container
     * @throws ShapeException throws shape exception if either colors are invalid or the coordinates
     * are invlaid
     */
    public Ellipse(Color penColor, Color fillColor, double x1, double y1, double x2, double y2) throws ShapeException {
        super(penColor, fillColor);
        if(x1 < 0 || y1 < 0 || x2 < 0 || y2 < 0){
            throw new ShapeException("Coordinates cannot be negative");
        } else {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    /**
     * Constructs an ellipse object given the pen color, fill color, top-left coordinate
     * of the containing rectangle
     * @param penColor the pen color outline of the ellipse
     * @param fillColor the fill color of the ellipse
     * @param x1 x coordinate of the top left
     * @param y1 y coordinate of the top left
     * @throws ShapeException throws shape exception if either colors and/or coordinates are invalid
     */
    public Ellipse(Color penColor, Color fillColor, double x1, double y1) throws ShapeException {
        super(penColor, fillColor);
        if(x1 < 0 || y1 < 0){
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
     * @throws ShapeException throws shape exception if any of the coordinates are invalid
     */
    @Override
    public double[] getCoordinates() throws ShapeException {
        double [] coord = {x1,y1,x2,y2};
        for (double v : coord) {
            if (v < 0) {
                throw new ShapeException("Coordinates found were negative");
            }
        }
        return coord;
    }

    /**
     * Gets the pen color outline of the ellipse
     * @return pen outline color
     */
    @Override
    public Color getPenColor() {
        return this.penColor;
    }

    /**
     * Gets the fill color of the ellipse
     * @return fill color of ellipse
     */
    @Override
    public Color getFillColor() {
        return this.fillColor;
    }

    /**
     * Sets the pen color of the outline of the ellipse
     * @param penColor the color of the ellipse outline to be set
     * @throws ShapeException throws shape exception if color is invalid
     */
    public void setPenColor(Color penColor) throws ShapeException{
        if(penColor != null && penColor.getClass() != Color.class) {
            throw new ShapeException("Invalid pen color!");
        } else{
            this.penColor = penColor;
        }
    }

    /**
     * Sets the fill color of the rectangle
     * @param fillColor color of the fill
     * @throws ShapeException throws shape exception if color is invalid
     */
    public void setFillColor(Color fillColor) throws ShapeException{
        if(fillColor != null && fillColor.getClass() != Color.class) {
            throw new ShapeException("Invalid fill color!");
        } else {
            this.fillColor = fillColor;
        }
    }

    /**
     * Sets the top left coordinate of the containing rectangle
     * @param x1 x coordinate of the top left
     * @param y1 y coordinate of the top left
     * @throws ShapeException throws shape exception if the coordinates given are invalid
     */
    @Override
    public void setStartPoint(double x1, double y1) throws ShapeException{
        if(x1 < 0 || y1 <0){
            throw new ShapeException("Coordinates cannot be a negative");
        }else{
            this.x1 = x1;
            this.y1 = y1;
        }
    }

    /**
     * Sets the bottom right coordinate of the conating rectangle
     * @param x2 x coordinate of the top left
     * @param y2 y coordinate of the top left
     * @throws ShapeException throws shape exception if the coordinates given are invalid
     */
    public void setEndPoint(double x2, double y2) throws ShapeException{
        if(x2 < 0 || y2 <0) {
            throw new ShapeException("Coordinates cannot be a negative");
        }else {
            this.x2 = x2;
            this.y2 = y2;
        }
    }

}
