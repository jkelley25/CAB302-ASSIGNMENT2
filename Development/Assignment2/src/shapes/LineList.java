package shapes;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LineList extends JPanel {
    private ArrayList<Line> lines = new ArrayList<>();

    /**
     * Method for constructing line objects
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    public void AddLine(double x1, double y1, double x2, double y2){
        this.lines.add(new Line(x1,y1,x2,y2));
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
        for(Line lin: lines){
            lin.paint(g);
        }
    }

    /**
     * Gets the Line object given the index
     * @param index position of the desired Line
     * @return Line object
     */
    public Line GetLine(int index){
        return lines.get(index);
    }



}
