package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import shapes.AbstractShape;
import shapes.Line;
import vec.VecReader;
import java.awt.*;
import java.util.ArrayList;

/**
 * Unit test for Line Class
 */
class LineTest {
    private VecReader vec = new VecReader("TestFiles/LineTest.VEC"); // file being tested
    private Line line;
    private final int SCALE = 600;

    /**
     * Clear lineCommand and initialize vec object
     */
    @BeforeEach
    void Init(){
        line = null;
    }

    /**
     * Test construction of LineCommand objects
     */
    @Test
    void TestConstruction(){
        line = new Line(Color.black,null,1,1,1,1);
    }

    /**
     * Test the coordinates of a line
     */
    @Test
    void TestLineCoords(){
        line = new Line(Color.black, null,1,1,10,10);
        double [] actual = line.getCoordinates();
        double[] expected = {1,1,10,10};
        // assert each value in the coordinate array
        assertEquals(expected[0], actual[0]);
        assertEquals(expected[1], actual[1]);
        assertEquals(expected[2], actual[2]);
        assertEquals(expected[3], actual[3]);
    }

    /**
     * Test the pen colour of line
     */
    @Test
    void TestPenColour(){
        line = new Line(Color.red, null,1,1,10,10);
        Color actual = line.getPenColor();
        Color expected = Color.red;
        assertEquals(expected,actual);
    }

    /**
     * Test the coordinates of first command in the LineTest.vec file
     */
    @Test
    void TestFirstCommand(){
        ArrayList<AbstractShape> lines =vec.GetData();
        double [] actual = lines.get(0).getCoordinates(); //get coords of first line command
        double [] expected = {0.000000*SCALE, 0.000000*SCALE, 1.000000*SCALE, 0.000000*SCALE};
        // assert each value in the coordinate array
        assertEquals(expected[0], actual[0]);
        assertEquals(expected[1], actual[1]);
        assertEquals(expected[2], actual[2]);
        assertEquals(expected[3], actual[3]);
    }

    /**
     * Test the coordinates of first command in the LineTest.vec file
     */
    @Test
    void TestLastCommand(){
        ArrayList<AbstractShape> lines =vec.GetData();
        double [] actual = lines.get(lines.size() - 1).getCoordinates(); // get coords of last cmd
        double [] expected = {0.000000*SCALE, 0.000000*SCALE, 1.000000*SCALE, 0.000000*SCALE};
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
        int expected = 44;
        assertEquals(expected,actual);
    }

}
