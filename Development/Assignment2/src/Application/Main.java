package Application;

import javax.swing.*;

public class Main {

    public static BuildApp App;

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
                public void run() {
                App = new BuildApp();
            }
        });
    }
}
