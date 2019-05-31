package vec;

import Application.BuildApp;
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
        for(int i = 0; i < shapes.size(); i++) {
            double[] coord = shapes.get(i).getCoordinates();
            writeLine(writer, i, coord);
            writeRectangle(writer, i, coord);
            writeEllipse(writer, i, coord);
            writePolygon(writer, i, coord);
        }
        writer.close(); // close when finished writing
    }

    // ------------------------------------------------- Helper Methods -------------------------------- \\

    private void writePolygon(BufferedWriter writer, int i, double[] coord) throws IOException {
        if(shapes.get(i).getClass().equals(Polygon.class)){
            // write PEN only if previous shape has different color
            if(i == 0 && shapes.get(i).getPenColor() != null) {
                String hex = "#" + Integer.toHexString(shapes.get(i).getPenColor().getRGB()).substring(2);
                writer.write("PEN " + hex.toUpperCase());
                writer.newLine();
            }
            if (i > 0 && shapes.get(i).getPenColor() != null && shapes.get(i).getPenColor() != shapes.get(i - 1).getPenColor()) {
                String hex = "#" + Integer.toHexString(shapes.get(i).getPenColor().getRGB()).substring(2);
                writer.write("PEN " + hex.toUpperCase());
                writer.newLine();
            }
            writer.write("POLYGON ");
            for(double cd: coord){
                writer.write(cd/ BuildApp.scale + " "); // add each coord value
            }
            writer.newLine();
        }
    }

    private void writeEllipse(BufferedWriter writer, int i, double[] coord) throws IOException {
        if(shapes.get(i).getClass().equals(Ellipse.class)){
            // write PEN only if previous shape has different color
            if(i == 0 && shapes.get(i).getPenColor() != null) {
                String hex = "#" + Integer.toHexString(shapes.get(i).getPenColor().getRGB()).substring(2);
                writer.write("PEN " + hex.toUpperCase());
                writer.newLine();
            }
            if (i > 0 && shapes.get(i).getPenColor() != null && shapes.get(i).getPenColor() != shapes.get(i - 1).getPenColor()) {
                String hex = "#" + Integer.toHexString(shapes.get(i).getPenColor().getRGB()).substring(2);
                writer.write("PEN " + hex.toUpperCase());
                writer.newLine();
            }
            writer.write("ELLIPSE " + coord[0]/BuildApp.scale + " " + coord[1]/BuildApp.scale + " " +
                    coord[2]/BuildApp.scale + " " + coord[3]/BuildApp.scale);
            writer.newLine();
        }
    }

    private void writeRectangle(BufferedWriter writer, int i, double[] coord) throws IOException {
        if(shapes.get(i).getClass().equals(Rectangle.class)){
            // write PEN only if previous shape has different color
            if(i == 0 && shapes.get(i).getPenColor() != null) {
                String hex = "#" + Integer.toHexString(shapes.get(i).getPenColor().getRGB()).substring(2);
                writer.write("PEN " + hex.toUpperCase());
                writer.newLine();
            }
            if (i > 0 && shapes.get(i).getPenColor() != null && shapes.get(i).getPenColor() != shapes.get(i - 1).getPenColor()) {
                String hex = "#" + Integer.toHexString(shapes.get(i).getPenColor().getRGB()).substring(2);
                writer.write("PEN " + hex.toUpperCase());
                writer.newLine();
            }
            writer.write("RECTANGLE " + coord[0]/BuildApp.scale + " " + coord[1]/BuildApp.scale + " " +
                    coord[2]/BuildApp.scale + " " + coord[3]/BuildApp.scale);
            writer.newLine();
        }
    }

    private void writeLine(BufferedWriter writer, int i, double[] coord) throws IOException {
        if (shapes.get(i).getClass().equals(Line.class)) {
            // write PEN only if previous shape has different color
            if(i == 0 && shapes.get(i).getPenColor() != null) {
                String hex = "#" + Integer.toHexString(shapes.get(i).getPenColor().getRGB()).substring(2);
                writer.write("PEN " + hex.toUpperCase());
                writer.newLine();
            }
            if (i > 0 && shapes.get(i).getPenColor() != null && shapes.get(i).getPenColor() != shapes.get(i - 1).getPenColor()) {
                String hex = "#" + Integer.toHexString(shapes.get(i).getPenColor().getRGB()).substring(2);
                writer.write("PEN " + hex.toUpperCase());
                writer.newLine();
            }
            // add new command to file
            writer.write("LINE " + coord[0] / BuildApp.scale + " " + coord[1] / BuildApp.scale + " " +
                    coord[2] / BuildApp.scale + " " + coord[3] / BuildApp.scale);
            writer.newLine();
        }
    }

}
