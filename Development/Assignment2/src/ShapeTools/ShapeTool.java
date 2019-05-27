package ShapeTools;

//import java.io.Serializable;

import Tools.Tool;

import java.awt.*;

public abstract class ShapeTool extends Tool {
    Point startDragPoint;
    Point endDragPoint;
    ShapeComponent Component;
    boolean Complete = false;
    /*
    Calls default Tool Constructor - with default Black Colour
     */
    public ShapeTool(){
        super();
    }
    /*
    @param: Outline Colour of Shape
    Constructor to create a shape tool that is outlined in a specified colour
     */
    public ShapeTool(Color ShapeOutlineColour){
        super(ShapeOutlineColour);
    }

    public void setColour(Color OutlineColour){
        this.ToolColour = OutlineColour;
    }

    public Point getStartDragPoint(){
        return this.startDragPoint;
    }

    public Point getEndDragPoint(){
        return this.endDragPoint;
    }

    public void setStartDragPoint(Point startDragPoint) {
        this.startDragPoint = startDragPoint;
    }

    public void setEndDragPoint(Point endDragPoint) {
        this.endDragPoint = endDragPoint;
    }

    public ShapeComponent getComponent(){
        return this.Component;
    }

}
