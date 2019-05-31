package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class test extends JPanel implements ActionListener,ItemListener
{
    Button[] colors;
    Checkbox foreground,background;
    TextArea workarea;
    CheckboxGroup cbg;
    Panel buttonpanel,checkpanel,palettepanel;
    String colour;
    public test(){
        init();
    }

    public void init()
    {
        buttonpanel=new Panel();
        buttonpanel.setLayout(new GridLayout(3,3));
        colors=new Button[9];
        colors[0]=new Button("RED");
        colors[1]=new Button("GREEN");
        colors[2]=new Button("BLUE");
        colors[3]=new Button("CYAN");
        colors[4]=new Button("ORANGE");
        colors[5]=new Button("WHITE");
        colors[6]=new Button("BLACK");
        colors[7]=new Button("YELLOW");
        colors[8]=new Button("PINK");
        for(int i=0;i<9;i++)
        {
            colors[i].addActionListener(this);
            buttonpanel.add(colors[i]);
        }
        checkpanel=new Panel();
        checkpanel.setLayout(new FlowLayout());
        cbg=new CheckboxGroup();
        foreground=new Checkbox("ForeGround",cbg,true);
        background=new Checkbox("BackGround",cbg,false);
        foreground.addItemListener(this);
        background.addItemListener(this);
        checkpanel.add(foreground);
        checkpanel.add(background);
        workarea=new TextArea(8,40);
        workarea.setFont(new Font("Garamond",Font.BOLD,20));
        palettepanel=new Panel();
        palettepanel.setLayout(new BorderLayout());
        palettepanel.add(workarea,BorderLayout.CENTER);
        palettepanel.add(checkpanel,BorderLayout.EAST);
        palettepanel.add(buttonpanel,BorderLayout.SOUTH);
        add(palettepanel);
    }
    public void itemStateChanged(ItemEvent ie)
    {
    }
    public void actionPerformed(ActionEvent ae)
    {
        colour=ae.getActionCommand();
        if(foreground.getState()==true)
            workarea.setForeground(getColour());
        if(background.getState()==true)
            workarea.setBackground(getColour());
    }
    public Color getColour()
    {
        Color mycolor=null;
        if(colour.equals("RED"))
            mycolor=Color.red;
        if(colour.equals("GREEN"))
            mycolor=Color.green;
        if(colour.equals("BLUE"))
            mycolor=Color.blue;
        if(colour.equals("CYAN"))
            mycolor=Color.cyan;
        if(colour.equals("ORANGE"))
            mycolor=Color.orange;
        if(colour.equals("WHITE"))
            mycolor=Color.white;
        if(colour.equals("BLACK"))
            mycolor=Color.black;
        if(colour.equals("YELLOW"))
            mycolor=Color.yellow;
        if(colour.equals("PINK"))
            mycolor=Color.pink;
        return mycolor;
    }

    public static void main(String[] args) {
            JFrame frame = new JFrame();

            test t = new test();
            t.setLayout(new FlowLayout());
            t.setPreferredSize(new Dimension(900,900));
            t.setVisible(true);
        frame.add(t);
        frame.setVisible(true);

    }
}