package shapes;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Draw extends JPanel {
    private ArrayList<ShapeInterface> shapes = new ArrayList<>();

    public Draw(){
        setBorder(BorderFactory.createLineBorder(Color.black));

    }

    public void addCommand(ShapeInterface s){
        shapes.add(s);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);


        for (ShapeInterface shape: shapes){
            shape.paint(g);
        }

    }
}
