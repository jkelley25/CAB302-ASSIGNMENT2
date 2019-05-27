package GUI;

import Application.ActivPanel;
import Tools.Tools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Application.*;

import Application.Main.App;
import Application.*;
import Tools.Tools;

import static Application.Main.App;

public class ToolBarButton extends JButton implements ActionListener {
    boolean selected;
    Tools tool;

    public ToolButton(String imageFile, Tools tool, String ToolTip){
        //button.setSize(new Dimension(15,15));
        ImageIcon buttonIcon = new ImageIcon(imageFile);
        Image img = buttonIcon.getImage();
        Image img2 = img.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        buttonIcon = new ImageIcon(img2);
        this.setIcon(buttonIcon);
        this.setToolTipText(ToolTip);
        this.tool = tool;
        this.addActionListener(this);
    };

    public void paintComponent(Graphics G){
        super.paintComponent(G);
    }

    public void actionPerformed(ActionEvent event){
        ActivPanel.setCurrentTool(this.tool);

    }

}
