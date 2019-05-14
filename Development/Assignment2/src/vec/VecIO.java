package vec;

import shapes.*;
import java.io.*;
import java.util.ArrayList;


public class VecIO extends FileIO{
    private ArrayList<String[]> data = new ArrayList<>();
    public LineList lines = new LineList();

    private final int SCALE = 200;

    public VecIO(String fileName){
        super(fileName);
    }

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


    public void GetCommands(){
        for(String [] com: data){
            if(com[0].equals("LINE")){
                lines.AddLine(Double.parseDouble(com[1]) * SCALE, Double.parseDouble(com[2]) * SCALE,
                        Double.parseDouble(com[3]) * SCALE, Double.parseDouble(com[4]) * SCALE );  // create new line
            }
        }
    }
}
