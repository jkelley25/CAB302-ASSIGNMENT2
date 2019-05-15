package shapes;

import java.awt.*;

public interface ShapeInterface {

    /**
     * Method for painting to panel using Graphics class
     * @param g graphics object for drawing
     */
    void paint(Graphics g);

    /**
     * Gets the coordinates of the first point
     * @return
     */
    double [] getCoordinates();
}
