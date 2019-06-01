package Application;

import GUI.CanvasPanel;
import Observers.CanvasPanelListener;
import Observers.EditMenuListener;
import Observers.FileMenuListener;
import Observers.ToolBarListener;
import gui.MenuBars;
import gui.StatusBar;
import gui.ToolBars;
import shapes.Draw;
import vec.VecReader;
import javax.swing.*;

import vec.VecWriter;
import java.awt.*;
import java.awt.event.*;

public class BuildApp extends JFrame {

    public static CanvasPanel CanvasPanel;
    public static Draw drawCanvas;
    public static VecWriter vecWriter = new VecWriter();
    public static String vecFilePath = null;
    public static int scale = 600;

    //STATES
    public static String currentShape;
    public static Color penColor;
    public static Color fillColor;

    /**
     * Main Jframe constructor for the application
     */
    public BuildApp() {
        this.setSize(1024, 768);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("VDT: Vector Design Tool");
        loadNewCanvas();
    }

    /**
     * Method for importing to current canvas
     */
    public static void reloadCanvas(){
        VecReader vecRead = new VecReader(vecFilePath);
        drawCanvas.clearCommands();
        drawCanvas.setCommands(vecRead.GetData());
        drawCanvas.repaint();
    }

    /**
     * Load components to the main frame
     */
    public void loadNewCanvas(){
        drawCanvas = new Draw();
        CanvasPanel = new CanvasPanel();
        ToolBars toolBar = new ToolBars();
        gui.MenuBars menuBars = new MenuBars();
        StatusBar status = new StatusBar();

        this.add(status, BorderLayout.PAGE_END);
        this.add(toolBar, BorderLayout.WEST);
        this.add(menuBars, BorderLayout.NORTH);

        // Adding listeners to components
        menuBars.addFileMenuListner(new FileMenuListener()); // add listener to menu
        menuBars.addEditMenuListener(new EditMenuListener()); // add listener to edit menu
        drawCanvas.addMouseListener(new CanvasPanelListener()); // add listener to canvas
        toolBar.addToolBarListener(new ToolBarListener()); // add listeners to toolbar buttons
//        this.addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentResized(ComponentEvent e) {
//                if(scale == 600){
//                    if(drawCanvas.getSize().width == 600){
//                        drawCanvas.setPreferredSize(new Dimension(900, 900));
//                    }
//                }
//
//                if(drawCanvas.getSize().width == 900){
//                    scale = 900;
//                    drawCanvas.repaint();
//                }
//            }
//        });

        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new MyDispatcher());

        CanvasPanel.add(drawCanvas);
        CanvasPanel.setLayout(new FlowLayout());
        this.add(CanvasPanel, BorderLayout.CENTER);

        this.setBackground(Color.WHITE);
        this.setVisible(true);
    }

    // Inner class for listening for key commands
    private class MyDispatcher implements KeyEventDispatcher {
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            // Control + Z for undo cmd
            if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Z) {
                try{
                    drawCanvas.removeCommand(); // remove last command
                    drawCanvas.repaint();
                } catch (NullPointerException n){
                    System.out.println("No more shapes to undo");
                }
            }
            return false;
        }
    }
}

