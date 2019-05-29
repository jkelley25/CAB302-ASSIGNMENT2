package Application;

import GUI.CanvasPanel;
import gui.MenuBars;
import gui.ToolBars;
import shapes.Draw;
import shapes.Polygon;
import shapes.Rectangle;
import vec.VecReader;
import javax.swing.*;
import shapes.*;

import java.awt.*;
import java.awt.event.*;

import static gui.MenuBars.*;

public class BuildApp extends JFrame {

    private static ToolBars ToolBar;
    public static CanvasPanel CanvasPanel;
    private static MenuBars MenuBars = new MenuBars();
    public static Draw drawCanvas;
    private static VecReader vec;
    private static String vecFilePath = null;

    //STATES
    private static String currentShape;
    private static Color penColor;
    private static Color fillColor;
    private static Color selectedColor = null;
    private static boolean load = false;
    private static boolean save = false;

    public BuildApp() {
        this.setSize(1024, 768);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("VDT: Vector Design Tool");

        drawCanvas = new Draw();
        CanvasPanel = new CanvasPanel();
        ToolBar = new ToolBars();
        ToolBar.addToolBarListener(new ToolBarListener()); // add listeners to toolbar buttons

        this.add(ToolBar, BorderLayout.WEST);
        this.add(MenuBars, BorderLayout.NORTH);
        //this.add(new JScroll Pane(CanvasPanel), BorderLayout.CENTER);

        if (vecFilePath != null) {
            vec = new VecReader(vecFilePath);
            drawCanvas = vec.getDrawCommands();
        }

        // Adding listeners to components
        MenuBars.addFileMenuListner(new fileMenuListener()); // add listener to menu
        drawCanvas.addMouseListener(new CanvasPanelListener()); // add listener to canvas
        this.addKeyListener(new keyListener());
        drawCanvas.addKeyListener(new keyListener());
        this.setFocusable(true);
        //drawCanvas.addPropertyChangeListener(new propertyListener());
        drawCanvas.setFocusable(true);

        CanvasPanel.add(drawCanvas);
        CanvasPanel.setLayout(new FlowLayout());

        this.add(CanvasPanel, BorderLayout.CENTER);
        this.setBackground(Color.WHITE);
        //this.pack();
        this.setVisible(true);
    }

