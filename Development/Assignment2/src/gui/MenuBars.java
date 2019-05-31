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

    public static JMenuItem New, Open, Save, SaveAs, Import, Export, Quit; // File Menu
    public static JMenuItem Undo, Redo, UndoHistory, ClearCanvas;
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
        Open = new JMenuItem("Open");
        Save = new JMenuItem("Save");
        SaveAs = new JMenuItem("Save As");
        Import = new JMenuItem("Import");
        Export = new JMenuItem("Export");
        Quit = new JMenuItem("Quit");


        /* Add menu items to menu */
        addMenuItem(New, File);
        addMenuItem(Open, File);
        addMenuItem(Save, File);
        addMenuItem(SaveAs, File);
        addMenuItem(Import, File);
        addMenuItem(Export, File);
        File.addSeparator();
        addMenuItem(Quit, File);

        return File;
    }

    public void addFileMenuListner(ActionListener listener){
        New.addActionListener(listener);
        Open.addActionListener(listener);
        Save.addActionListener(listener);
        SaveAs.addActionListener(listener);
        Import.addActionListener(listener);
        Export.addActionListener(listener);
        Quit.addActionListener(listener);
    }

    public void addEditMenuListener(ActionListener listener){
        Undo.addActionListener(listener);
        Redo.addActionListener(listener);
        UndoHistory.addActionListener(listener);
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
        UndoHistory = new JMenuItem("Undo History");
        ClearCanvas = new JMenuItem("Clear Canvas");
        // Add Menu Items to Menu
        addMenuItem(Undo, Edit);
        addMenuItem(Redo, Edit);
        addMenuItem(UndoHistory, Edit);
        addMenuItem(ClearCanvas, Edit);

        return Edit;
    }

    private static JMenu helpMenu() {
        /* Create Help Menu */
        JMenu Help = new JMenu("Help");
        /* Add Instructions to list */
        JMenuItem Instructions = new JMenuItem("How To");
        addMenuItem(Instructions, Help);
        /* Add Help Menu to Menu Bar */
        // MenuBar.add(Help);

        /* Call After Importing/New Function is created
        JWindow Page = new JWindow();
        --> Creates New Tab
         */
//        JToolBar ToolBar = new JToolBar();
//        JToggleButton Select = new JToggleButton();
//        JToolTip SelectTip = new JToolTip();
//        SelectTip.setTipText("Select");
//        Select.add(SelectTip);
//        ToolBar.add(Select);

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

//    public static void main(String[] args) {
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                createGUI();
//            }
//        });
//    }
//}
