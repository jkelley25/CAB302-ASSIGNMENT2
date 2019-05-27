package PaintingTools;

import java.awt.*;
import java.io.Serializable;

public abstract class PaintComponent implements Serializable {
    protected Point coords;
    protected Color paintColour;

    public PaintComponent(Point Coordinates,Color PaintingColour){
        this.coords = Coordinates;
        this.paintColour = PaintingColour;
    }



   // public PaintComponent(Color PaintingColour){
//        this.paintColour = PaintingColour;
//    }

//    public PaintComponent(){
//
//    }

    public Point getCoords(){
        return this.coords;
    }

    public Color getColour(){
        return this.paintColour;
    }

    public abstract void repaintComponent(Graphics Up);

}
