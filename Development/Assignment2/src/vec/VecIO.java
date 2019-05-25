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

    private final int SCALE = 600; // value to resize the command

    /**
     * Construct a VecIO object given the fileName
     * @param fileName name of the file to be used
     */
    public VecIO(String fileName){
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


    public void writeShape(Shape shape) throws IOException {
        double [] coord = shape.getCoordinates();
        BufferedWriter writer = new BufferedWriter(new FileWriter("writetest.vec",
                true)); // allow for appending

        // add new command to file
        writer.write("LINE " + coord[0] + " " + coord[1] + " " + coord[2] + " "
        + coord[3]);
        writer.newLine();
        writer.close();
    }

    /**
     * Method for fetching each command in the VEC file
     */
    public void GetCommands(){
        // iterate through commands and create shape object
        for(String [] com: data){

            /* NEED TO REFACTOR ALL THESE IF STATEMENTS TO OWN METHODS */
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
                int size = com.length - 1;
                double [] x = new double[size/2];
                double [] y = new double [size/2];

                ArrayList<Double> xcoord = new ArrayList<>();
                ArrayList<Double> ycoord = new ArrayList<>();

                // add every second value to xcoord
                for (int i = 1; i < com.length; i+=2){
                    xcoord.add(Double.parseDouble(com[i])*SCALE);
                }

                // add every second value to xcoord
                for (int i = 2; i < com.length; i+=2){
                    ycoord.add(Double.parseDouble(com[i])*SCALE);
                }

                // add xcoord to x
                for (int i = 0; i < x.length; i++){
                    x[i] = xcoord.get(i);
                }

                // add ycoord to y
                for (int i = 0; i < x.length; i++){
                    y[i] = ycoord.get(i);
                }
                Polygon poly = new Polygon(penColor, fillColor,x,y); // create polygon
                draw.addCommand(poly);
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
