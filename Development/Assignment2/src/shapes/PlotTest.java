package shapes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vec.VecIO;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlotTest {
    VecIO vec;
    Plot plot;
    private final int SCALE = 200;

    /**
     * Clear plot and initialize vec object
     */
    @BeforeEach
    public void Init(){
        plot = null;
        vec = new VecIO("plottest.VEC");
        vec.ReadFile();
        vec.GetCommands();
    }

    /**
     * Test PlotCommand construction
     */
    @Test
    public void TestConstruction(){
        plot = new Plot(1,1);
    }

    /**
     * Test Plot coordinate
     */
    @Test
    public void TestCoordinate(){
        plot = new Plot(10,20);
        double [] actual = plot.getCoordinates();
        double[] expected = {10,20};
        // assert each value of the coordinate
        assertEquals(expected[0], actual[0]);
        assertEquals(expected[1], actual[1]);
    }

    /**
     * Test the coordinate of the first PLOT command from the VEC file
     */
    @Test
    public void TestFirstPlotCommand(){
        ArrayList<ShapeInterface> shapes = vec.GetData();
        double [] actual  = shapes.get(0).getCoordinates(); // get first plot command
        double [] expected = {0.5*SCALE, 0.5*SCALE};
        // assert each value of the coordinate
        assertEquals(expected[0], actual[0]);
        assertEquals(expected[1], actual[1]);
    }

    /**
     * Test the coordinate of the last PLOT command from the VEC file
     */
    @Test
    public void TestLastPlotCommand(){
        ArrayList<ShapeInterface> shapes = vec.GetData();
        double [] actual = shapes.get(shapes.size() - 1).getCoordinates(); // get last plot command
        double [] expected = {0.48*SCALE, 0.52*SCALE};
        // assert each value of the coordinate
        assertEquals(expected[0], actual[0]);
        assertEquals(expected[1], actual[1]);
    }


}
