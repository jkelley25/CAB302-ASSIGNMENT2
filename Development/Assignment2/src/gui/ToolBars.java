package gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Class that holds all the toolbars of the application
 */
public class ToolBars extends JPanel {

    public static JButton  lineButton, penButton, squareButton,
            fillButton, penColorButton, polygonButton, ellipseButton; // Static Buttons

    /**
     * Creates the toolbar
     */
    public ToolBars() {
        this.setBackground(Color.DARK_GRAY);
        // All Credit for Icon Images go to "https://www.flaticon.com/authors/pixel-perfect"
        lineButton = createToolButton("./resources/icons/line.png", "Line");
        penButton = createToolButton("./resources/icons/pencil.png", "Pen");
        squareButton = createToolButton("./resources/icons/square.png", "Square");
        fillButton = createToolButton("./resources/icons/fill.png", "Fill Color");
        penColorButton = createToolButton("./resources/icons/colourwheel.png", " Pen Colour");
        polygonButton = createToolButton("./resources/icons/polygon.png", "Polygon");
        ellipseButton = createToolButton("./resources/icons/ellipse.png", "Ellipse");
        // add to Toolbar panel
        this.add(penButton);
        this.add(lineButton);
        this.add(squareButton);
        this.add(polygonButton);
        this.add(ellipseButton);
        this.add(fillButton);
        this.add(penColorButton);
        this.setPreferredSize(new Dimension(75, 100));
    }

    /**
     * Add actionlistener to each tool
     * @param listener the actionlistener to be added
     */
    public void addToolBarListener (ActionListener listener){
        lineButton.addActionListener(listener);
        penButton.addActionListener(listener);
        squareButton.addActionListener(listener);
        fillButton.addActionListener(listener);
        polygonButton.addActionListener(listener);
        ellipseButton.addActionListener(listener);
        penColorButton.addActionListener(listener);
    }

    /**
     * Creates a tool bar button given the icon and tooltip
     * @param imageFile the icon of the button
     * @param ToolTip the tool tip message
     * @return Jbutton create
     */
    private JButton createToolButton(String imageFile, String ToolTip) {
        JButton button = new JButton();
        button.setSize(new Dimension(30,30));
        ImageIcon buttonIcon = new ImageIcon(imageFile);
        Image img = buttonIcon.getImage();
        Image img2 = img.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        buttonIcon = new ImageIcon(img2);
        button.setIcon(buttonIcon);
        button.setToolTipText(ToolTip);
        return button;
    }

}



