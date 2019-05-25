@@ -0,0 +1,161 @@
package gui;

import gui.MenuKeyListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.undo.UndoManager;


/* TopLevelDemo.java requires no other files. */
public class TopLevelUI{
    /**
     * Generates the top level of the basic User Interface
     */
    private static void createGUI() {
        // Set aesthetic of JFrame to create Native Feel
        JFrame.setDefaultLookAndFeelDecorated(true);

        // Create and set up the window
        JFrame frame = new JFrame("VDT: Vector Design Tool");
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
        frame.getContentPane().add(Label, BorderLayout.CENTER);

        // Display the window.
        frame.pack();
        frame.setVisible(true);
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