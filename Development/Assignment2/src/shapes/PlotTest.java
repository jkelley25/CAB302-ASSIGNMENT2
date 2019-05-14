package shapes;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vec.VecIO;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlotTest {
    VecIO vec;
    PlotList plots;
    private final int SCALE = 200;

    /**
     * Clear plots and initialize vec object
     */
    @BeforeEach
    public void Init(){
        plots = null;
        vec = new VecIO("plottest.VEC");
        vec.ReadFile();
        vec.GetCommands();
    }

    /**
     * Test PlotList construction
     */
    @Test
    public void TestConstruction(){
        plots = new PlotList();
    }

    /**
     * Test Plot coordinate
     */
    @Test
    public void TestCoordinate(){
        plots = new PlotList();
        plots.AddPlot(10,20);
        Plot plot = plots.GetPlot(0);
        double [] actual = plot.GetCoordinates();
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
        plots = vec.GetPlots();
        Plot plot = plots.GetPlot(0); // get first PLOT command
        double [] actual = plot.GetCoordinates();
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
        plots = vec.GetPlots();
        Plot plot = plots.GetPlot(vec.GetData().size() - 1); // get last plot command
        double [] actual = plot.GetCoordinates();
        double [] expected = {0.48*SCALE, 0.52*SCALE};
        // assert each value of the coordinate
        assertEquals(expected[0], actual[0]);
        assertEquals(expected[1], actual[1]);
    }


}
