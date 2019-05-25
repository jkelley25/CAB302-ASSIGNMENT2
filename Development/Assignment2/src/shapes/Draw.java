package shapes;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Draw extends JPanel {
    private ArrayList<Shape> shapes = new ArrayList<>();

    public Draw(){
        setPreferredSize(new Dimension(600,600));
        setBackground(Color.white);
    }

    public void addCommand(Shape s){
        shapes.add(s);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        // paint every shape
        for (Shape shape: shapes){
            shape.paint(g);
        }
    }

    public ArrayList<Shape> getCommands(){
        return shapes;
    }
}
