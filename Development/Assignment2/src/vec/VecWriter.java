package vec;

import Application.BuildApp;
import shapes.*;
import shapes.Polygon;
import shapes.Rectangle;

import java.io.BufferedWriter;
import java.io.File;
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

    /**
     * Sets the shapes that are to be saved
     * @param newShapes Arraylist of shapes that are to be saved
     */
    public void setShapes(ArrayList<AbstractShape> newShapes){
        this.shapes = null;
        this.shapes = newShapes;
    }

    /**
     * Saves the shape in the the Arraylist of abstract shapes in the file path given
     * @param filePath the filepath in which to save the file
     * @throws IOException throws if there is an error in the saving process
     */
    public void saveToFile(String filePath) throws IOException {
        // clear file
        File f = new File(filePath);
        if(f.exists()){
            f.delete();
            try {
                f.createNewFile();
            } catch (IOException e) {
                System.out.println("Error in saving to filepath given");
            }
        }
        // writ to file
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,
                true)); // allow for appending
        writer.flush();
        for(int i = 0; i < shapes.size(); i++) {
            double[] coord = new double[0];
            try {
                coord = shapes.get(i).getCoordinates();
            } catch (ShapeException e) {
                e.printStackTrace();
            }
            writeLine(writer, i, coord);
            writeRectangle(writer, i, coord);
            writeEllipse(writer, i, coord);
            writePolygon(writer, i, coord);
            writePlot(writer, i, coord);
        }
        writer.close(); // close when finished writing
    }

    private void writePolygon(BufferedWriter writer, int i, double[] coord) throws IOException {
        if(shapes.get(i).getClass().equals(Polygon.class)){
            // write PEN/FILL only if previous shape has different color
            writePen(writer, i);
            writeFill(writer, i);
            writer.write("POLYGON ");
            for(double cd: coord){
                writer.write(cd/ BuildApp.scale + " "); // add each coord value
            }
            writer.newLine();
        }
    }

    private void writeEllipse(BufferedWriter writer, int i, double[] coord) throws IOException {
        if(shapes.get(i).getClass().equals(Ellipse.class)){
            // write PEN/FILL only if previous shape has different color
            writePen(writer, i);
            writeFill(writer, i);
            writer.write("ELLIPSE " + coord[0]/BuildApp.scale + " " + coord[1]/BuildApp.scale + " " +
                    coord[2]/BuildApp.scale + " " + coord[3]/BuildApp.scale);
            writer.newLine();
        }
    }

    private void writeRectangle(BufferedWriter writer, int i, double[] coord) throws IOException {
        if(shapes.get(i).getClass().equals(Rectangle.class)){
            // write PEN only if previous shape has different color
            writePen(writer, i);
            writeFill(writer, i);

            writer.write("RECTANGLE " + coord[0]/BuildApp.scale + " " + coord[1]/BuildApp.scale + " " +
                    coord[2]/BuildApp.scale + " " + coord[3]/BuildApp.scale);
            writer.newLine();
        }
    }

    private void writeLine(BufferedWriter writer, int i, double[] coord) throws IOException {
        if (shapes.get(i).getClass().equals(Line.class)) {
            // write PEN/FILL only if previous shape has different color
            writePen(writer, i);
            // add new command to file
            writer.write("LINE " + coord[0] / BuildApp.scale + " " + coord[1] / BuildApp.scale + " " +
                    coord[2] / BuildApp.scale + " " + coord[3] / BuildApp.scale);
            writer.newLine();
        }
    }

    private void writePlot(BufferedWriter writer, int i, double[] coord) throws IOException {
        if (shapes.get(i).getClass().equals(Plot.class)) {
            // write PEN/FILL only if previous shape has different color
            writePen(writer, i);
            // add new command to file
            writer.write("PLOT " + coord[0] / BuildApp.scale + " " + coord[1] / BuildApp.scale);
            writer.newLine();
        }
    }

    private void writePen(BufferedWriter writer, int i) throws IOException {
        // write PEN only if previous shape has different color
        if (i == 0 && shapes.get(i).getPenColor() != null) {
            String hex = "#" + Integer.toHexString(shapes.get(i).getPenColor().getRGB()).substring(2);
            writer.write("PEN " + hex.toUpperCase());
            writer.newLine();
        }
        if (i > 0 && shapes.get(i).getPenColor() != null && shapes.get(i).getPenColor()
                != shapes.get(i - 1).getPenColor()) {
            String hex = "#" + Integer.toHexString(shapes.get(i).getPenColor().getRGB()).substring(2);
            writer.write("PEN " + hex.toUpperCase());
            writer.newLine();
        }
    }

    private void writeFill(BufferedWriter writer, int i) throws IOException {
        // write PEN/FILL only if previous shape has different color
        if(i == 0 && shapes.get(i).getFillColor() != null) {
            String hex = "#" + Integer.toHexString(shapes.get(i).getFillColor().getRGB()).substring(2);
            writer.write("FILL " + hex.toUpperCase());
            writer.newLine();
        }

        if (i > 0 && shapes.get(i).getFillColor() != null && shapes.get(i).getFillColor()
                != shapes.get(i - 1).getFillColor()) {
            String hex = "#" + Integer.toHexString(shapes.get(i).getFillColor().getRGB()).substring(2);
            writer.write("FILL " + hex.toUpperCase());
            writer.newLine();
        }
    }
}
