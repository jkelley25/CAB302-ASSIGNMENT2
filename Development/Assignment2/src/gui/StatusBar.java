package gui;

import Application.BuildApp;

import javax.swing.*;
import java.awt.*;

public class StatusBar extends JPanel {

    public StatusBar(){
        this.setPreferredSize(new Dimension(this.getWidth(), 35));
        this.setLayout(new BorderLayout());
        JLabel crtColor = new JLabel("TEST    ");
        crtColor.setBackground(Color.green);
        crtColor.setVisible(true);
        JButton clear = new JButton("Reset ");
        clear.setBackground(Color.decode("#718093"));
        clear.setPreferredSize(new Dimension(75, 20));
        clear.setFocusable(false);

        JLabel pen = new JLabel("PEN");
        pen.setForeground(BuildApp.penColor);
        JLabel fill = new JLabel("FILL");
        this.add(clear, BorderLayout.LINE_START);
        this.add(pen, BorderLayout.CENTER);
        this.add(crtColor, BorderLayout.LINE_END);
    }

    private void createLabels() {
        JLabel crtColor = new JLabel("   ");
        crtColor.setBackground(Color.green);
        crtColor.setVisible(true);
    }

}
