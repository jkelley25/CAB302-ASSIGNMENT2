package shapes;
import java.awt.*;

public class Plot extends Shape {
    private double x;
    private double y;

    public Plot(Color color, boolean fill, double x, double y){
        super(color, fill);
        this.x = x;
        this.y = y;
    }

    /**
     * Method for painting to panel using Graphics class
     * @param g graphics object for drawing
     */
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        g2.drawString(".", (float)x, (float)y);
    }

    /**
     * Gets the coordinates of the single point
     * @return coordinates of the Plot point
     */
    public double [] getCoordinates(){
        double [] coord = {x,y};
        return coord;
    }
}
