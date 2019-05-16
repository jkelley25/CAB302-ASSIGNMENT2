package vec;

import shapes.*;
import shapes.Polygon;
import shapes.Rectangle;
import shapes.Shape;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Class for handling input and output of VEC file format. Extends FileIO
 */
public class VecIO extends FileIO{
    private ArrayList<String[]> data = new ArrayList<>();
    private ArrayList<Shape> shapes = new ArrayList<>();
    private Draw draw = new Draw();
    private Color penColor;
    private Color fillColor = null;

    private final int SCALE = 200; // value to resize the command

    /**
     * Construct a VecIO object given the fileName
     * @param fileName name of the file to be used
     */
    public VecIO(String fileName){
        super(fileName);
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
            if(com[0].equals("PEN")){
                penColor = Color.decode(com[1]);
            }

            // check for fill command
            if(com[0].equals("FILL") && !com[1].equals("OFF")){
                fillColor = Color.decode(com[1]);

            }

            if(com[0].equals("FILL") && com[1].equals("OFF")){
                fillColor = null;
            }

            if(com[0].equals("LINE")){
                Line line = new Line(penColor, fillColor,
                        Double.parseDouble(com[1]) * SCALE, Double.parseDouble(com[2]) * SCALE,
                        Double.parseDouble(com[3]) * SCALE, Double.parseDouble(com[4]) * SCALE );

                draw.addCommand(line);
                shapes.add(line);
            }
            if(com[0].equals("PLOT")){
                Plot plot = new Plot(penColor, fillColor,
                        Double.parseDouble(com[1]) * SCALE,
                        Double.parseDouble(com[2]) * SCALE);
                draw.addCommand(plot);
                shapes.add(plot);
            }

            if(com[0].equals("RECTANGLE")){
                Rectangle rect = new Rectangle(penColor, fillColor,
                        Double.parseDouble(com[1]) * SCALE,
                        Double.parseDouble(com[2]) * SCALE,
                        Double.parseDouble(com[3]) * SCALE, Double.parseDouble(com[4])*SCALE);
                draw.addCommand(rect);
                shapes.add(rect);
            }

            if(com[0].equals("ELLIPSE")){
                Ellipse ellipse = new Ellipse(penColor, fillColor,
                        Double.parseDouble(com[1]) * SCALE,
                        Double.parseDouble(com[2]) * SCALE,
                        Double.parseDouble(com[3]) * SCALE, Double.parseDouble(com[4])*SCALE);
                draw.addCommand(ellipse);
            }

            if(com[0].equals("POLYGON")){
                int size = com.length/2 + 1;
                double [] x = new double[size];
                double [] y = new double [size];

                // get all x coordinates from data and store in x array
                for(int i = 0; i < size; i ++){
                    for(int j = 1; j < size; j += 2){
                        x[i] = Double.parseDouble(com[j]) * SCALE;
                    }
                }

                // get all y coordinates from data and store in y array
                for(int i = 0; i < size; i ++){
                    for(int j = 1; j < size; j += 2){
                        y[i] = Double.parseDouble(com[j]) * SCALE;
                    }
                }


                Polygon poly = new Polygon(penColor, fillColor,x,y); // create polygon

                draw.addCommand(poly);
//                for(Line line: poly.getLines()){
//                    draw.addCommand(line);
//                }

            }

        }
    }

    public ArrayList<Shape> GetData(){
        return shapes;
    }

    public Draw getDrawCommands(){
        return draw;
    }




}
