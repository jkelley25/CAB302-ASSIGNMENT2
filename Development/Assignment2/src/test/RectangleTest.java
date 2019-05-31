package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import shapes.AbstractShape;
import vec.VecReader;
import shapes.Rectangle;

import java.awt.*;
import java.util.ArrayList;

/**
 * Unit test for Rectangle class
 */
public class RectangleTest {
    private Rectangle rect;
    private VecReader vec = new VecReader("TestFiles/RectTest.vec");
    private final int SCALE = 600;

    /**
     * Clear rectangle before each test
     */
    @BeforeEach
    void Init(){
        rect = null;
    }

    /**
     * Test object construction
     */
    @Test
    void TestConstruction(){
        rect = new Rectangle(Color.black, null, 100,100,200,200);
    }

    /**
     * Test coordinates of rectangle
     */
    @Test
    void TestCoordinates(){
        rect = new Rectangle(Color.black, null, 100,100,200,200);
        double [] actual = rect.getCoordinates();
        double [] expected = {100,100,200,200};
        // assert each value in the coordinate array
        assertEquals(expected[0], actual[0]);
        assertEquals(expected[1], actual[1]);
        assertEquals(expected[2], actual[2]);
        assertEquals(expected[3], actual[3]);
    }

    /**
     * Test Pen colour of the rectangle
     */
    @Test
    void TestPenColour(){
        rect = new Rectangle(Color.orange, null, 100,100,200,200);
        assertEquals(Color.orange, rect.getPenColor());
    }

    /**
     * Test Fill colour of the rectangle
     */
    @Test
    void TestFillColour(){
        rect = new Rectangle(Color.orange, Color.CYAN, 100,100,200,200);
        assertEquals(Color.cyan, rect.getFillColor());
    }

    /**
     * Test the coordinates of the first command in RectTest.vec file
     */
    @Test
    void TestFirstCommand(){
        ArrayList<AbstractShape> rects =vec.GetData();
        double [] actual = rects.get(0).getCoordinates(); //get coords of first line command
        double [] expected = {0.1*SCALE, 0.1*SCALE, 0.5*SCALE, 0.5*SCALE};
        // assert each value in the coordinate array
        assertEquals(expected[0], actual[0]);
        assertEquals(expected[1], actual[1]);
        assertEquals(expected[2], actual[2]);
        assertEquals(expected[3], actual[3]);
    }

    /**
     * Test the coordinates of the last command in RectTest.vec
     */
    @Test
    void TestLastCommand(){
        ArrayList<AbstractShape> rect =vec.GetData();
        double [] actual = rect.get(rect.size() - 1).getCoordinates(); // get coords of last cmd
        double [] expected = {0.5*SCALE, 0.5*SCALE, 0.9*SCALE, 0.9*SCALE};
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
        int expected = 5;
        assertEquals(expected,actual);
    }

}
