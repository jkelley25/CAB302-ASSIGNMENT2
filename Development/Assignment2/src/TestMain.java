import vec.VecIO;

import javax.swing.*;
import java.awt.*;

public class TestMain {

    public static void main (String[] args) {
        VecIO vec = new VecIO("lineplottest.VEC");
        vec.ReadFile();
        vec.GetCommands();
        JFrame frame = new JFrame("Test");
        frame.setBackground(Color.WHITE);
        frame.setVisible(true);
        frame.setSize(800,500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(vec.GetPlots());
        frame.add(vec.GetLines());

        vec.GetPlots().Draw();
        vec.GetLines().Draw();

    }
}
