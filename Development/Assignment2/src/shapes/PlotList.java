package shapes;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlotList extends JPanel {
    private ArrayList<Plot> plots = new ArrayList<>();

    public void AddPlot(double x, double y){
        this.plots.add(new Plot(x,y));
    }

    /**
     * Method for drawing all the lines
     */
    public void Draw(){
        repaint();
    }

    /**
     * Method for painting all lines
     * @param g Graphics objects used for create line graphics
     */
    @Override
    public void paintComponent(Graphics g){
        for(Plot pl: plots){
            pl.paint(g);
        }
    }

    /**
     * Gets the Line object given the index
     * @param index position of the desired Line
     * @return Line object
     */
    public Plot GetPlot(int index){
        return plots.get(index);
    }


}
