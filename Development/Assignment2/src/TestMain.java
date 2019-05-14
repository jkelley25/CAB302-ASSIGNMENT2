import vec.*;
import javax.swing.*;
import java.io.IOException;

public class TestMain {

    public static void main (String[] args) {
        VecIO vec = new VecIO("temp.VEC");
        vec.ReadFile();
        vec.GetCommands();
        JFrame frame = new JFrame("Test");
        frame.setVisible(true);
        frame.setSize(800,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(vec.lines);
        vec.lines.Draw();
    }
}
