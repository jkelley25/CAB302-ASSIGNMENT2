package ShapeTools;

import PaintingTools.PaintComponent;

import java.awt.*;

public abstract class ShapeComponent extends PaintComponent {
    protected Point startDragPoint;
    protected Point endDragPoint;

    public ShapeComponent(Color OutlineColour, Point startDragPoint, Point endDragPoint) {
        super(OutlineColour);
        this.startDragPoint = startDragPoint;
        this.endDragPoint = endDragPoint;
    }

        public Point getStartDragPoint(){
            return this.startDragPoint;
        }

        public Point getEndDragPoint(){
            return this.endDragPoint;
        }

        public abstract void repaintComponent(Graphics Up);
    }
