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
        shapes.remove(shapes.size() - 1);
    }

    public void paintComponent(Graphics g) throws NullPointerException{
        super.paintComponent(g);
        // paint every shape
        for (AbstractShape shape: shapes){
            shape.paint(g);
        }
    }

    public ArrayList<AbstractShape> getCommands(){
        return shapes;
    }

    public void setCommands(ArrayList<AbstractShape> shapes){
        this.shapes = shapes;
    }

    public void clearCommands(){
        shapes = null;
    }
}
