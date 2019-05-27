package GUI;

import Application.ActivPanel;
import Application.ActivePanel;
import Application.Main;
import Tools.Tools;

import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Application.Main.App;
import static Tools.Tools.RECTANGLE;

public class ToolBars extends JPanel {

    Tools tool;

    public ToolBars() {
        this.setBackground(Color.DARK_GRAY);
        //this.setSize(new Dimension(1, 1));

        //Box ToolBox = Box.createVerticalBox();
        JButton selectButton, lineButton, penButton, eraseButton, squareButton, fillButton, colourButton;

        // All Credit for Icon Images go to "https://www.flaticon.com/authors/pixel-perfect"
        selectButton = createToolButton("./src/select.png", "Select", );
        lineButton = createToolButton("./src/line.png", "Line");
        penButton = createToolButton("./src/pencil.png", "Pen");
        eraseButton = createToolButton("./src/eraser.png", "Erase");
        squareButton = createToolButton("./src/square.png", "Square");
        fillButton = createToolButton("./src/fill.png", "Fill");
        colourButton = createToolButton("./src/colourwheel.png", "Colour");

        this.add(selectButton);
        this.add(lineButton);
        this.add(penButton);
        this.add(eraseButton);
        this.add(squareButton);
        this.add(fillButton);
        this.add(colourButton);

        //this.add(ToolBox);

        //this.setPreferredSize(new Dimension(50, 200));
        //this.setPreferredSize(new Dimension(100, 200));

        this.setPreferredSize(new Dimension(50, 100));
    }

        private JButton createToolButton(String imageFile, String ToolTip, Tools tool){
            JButton button = new JButton();
            //button.setSize(new Dimension(15,15));
            ImageIcon buttonIcon = new ImageIcon(imageFile);
            Image img = buttonIcon.getImage();
            Image img2 = img.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
            buttonIcon = new ImageIcon(img2);
            button.setIcon(buttonIcon);
            button.setToolTipText(ToolTip);

            addButtonListener(button, tool);
            return button;
        }

        // Put this method in a new class
        private void addButtonListener(JButton button, Tools tool) {
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    if(tool == Tools.LINE){
                        ActivPanel.setCurrentTool = Tools.LINE;
                    }
                    if(tool == RECTANGLE){
                        ActivPanel.setCurrentTool = RECTANGLE;
                    }
                    if(tool == Tools.PEN){
                        ActivPanel.setCurrentTool = Tools.PEN;
                    }
//                Canvas.setCurrentTool(currentTool);
//                if(currentTool = 6){
//                    // currentColour = JColorChooser.showDialog(null, "Please select a colour", Color.BLACK);
//                    // updateColourIcon(currentColour);
//                }
//                if(currentTool = 7){
//                    // button.
//                }
                }
            }
        }
}


