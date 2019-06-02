package gui;

import Observers.StatusBarListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Class that shows the status bar at the bottom, extends JPanel
 */
public class StatusBar extends JPanel {
    public static JButton reset; // static JButton

    /**
     * Create status bar
     */
    public StatusBar(){
        this.setPreferredSize(new Dimension(this.getWidth(), 35));
        this.setLayout(new BorderLayout());
        reset = new JButton("Reset ");
        reset.setBackground(Color.decode("#718093"));
        reset.setPreferredSize(new Dimension(75, 20));
        reset.setFocusable(false);
        this.add(reset, BorderLayout.LINE_START);
    }

    /**
     * Add actionlistener to reset button
     * @param listener Action listener to be added
     */
    public void addStatusBarListener(ActionListener listener){
        reset.addActionListener(listener);
    }
}
