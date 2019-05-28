package Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MouseMonitor extends MouseAdapter implements Runnable {

    protected Thread drawThread;
    protected boolean mousePressed;
    protected boolean mouseClicked;

    public MouseMonitor(){
    }

    public void mousePressed(MouseEvent event){
        if(this.drawThread == null){
            this.startThread();
        }
        this.mousePressed = true;
    }

    public void mouseReleased(MouseEvent event){
        this.mousePressed = false;
    }

    public void mouseClicked(MouseEvent event){
        this.mouseClicked = false;
    }

    public void mouseExited(MouseEvent event){
        this.mousePressed = false;
    }

    @Override
    public void run(){
        /*
        Need to override again, when extending MouseMonitor Class
         */
    }
    private void startThread(){
        this.drawThread = new Thread(this);
        this.drawThread.start();
    }
}