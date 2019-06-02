package Observers;

import Application.BuildApp;
import gui.StatusBar;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Class for listening for status bar actions
 */
public class StatusBarListener implements ActionListener {
    // Perform action if reset buttom is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == StatusBar.reset){
            // reset pen and fill to default
            BuildApp.penColor = Color.black;
            BuildApp.fillColor = null;
        }
    }
}
