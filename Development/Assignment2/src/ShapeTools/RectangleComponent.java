package ShapeTools;

import java.awt.*;

public class RectangleComponent extends ShapeComponent{
    public RectangleComponent(Color Outline, Point Start, Point End){
        super(Outline, Start, End);
    }

    public void repaintComponent(Graphics Up){
        Graphics2D repaint = (Graphics2D)Up;
        repaint.setPaint(this.paintColour);

        if(this.endDragPoint.x - this.startDragPoint.x < 0 && this.endDragPoint.y - this.startDragPoint.y < 0){
            repaint.drawRect(this.endDragPoint.x, this.endDragPoint.y, this.startDragPoint.x, this.startDragPoint.y);
        }

        if (this.endDragPoint.x - this.startDragPoint.x > 0 && this.endDragPoint.y - this.startDragPoint.y < 0) {
            repaint.drawRect(this.startDragPoint.x, this.endDragPoint.y, Math.abs(this.endDragPoint.x - this.startDragPoint.x), Math.abs(this.endDragPoint.y - this.startDragPoint.y));
        }

        if (this.endDragPoint.x - this.startDragPoint.x > 0 && this.endDragPoint.y - this.startDragPoint.y > 0) {
            repaint.drawRect(this.startDragPoint.x, this.startDragPoint.y, Math.abs(this.endDragPoint.x - this.startDragPoint.x), Math.abs(this.endDragPoint.y - this.startDragPoint.y));
        }

        if (this.endDragPoint.x - this.startDragPoint.x < 0 && this.endDragPoint.y - this.startDragPoint.y > 0) {
            repaint.drawRect(this.endDragPoint.x, this.startDragPoint.y, Math.abs(this.endDragPoint.x - this.startDragPoint.x), Math.abs(this.endDragPoint.y - this.startDragPoint.y));
        }
    }
}
