package PaintingTools;

import java.awt.*;

public class PenComponent extends PaintComponent{

    Point Pt;

            public PenComponent(Point currentPoint, Color penColour){
                super.(currentPoint, penColour);
            }

            public PenComponent(){

            }

            public void repaintComponent(Graphics g){
                Graphics2D g2 = (Graphics2D)g;
                g2.setPaint(this.paintColour);
                g2.drawLine(this.coords.x, this.coords.y, this.Pt.x, this.Pt.y);

            }
}
