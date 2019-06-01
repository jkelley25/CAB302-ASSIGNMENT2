package shapes;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Class for the for the main drawing panel, extends JPanel
 */
public class Draw extends JPanel {
    private ArrayList<AbstractShape> shapes = new ArrayList<>();
    private ArrayList<AbstractShape> history = new ArrayList<>();

    /**
     * Constructs the Draw object, setting its dimensions and background
     */
    public Draw(){
        setPreferredSize(new Dimension(600,600));
        setBackground(Color.white);
    }

    /**
     * Adds shapes to the shapes array to be drawn on the panel
     * @param s shape to the added
     */
    public void addCommand(AbstractShape s){
        shapes.add(s);
        history.add(s); // add to history for redo
    }

    /**
     * Removes th last shape in the shapes array
     * @throws NullPointerException throws nullexception if no more shapes to be removed
     */
    public void removeCommand() throws NullPointerException {

        if(shapes.size() == 0){
            throw new NullPointerException("No shapes to undo");
        } else {
            shapes.remove(shapes.size() - 1);
        }
    }

    /**
     * Method for painting all shapes in to panel using Graphics class
     * @param g graphics object for drawing
     * @throws NullPointerException throws null exception if there are no shapes to be drawn
     */
    public void paintComponent(Graphics g) throws NullPointerException{
        super.paintComponent(g);
        // paint every shape
        for (AbstractShape shape: shapes){
            shape.paint(g);
        }
    }

    /**
     * Gets every shape command in the array of shapes
     * @return array of shapes
     */
    public ArrayList<AbstractShape> getCommands(){
        return shapes;
    }

    /**
     * Sets the shapes array to be drawn
     * @param shapes the new shapes array
     */
    public void setCommands(ArrayList<AbstractShape> shapes){
        this.shapes = shapes;
        this.history = shapes;
    }

    /**
     * Method for adding the recently removed shape
     * @throws NullPointerException throws null exception if there are no more left
     */
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

    /**
     * Method for clearing all of the shape commands in the canvas
     */
    public void clearCommands(){
        shapes.clear();
    }

}
