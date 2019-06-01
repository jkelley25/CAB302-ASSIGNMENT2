package shapes;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Rectangle extends AbstractShape {
    private double x1;
    private double y1;
    private double x2;
    private double y2;

    /**
     * Constructs a rectangle object given the pen color, fill color, top-left coordinate and
     * bottom right coordinate
     * @param penColor the pen color outline of the rectangle
     * @param fillColor the fill color of the rectangle
     * @param x1 x coordinate of the top left
     * @param y1 y coordinate of the top left
     * @param x2 x coordinate of the bottom right
     * @param y2 y coordinate of the bottom right
     * @throws ShapeException throws shape exception if either colors are invalid and/or
     * coordinates are invalid
     */
    public Rectangle(Color penColor, Color fillColor, double x1, double y1, double x2, double y2) throws ShapeException {
        super(penColor, fillColor);
        if(x1 < 0 || y1 < 0 || x2 < 0 || y2 < 0){
            throw new ShapeException("Coordinates cannot be negative");
        } else {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    /**
     * Constructs a rectangle object given the pen color, fill color, top-left coordinate
     * @param penColor the pen color outline of the rectangle
     * @param fillColor the fill color of the rectangle
     * @param x1 x coordinate of the top left
     * @param y1 y coordinate of the top left
     * @throws ShapeException throws shape exception if either colors and/or coordinates are invalid
     */
    public Rectangle(Color penColor, Color fillColor, double x1, double y1) throws ShapeException {
        super(penColor, fillColor);
        if(x1 < 0 || y1 < 0){
            throw new ShapeException("Coordinates cannot be negative");
        } else {
            this.x1 = x1;
            this.y1 = y1;
        }
    }

    /**
     * Method for painting to panel using Graphics class
     * @param g graphics object for drawing
     */
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(penColor);
        Rectangle2D rect = new Rectangle2D.Double(x1, y1, x2 - x1, y2 - y1);
        g2.draw(rect);
        //Check if fill is on
        if(fillColor != null){
            g2.setColor(fillColor);
            g2.fill(rect);
        }
    }

    /**
     * Gets the coordinates of the top left and bottom right of the rectangle
     * @return double array containing corner coordinates of rectangle
     * @throws ShapeException throws shape exception if any anyt of the coordinates returned
     * are negative
     */
    @Override
    public double[] getCoordinates() throws ShapeException{
        double [] coord = {x1, y1, x2, y2};
        for (double v : coord) {
            if (v < 0) {
                throw new ShapeException("Coordinates found were negative");
            }
        }
        return coord;
    }

    /**
     * Gets the pen color of the outline of the rectangle
     * @return color of the outline
     */
    @Override
    public Color getPenColor() {
        return this.penColor;
    }

    /**
     * Gets the fill color of the the rectangle
     * @return color of the fill
     */
    @Override
    public Color getFillColor() {
        return this.fillColor;
    }

    /**
     * Sets the pen color of the outline of the rectangle
     * @param penColor the color of the retangle outline to be set
     * @throws ShapeException throws shape exception if color is invalid
     */
    public void setPenColor(Color penColor) throws ShapeException{
        if(penColor != null && penColor.getClass() != Color.class) {
            throw new ShapeException("Invalid pen color!");
        } else{
            this.penColor = penColor;
        }
    }

    /**
     * Sets the fill color of the rectangle
     * @param fillColor color of the fill
     * @throws ShapeException throws shape exception if color is invalid
     */
    public void setFillColor(Color fillColor) throws ShapeException{
        if(fillColor != null && fillColor.getClass() != Color.class) {
            throw new ShapeException("Invalid fill color!");
        } else {
            this.fillColor = fillColor;
        }
    }

    /**
     * Sets the top left coordinate of the rectangle
     * @param x1 x coordinate of the top left
     * @param y1 y coordinate of the top left
     * @throws ShapeException throws shape exception if the coordinates given are invalid
     */
    @Override
    public void setStartPoint(double x1, double y1) throws ShapeException{
        if(x1 < 0 || y1 <0){
            throw new ShapeException("Coordinates cannot be a negative");
        }else{
            this.x1 = x1;
            this.y1 = y1;
        }
    }

    /**
     * Sets the bottom right coordinate of the rectangle
     * @param x2 x coordinate of the top left
     * @param y2 y coordinate of the top left
     * @throws ShapeException throws shape exception if the coordinates given are invalid
     */
    public void setEndPoint(double x2, double y2) throws ShapeException{
        if(x2 < 0 || y2 <0) {
            throw new ShapeException("Coordinates cannot be a negative");
        }else {
            this.x2 = x2;
            this.y2 = y2;
        }
    }
}
