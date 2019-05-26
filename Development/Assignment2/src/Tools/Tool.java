package Tools;


import java.awt.*;

/*
Abstract Class to create Painting Tools
 */

public class Tool {

    protected Point StartPoint;
    protected Color ToolColour;
    protected Point EndPoint;
    protected boolean Drawing;

    /*
    @param: Color to set the colour of the drawing tool
    Constructor to create tool - with specified colour
     */
    public Tool(Color toolColour){
        this.ToolColour = toolColour;
    }

    /*
    Constructor to create Tool using default colour - black
     */
    public Tool(){
        this.ToolColour = Color.BLACK;
    }

    /*
    Boolean method to check if currently drawing on Active Canvas
     */
    public boolean checkDrawing(){
        return this.Drawing;
    }

    public void setDrawing(boolean DrawingState){
        this.Drawing = DrawingState;
    }

    public void setStartPoint(Point MouseCoords){
        this.StartPoint = MouseCoords;
    }

    public Point getStartPoint(){
        return this.StartPoint;
    }

    public void setEndPoint(Point MouseCoords){
        this.EndPoint = MouseCoords;
    }

    public Color getColour(){
        return this.ToolColour;
    }

    public void setColour(Color newToolColour){
        this.ToolColour = newToolColour;
    }



}

