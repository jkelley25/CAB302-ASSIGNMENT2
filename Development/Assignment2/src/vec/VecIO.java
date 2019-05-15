package vec;

import shapes.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Class for handling input and output of VEC file format. Extends FileIO
 */
public class VecIO extends FileIO{
    private ArrayList<String[]> data = new ArrayList<>();
    private Draw draw = new Draw();

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
            if(com[0].equals("LINE")){
                Line line = new Line(Double.parseDouble(com[1]) * SCALE, Double.parseDouble(com[2]) * SCALE,
                        Double.parseDouble(com[3]) * SCALE, Double.parseDouble(com[4]) * SCALE );
                draw.addCommand(line);
            }
            if(com[0].equals("PLOT")){
                Plot plot = new Plot(Double.parseDouble(com[1]) * SCALE,
                        Double.parseDouble(com[2]) * SCALE);
                draw.addCommand(plot);
            }
            if(com[0].equals("RECTANGLE")){
                Rectangle rect = new Rectangle(Double.parseDouble(com[1]) * SCALE,
                        Double.parseDouble(com[2]) * SCALE,
                        Double.parseDouble(com[3]) * SCALE, Double.parseDouble(com[4]));
                draw.addCommand(rect);
            }
        }
    }

    public ArrayList<String[]> GetData(){
        return data;
    }

    public Draw getDrawCommands(){
        return draw;
    }




}
