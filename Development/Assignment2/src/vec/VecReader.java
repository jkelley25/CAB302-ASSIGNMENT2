package vec;

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
    private Draw draw = new Draw();
    private Color penColor;
    private Color fillColor = null;

    private final int SCALE = 600; // value to resize the command

    /**
     * Construct a VecReader object given the fileName
     * @param fileName name of the file to be used
     */
    public VecReader(String fileName){
        super(fileName);
        ReadFile();
        GetCommands();
    }

    /**
     * Method for reading VEC file format
     */
    @Override
    public void ReadFile() {
        // This will reference one line at a time
        String line;
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);
            // read every line
            while((line = bufferedReader.readLine()) != null) {
                String [] tmp = line.split(" "); // Split by space
                data.add(tmp);
            }
            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
        }
    }


    /**
     * Method for fetching each command in the VEC file
     */
    public void GetCommands(){
        // iterate through commands and create shape object
        for(String [] com: data){
            // check for PEN cmd
            if(com[0].equals("PEN")){
                penColor = CommandRead.readPen(com[1]);
            }
            // check for FILL cmd
            if(com[0].equals("FILL") && !com[1].equals("OFF")){
                fillColor = CommandRead.readFill(com[1]);
            }
            // check for FILL OFF cmd
            if(com[0].equals("FILL") && com[1].equals("OFF")){
                fillColor = null;
            }
            // check for LINE cmd
            if(com[0].equals("LINE")){
                Line newLine = CommandRead.readLine(penColor, fillColor, com,SCALE);
                draw.addCommand(newLine);
            }
            // check for PLOT cmd
            if(com[0].equals("PLOT")){
                Plot plot = CommandRead.readPlot(penColor, com, SCALE);
                draw.addCommand(plot);
            }
            // check for RECTANGLE cmd
            if(com[0].equals("RECTANGLE")){
                Rectangle rect =  CommandRead.readRetangle(penColor, fillColor, com, SCALE);
                draw.addCommand(rect);
            }
            // check for ELLIPSE cmd
            if(com[0].equals("ELLIPSE")){
                Ellipse ellipse = CommandRead.readEllipse(penColor, fillColor, com, SCALE);
                draw.addCommand(ellipse);
            }
            // check for POLYGON cmd
            if(com[0].equals("POLYGON")){
                Polygon poly = CommandRead.readPolygon(penColor, fillColor, com, SCALE);
                draw.addCommand(poly);
            }
        }
    }

    public ArrayList<AbstractShape> GetData(){
        return shapes;
    }

    public Draw getDrawCommands(){
        return draw;
    }




}
