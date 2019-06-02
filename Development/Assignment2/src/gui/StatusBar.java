package gui;

import Application.BuildApp;
import Observers.StatusBarListener;

import javax.swing.*;
import java.awt.*;

public class StatusBar extends JPanel {
    public static JButton clear;

    public StatusBar(){
        this.setPreferredSize(new Dimension(this.getWidth(), 35));
        this.setLayout(new BorderLayout());
        clear = new JButton("Reset ");
        clear.setBackground(Color.decode("#718093"));
        clear.setPreferredSize(new Dimension(75, 20));
        clear.setFocusable(false);
        this.add(clear, BorderLayout.LINE_START);
    }

    public void addStatusBarListener(StatusBarListener listener){
        clear.addActionListener(listener);
    }
}
