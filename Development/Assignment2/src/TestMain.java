import gui.Wiring;
import shapes.Draw;
import shapes.Line;
import shapes.Rectangle;
import vec.VecIO;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class TestMain {

    public static void main (String[] args) {
        VecIO vec = new VecIO("example1.vec");
        vec.ReadFile();
        vec.GetCommands();
        JFrame frame = new JFrame("Test");

        frame.setLayout(new FlowLayout());
        frame.setBackground(Color.WHITE);

        frame.setSize(1000,600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(vec.getDrawCommands(), BorderLayout.CENTER);
//
//        JPanel panelleft = new JPanel();
//        JPanel panelright = new JPanel();
//        JPanel panelbottom = new JPanel();
//
//
//        frame.getContentPane().add(panelleft, BorderLayout.WEST);
//        frame.getContentPane().add(panelright, BorderLayout.EAST);
//        frame.getContentPane().add(panelbottom, BorderLayout.SOUTH);
        frame.setVisible(true);

    }

    public static class ButtonListener implements ActionListener, ChangeListener,
            MouseListener, MouseMotionListener {

        static Draw draw = new Draw();

        public void actionPerformed(ActionEvent e) {
            System.out.println(e);
        }

        public void stateChanged(ChangeEvent e) {
            System.out.println(e);
        }

        public void mouseClicked(MouseEvent e) {
            System.out.println(e);

        }

        public void mousePressed(MouseEvent e) {
            System.out.println(e);

            Line line = new Line (Color.black, Color.lightGray, 10,10,100,100);
            draw.addCommand(line);

        }

        public void mouseReleased(MouseEvent e) {
            System.out.println(e);
        }

        public void mouseEntered(MouseEvent e) {
            System.out.println(e);
        }

        public void mouseExited(MouseEvent e) {
            System.out.println(e);
        }

        public void mouseDragged(MouseEvent e) {
            System.out.println(e);
        }

        public void mouseMoved(MouseEvent e) {
            System.out.println(e);
        }
    }

}
