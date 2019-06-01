package Observers;

import gui.ToolBars;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Application.BuildApp.*;

public class ToolBarListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ToolBars.penButton) {
            currentShape = "Pen";
        }
        if (e.getSource() == ToolBars.fillButton) {
            fillColor = JColorChooser.showDialog(null, "Pick fill color", penColor);
            if (fillColor == null) {
                fillColor = null;
            }
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
        if (e.getSource() == ToolBars.colourButton) {
            penColor = JColorChooser.showDialog(null, "Pick pen color", penColor);
            if (penColor == null) {
                penColor = Color.black;
            }
        }
    }
}
