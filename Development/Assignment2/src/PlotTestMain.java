import vec.VecIO;
import javax.swing.*;
import java.awt.*;

public class PlotTestMain {

    public static void main(String[] args){
        VecIO vec = new VecIO("plottest.VEC");
        vec.ReadFile();
        vec.GetCommands();
        vec.GetPlots().Draw();
        JFrame frame = new JFrame("Test");
        frame.setBackground(Color.WHITE);
        frame.setVisible(true);
        frame.setSize(800,500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(vec.GetPlots());
    }
}
