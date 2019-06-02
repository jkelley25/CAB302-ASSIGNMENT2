package gui;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/**
 * Class that contains the main menu bar, extends JMenuBar
 */

/* TopLevelDemo.java requires no other files. */
public class MenuBars extends JMenuBar {
    public static JMenuItem New, Save, SaveAs, Load, Quit; // File Menu
    public static JMenuItem Undo, Redo, ClearCanvas; // Edit menu
    /**
     * Generates the top level of the basic User Interface
     */
    public MenuBars() {
        //JMenuBar MenuBar = new JMenuBar();
        this.setOpaque(true);
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(200, 20));

        JMenu fileMenu = fileMenu();
        JMenu editMenu = editMenu();
        JMenu helpMenu = helpMenu();

        this.add(fileMenu);
        this.add(editMenu);
        this.add(helpMenu);
    }

    /**
     * Add an actionlistener to each menu item
     * @param listener the action listener object
     */
    public void addFileMenuListner(ActionListener listener){
        New.addActionListener(listener);
        Save.addActionListener(listener);
        SaveAs.addActionListener(listener);
        Load.addActionListener(listener);
        Quit.addActionListener(listener);
    }

    /**
     * Add an actionlistener to each edit menu item
     * @param listener the action listener
     */
    public void addEditMenuListener(ActionListener listener){
        Undo.addActionListener(listener);
        Redo.addActionListener(listener);
        ClearCanvas.addActionListener(listener);
    }

    /**
     * Adds File as a Menu to Menu Bar
     * @return returns the Jmenu file
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


    /**
     * Adds menu items to the edit menu
     * @return the Jmenu object
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

    /**
     * Adds menu items to the help menu
     * @return Jmenu object
     */
    private static JMenu helpMenu() {
        /* Create Help Menu */
        JMenu Help = new JMenu("Help");
        /* Add Instructions to list */
        JMenuItem Instructions = new JMenuItem("How To");
        addMenuItem(Instructions, Help);
        return Help;
    }

    /**
     * Add Jmenu to file menu
     * @param MenuItem menu item
     * @param Menu the main menu
     */
    private static void addMenuItem(JMenuItem MenuItem, JMenu Menu) {
        Menu.add(MenuItem);
    }
}

