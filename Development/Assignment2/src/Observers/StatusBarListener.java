package Observers;

import Application.BuildApp;
import gui.StatusBar;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Class for listening for status bar actions
 */
public class StatusBarListener implements ActionListener, PropertyChangeListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == StatusBar.clear){
            BuildApp.penColor = Color.black;
            BuildApp.fillColor = null;
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
