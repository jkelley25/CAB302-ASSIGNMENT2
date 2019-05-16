package shapes;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Draw extends JPanel {
    private ArrayList<Shape> shapes = new ArrayList<>();

    public Draw(){
        setBorder(BorderFactory.createLineBorder(Color.black));

    }

    public void addCommand(Shape s){
        shapes.add(s);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        for (Shape shape: shapes){
            shape.paint(g);
        }
    }
}
