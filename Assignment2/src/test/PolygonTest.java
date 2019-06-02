package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import shapes.AbstractShape;
import shapes.Polygon;
import shapes.ShapeException;
import vec.VecReader;

import java.awt.*;
import java.util.ArrayList;

/**
 * Unit test for Polygon class
 */
public class PolygonTest {
    private Polygon poly;
    private VecReader vec = new VecReader("TestFiles/PolyTest.vec");
    private final int SCALE = 600;
    private double [] xcoords = {1, 2};
    private double [] ycoords = {3, 4};

    @BeforeEach
    void Init(){
        poly = null;
    }

    @Test
    void TestConstruction() throws ShapeException {
        poly = new Polygon(Color.black, null, xcoords, ycoords);
    }

    @Test
    void TestCoordinates() throws ShapeException {
        poly = new Polygon(Color.black, null, xcoords, ycoords);
        double [] actual = poly.getCoordinates();
        double [] expected = {1,2,3,4};
        // assert each value in the coordinate array
        assertEquals(expected[0], actual[0]);
        assertEquals(expected[1], actual[1]);
        assertEquals(expected[2], actual[2]);
        assertEquals(expected[3], actual[3]);
    }

    @Test
    void TestPenColor() throws ShapeException{
        poly = new Polygon(Color.orange, null, xcoords, ycoords);
        assertEquals(Color.orange, poly.getPenColor());
    }

    @Test
    void TestFillColor() throws ShapeException{
        poly = new Polygon(Color.orange, Color.CYAN, xcoords, ycoords);
        assertEquals(Color.cyan, poly.getFillColor());
    }

    /**
     * Test the coordinates of the first command in RectTest.vec file
     */
    @Test
   void TestCommand() throws ShapeException {
        ArrayList<AbstractShape> poly = vec.GetData();
        double [] actual = poly.get(0).getCoordinates(); //get coords of first line command
        double [] expected = {0.245000*SCALE, 0.125000*SCALE, 0.065000*SCALE,
               0.228923*SCALE, 0.065000*SCALE, 0.021077*SCALE};
        // assert each value in the coordinate array
        assertEquals(expected[0], actual[0]);
        assertEquals(expected[2], actual[1]);
        assertEquals(expected[4], actual[2]);
    }

    /**
     * Test the number of commands in the file
     */
    @Test
    void TestNumCommands(){
        int actual  = vec.GetData().size(); //get data size
        int expected = 4;
        assertEquals(expected,actual);
    }


    /**
     * Test exception is thrown for invalid color
     */
    @Test
    void TestException() {
        assertThrows(Exception.class, () -> {
            poly = new Polygon(Color.decode(""), null,xcoords,ycoords);
        });
    }

    /**
     * Test invalid color msg
     */
    @Test
    void TestExceptionMessage() {
        try {
            poly = new Polygon(Color.decode("23"), null, xcoords,ycoords);
        } catch (ShapeException e) {
            assertEquals("Invalid pen color!", e.getMessage());
        }
    }

    /**
     * Test invalid fill color exception
     */
    @Test
    void TestFillExceptionMsg(){
        try {
            poly = new Polygon(Color.black, Color.decode("23"), xcoords,ycoords);
        } catch (ShapeException e) {
            assertEquals("Invalid fill color!", e.getMessage());
        }
    }

    /**
     * Test neg exception is thrown
     */
    @Test
    void TestNegException(){
        double [] x = { -1, 2};
        double [] y = { 2, 2};
        assertThrows(Exception.class, () -> {
            poly = new Polygon(Color.black, null, x,y);
        });
    }

    /**
     * Test negative msg
     */
    @Test
    void TestNegExcpMsg(){
        double [] x = { -1, 2};
        double [] y = { 2, 2};
        try {
            poly = new Polygon(Color.green, null, x,y);
        } catch (ShapeException e) {
            assertEquals("Coordinates cannot be negative", e.getMessage());
        }
    }
}