    // Class for listening for file
    private static class fileMenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == Quit) {
                System.exit(0);
            }

            if (e.getSource() == Open) {
                JFileChooser fc = new JFileChooser();
                fc.setCurrentDirectory(new java.io.File("."));
                fc.setDialogTitle("Open a vec file");
                if (fc.showOpenDialog(Open) == JFileChooser.APPROVE_OPTION) {
                    vecFilePath = fc.getSelectedFile().getAbsolutePath();
                    BuildApp build = new BuildApp();
                }
                System.out.println(vecFilePath);
            }
        }
    }

    // Inner class for listening for tool bar
    public static class ToolBarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == ToolBar.penButton) {
                currentShape = "Pen";
            }

            if (e.getSource() == ToolBar.fillButton) {
                fillColor = JColorChooser.showDialog(null, "Pick fill color", penColor);
                if (fillColor == null) {
                    fillColor = null;
                }
            }

            if (e.getSource() == ToolBar.lineButton) {
                currentShape = "Line";
            }

            if (e.getSource() == ToolBar.squareButton) {
                currentShape = "Rectangle";
            }

            if (e.getSource() == ToolBar.polygonButton) {
                currentShape = "Polygon";
            }

            if (e.getSource() == ToolBar.ellipseButton) {
                currentShape = "Ellipse";
            }

            if (e.getSource() == ToolBar.colourButton) {
                penColor = JColorChooser.showDialog(null, "Pick pen color", penColor);
                if (penColor == null) {
                    penColor = Color.black;
                }
            }
        }
    }

    // Inner class for listening for key presses
    public static class keyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {

        }
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.isControlDown()) {
                drawCanvas.removeCommand();
                drawCanvas.repaint();
                System.out.println("Hello");
            }
        }
        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    // Inner class for listening for drawing commands on canvas
    public static class CanvasPanelListener implements
            MouseListener, MouseMotionListener, Runnable {

        private double x1;
        private double y1;
        private double x2;
        private double y2;
        private int numclicks = 0;
        Thread t;
        Point m = new Point();
        private Line line;
        private Rectangle rectangle;
        private Ellipse ellipse;
        private Polygon poly;

        public void mouseClicked(MouseEvent e) {
            System.out.println(e);

            if (currentShape.equals("Polygon")) {
                ++numclicks;
                // create empty polygon on first click
                if (numclicks == 1) {
                    poly = new Polygon(penColor, fillColor);
                    poly.addLines((double) e.getX(), (double) e.getY());
                }

                if (numclicks > 1) {
                    poly.addLines((double) e.getX(), (double) e.getY());
                    drawCanvas.addCommand(poly);
                    drawCanvas.repaint();
                }
                // check for right click for closing polygon
                if (e.getButton() == MouseEvent.BUTTON3) {
                    poly.closePolygon();
                    numclicks = 0; // reset polygon
                }
            }
        }

        public void mousePressed(MouseEvent e) {
            System.out.println(e);
            // get initial position of mouse when pressed
            x1 = e.getX();
            y1 = e.getY();

            // Check which shape is drawing
            if(currentShape.equals("Line")){
                line = new Line(Color.lightGray,null, x1,y1);
                drawCanvas.addCommand(line);
            }

            if(currentShape.equals("Rectangle")){
                rectangle = new Rectangle(Color.lightGray, null, x1,y1);
                drawCanvas.addCommand(rectangle);
            }

            if(currentShape.equals("Ellipse")){
                ellipse = new Ellipse(Color.lightGray, null, x1, y1);
                drawCanvas.addCommand(ellipse);
            }
            // start preview thread
            t = new Thread(this);
            t.start();
        }

        public void mouseReleased(MouseEvent e) {
            t.stop();
            System.out.println(e);
            x2 = e.getX();
            y2 = e.getY();

            if (currentShape.equals("Line")) {
                line.setPenColor(penColor);
                drawCanvas.repaint();
            }

            if(currentShape.equals("Rectangle")){
                rectangle.setPenColor(penColor);
                rectangle.setFillColor(fillColor);
                drawCanvas.repaint();
            }

            if(currentShape.equals("Ellipse")){
                ellipse.setPenColor(penColor);
                drawCanvas.repaint();
            }
        }

        public void mouseEntered(MouseEvent e) {
            //System.out.println(e);
        }

        public void mouseExited(MouseEvent e) {
            //System.out.println(e);
        }

        public void mouseDragged(MouseEvent e) {
            System.out.println(e);
        }

        public void mouseMoved(MouseEvent e) {
            System.out.println(e);
        }

        @Override
        public void run() {
            while(true)
            {
                m = drawCanvas.getMousePosition();
                if(currentShape.equals("Line")){
                    line.setEndPoint(m.getX(),m.getY());
                }

                if(currentShape.equals("Rectangle")){
                    if (m.getY() < y1) {
                        rectangle.setStartPoint(m.getX(), m.getY());
                        rectangle.setEndPoint(x1, y1);
                    }
                    if(m.getY() < y1 && m.getX() > x1){
                        System.out.println("Test");

                        // if drawing from bottom left to top right
                        // BUG not fixed                                      /// BUG ///
                        //double width  = m.getX() - x1;
                        rectangle.setEndPoint(m.getX(), y1);
                        rectangle.setStartPoint(x1,m.getY());
                        //rectangle.setEndPoint(m.getX(), y1*m.getY());/// NEED TO CHANGE THE Y COORD OF ENDPOINT SHOULD BE THE HEIGHT
                    }
                    if(m.getX() > x1) {
                        rectangle.setEndPoint(m.getX(),m.getY());
                    }
                }

                if(currentShape.equals("Ellipse")){
                    if (m.getY() < y1) {
                        ellipse.setStartPoint(m.getX(), m.getY());
                        ellipse.setEndPoint(x1, y1);
                    }
                    if(m.getY() < y1 && m.getX() > x1){
                        System.out.println("Test");
                        // if drawing from bottom left to top right
                        // BUG not fixed                                      /// BUG ///
                        //double width  = m.getX() - x1;
                        ellipse.setEndPoint(m.getX(), y1 + m.getY());
                        ellipse.setStartPoint(x1,m.getY());
                        //rectangle.setEndPoint(m.getX(), y1*m.getY());/// NEED TO CHANGE THE Y COORD OF ENDPOINT SHOULD BE THE HEIGHT
                    }
                    if(m.getX() > x1) {
                        ellipse.setEndPoint(m.getX(),m.getY());
                    }
                }

                drawCanvas.repaint();
                System.out.println(m.getX() + ": " + m.getY());
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

