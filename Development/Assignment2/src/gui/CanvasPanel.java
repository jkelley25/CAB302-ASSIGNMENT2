package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Canvas panel which will contain the main canvas
 */
public class CanvasPanel extends JPanel {

    /**
     * Create the canvas
     */
    public CanvasPanel(){
        this.setBackground(Color.decode("#718093"));
        this.setPreferredSize(new Dimension(1078, 764));
    }
}
