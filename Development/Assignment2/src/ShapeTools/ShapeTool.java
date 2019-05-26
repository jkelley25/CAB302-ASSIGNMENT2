package ShapeTools;

//import java.io.Serializable;

import Tools.Tool;

import java.awt.*;

public abstract class ShapeTool extends Tool {

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

}
