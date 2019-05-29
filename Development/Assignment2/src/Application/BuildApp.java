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
    private static Color fillColor = null;
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
                fillColor = penColor;
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
                penColor = JColorChooser.showDialog(null, "Pick color", penColor);
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
                drawCanvas.repaint();
            }

            if(currentShape.equals("Ellipse")){
                ellipse.setPenColor(penColor);
                drawCanvas.repaint();
            }
//
//            // Bug: can't draw from bottom left to top right rectangle
//            if (currentShape == "Rectangle") {
//                if (y2 < y1) {
//                    rect = new Rectangle(penColor, fillColor, x2, y2, x1, y1);
//                } else {
//                    rect = new Rectangle(penColor, fillColor, x1, y1, x2, y2);
//                }
//                drawCanvas.addCommand(rect);
//                drawCanvas.repaint();
//            }

            // Incomplete: uses rectangle outline to draw ellipse
//            if (currentShape.equals("Ellipse")) {
//                ellipse = new Ellipse(penColor, fillColor, x1, y1, x2, y2);
//                drawCanvas.addCommand(ellipse);
//                drawCanvas.repaint();
//            }
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
                    } else if(m.getY() < y1 && m.getX() > x1){
                        rectangle.setStartPoint(x1, y1 - m.getY());
                        rectangle.setEndPoint(m.getX(), m.getY());
                    }
                    else {
                        rectangle.setEndPoint(m.getX(),m.getY());
                    }
                }

                if(currentShape.equals("Ellipse")){
                    ellipse.setEndPoint(m.getX(), m.getY());
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

//        int mousePressedCount;
//        Point InitPoint;
//        Point PrevPoint;
//        Point CurrentPoint;
//        Point P1;
//
//        public void StartThread() {
//            thread = new Thread(this);
//            thread.start();
//        }
//
//        public boolean mousePressed(){
//
//            return mousePressed;
//
//        }

//        @Override
//        public void run() {
//            System.out.println("RUN");
//            if (mousePressed()) {
//                if (mousePressedCount < 1) {
//                    PrevPoint = P1.getLocation();
//                    InitPoint = P1.getLocation();
//                } else {
//                    if (CurrentPoint != PrevPoint) {
//                        CurrentPoint = P1.getLocation();
//                        drawCanvas.addCommand(new Line(Color.green, null, InitPoint.getX(), InitPoint.getY(), CurrentPoint.getX(), CurrentPoint.getY()));
//                        PrevPoint = CurrentPoint;
//                        mousePressedCount++;
//                        drawCanvas.repaint();
//                        System.out.println("TEST");
//                    }
//                }
//                try {
//                    Thread.sleep(10);
//
//                } catch (InterruptedException e1) {
//
//                }
//            } else {
//                thread.stop();
//            }
//        }
    }
}

