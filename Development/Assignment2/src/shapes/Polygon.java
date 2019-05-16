package shapes;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;

public class Polygon extends Shape {
    private double [] x;
    private double [] y;
    private ArrayList<Line> lines = new ArrayList<>();

    public Polygon(Color penColor, Color fillColor, double [] x, double [] y){
        super(penColor, fillColor);
        this.x = x;
        this.y = y;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(penColor);

        Path2D path = new Path2D.Double();
        path.moveTo(x[0], y[0]);

        for(int i = 1; i < x.length; ++i){
            path.lineTo(x[i], y[i]);

            System.out.println(x[i] + ":" +y[i]);
        }
        path.closePath();
        g2.draw(path);

//        for(int i = 0; i < x.length - 1; i++){
//            g2.draw(new Line2D.Double(x[i], y[i], x[i+1], y[i+1]));
//        }

    }

    public ArrayList<Line> getLines(){
        return lines;
    }

    @Override
    public double[] getCoordinates() {
        return new double[0];
    }
}
