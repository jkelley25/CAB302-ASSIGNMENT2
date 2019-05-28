package Application;

import javax.swing.*;

public class Main {

    public static BuildApp App;

    public Main(){

    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BuildApp();
            }
        });
       // App = new BuildApp();
    }
}
