package gui;

import vec.VecIO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.undo.UndoManager;



public class BasicUI extends JFrame{
    /**
     * Generates the top level of the basic User Interface
     */

    /* Constructor for BasicUI */
    public BasicUI(){
        //
        // this.addMouseListener(this);
    }

    private static void createGUI() {
        // Set aesthetic of JFrame to create Native Feel
        JFrame.setDefaultLookAndFeelDecorated(true);

        // Create and set up the window
        JFrame frame = new JFrame("VDT: Vector Design Tool");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Call Menu Bar function which generates a JMenuBar
        JMenuBar MenuBar = new JMenuBar();
        menuBar(MenuBar);

        // Create JPanel for Canvas


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

        // Add Action Listener to Menu Items
        New.addActionListener(HandleMenuItems);
        Open.addActionListener(HandleMenuItems);
        Save.addActionListener(HandleMenuItems);
        SaveAs.addActionListener(HandleMenuItems);
        Import.addActionListener(HandleMenuItems);
        Export.addActionListener(HandleMenuItems);
        Quit.addActionListener(HandleMenuItems);

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


    // Class for Handling Menu Item Events (i.e. When User Selects a menu list item)
    private class HandleMenuItems implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event){
            // If user selects 'Quit' exit the program
            if(event.getSource() == Quit){
                dispose(); // Terminate all components in User Interface
                System.exit(0); // Exit the Application
            }

            // If user selects 'new' wipe the canvas and wipe any loaded data
            if(event.getSource() == New){
                FramesAndPanels.createPanel(Color.WHITE);
                /* Make sure that when 'new' is called any new canvas when saved
                does not include previously opened file data - write test!
                */

                /*
                * Example of Method Name
                * VecIO.clearVECTORS();
                */
            }

            if(event.getSource() == Save){
                // VecIO.save();
                /* Need to implement save functionality */
            }

            if(event.getSource() == SaveAs){
                // VecIO.saveAs();
                /* Need to implement Save As functionality */
            }

            if(event.getSource() == Import){
                // Call Function to read new file, load vector data and close file
                VecIO.readfile();

                // Two functions below need implementing:
                // VecIO.loadVECTORS(); 
                // VecIO.closeFile();
            }

            if(event.getSource() == Export){
                // VecIO.writefile();
                /* 
                * Need to add functionality for writing a .vec file from the canvas
                */
            }


        }
    }

    /* Undo Function --> add to Edit Menu --> Wire Up to Ctrl-Z key Event */
    // private static void doUndo(){
    //     UndoManager Undo = new UndoManager();

    // }


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
        // Add Instructions to help menu
        JMenuItem Instructions = new JMenuItem("How To");
        addMenuItem(Instructions, Help);
        // Add Help Menu to Menu Bar
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
