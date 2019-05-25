package gui;

import shapes.Draw;
import shapes.Line;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Wiring extends JFrame implements ActionListener, Runnable {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;

    private JPanel pnlDisplay;
    private JPanel jPanel2;
    private JPanel pnlBtn;
    private JPanel jPanel4;
    private JPanel jPanel5;

    private JButton btnLoad;
    private JButton btnUnLoad;
    private JButton btnFind;
    private JButton btnSwitch;

    private JTextArea areDisplay;

    public Wiring(String title) throws HeadlessException {
        super(title);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Get event source
        Object src = e.getSource();
      //Consider the alternatives - not all active at once.
        if (src==btnLoad) {
            JButton btn = ((JButton) src);
            Draw draw = new Draw();
            Line line = new Line(Color.black,Color.lightGray, 10,10,50,50);
            draw.addCommand(line);

            pnlDisplay.add(draw);

        }
        if (src==btnUnLoad){
            JButton btn = (JButton) src;
            areDisplay.setText(btn.getText().trim());
        }
        if (src==btnSwitch){
            JOptionPane.showMessageDialog(this,"A Stupid Warning Message",
                    "Wiring Class: Warning", JOptionPane.WARNING_MESSAGE);
        }
        if (src==btnFind){
            JOptionPane.showMessageDialog(this,"An error message",
                    "Warning",JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void run() {
        createGUI();
    }

    // Method to construct GUI
    private void createGUI(){
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());



        jPanel2 = createPanel(Color.lightGray);
        getContentPane().add(jPanel2, BorderLayout.NORTH);

        pnlBtn = createPanel(Color.lightGray);
        getContentPane().add(pnlBtn, BorderLayout.SOUTH);

        jPanel4 = createPanel(Color.lightGray);
        getContentPane().add(jPanel4, BorderLayout.EAST);

        jPanel5 = createPanel(Color.lightGray);
        getContentPane().add(jPanel5, BorderLayout.WEST);

        btnLoad = createButton("Load");
        pnlBtn.add(btnLoad);

        btnUnLoad = createButton("Unload");
        pnlBtn.add(btnUnLoad);

        btnFind = createButton("Find");
        pnlBtn.add(btnFind);

        btnSwitch = createButton("Switch");
        pnlBtn.add(btnSwitch);

        areDisplay = createDisplay();


        pnlDisplay = createPanel(Color.WHITE);
        getContentPane().add(pnlDisplay, BorderLayout.CENTER);

        getContentPane().add(areDisplay);

        layoutButtonPanel();
        repaint();
        setVisible(true);
    }

    // Method for creating a panel with different colors
    private JPanel createPanel(Color c){
        JPanel jPanel = new JPanel();
        jPanel.setBackground(c);
        return jPanel;
    }

    //Layout manager
    private void layoutButtonPanel(){
        GridBagLayout layout = new GridBagLayout();
        pnlBtn.setLayout(layout);
        //add components to grid
        GridBagConstraints constraints = new GridBagConstraints();
        //Defaults
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 500;
        constraints.weighty = 500;

        addToPanel(pnlBtn, btnLoad,constraints,0,0,2,1);
        addToPanel(pnlBtn, btnUnLoad,constraints,2,0,2,1);
        addToPanel(pnlBtn, btnFind,constraints,0,2,2,1);
        addToPanel(pnlBtn, btnSwitch,constraints,2,2,2,1);
    }

    private JButton createButton(String str){
        JButton button = new JButton(str);
        button.addActionListener(this::actionPerformed);
        return button;
    }

    /**
     *
     * A convenience method to add a component to given grid bag
     * layout locations. Code due to Cay Horstmann
     *
     * @param c the component to add
     * @param constraints the grid bag constraints to use
     * @param x the x grid position
     * @param y the y grid position
     * @param w the grid width of the component
     * @param h the grid height of the component
     */
    private void addToPanel(JPanel jp,Component c, GridBagConstraints constraints,int x, int y, int w, int h) {
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = w;
        constraints.gridheight = h;
        jp.add(c, constraints);
    }

    private JTextArea createDisplay(){
        JTextArea display = new JTextArea();
        display.setEditable(false);
        display.setLineWrap(true);
        display.setFont(new Font("Arial",Font.BOLD,24));
        display.setBorder(BorderFactory.createEtchedBorder());
        return display;
    }


    public static void main(String [] args){
        SwingUtilities.invokeLater(new Wiring("Wiring GUIS"));
    }
}


