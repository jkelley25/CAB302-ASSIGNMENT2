package gui;

import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBars extends JPanel {

    Tool tool;
    //Box ToolBox = Box.createVerticalBox();
    public static JButton selectButton, lineButton, penButton, eraseButton, squareButton,
            fillButton, colourButton, polygonButton, ellipseButton;

    public ToolBars() {
        this.setBackground(Color.DARK_GRAY);
        //this.setSize(new Dimension(1, 1));

        // All Credit for Icon Images go to "https://www.flaticon.com/authors/pixel-perfect"
        selectButton = createToolButton("./src/icons/select.png", "Select");
        lineButton = createToolButton("./src/icons/line.png", "Line");
        penButton = createToolButton("./src/icons/pencil.png", "Pen");
        eraseButton = createToolButton("./src/icons/eraser.png", "Erase");
        squareButton = createToolButton("./src/icons/square.png", "Square");
        fillButton = createToolButton("./src/icons/fill.png", "Fill");
        colourButton = createToolButton("./src/icons/colourwheel.png", "Colour");
        polygonButton = createToolButton("./src/icons/polygon.png", "Polygon");
        ellipseButton = createToolButton("./src/icons/ellipse.png", "Ellipse");

        this.add(selectButton);
        this.add(penButton);
        this.add(lineButton);
        this.add(squareButton);
        this.add(polygonButton);
        this.add(ellipseButton);
        this.add(eraseButton);
        this.add(fillButton);
        this.add(colourButton);

        //this.add(ToolBox);

        //this.setPreferredSize(new Dimension(50, 200));
        //this.setPreferredSize(new Dimension(100, 200));

        this.setPreferredSize(new Dimension(50, 100));
    }

        private static JButton createToolButton(String imageFile, String ToolTip){
            JButton button = new JButton();
            //button.setSize(new Dimension(15,15));
            ImageIcon buttonIcon = new ImageIcon(imageFile);
            Image img = buttonIcon.getImage();
            Image img2 = img.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
            buttonIcon = new ImageIcon(img2);
            button.setIcon(buttonIcon);
            button.setToolTipText(ToolTip);

            addButtonListener(button, ToolTip);
            return button;
        }

        // Add Actionlistener to each tool
        public void addToolBarListener(ActionListener listener){
            selectButton.addActionListener(listener);
            lineButton.addActionListener(listener);
            penButton.addActionListener(listener);
            squareButton.addActionListener(listener);
            fillButton.addActionListener(listener);
            polygonButton.addActionListener(listener);
            ellipseButton.addActionListener(listener);
            colourButton.addActionListener(listener);
        }

        // Put this method in a new class
        private static void addButtonListener(JButton button, String currentTool){
            button.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent event){

                    if(event.getSource() == lineButton){

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
            });
        }

}
