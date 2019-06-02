package Observers;

import Application.BuildApp;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static Application.BuildApp.*;
import static gui.MenuBars.*;

/**
 * Class for listening for file menu actions
 */
public class FileMenuListener implements ActionListener, Runnable {
    Thread t;
    private String savePath;

    /**
     * Check which action is performed in the file menu
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Quit) {
            int result = JOptionPane.showConfirmDialog( CanvasPanel ,
                    "Do you want to Exit ?", "Exit Confirmation : ",
                    JOptionPane.YES_NO_OPTION);
            if(result == 0){
                System.exit(result);
            }
        }
        // Create new canvas
        if(e.getSource() == New){
            int result = JOptionPane.showConfirmDialog( CanvasPanel ,
                    "Start new? This will clear current canvas", "New Confirmation : ",
                    JOptionPane.OK_CANCEL_OPTION);
            if(result == 0){
                // clear canvas
                drawCanvas.clearCommands();
                drawCanvas.repaint();
                savePath = null;
            }
        }
        // load file
        if(e.getSource() == Load){
            FileFilter filter = new FileNameExtensionFilter("Vec File","vec");
            JFileChooser fc = new JFileChooser();
            fc.setFileFilter(filter);
            fc.setCurrentDirectory(new java.io.File("."));
            fc.setDialogTitle("Load vecRead file to current window");
            if(fc.showOpenDialog(Load) == JFileChooser.APPROVE_OPTION){
                vecFilePath = fc.getSelectedFile().getAbsolutePath();
                savePath = vecFilePath; // set as default save location
                reloadCanvas(); // reload canvas
                penColor = Color.black;
                fillColor = null;
            }
        }
        // save current filepath if loaded
        if(e.getSource() == Save ){
            if(vecFilePath == null){
                JFileChooser fc = new JFileChooser();
                fc.setCurrentDirectory(new java.io.File("."));
                fc.setDialogTitle("Save to file");
                if (fc.showOpenDialog(Save) == JFileChooser.APPROVE_OPTION) {
                    vecFilePath = fc.getSelectedFile().getAbsolutePath();
                    t = new Thread(this);
                    t.start();
                }
            } else {
                try {
                    vecWriter.setShapes(drawCanvas.getCommands());
                    vecWriter.saveToFile(savePath);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        // opens up a save dialog
        if(e.getSource() == SaveAs){
            JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(new java.io.File("."));
            fc.setDialogTitle("Save to file");
            if(fc.showOpenDialog(SaveAs) == JFileChooser.APPROVE_OPTION){
                savePath = fc.getSelectedFile().getAbsolutePath();
                try {
                    vecWriter.setShapes(drawCanvas.getCommands());
                    vecWriter.saveToFile(savePath);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public void run() {
        BuildApp newApp = new BuildApp();
        newApp.loadNewCanvas();
    }
}