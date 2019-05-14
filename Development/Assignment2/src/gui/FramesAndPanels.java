package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FramesAndPanels extends JFrame implements ActionListener, Runnable {
    public static final int WIDTH = 300;
    public static final int HEIGHT = 200;

    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel5;

    public FramesAndPanels(String title) throws HeadlessException {
        super(title);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void run() {
        createGUI();
    }

    // Method to construct GUI
    private void createGUI(){
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        jPanel1 = createPanel(Color.CYAN);
        getContentPane().add(jPanel1, BorderLayout.CENTER);

        jPanel2 = createPanel(Color.GREEN);
        getContentPane().add(jPanel2, BorderLayout.NORTH);

        jPanel3 = createPanel(Color.RED);
        getContentPane().add(jPanel3, BorderLayout.SOUTH);

        jPanel4 = createPanel(Color.MAGENTA);
        getContentPane().add(jPanel4, BorderLayout.EAST);

        jPanel5 = createPanel(Color.YELLOW);
        getContentPane().add(jPanel5, BorderLayout.WEST);

        repaint();
        setVisible(true);
    }

    // Method for creating a panel with different colors
    private JPanel createPanel(Color c){
        JPanel jPanel = new JPanel();
        jPanel.setBackground(c);
        return jPanel;
    }

    public static void main(String [] args){
        SwingUtilities.invokeLater(new FramesAndPanels("BorderLayout"));
    }
}


