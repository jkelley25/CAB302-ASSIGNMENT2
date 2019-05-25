package gui;

import shapes.Draw;
import shapes.Ellipse;
import shapes.Line;
import shapes.Polygon;
import shapes.Rectangle;
import vec.VecIO;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.undo.UndoManager;

/* TopLevelDemo.java requires no other files. */
public class BasicUI {
    private static Draw draw = new Draw();
    private static VecIO vec;

    // STATES
    private static String shape;
    private static boolean load = true;
    private static boolean save = true;


    /**
     * Generates the top level of the basic User Interface
     */
    private static void createGUI() {
        // Set aesthetic of JFrame to create Native Feel
        JFrame.setDefaultLookAndFeelDecorated(true);

        // Create and set up the window
        JFrame frame = new JFrame("VDT: Vector Design Tool");
        frame.setSize(1000,700);
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Call Menu Bar function which generates a JMenuBar
        JMenuBar MenuBar = new JMenuBar();
        menuBar(MenuBar);


        // Create a yellow label to put in the content pane.
        JLabel Label = new JLabel();
        Label.setOpaque(true);
        Label.setBackground(Color.LIGHT_GRAY);
        Label.setPreferredSize(new Dimension(200, 180));

        // Set the menu bar and add the label to the content pane
        frame.setJMenuBar(MenuBar);

        // Set the shape using this variable for now until tools are created
        shape = "Polygon";

        vec = new VecIO("vecfiles/example2.vec");

        //Check if file is loaded
        if(load){
            draw = vec.getDrawCommands();
        }

        if(save){

        }

        //add draw canvas panel
        frame.add(draw);
        draw.addMouseListener(new CanvasPanelListener());

        // Display the window.
        frame.setVisible(true);
    }


