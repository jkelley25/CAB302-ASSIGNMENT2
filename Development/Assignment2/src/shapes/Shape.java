package shapes;

import java.awt.*;

public abstract class Shape {
    Color penColor;
    Color fillColor;

    /**
     * Shape constructor given a color
     * @param penColor color of the outline of shape
     */
    public Shape(Color penColor, Color fillColor){
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



}
