package GUI;

import javax.swing.*;
import java.awt.*;

public class CanvasPanel extends JPanel {

    public CanvasPanel(){
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(1078, 764));
    }

    public CanvasPanel(Color CanvasColour){
        this.setBackground(CanvasColour);
        this.setPreferredSize(new Dimension(1078, 764));
    }

    public CanvasPanel(Color CanvasColour, int x, int y){
        this.setBackground(CanvasColour);
        this.setPreferredSize(new Dimension(x, y));
    }

}
