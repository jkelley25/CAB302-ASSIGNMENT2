package shapes;

import java.awt.*;

/**
 * Abstract shape class
 */
public abstract class AbstractShape {
    Color penColor;
    Color fillColor;

    /**
     * AbstractShape constructor given a pen and fill color
     * @param penColor pen color outline of the shape
     * @param fillColor fill color outline of the shape
     * @throws ShapeException throws an exception if either color is invalid
     */
    public AbstractShape(Color penColor, Color fillColor) throws ShapeException {
        if(penColor != null && penColor.getClass() != Color.class){
            throw new ShapeException("Invalid pen color!");
        } else if(fillColor != null && fillColor.getClass() != Color.class){
            throw new ShapeException("Invalid fill color!");
        } else{
            this.penColor = penColor;
            this.fillColor = fillColor;
        }
    }

    /**
     * Method for painting to panel using Graphics class
     * @param g graphics object for drawing
     */
    public abstract void paint(Graphics g);

    /**
     * Gets the coordinates of shape
     * @return double array containing coordinates
     */
    public abstract double [] getCoordinates () throws ShapeException;

    /**
     * Get the pen color of the shape
     * @return
     */
    public abstract Color getPenColor ();

    /**
     * Get the fill color of the shape
     * @return
     */
    public abstract Color getFillColor();

    /**
     * Set the pen color of the shape
     * @param penColor color of the pen to be set
     * @throws ShapeException if the color is invalid
     */
    public abstract void setPenColor(Color penColor) throws ShapeException;

    /**
     * Set the start point of the shape
     * @param x1 x coordinate of the first point
     * @param y1 y coordinate of the first point
     * @throws ShapeException
     */
    public abstract void setStartPoint(double x1, double y1) throws ShapeException;

    /**
     * Set the end point of the shape
     * @param x2 x coordinate of the end point
     * @param y2 y coordinate of the end point
     * @throws ShapeException
     */
    public abstract void setEndPoint(double x2, double y2) throws ShapeException;


}
