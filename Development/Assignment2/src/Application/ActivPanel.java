package Application;

import PaintingTools.PenTool;
import Tools.*;
import PaintingTools.*;
import ShapeTools.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ActivPanel extends JPanel implements Runnable {

    public int Frame;
    public ArrayList Components;
    protected boolean DrawingState = false;
    protected boolean mousePressed = false;
    protected boolean mouseClicked = false;
    public Tools currentTool;
    public Tool tool;
    protected Color currentColour;
    protected Thread drawThread;

    public ActivPanel(){
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(1024, 768));
        Application.MouseMonitor Mouse = new Application.MouseMonitor();
        this.addMouseListener(Mouse);
        this.Components = new ArrayList();
        //this.allTools.add(new )
        this.currentColour = Color.BLACK;
        this.currentTool = Tools.LINE;
        this.tool = new LineTool(this.currentColour);
        this.Frame = -1;
    }

    public void initThread(){
        this.drawThread = new Thread(this);
        this.drawThread.start();
    }

    public void paintComponent(Graphics Update) {
        super.paintComponent(Update);
        // Paint all components saved! -- instead reference VEC files and repaint all shapes!
        for (int i = 0; i < this.Components.size(); i++) {
            // Components.get(i).repaint();
        }
    }

    public void TempShapeComponent(Color Outline, Point Start, Point End) {
        switch (this.currentTool) {
            case RECTANGLE:
                this.Components.add(new RectangleComponent(Outline, Start, End));
            break;

            case LINE:
                this.Components.add(new LineComponent(Outline, Start, End));
        }
    }

    public void setCurrentColour(Color Outline) {
        this.currentColour = Outline;
    }

    public Color getCurrentColour() {
        return this.currentColour;
    }

    public void setCurrentTool(Tools tool) {
        this.currentTool = tool;
        if (this.drawThread == null) {
            this.initThread();
        }
    }

    public boolean getMousePressed(){
            return this.mousePressed;
        }


    public boolean checkDrawing() {
        return this.DrawingState;
    }


    @Override
    public void run() {
        while (true) {
            // Check if the mouse is on the canvas
            if (this.getMousePosition() != null) {
                // check that mouse is pressed
                if (getMousePressed() == false) {
                    this.tool.setStartPoint(null);
                    this.tool.setDrawing(false);
                }
                // if mouse is pressed
                else {
                    // check if currently drawing onto canvas
                    if (checkDrawing()) {
                        if (getMousePosition() != this.tool.getStartPoint()) {
                            this.tool.setEndPoint(tool.getStartPoint());
                            if (getMousePosition() != null) {
                                tool.setStartPoint(new Point(this.getMousePosition()));
                            }
                            if (this.currentTool == Tools.PEN) {
                                this.Components.add(new PenComponent(this.tool.getEndPoint(), this.tool.getStartPoint(), this.getCurrentColour()))
                            }
                            this.repaint();
                        } else {
                            this.tool.setDrawing(true);
                            if (getMousePosition() != this.tool.getStartPoint()) {
                                this.tool.setEndPoint(tool.getStartPoint());
                                if (getMousePosition() != null) {
                                    tool.setStartPoint(new Point(this.getMousePosition()));
                                }
                                if (this.currentTool == Tools.LINE) {
                                    this.Components.add(new LineComponent(this.tool.getEndPoint(), this.tool.getStartPoint(), this.getCurrentColour()))
                                }
                                this.repaint();

                            }
                        }
                    }
                        /*
                        Logic needs to be rethought although basic functioanlity shown

                         */
                    if (this.currentTool == Tools.LINE) {
                        if (this.getMousePosition() != null) {
                            if (this.tool.getStartPoint() != this.getMousePosition()) {

                                // this.tool.setEndDragPoint(this.getMousePosition());
                                // this.tool.set
                                this.TempShapeComponent(this.currentColour, this.tool.getStartDragPoint(), this.tool.getStartPoint());


                            }
                        }
                        this.Components.add(new LineComponent(this.tool.getEndPoint(), this.tool.getStartPoint(), this.currentColour));
                    }
                }

            }
        }
    }
}


