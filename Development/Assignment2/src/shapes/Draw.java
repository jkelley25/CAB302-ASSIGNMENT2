package shapes;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;


public class Draw extends JPanel {
    private ArrayList<AbstractShape> shapes = new ArrayList<>();

    public Draw(){
        setPreferredSize(new Dimension(600,600));
        setBackground(Color.white);
    }

    public void addCommand(AbstractShape s){
        shapes.add(s);
    }

    public void removeCommand(){
        shapes.remove(shapes.size());
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        // paint every shape
        for (AbstractShape shape: shapes){
            shape.paint(g);
        }
    }

    public ArrayList<AbstractShape> getCommands(){
        return shapes;
    }


}
