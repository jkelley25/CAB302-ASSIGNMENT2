package shapes;
import java.awt.*;

public class Plot implements ShapeInterface{
    private double x;
    private double y;

    public Plot(double x, double y){
        this.x = x;
        this.y = y;
    }

    /**
     * Method for painting to panel using Graphics class
     * @param g graphics object for drawing
     */
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
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
