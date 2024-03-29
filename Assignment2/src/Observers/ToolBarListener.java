package Observers;

import gui.ToolBars;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Application.BuildApp.*;

/**
 * Class for listening for toolbar actions
 */
public class ToolBarListener implements ActionListener {
    // Set the current shape and/or color depending on the source of event
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ToolBars.penButton) {
            currentShape = "Plot";
        }
        if (e.getSource() == ToolBars.fillButton) {
            fillColor = JColorChooser.showDialog(null, "Pick fill color", penColor);
        }
        if (e.getSource() == ToolBars.lineButton) {
            currentShape = "Line";
        }
        if (e.getSource() == ToolBars.squareButton) {
            currentShape = "Rectangle";
        }
        if (e.getSource() == ToolBars.polygonButton) {
            currentShape = "Polygon";
        }
        if (e.getSource() == ToolBars.ellipseButton) {
            currentShape = "Ellipse";
        }
        if (e.getSource() == ToolBars.penColorButton) {
            penColor = JColorChooser.showDialog(null, "Pick pen color", penColor);
            if (penColor == null) {
                penColor = Color.black;
            }
        }
    }
}
