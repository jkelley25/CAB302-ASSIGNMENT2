package shapes;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LineList extends JPanel {
    public static ArrayList<Line> lines = new ArrayList<>();


    public void AddLine(double x1, double y1, double x2, double y2){
        this.lines.add(new Line(x1,y1,x2,y2));
    }

    public void Draw(){
        repaint();
    }

    public void paintComponent(Graphics g){
        for(Line lin: lines){
            lin.paint(g);
        }
    }

}
