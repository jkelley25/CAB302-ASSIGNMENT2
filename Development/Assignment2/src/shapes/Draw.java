package shapes;

import vec.VecReader;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Draw extends JPanel {
    private ArrayList<AbstractShape> shapes = new ArrayList<>();
    private ArrayList<AbstractShape> history = new ArrayList<>();


    public Draw(){
        setPreferredSize(new Dimension(600,600));
        setBackground(Color.white);
    }


    public void addCommand(AbstractShape s){
        shapes.add(s);
        history.add(s); // add to history for redo
    }

    public void removeCommand() throws NullPointerException {

        if(shapes.size() == 0){
            throw new NullPointerException("No shapes to undo");
        } else {
            shapes.remove(shapes.size() - 1);
        }
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
        this.history = shapes;
    }

    public void redoCommand() throws NullPointerException{
        if(history.size()  < shapes.size() ){
            throw new NullPointerException();
        }

        if (!history.isEmpty()) {
            if(shapes.size() == history.size()){
                //shapes.add(history.get(shapes.size() - 1));
                throw new NullPointerException();
            }
            shapes.add(history.get(shapes.size()));
            this.repaint();
        }
    }
    public void clearCommands(){
        shapes.clear();
    }

}
