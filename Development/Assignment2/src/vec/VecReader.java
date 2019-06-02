package vec;

import Application.BuildApp;
import shapes.*;
import shapes.Polygon;
import shapes.Rectangle;
import shapes.AbstractShape;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Class for handling input and output of VEC file format. Extends FileIO
 */
public class VecReader extends FileIO{
    private ArrayList<String[]> data = new ArrayList<>();
    private ArrayList<AbstractShape> shapes = new ArrayList<>();
    private Color penColor = Color.BLACK;
    private Color fillColor = null;

    /**
     * Construct a VecReader object given the fileName
     * @param fileName name of the file to be used
     */
    public VecReader(String fileName){
        super(fileName);
        ReadFile();
        try {
            GetCommands();
        } catch (ShapeException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for reading VEC file format
     */
    @Override
    public void ReadFile() {
        // Read one line at a time
        String line;
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);
            // read every line
            while((line = bufferedReader.readLine()) != null) {
                String [] tmp = line.split(" "); // Split by space
                data.add(tmp);
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to vec open file '" + fileName + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading vec file '" + fileName + "'");
        }
    }

    public ArrayList<AbstractShape> GetData(){
        return shapes;
    }

    /**
     * Method for fetching each command in the VEC file
     */
    private void GetCommands() throws ShapeException {
        // iterate through commands and create shape object
        for(String [] com: data){
            // check for PEN cmd
            if(com[0].equals("PEN")){
                penColor = readPen(com[1]);
            }
            // check for FILL cmd
            if(com[0].equals("FILL") && !com[1].equals("OFF")){
                fillColor = readFill(com[1]);
            }
            // check for FILL OFF cmd
            if(com[0].equals("FILL") && com[1].equals("OFF")){
                fillColor = null;
            }
            // check for LINE cmd
            if(com[0].equals("LINE")){
                Line newLine = readLine(penColor, fillColor, com, BuildApp.scale);
                //draw.addCommand(newLine);
                shapes.add(newLine);
            }
            // check for PLOT cmd
            if(com[0].equals("PLOT")){
                Plot plot = readPlot(penColor, com, BuildApp.scale);
                //draw.addCommand(plot);
                shapes.add(plot);
            }
            // check for RECTANGLE cmd
            if(com[0].equals("RECTANGLE")){
                Rectangle rect =  readRetangle(penColor, fillColor, com, BuildApp.scale);
                //draw.addCommand(rect);
                shapes.add(rect);
            }
            // check for ELLIPSE cmd
            if(com[0].equals("ELLIPSE")){
                Ellipse ellipse = readEllipse(penColor, fillColor, com, BuildApp.scale);
                //draw.addCommand(ellipse);
                shapes.add(ellipse);
            }
            // check for POLYGON cmd
            if(com[0].equals("POLYGON")){
                Polygon poly = readPolygon(penColor, fillColor, com, BuildApp.scale);
                //draw.addCommand(poly);
                shapes.add(poly);
            }
        }
    }


    /**
     * Reads the PEN command and returns its Color
     * @param hexCode hexadecimal color code in the command
     * @return Color of the PEN command
     */
    private Color readPen(String hexCode){
        return Color.decode(hexCode);
    }

    /**
     * Reads the FILL command and returns its Color
     * @param hexCode hexadecimal color code in the command
     * @return Color of the FILL command
     */
    private Color readFill(String hexCode){
        return Color.decode(hexCode);
    }

    /**
     * Method that reads the REACTANGLE command and returns a Rectangle shape
     * @param penColour The pen colour outline of the rectangle if set, default is black
     * @param fillColour The fill colour of the fill of the shape if set, default is null
     * @param cmd The string array of actual command, contains the coordinates
     * @param scale The scale value in which to scale the vector coordinates
     * @return Line object
     */
    private Line readLine(Color penColour, Color fillColour, String [] cmd, int scale) throws ShapeException {
        return new Line(penColour, fillColour,
                Double.parseDouble(cmd[1]) * scale, Double.parseDouble(cmd[2]) * scale,
                Double.parseDouble(cmd[3]) * scale, Double.parseDouble(cmd[4]) * scale );
    }

    /**
     * Method that reads the PLOT command and returns a Plot shape
     * @param penColour The pen colour of the plot if set, default is black
     * @param cmd The string array of actual command, contains the coordinates
     * @param scale The scale value in which to scale the vector coordinates
     * @return Plot object
     */
    private Plot readPlot(Color penColour, String [] cmd, int scale) throws ShapeException {
        return new Plot(penColour, null,
                Double.parseDouble(cmd[1]) * scale,
                Double.parseDouble(cmd[2]) * scale);
    }

    /**
     * Method that reads the RECTANGLE command and returns a Rectangle shape
     * @param penColour The pen colour of the plot if set, default is black
     * @param fillColour The fill colour of the fill of the shape if set
     * @param cmd The string array of actual command, contains the coordinates
     * @param scale The scale value in which to scale the vector coordinates
     * @return Rectangle object
     */
    private Rectangle readRetangle(Color penColour, Color fillColour, String [] cmd, int scale) throws ShapeException {
        return new Rectangle(penColour, fillColour,
                Double.parseDouble(cmd[1]) * scale,
                Double.parseDouble(cmd[2]) * scale,
                Double.parseDouble(cmd[3]) * scale, Double.parseDouble(cmd[4])*scale);
    }

    /**
     * Method that reads the ELLIPSE command and returns a Ellipse shape
     * @param penColour The pen colour of the plot if set, default is black
     * @param fillColour The fill colour of the fill of the shape if set
     * @param cmd The string array of actual command, contains the coordinates
     * @param scale The scale value in which to scale the vector coordinates
     * @return Ellipse object
     */
    private Ellipse readEllipse(Color penColour, Color fillColour, String [] cmd, int scale) throws ShapeException {
        return new Ellipse(penColour, fillColour,
                Double.parseDouble(cmd[1]) * scale,
                Double.parseDouble(cmd[2]) * scale,
                Double.parseDouble(cmd[3]) * scale, Double.parseDouble(cmd[4])*scale);
    }

    /**
     * Method that reads the Polygon command and returns a Polygon shape
     * @param penColour The pen colour of the plot if set, default is black
     * @param fillColour The fill colour of the fill of the shape if set
     * @param cmd The string array of actual command, contains the coordinates
     * @param scale The scale value in which to scale the vector coordinates
     * @return Polygon object
     */
    private Polygon readPolygon(Color penColour, Color fillColour, String [] cmd, int scale) throws ShapeException {
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
