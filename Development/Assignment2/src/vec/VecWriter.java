package vec;

import shapes.AbstractShape;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class VecWriter extends FileIO {

    public VecWriter(String fileName){
        super(fileName);
    }
    @Override
    public void ReadFile() {

    }


    public void writeShape(AbstractShape shape) throws IOException {
        double [] coord = shape.getCoordinates();
        BufferedWriter writer = new BufferedWriter(new FileWriter("writetest.vec",
                true)); // allow for appending

        // add new command to file
        writer.write("LINE " + coord[0] + " " + coord[1] + " " + coord[2] + " "
                + coord[3]);
        writer.newLine();
        writer.close();
    }

}
