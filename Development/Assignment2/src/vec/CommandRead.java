package vec;

import shapes.*;
import shapes.Polygon;
import shapes.Rectangle;

import java.awt.*;
import java.util.ArrayList;

/**
 * Class for reading each vec commands, contains static methods that returns the
 * desired colour or shape
 */
public class CommandRead {

    /**
     * Reads the PEN command and returns its Color
     * @param hexCode hexadecimal color code in the command
     * @return Color of the PEN command
     */
    static Color readPen(String hexCode){
        return Color.decode(hexCode);
    }

    /**
     * Reads the FILL command and returns its Color
     * @param hexCode hexadecimal color code in the command
     * @return Color of the FILL command
     */
    static Color readFill(String hexCode){
        return Color.decode(hexCode);
    }

    /**
     * Static method that reads the REACTANGLE command and returns a Rectangle shape
     * @param penColour The pen colour outline of the rectangle if set, default is black
     * @param fillColour The fill colour of the fill of the shape if set, default is null
     * @param cmd The string array of actual command, contains the coordinates
     * @param scale The scale value in which to scale the vector coordinates
     * @return Line object
     */
    static Line readLine(Color penColour, Color fillColour, String [] cmd, int scale) throws ShapeException {
        return new Line(penColour, fillColour,
                Double.parseDouble(cmd[1]) * scale, Double.parseDouble(cmd[2]) * scale,
                Double.parseDouble(cmd[3]) * scale, Double.parseDouble(cmd[4]) * scale );
    }

    /**
     * Static method that reads the PLOT command and returns a Plot shape
     * @param penColour The pen colour of the plot if set, default is black
     * @param cmd The string array of actual command, contains the coordinates
     * @param scale The scale value in which to scale the vector coordinates
     * @return Plot object
     */
    static Plot readPlot(Color penColour, String [] cmd, int scale) throws ShapeException {
        return new Plot(penColour, null,
                Double.parseDouble(cmd[1]) * scale,
                Double.parseDouble(cmd[2]) * scale);
    }

    /**
     * Static method that reads the RECTANGLE command and returns a Rectangle shape
     * @param penColour The pen colour of the plot if set, default is black
     * @param fillColour The fill colour of the fill of the shape if set
     * @param cmd The string array of actual command, contains the coordinates
     * @param scale The scale value in which to scale the vector coordinates
     * @return Rectangle object
     */
    static Rectangle readRetangle(Color penColour, Color fillColour, String [] cmd, int scale) throws ShapeException {
        return new Rectangle(penColour, fillColour,
                Double.parseDouble(cmd[1]) * scale,
                Double.parseDouble(cmd[2]) * scale,
                Double.parseDouble(cmd[3]) * scale, Double.parseDouble(cmd[4])*scale);
    }

    /**
     * Static method that reads the ELLIPSE command and returns a Ellipse shape
     * @param penColour The pen colour of the plot if set, default is black
     * @param fillColour The fill colour of the fill of the shape if set
     * @param cmd The string array of actual command, contains the coordinates
     * @param scale The scale value in which to scale the vector coordinates
     * @return Ellipse object
     */
    static Ellipse readEllipse(Color penColour, Color fillColour, String [] cmd, int scale) throws ShapeException {
        return new Ellipse(penColour, fillColour,
                Double.parseDouble(cmd[1]) * scale,
                Double.parseDouble(cmd[2]) * scale,
                Double.parseDouble(cmd[3]) * scale, Double.parseDouble(cmd[4])*scale);
    }

    /**
     * Static method that reads the Polygon command and returns a Polygon shape
     * @param penColour The pen colour of the plot if set, default is black
     * @param fillColour The fill colour of the fill of the shape if set
     * @param cmd The string array of actual command, contains the coordinates
     * @param scale The scale value in which to scale the vector coordinates
     * @return Polygon object
     */
    static Polygon readPolygon(Color penColour, Color fillColour, String [] cmd, int scale) throws ShapeException {
        int size = cmd.length - 1;
        double [] x = new double[size/2];
        double [] y = new double [size/2];

        ArrayList<Double> xcoord = new ArrayList<>();
        ArrayList<Double> ycoord = new ArrayList<>();

        // add every second value to xcoord
        for (int i = 1; i < cmd.length; i+=2){
            xcoord.add(Double.parseDouble(cmd[i])*scale);
        }

        // add every second value to xcoord
        for (int i = 2; i < cmd.length; i+=2){
            ycoord.add(Double.parseDouble(cmd[i])*scale);
        }

        // add xcoord to x
        for (int i = 0; i < x.length; i++){
            x[i] = xcoord.get(i);
        }

        // add ycoord to y
        for (int i = 0; i < x.length; i++){
            y[i] = ycoord.get(i);
        }
        return new Polygon(penColour, fillColour,x,y);
    }
}
