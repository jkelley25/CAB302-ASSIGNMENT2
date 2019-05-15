package shapes;

import java.awt.*;

public abstract class ShapeInterface {
    public static Color color;
    static boolean pen = false;
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
        color = c;
        penOn();
    }

    public static void penOn(){
        pen = true;
    }

    public static void penOFF(){
        pen = false;
    }


}
