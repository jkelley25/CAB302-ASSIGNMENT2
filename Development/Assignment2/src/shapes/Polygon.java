package shapes;

import java.awt.*;
import java.awt.geom.Path2D;
import java.util.ArrayList;

public class Polygon extends AbstractShape {
    private double [] x;
    private double [] y;
    private ArrayList<Double> coords = new ArrayList<>();
    // coords from drawing
    private ArrayList<Double> xdr = new ArrayList<>();
    private ArrayList<Double> ydr = new ArrayList<>();
    private boolean close = false;

    /**
     * Creates a Polygon object given a pen color and/or fill color and the point coordinates
     * of the the polygon
     * @param penColor pen color outline of the polygon
     * @param fillColor fill of the polygon
     * @param x array of x coordinates
     * @param y array of y coordinates
     * @throws ShapeException throws shape exception if either colors are invalid or any
     * of the coordinates are invalid
     */
    public Polygon(Color penColor, Color fillColor, double [] x, double [] y) throws ShapeException {
        super(penColor, fillColor);
        for(int i = 0; i < x.length; i++){
            if(x[i] < 0 || y[i] < 0){
                throw new ShapeException("Coordinates cannot be negative");
            }
        }
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a Polygon object given a pen color and/or fill color
     * @param penColor pen color outline of the polygon
     * @param fillColor fill color of the polygon
     * @throws ShapeException throws shape exception if either colors are invalid
     */
    public Polygon(Color penColor, Color fillColor) throws ShapeException {
        super(penColor,fillColor);
    }

    /**
     * Method for painting to panel using Graphics class
     * @param g graphics object for drawing
     */
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(penColor);
        Path2D path = new Path2D.Double();
        // check if coords from drawing isn't empty
        if(!xdr.isEmpty()){
            x = new double[xdr.size() ];
            y = new double[ydr.size() ];
            // populate coords array
            for (int i = 0; i < x.length; i++){
                x[i] = xdr.get(i);
                y[i] = ydr.get(i);
            }
        }
        path.moveTo(x[0], y[0]); // move to initial point
        // connect each line
        for(int i = 1; i < x.length; ++i){
            path.lineTo(x[i], y[i]);
        }
        if(xdr.isEmpty()){
            path.closePath(); // close polygon
        }
        if(close){
            path.closePath(); // close polygon
        }
        g2.draw(path);
        //Check if fill is on
        if(fillColor != null){
            g2.setColor(fillColor);
            g2.fill(path);
        }
    }

    /**
     * Add next point coorinate of the line for the polygon object
     * @param x1 x coordinate of the next line
     * @param y1 y coordinte of the next line
     */
    public void addLines(double x1, double y1) throws ShapeException{
        if(x1 < 0 || y1 < 0){
            throw new ShapeException("Coordinates cannot be negative");
        } else {
            xdr.add(x1);
            ydr.add(y1);
            // add to coordinates
            coords.add(x1);
            coords.add(y1);
        }
    }

    /**
     * Method for closing the polygon
     */
    public void closePolygon(){
        close = true;
    }

    /**
     * Gets the array of all the point coordinates of the polygon
     * @return array of doubles containing the coordinates
     * @throws ShapeException throws shape exception if any of the coordinates returned
     * are invalid
     */
    @Override
    public double[] getCoordinates() throws ShapeException {
        double [] coord = null;
        if(!coords.isEmpty()){
            double [] polycoords = new double[coords.size() - 1];
            // populate poly coordinates
            for(int i = 0; i < polycoords.length; i++){
                polycoords[i] = coords.get(i);
            }
            for (double v : polycoords) {
                if (v < 0) {
                    throw new ShapeException("Coordinates found were negative");
                }
            }
            return polycoords;
        } else {
            return concatArray(x,y);
        }
    }

    /**
     * Gets the pen color of the outline of the polygon
     * @return color of the outline
     */
    @Override
    public Color getPenColor() {
        return this.penColor;
    }

    /**
     * Gets the fill color of the the polygon
     * @return color of the fill
     */
    @Override
    public Color getFillColor() {
        return this.fillColor;
    }


    /**
     * Sets the pen color of the outline of the polygon
     * @param penColor the color of the polygon outline to be set
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
     * Not implemented for polygon
     * @param x1 //
     * @param y1 //
     * @throws ShapeException //
     */
    @Override
    public void setStartPoint(double x1, double y1) throws ShapeException {
        // Does nothing
    }

    /**
     * Not implemented for polygon
     * @param x2 //
     * @param y2 //
     * @throws ShapeException //
     */
    @Override
    public void setEndPoint(double x2, double y2) throws ShapeException {
        // Does nothing
    }

    private double [] concatArray(double [] x, double [] y){
        int length = x.length + y.length;
        double []result = new double [length];
        System.arraycopy(x,0,result,0,x.length);
        System.arraycopy(y,0,result, x.length, y.length);
        return result;
    }


}
