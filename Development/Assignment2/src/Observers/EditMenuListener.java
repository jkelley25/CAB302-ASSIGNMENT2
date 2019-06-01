package Observers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Application.BuildApp.drawCanvas;
import static gui.MenuBars.*;
import static gui.MenuBars.ClearCanvas;

/**
 * Class for listening for edit menu actions
 */
public class EditMenuListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == Undo){
            try{
                drawCanvas.removeCommand(); // remove last command
                drawCanvas.repaint();
            } catch (NullPointerException n){
                System.out.println("No shapes to undo");
            }
        }
        if(e.getSource() == Redo){
            try{
                drawCanvas.redoCommand();
                drawCanvas.repaint();
                System.out.println("Test");
            } catch (NullPointerException n){
                System.out.println("No redos left");
            }
        }
        if(e.getSource() == ClearCanvas){
            drawCanvas.clearCommands();
            drawCanvas.repaint();
        }
    }
}