package shapes;

import java.awt.*;

public abstract class Shape {
    protected Color color;
    protected boolean fill;
    public static Color globalColor = Color.black; // initial pen color
    public static Color fillColor;
    public static boolean fillFlag = false; // initial fill is off


    /**
     * Shape constructor given a color
     * @param color color of the outline of shape
     */
    public Shape(Color color, boolean fill){
        this.color = color;
        this.fill = fill;
    }

    /**
     * Method for painting to panel using Graphics class
     * @param g graphics object for drawing
     */
    public abstract void paint(Graphics g);

    /**
     * Gets the coordinates of the first point
     * @return
     */
    public abstract double [] getCoordinates();

    /**
     * Sets the color if PEN command is present
     */
    public static void setColour(Color c){
        globalColor = c;
    }

    public static void setFill(Color c){
        fillColor = c;
        fillFlag = true;
    }

    public static void setFillOff(){
        fillFlag = false;
    }

}
