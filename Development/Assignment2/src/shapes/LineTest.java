//package shapes;
//
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import org.junit.jupiter.api.BeforeEach;
//import vec.VecIO;
//
//public class LineTest {
//    VecIO vec;
//    Line line;
//    private final int SCALE = 200;
//
//    /**
//     * Clear lineCommand and initialize vec object
//     */
//    @BeforeEach
//    public void Init(){
//        line = null;
//        vec = new VecIO("linetest.VEC");
//        vec.ReadFile();
//        vec.GetCommands();
//    }
//
//    /**
//     * Test construction of LineCommand objects
//     */
//    @Test
//    public void TestConstruction(){
//        line = new Line(1,1,1,1);
//    }
//
//    /**
//     * Test the coordinates of a line
//     */
//    @Test
//    public void TestAddLine(){
//        line = new Line(1,1,10,10);
//        double [] actual = line.getCoordinates();
//        double[] expected = {1,1,10,10};
//        // assert each value in the coordinate array
//        assertEquals(expected[0], actual[0]);
//        assertEquals(expected[1], actual[1]);
//        assertEquals(expected[2], actual[2]);
//        assertEquals(expected[3], actual[3]);
//    }
//
//
//    /**
//     * Test the number of commands in the file
//     */
//    @Test
//    public void TestNumCommands(){
//        int actual  = vec.GetData().size() - 1; //get data size
//        int expected = 12;
//        assertEquals(expected,actual);
//    }
//
//}
