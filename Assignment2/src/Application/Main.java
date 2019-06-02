package Application;

import javax.swing.*;

/**
 * Main entry point of the application
 */
public class Main {

    private static BuildApp App;

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
                public void run() {
                App = new BuildApp();
            }
        });
    }
}
