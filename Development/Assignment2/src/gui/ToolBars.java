package gui;


import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;
import java.awt.event.ActionListener;
public class ToolBars extends JPanel {

    Tool tool;
    //Box ToolBox = Box.createVerticalBox();
    public static JButton selectButton, lineButton, penButton, eraseButton, squareButton,
            fillButton, colourButton, polygonButton, ellipseButton;


    public ToolBars() {
        this.setBackground(Color.DARK_GRAY);
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
        this.setPreferredSize(new Dimension(75, 100));
    }


    private JButton createToolButton(String imageFile, String ToolTip) {
        JButton button = new JButton();
        //button.setSize(new Dimension(15,15));
        ImageIcon buttonIcon = new ImageIcon(imageFile);
        Image img = buttonIcon.getImage();
        Image img2 = img.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        buttonIcon = new ImageIcon(img2);
        button.setIcon(buttonIcon);
        button.setToolTipText(ToolTip);
        return button;
    }

    // Add Actionlistener to each tool
    public void addToolBarListener (ActionListener listener){
        selectButton.addActionListener(listener);
        lineButton.addActionListener(listener);
        penButton.addActionListener(listener);
        squareButton.addActionListener(listener);
        fillButton.addActionListener(listener);
        polygonButton.addActionListener(listener);
        ellipseButton.addActionListener(listener);
        colourButton.addActionListener(listener);
    }
}



