package Application;

import gui.CanvasPanel;
import Observers.*;
import gui.MenuBars;
import gui.StatusBar;
import gui.ToolBars;
import shapes.Draw;
import vec.VecReader;
import javax.swing.*;

import vec.VecWriter;
import java.awt.*;
import java.awt.event.*;

/**
 * The main JFame of the application, where components and listeners are added
 */
public class BuildApp extends JFrame {

    /**
     * Static variables of the Application
     */
    public static CanvasPanel CanvasPanel;
    public static Draw drawCanvas;
    public static VecWriter vecWriter = new VecWriter();
    public static String vecFilePath = null;
    public static String currentShape;
    public static Color penColor = Color.black;
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
        currentShape = "Plot";
        loadNewCanvas();
    }

    /**
     * Method for loading to current canvas
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
        status.addStatusBarListener(new StatusBarListener()); // add listener for status bar
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new MyDispatcher());
        CanvasPanel.add(drawCanvas);
        CanvasPanel.setLayout(new FlowLayout());
        this.add(CanvasPanel, BorderLayout.CENTER);
        this.setBackground(Color.WHITE);
        this.setVisible(true);
    }

    /**
     * Inner class for listening for key commands
     */
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

