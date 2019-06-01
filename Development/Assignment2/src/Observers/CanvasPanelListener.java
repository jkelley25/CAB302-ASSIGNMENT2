package Observers;

import shapes.*;
import shapes.Rectangle;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import static Application.BuildApp.*;

/**
 * Class for listening for mouse actions on the main canvas
 */
public class CanvasPanelListener implements
        MouseListener, MouseMotionListener, Runnable {

    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private int numclicks = 0;
    Thread t;
    Point m = new Point();
    private Plot plot;
    private Line line;
    private shapes.Rectangle rectangle;
    private Ellipse ellipse;
    private shapes.Polygon poly;

    public void mouseClicked(MouseEvent e) {
        System.out.println(e);
        if (currentShape.equals("Polygon")) {
            ++numclicks;
            createPolgon(e);
        }

        if(currentShape.equals("Plot")){
            try {
                plot = new Plot(penColor, null, e.getX(),e.getY());
                drawCanvas.addCommand(plot);
                drawCanvas.repaint();
            } catch (ShapeException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void mousePressed(MouseEvent e) {
        System.out.println(e);
        try {
            createShapes(e);
        } catch (ShapeException ex) {
            ex.printStackTrace();
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
        drawShapes();
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
    public void run() throws NullPointerException{
        while(true)
        {
            m = drawCanvas.getMousePosition();
            if(currentShape.equals("Line")){
                try {
                    line.setEndPoint(m.getX(),m.getY());
                } catch (ShapeException e) {
                    e.printStackTrace();
                }
            }

            if(currentShape.equals("Rectangle")){
                if (m.getY() < y1) {
                    try {
                        rectangle.setStartPoint(m.getX(), m.getY());
                    } catch (ShapeException e) {
                        e.printStackTrace();
                    }
                    try {
                        rectangle.setEndPoint(x1, y1);
                    } catch (ShapeException e) {
                        e.printStackTrace();
                    }
                }
                if(m.getY() < y1 && m.getX() > x1){
                    System.out.println("Test");

                    // if drawing from bottom left to top right
                    // BUG not fixed                                      /// BUG ///
                    //double width  = m.getX() - x1;
                    try {
                        rectangle.setEndPoint(m.getX(), y1);
                    } catch (ShapeException e) {
                        e.printStackTrace();
                    }
                    try {
                        rectangle.setStartPoint(x1,m.getY());
                    } catch (ShapeException e) {
                        e.printStackTrace();
                    }
                    //rectangle.setEndPoint(m.getX(), y1*m.getY());/// NEED TO CHANGE THE Y COORD OF ENDPOINT SHOULD BE THE HEIGHT
                }
                if(m.getX() > x1) {
                    try {
                        rectangle.setEndPoint(m.getX(),m.getY());
                    } catch (ShapeException e) {
                        e.printStackTrace();
                    }
                }
            }

            if(currentShape.equals("Ellipse")){
                if (m.getY() < y1) {
                    try {
                        ellipse.setStartPoint(m.getX(), m.getY());
                    } catch (ShapeException e) {
                        e.printStackTrace();
                    }
                    try {
                        ellipse.setEndPoint(x1, y1);
                    } catch (ShapeException e) {
                        e.printStackTrace();
                    }
                }
                if(m.getY() < y1 && m.getX() > x1){
                    System.out.println("Test");
                    // if drawing from bottom left to top right
                    // BUG not fixed                                      /// BUG ///
                    //double width  = m.getX() - x1;
                    try {
                        ellipse.setEndPoint(m.getX(), y1 + m.getY());
                    } catch (ShapeException e) {
                        e.printStackTrace();
                    }
                    try {
                        ellipse.setStartPoint(x1,m.getY());
                    } catch (ShapeException e) {
                        e.printStackTrace();
                    }
                    //rectangle.setEndPoint(m.getX(), y1*m.getY());/// NEED TO CHANGE THE Y COORD OF ENDPOINT SHOULD BE THE HEIGHT
                }
                if(m.getX() > x1) {
                    try {
                        ellipse.setEndPoint(m.getX(),m.getY());
                    } catch (ShapeException e) {
                        e.printStackTrace();
                    }
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

    // ------------------------------------------------- Helper Methods -------------------------------- \\
    private void createPolgon(MouseEvent e) {
        // create empty polygon on first left click
        if(e.getButton() == MouseEvent.BUTTON1){
            if (numclicks == 1) {
                try {
                    poly = new shapes.Polygon(penColor, fillColor);
                } catch (ShapeException ex) {
                    ex.printStackTrace();
                }
                try {
                    poly.addLines((double) e.getX(), (double) e.getY());
                } catch (ShapeException ex) {
                    ex.printStackTrace();
                }
            }

            if (numclicks > 1) {
                try {
                    poly.addLines((double) e.getX(), (double) e.getY());
                } catch (ShapeException ex) {
                    ex.printStackTrace();
                }
                drawCanvas.addCommand(poly);
                drawCanvas.repaint();
            }
        }

        // check for right click for closing polygon
        if (e.getButton() == MouseEvent.BUTTON3) {
            poly.closePolygon();
            numclicks = 0; // reset polygon
            vecWriter.addShapeToFile(poly);
        }
    }

    private void createShapes(MouseEvent e) throws ShapeException {
        // get initial position of mouse when pressed
        x1 = e.getX();
        y1 = e.getY();

        // Check which shape is drawing
        if(currentShape.equals("Line")){
            try {
                line = new Line(Color.lightGray,null, x1,y1);
            } catch (ShapeException ex) {
                ex.printStackTrace();
            }
            drawCanvas.addCommand(line);
        }

        if(currentShape.equals("Rectangle")){
            rectangle = new Rectangle(Color.lightGray, null, x1,y1);
            drawCanvas.addCommand(rectangle);
        }

        if(currentShape.equals("Ellipse")){
            try {
                ellipse = new Ellipse(Color.lightGray, null, x1, y1);
            } catch (ShapeException ex) {
                ex.printStackTrace();
            }
            drawCanvas.addCommand(ellipse);
            vecWriter.addShapeToFile(ellipse);
        }
    }

    private void drawShapes() {
        if (currentShape.equals("Line")) {
            try {
                line.setPenColor(penColor);
            } catch (ShapeException e) {
                e.printStackTrace();
            }
            drawCanvas.repaint();
            vecWriter.addShapeToFile(line);
        }

        if(currentShape.equals("Rectangle")){
            try {
                rectangle.setPenColor(penColor);
            } catch (ShapeException e) {
                e.printStackTrace();
            }
            try {
                rectangle.setFillColor(fillColor);
            } catch (ShapeException e) {
                e.printStackTrace();
            }
            drawCanvas.repaint();
            vecWriter.addShapeToFile(rectangle);
        }

        if(currentShape.equals("Ellipse")){
            try {
                ellipse.setPenColor(penColor);
            } catch (ShapeException e) {
                e.printStackTrace();
            }
            drawCanvas.repaint();
            vecWriter.addShapeToFile(ellipse);
        }
    }
}
