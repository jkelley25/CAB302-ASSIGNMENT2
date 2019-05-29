package vec;

import shapes.AbstractShape;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class VecWriter {
    private ArrayList<AbstractShape> shapes = new ArrayList<>();

    public VecWriter(){}

    /**
     * Adds a shape to the array of shapes
     * @param shape the shape to be added
     */
    public void addShapeToFile(AbstractShape shape){
        shapes.add(shape);
    }


    public void saveToFile(String filePath) throws IOException {
        for(AbstractShape shp: shapes){

            double [] coord = shp.getCoordinates();
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,
                    true)); // allow for appending

            // add new command to file
            writer.write("LINE " + coord[0] + " " + coord[1] + " " + coord[2] + " "
                    + coord[3]);
            writer.newLine();
            writer.close();
        }
    }

}