    public static class CanvasPanelListener implements ActionListener, ChangeListener,
            MouseListener, MouseMotionListener {

        private double x1;
        private double y1;
        private double x2;
        private double y2;
        Polygon poly;
        private int numclicks = 0;



        public void actionPerformed(ActionEvent e) {
            System.out.println(e);
        }

        public void stateChanged(ChangeEvent e) {
            System.out.println(e);
        }

        public void mouseClicked(MouseEvent e) {
            System.out.println(e);

            if (shape.equals("Polygon")){
                ++numclicks;
                // create empty polygon on first click
                if(numclicks == 1){
                    poly = new Polygon(Color.black, null);
                    poly.addLines((double) e.getX(), (double) e.getY());
                }

                if(numclicks > 1){
                    poly.addLines((double) e.getX(), (double) e.getY());
                    draw.addCommand(poly);
                    draw.repaint();
                }

                // check for right click for closing polygon
                if(e.getButton() == MouseEvent.BUTTON3){
                    poly.closePolygon();
                    numclicks = 0; // reset numclicks
                }
            }

        }

        public void mousePressed(MouseEvent e) {
            System.out.println(e);
            x1 = e.getX();
            y1 = e.getY();
        }

        public void mouseReleased(MouseEvent e) {
            System.out.println(e);
            x2 = e.getX();
            y2 = e.getY();

            // Shapes to be created
            Line line;
            Rectangle rect;
            Ellipse ellipse;

            if(shape == "Line"){
                line = new Line(Color.black, Color.lightGray, x1,y1,x2,y2);
                // Draw line
                draw.addCommand(line);
                draw.repaint();
                try {
                    vec.writeShape(line);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            // Bug: can't draw from bottom left to top right rectangle
            if(shape == "Rectangle"){
                if(y2 < y1){
                    rect = new Rectangle(Color.black, null, x2,y2,x1,y1);
                } else {
                    rect = new Rectangle(Color.black, null, x1,y1,x2,y2);
                }
                draw.addCommand(rect);
                draw.repaint();
            }

            // Incomplete: uses rectangle outline to draw ellipse
            if(shape == "Ellipse"){
                ellipse = new Ellipse(Color.black, Color.CYAN, x1,y1,x2,y2);
                draw.addCommand(ellipse);
                draw.repaint();
            }
        }

        public void mouseEntered(MouseEvent e) {
            System.out.println(e);
        }

        public void mouseExited(MouseEvent e) {
            System.out.println(e);
        }

        public void mouseDragged(MouseEvent e) {
            System.out.println(e);
        }

        public void mouseMoved(MouseEvent e) {
            System.out.println(e);
        }
    }



    private static void menuBar(JMenuBar MenuBar) {
        MenuBar.setOpaque(true);
        MenuBar.setBackground(Color.WHITE);
        MenuBar.setPreferredSize(new Dimension(200, 20));

        fileMenu(MenuBar);
        editMenu(MenuBar);
        helpMenu(MenuBar);

    }

    /* @param JMenuBar
    Adds File as a Menu to Menu Bar
     */
    private static void fileMenu(JMenuBar MenuBar) {
        JMenu File = new JMenu("File");
        MenuBar.add(File);

        JMenuItem New = new JMenuItem("New");
        JMenuItem Open = new JMenuItem("Open");
        JMenuItem Save = new JMenuItem("Save");
        JMenuItem SaveAs = new JMenuItem("Save As");
        JMenuItem Import = new JMenuItem("Import");
        JMenuItem Export = new JMenuItem("Export");
        JMenuItem Quit = new JMenuItem("Quit");

        /* Add Item Handler to newly generated JMenuItem's */
        AddMenuListHandler(New, Open, Save, SaveAs, Import, Export, Quit);

        /* Add menu items to menu */
        addMenuItem(New, File);
        addMenuItem(Open, File);
        addMenuItem(Save, File);
        addMenuItem(SaveAs, File);
        addMenuItem(Import, File);
        addMenuItem(Export, File);
        File.addSeparator();
        addMenuItem(Quit, File);
    }

    /*
    @param JMenuItem(s)
    Adds Item Handler Action Listener to JMenuItem */
    private static void AddMenuListHandler(JMenuItem Item, JMenuItem...Items) {
        // ActionEvent CtrlZ = new ActionEvent(Undo, 1, "Ctrl-Z Undo" );
        Item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doUndo();
                /* Add Controller.Actions.doUndo(); here
                Controller is Main Loop, Actions is a class that is called
                by = new Actions() in Controller, doUndo() is called in Actions
                 */
            }
        });
    }

    /* Undo Function --> add to Edit Menu --> Wire Up to Ctrl-Z key Event */
    private static void doUndo(){
        final UndoManager Undo = new UndoManager();

    }


    /* @param JMenuBar
    Adds Edit as a Menu to Menu Bar
     */
    private static void editMenu(JMenuBar MenuBar) {
        JMenu Edit = new JMenu("Edit");
        // Add Edit Menu to Menu Bar
        MenuBar.add(Edit);
        // Create Menu Items
        JMenuItem Undo = new JMenuItem("Undo");
        JMenuItem Redo = new JMenuItem("Redo");
        // Add Menu Items to Menu
        addMenuItem(Undo, Edit);
        addMenuItem(Redo, Edit);
    }

    private static void helpMenu(JMenuBar MenuBar) {
        /* Create Help Menu */
        JMenu Help = new JMenu("Help");
        /* Add Instructions to list */
        JMenuItem Instructions = new JMenuItem("How To");
        addMenuItem(Instructions, Help);
        /* Add Help Menu to Menu Bar */
        MenuBar.add(Help);

        /* Call After Importing/New Function is created
        JWindow Page = new JWindow();
        --> Creates New Tab
         */
        JToolBar ToolBar = new JToolBar();
        JToggleButton Select = new JToggleButton();
        JToolTip SelectTip = new JToolTip();
        SelectTip.setTipText("Select");
        Select.add(SelectTip);
        ToolBar.add(Select);
    }

    /*
    @param JMenuItem and JMenu
    Adds a Menu Item to Menu
     */
    private static void addMenuItem(JMenuItem MenuItem, JMenu Menu) {
        Menu.add(MenuItem);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createGUI();
            }
        });
    }
}
