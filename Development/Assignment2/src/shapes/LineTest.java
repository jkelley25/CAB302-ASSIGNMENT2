package shapes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import vec.VecIO;

public class LineTest {
    VecIO vec;
    LineList lineList;
    private final int SCALE = 200;

    /**
     * Clear lineList and initialize vec object
     */
    @BeforeEach
    public void Init(){
        lineList = null;
        vec = new VecIO("linetest.VEC");
        vec.ReadFile();
        vec.GetCommands();
    }

    /**
     * Test construction of LineList objects
     */
    @Test
    public void TestConstruction(){
        lineList = new LineList();
    }

    /**
     * Test the coordinates of a line
     */
    @Test
    public void TestAddLine(){
        lineList = new LineList();
        lineList.AddLine(1,1,10,10);
        Line line = lineList.GetLine(0);
        double [] actual = line.GetCoordinates();
        double[] expected = {1,1,10,10};
        // assert each value in the coordinate array
        assertEquals(expected[0], actual[0]);
        assertEquals(expected[1], actual[1]);
        assertEquals(expected[2], actual[2]);
        assertEquals(expected[3], actual[3]);
    }

    /**
     * Test the coordinate of the LINE command from the VEC file
     */
    @Test
    public void TestVECLineCommand(){
        LineList lines = vec.GetLines();
        double [] actual = lines.GetLine(0).GetCoordinates(); // get coordinates of the first command
        double [] expected = {0.51*SCALE, 0.08*SCALE, 0.65*SCALE, 0.31*SCALE};
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
    public void TestNumCommands(){
        int actual  = vec.GetData().size() - 1; //get data size
        int expected = 12;
        assertEquals(expected,actual);
    }

}
