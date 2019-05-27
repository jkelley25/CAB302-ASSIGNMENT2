package PaintingTools;

import ShapeTools.ShapeComponent;

import java.awt.*;

public class LineComponent extends ShapeComponent {
    public LineComponent(Color LineColour, Point startDragPoint, Point endDragPoint){
        super(LineColour, startDragPoint, endDragPoint);
    }

    public void update(Graphics LineDraw){
        Graphics2D lineDraw = (Graphics2D)LineDraw;
        lineDraw.setPaint(this.paintColour);
        lineDraw.drawLine(this.startDragPoint.x, this.startDragPoint.y, this.endDragPoint.x, this.endDragPoint.y);

    }
}
