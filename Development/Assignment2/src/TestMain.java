import shapes.*;
import shapes.Rectangle;
import vec.VecIO;

import javax.swing.*;
import java.awt.*;

public class TestMain {

    public static void main (String[] args) {
        VecIO vec = new VecIO("rectangle.VEC");
        vec.ReadFile();
        vec.GetCommands();
        JFrame frame = new JFrame("Test");
        frame.setBackground(Color.WHITE);
        frame.setVisible(true);
        frame.pack();
        frame.setSize(800,500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Rectangle rect1 = new Rectangle(Color.black, 10,10,50,50);
        Rectangle rect2 = new Rectangle(Color.MAGENTA, 100,100,100,100);

        Draw draw = new Draw();
        draw.addCommand(rect1);
        draw.addCommand(rect2);

        frame.add(vec.getDrawCommands(), BorderLayout.CENTER);






    }
}
