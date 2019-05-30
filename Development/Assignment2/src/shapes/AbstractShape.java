package shapes;

import java.awt.*;

public abstract class AbstractShape {
    Color penColor;
    Color fillColor;

    /**
     * AbstractShape constructor given a color
     * @param penColor color of the outline of shape
     */
    public AbstractShape(Color penColor, Color fillColor){
        this.penColor = penColor;
        this.fillColor = fillColor;
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
    public abstract double [] getCoordinates();

    public abstract Color getPenColor();

    public abstract Color getFillColor();



}
