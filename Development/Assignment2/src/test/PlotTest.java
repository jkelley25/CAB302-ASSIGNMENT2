package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shapes.AbstractShape;
import shapes.Plot;
import shapes.ShapeException;
import vec.VecReader;

import java.awt.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit test for the Plot class
 */
class PlotTest {
    private VecReader vec = new VecReader("TestFiles/PlotTest.VEC");;
    private Plot plot;
    private final int SCALE = 600;

    /**
     * Clear plot and initialize vec object
     */
    @BeforeEach
    void Init(){
        plot = null;
    }

    /**
     * Test PlotCommand construction
     */
    @Test
    void TestConstruction() throws ShapeException {
        plot = new Plot(Color.black,null,1,1);
    }

    /**
     * Test Plot coordinate
     */
    @Test
    void TestCoordinate() throws ShapeException {
        plot = new Plot(Color.black, null,10,20);
        double [] actual = plot.getCoordinates();
        double[] expected = {10,20};
        // assert each value of the coordinate
        assertEquals(expected[0], actual[0]);
        assertEquals(expected[1], actual[1]);
    }

    /**
     * Test the pen colour of line
     */
    @Test
    void TestPenColour() throws ShapeException {
        plot = new Plot(Color.green, null,10,20);
        assertEquals(Color.green, plot.getPenColor());
    }

    /**
     * Test the coordinate of the first PLOT command in PlotTest.vec file
     */
    @Test
    void TestFirstPlotCommand() throws ShapeException {
        ArrayList<AbstractShape> plots = vec.GetData();
        double [] actual  = plots.get(0).getCoordinates(); // get first plot command
        double [] expected = {0.5*SCALE, 0.5*SCALE};
        // assert each value of the coordinate
        assertEquals(expected[0], actual[0]);
        assertEquals(expected[1], actual[1]);
    }

    /**
     * Test the coordinate of the last PLOT command in PlotTest.vec file
     */
    @Test
    void TestLastPlotCommand() throws ShapeException {
        ArrayList<AbstractShape> shapes = vec.GetData();
        double [] actual = shapes.get(shapes.size() - 1).getCoordinates(); // get last plot command
        double [] expected = {0.48*SCALE, 0.52*SCALE};
        // assert each value of the coordinate
        assertEquals(expected[0], actual[0]);
        assertEquals(expected[1], actual[1]);
    }

    /**
     * Test the number of commands in the file
     */
    @Test
    void TestNumCommands(){
        int actual  = vec.GetData().size(); //get data size
        int expected = 9;
        assertEquals(expected,actual);
    }

    /**
     * Test exception is thrown for invalid color
     */
    @Test
    void TestException() {
        assertThrows(Exception.class, () -> {
            plot = new Plot(Color.decode(""), null,10,20);
        });
    }

    /**
     * Test exception message
     */
    @Test
    void TestExceptionMessage() {
        try {
            plot = new Plot(Color.decode("23"), null, 1,1);
        } catch (ShapeException e) {
            assertEquals("Invalid pen color!", e.getMessage());
        }
    }

    /**
     * Test negative coord exception thrown
     */
    @Test
    void TestNegException(){
        assertThrows(Exception.class, () -> {
            plot = new Plot(Color.black, null, -1,-1);
        });
    }

    /**
     * Test negative coord message
     */
    @Test
    void TestNegExcpMsg(){
        try {
            plot = new Plot(Color.green, null, -1,1);
        } catch (ShapeException e) {
            assertEquals("Coordinate cannot be negative", e.getMessage());
        }
    }
}
