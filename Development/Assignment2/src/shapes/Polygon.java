package shapes;

import java.awt.*;
import java.awt.geom.Path2D;
import java.util.ArrayList;

public class Polygon extends AbstractShape {
    private double [] x;
    private double [] y;

    // coords from drawing
    private ArrayList<Double> xdr = new ArrayList<>();
    private ArrayList<Double> ydr = new ArrayList<>();
    private boolean close = false;


    // Creates a Polygon with no coordinates
    public Polygon(Color penColor, Color fillColor){
        super(penColor,fillColor);
    }

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

        // check if coords from drawing isn't empty
        if(!xdr.isEmpty()){
            x = new double[xdr.size() ];
            y = new double[ydr.size() ];

            // populate coords array
            for (int i = 0; i < x.length; i++){
                x[i] = xdr.get(i);
                y[i] = ydr.get(i);
            }
        }

        path.moveTo(x[0], y[0]); // move to initial point
        // connect each line
        for(int i = 1; i < x.length; ++i){
            path.lineTo(x[i], y[i]);
        }

        if(xdr.isEmpty()){
            path.closePath(); // close polygon
        }

        //check if closed on drawing
        if(close){
            path.closePath(); // close polygon
        }

        g2.draw(path);

        //Check if fill is on
        if(fillColor != null){
            g2.setColor(fillColor);
            g2.fill(path);
        }
    }

    public void addLines(double x1, double y1){
        xdr.add(x1);
        ydr.add(y1);
    }

    public void closePolygon(){
        close = true;
    }

    @Override
    public double[] getCoordinates() {
        return new double[0];
    }


}
