package Application;

import GUI.CanvasPanel;
import GUI.MenuBars;
import GUI.ToolBars;

import javax.swing.*;
import java.awt.*;

public class BuildApp extends JFrame {

    private final ToolBars ToolBar;
    private final CanvasPanel CanvasPanel;
    private final MenuBars MenuBars;

    public BuildApp(){
        this.setSize(1024, 768);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("VDT: Vector Design Tool");

        this.CanvasPanel = new CanvasPanel();
        this.MenuBars = new MenuBars();
        this.ToolBar = new ToolBars();

        this.add(ToolBar, BorderLayout.WEST);
        this.add(MenuBars, BorderLayout.NORTH);
        //this.add(new JScrollPane(CanvasPanel), BorderLayout.CENTER);
        this.add(CanvasPanel, BorderLayout.CENTER);

        this.setDefaultColours();
        //this.pack();
        this.setVisible(true);
    }

    public void setDefaultColours(){
        //this.CanvasPanel.setToolColour(Color.BLACK);
        this.setBackground(Color.WHITE);
    }
}
