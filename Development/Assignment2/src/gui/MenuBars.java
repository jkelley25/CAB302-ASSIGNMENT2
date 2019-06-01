package gui;

import GUI.CanvasPanel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;




/* TopLevelDemo.java requires no other files. */
public class MenuBars extends JMenuBar {
//    private static Draw draw = new Draw();
//    private static VecReader vec;

    // STATES
    private static String shape;
    public static boolean load = false;
    private static boolean save = true;
    public static String filePath;

    private CanvasPanel canvasPanel;

    private JMenu fileMenu;
    private JMenu editMenu;
    private JMenu helpMenu;

    public static JMenuItem New, Save, SaveAs, Load, Quit; // File Menu
    public static JMenuItem Undo, Redo, ClearCanvas;
    /**
     * Generates the top level of the basic User Interface
     */
    public MenuBars() {
        //JMenuBar MenuBar = new JMenuBar();
        this.setOpaque(true);
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(200, 20));

        fileMenu = fileMenu();
        editMenu = editMenu();
        helpMenu = helpMenu();

        this.add(fileMenu);
        this.add(editMenu);
        this.add(helpMenu);
    }


    //
    /* @param JMenuBar
    Adds File as a Menu to Menu Bar
     */
    private static JMenu fileMenu() {
        JMenu File = new JMenu("File");
        New = new JMenuItem("New");
        Save = new JMenuItem("Save");
        SaveAs = new JMenuItem("Save As");
        Load = new JMenuItem("Load");
        Quit = new JMenuItem("Quit");
        /* Add menu items to menu */
        addMenuItem(New, File);
        addMenuItem(Save, File);
        addMenuItem(SaveAs, File);
        addMenuItem(Load, File);
        File.addSeparator();
        addMenuItem(Quit, File);
        return File;
    }

    public void addFileMenuListner(ActionListener listener){
        New.addActionListener(listener);
        Save.addActionListener(listener);
        SaveAs.addActionListener(listener);
        Load.addActionListener(listener);
        Quit.addActionListener(listener);
    }

    public void addEditMenuListener(ActionListener listener){
        Undo.addActionListener(listener);
        Redo.addActionListener(listener);
        ClearCanvas.addActionListener(listener);
    }

    /* @param JMenuBar
    Adds Edit as a Menu to Menu Bar
     */
    private static JMenu editMenu() {
        JMenu Edit = new JMenu("Edit");
        // Add Edit Menu to Menu Bar
        // Create Menu Items
        Undo = new JMenuItem("Undo");
        Redo = new JMenuItem("Redo");
        ClearCanvas = new JMenuItem("Clear Canvas");
        // Add Menu Items to Menu
        addMenuItem(Undo, Edit);
        addMenuItem(Redo, Edit);
        addMenuItem(ClearCanvas, Edit);
        return Edit;
    }

    private static JMenu helpMenu() {
        /* Create Help Menu */
        JMenu Help = new JMenu("Help");
        /* Add Instructions to list */
        JMenuItem Instructions = new JMenuItem("How To");
        addMenuItem(Instructions, Help);
        return Help;
    }

    /*
    @param JMenuItem and JMenu
    Adds a Menu Item to Menu
     */
    private static void addMenuItem(JMenuItem MenuItem, JMenu Menu) {
        Menu.add(MenuItem);
    }
}

