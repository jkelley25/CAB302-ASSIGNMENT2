package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import shapes.AbstractShape;
import shapes.Ellipse;
import shapes.ShapeException;
import vec.VecReader;

import java.awt.*;
import java.util.ArrayList;

/**
 * Unit test for Ellipse class
 */
class EllipseTest {
    private Ellipse ellipse;
    private VecReader vec = new VecReader("TestFiles/EllipseTest.vec");
    private int SCALE = 600;

    /**
     * Clear object before each test
     */
    @BeforeEach
    void init(){
        ellipse = null;
    }

    /**
     * Test obeject construction
     *
     */
    @Test
    void TestConstuction() throws ShapeException {
        ellipse = new Ellipse(Color.black, null, 100, 100);
    }

    /**
     * Test Coordinates
     *
     */
    @Test
    void TestCoords() throws ShapeException {
        ellipse = new Ellipse(Color.black, null, 1,2,3,4);
        double [] actual = ellipse.getCoordinates();
        double [] expected = {1,2,3,4};
        // assert each value in the coordinate array
        assertEquals(expected[0], actual[0]);
        assertEquals(expected[1], actual[1]);
        assertEquals(expected[2], actual[2]);
        assertEquals(expected[3], actual[3]);
    }

    /**
     * Test start coordinate
     *
     */
    @Test
    void TestStartCoord() throws ShapeException {
        ellipse = new Ellipse(Color.black, null, 10,10);
        ellipse.setStartPoint(100,100);
        double [] actual = ellipse.getCoordinates();
        double [] expected = {100, 100};
        // assert each value in the coordinate array
        assertEquals(expected[0], actual[0]);
        assertEquals(expected[1], actual[1]);
    }

    /**
     * Test end coordiante
     *
     */
    @Test
    void TestEndCoord() throws ShapeException {
        ellipse = new Ellipse(Color.black, null, 10,10);
        ellipse.setEndPoint(200,200);
        double [] actual = ellipse.getCoordinates();
        double [] expected = {200, 200};
        assertEquals(expected[0], actual[2]);
        assertEquals(expected[1], actual[3]);
     }

    /**
     * Test pen color
     *
     */
    @Test
    void TestPenColor() throws ShapeException {
        ellipse = new Ellipse(Color.black, null, 1,1);
        ellipse.setPenColor(Color.green);
        assertEquals(Color.green, ellipse.getPenColor());
     }

    /**
     * Test fill color
     *
     */
    @Test
    void TestFillColor() throws ShapeException {
        ellipse = new Ellipse(Color.black, null, 1,1);
        ellipse.setFillColor(Color.green);
        assertEquals(Color.green, ellipse.getFillColor());
     }

    /**
     * Test the coordinates of the first command in RectTest.vec file
     */
    @Test
    void TestFirstCommand() throws ShapeException {
        ArrayList<AbstractShape> ell =vec.GetData();
        double [] actual = ell.get(0).getCoordinates(); //get coords of first ellipse command
        double [] expected = {0.0*SCALE, 0.0*SCALE, 1.0*SCALE, 0.5*SCALE};
        // assert each value in the coordinate array
        assertEquals(expected[0], actual[0]);
        assertEquals(expected[1], actual[1]);
        assertEquals(expected[2], actual[2]);
        assertEquals(expected[3], actual[3]);
    }

    /**
     * Test the coordinates of the first command in RectTest.vec file
     */
    @Test
    void TestLastCommand() throws ShapeException {
        ArrayList<AbstractShape> ell =vec.GetData();
        double [] actual = ell.get(ell.size() - 1).getCoordinates(); //get coords of first ellipse command
        double [] expected = {0.0*SCALE, 0.5*SCALE, 1.0*SCALE, 1.0*SCALE};
        // assert each value in the coordinate array
        assertEquals(expected[0], actual[0]);
        assertEquals(expected[1], actual[1]);
        assertEquals(expected[2], actual[2]);
        assertEquals(expected[3], actual[3]);
    }

    /**
     * Test the number of commands in the file
     */
    @Test
    void TestNumCommands(){
        int actual  = vec.GetData().size(); //get data size
        int expected = 2;
        assertEquals(expected,actual);
    }

    /**
     * Test invalid pen color exception is thrown
     */
    @Test
    void TestException() {
        assertThrows(Exception.class, () -> {
            ellipse = new Ellipse(Color.decode(""), null,10,20);
        });
    }

    /**
     * Test pen exception msg
     */
    @Test
    void TestPenExceptionMessage() {
        try {
            ellipse = new Ellipse(Color.decode("23"), null, 1,1);
        } catch (ShapeException e) {
            assertEquals("Invalid pen color!", e.getMessage());
        }
    }

    /**
     * Test Fill exception msg
     */
    @Test
    void TestFillExceptionMsg(){
        try {
            ellipse = new Ellipse(Color.black, Color.decode("23"), 1,1);
        } catch (ShapeException e) {
            assertEquals("Invalid fill color!", e.getMessage());
        }
    }

    /**
     * Test negative exception is thrown
     */
    @Test
    void TestNegException(){
        assertThrows(Exception.class, () -> {
            ellipse = new Ellipse(Color.black, null, -1,-1);
        });
    }

    /**
     * Test negative msg
     */
    @Test
    void TestNegExcpMsg(){
        try {
            ellipse = new Ellipse(Color.green, null, -1,1);
        } catch (ShapeException e) {
            assertEquals("Coordinates cannot be negative", e.getMessage());
        }
    }
}
