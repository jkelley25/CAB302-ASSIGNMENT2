package shapes;
import java.awt.*;

public class Plot extends AbstractShape {
    private double x;
    private double y;

    /**
     * Constructs a plot object that draws a single point on the canvas
     * @param penColor The color of the plot
     * @param fillColor // null
     * @param x The x coordinate of the point
     * @param y The y coordinate of the point
     * @throws ShapeException Throws shape exception if pencolor is invalid or coordinates are negative
     */
    public Plot(Color penColor, Color fillColor, double x, double y) throws ShapeException {
        super(penColor, fillColor);
        if(x < 0 || y < 0){
            throw new ShapeException("Coordinate cannot be negative");
        } else {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * Method for painting to panel using Graphics class
     * @param g graphics object for drawing
     */
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(penColor);
        g2.drawString(".", (float)x, (float)y);
    }

    /**
     * Gets the coordinates of the single point
     * @return coordinates of the Plot point
     * @throws ShapeException Throw shape exception if the coordinates returned are negative
     */
    @Override
    public double [] getCoordinates() throws ShapeException{
        if(x < 0 || y < 0){
            throw new ShapeException("Coordinates cannot be a negative");
        } else {
            return new double[]{x,y};
        }
    }

    /**
     * Gets the pen color of the plot object
     * @return pen color of the Plot
     */
    @Override
    public Color getPenColor() {
        return this.penColor;
    }

    /**
     * Gets the fill color of plot object
     * @return null since plot can't be filled
     */
    @Override
    public Color getFillColor() {
        return null;
    }

    /**
     * Sets the pen color of the plot
     * @param penColor the color of the pen to be set
     * @throws ShapeException throws shape exception if the color is invalid
     */
    @Override
    public void setPenColor(Color penColor) throws ShapeException {
        if(penColor != null && penColor.getClass() != Color.class) {
            throw new ShapeException("Invalid pen color!");
        } else {
            this.penColor = penColor;
        }
    }

    /**
     * Set the coordinate of the point
     * @param x1 x coordinate of the plot to be set
     * @param y1 y coordinate of the plot to be set
     */
    @Override
    public void setStartPoint(double x1, double y1) throws ShapeException {
        if(x1 < 0 || y1 < 0){
            throw new ShapeException("Coordinates cannot be negative");
        }
        this.x = x1;
        this.y = y1;
    }

    /**
     * Does nothing for plot
     * @param x2 //
     * @param y2 //
     */
    @Override
    public void setEndPoint(double x2, double y2) {
        // Does nothing
    }
}
