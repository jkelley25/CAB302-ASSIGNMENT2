package shapes;

import java.awt.*;

public abstract class AbstractShape {
    Color penColor;
    Color fillColor;
    /**
     * AbstractShape constructor given a color
     * @param penColor color of the outline of shape
     */


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
    public abstract double [] getCoordinates ();

    public abstract Color getPenColor ();

    public abstract Color getFillColor();



}
