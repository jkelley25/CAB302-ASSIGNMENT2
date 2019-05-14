import vec.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TestMain {

    public static void main (String[] args) {
        VecIO vec = new VecIO("linetest.VEC");
        vec.ReadFile();
        vec.GetCommands();
        JFrame frame = new JFrame("Test");
        frame.setBackground(Color.WHITE);
        frame.setVisible(true);
        frame.setSize(800,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(vec.GetLines());
        vec.GetLines().Draw();
    }
}
