package vec;

import shapes.*;
import shapes.Polygon;
import shapes.Rectangle;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class VecWriter {
    private ArrayList<AbstractShape> shapes = new ArrayList<>();
    private final int SCALE = 600;

    public VecWriter(){}

    /**
     * Adds a shape to the array of shapes
     * @param shape the shape to be added
     */
    public void addShapeToFile(AbstractShape shape){
        shapes.add(shape);
    }

    public void saveToFile(String filePath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,
                true)); // allow for appending
        for(int i = 0; i < shapes.size() - 1; i++){
            double [] coord = shapes.get(i).getCoordinates();
            if(shapes.get(i).getClass().equals(Line.class)){
                if(shapes.get(i).getPenColor() != null ){
                    String hex = "#"+Integer.toHexString(shapes.get(i).getPenColor().getRGB()).substring(2);
                    writer.write("PEN " + hex.toUpperCase());
                    writer.newLine();
                }
                // add new command to file
                writer.write("LINE " + coord[0]/SCALE + " " + coord[1]/SCALE + " " + coord[2]/SCALE + " "
                        + coord[3]/SCALE);
                writer.newLine();
            }
            if(shapes.get(i).getClass().equals(Rectangle.class)){
                writer.write("RECTANGLE " + coord[0]/SCALE + " " + coord[1]/SCALE + " " + coord[2]/SCALE + " "
                        + coord[3]/SCALE);
                writer.newLine();
            }
            if(shapes.get(i).getClass().equals(Ellipse.class)){
                writer.write("ELLIPSE " + coord[0]/SCALE + " " + coord[1]/SCALE + " " + coord[2]/SCALE + " "
                        + coord[3]/SCALE);
                writer.newLine();
            }
            if(shapes.get(i).getClass().equals(Polygon.class)){
                writer.write("POLYGON ");
                for(double cd: coord){
                    writer.write(cd/SCALE + " "); // add each coord value
                }
                writer.newLine();
            }
        }

        writer.close(); // close when finished writing
    }

}
