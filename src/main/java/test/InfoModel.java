package test;

import javax.swing.*;
import java.awt.*;

public class InfoModel extends JComponent{

    public static Rectangle menuFace;
    public static Rectangle backButton;

    private static GameFrame owner;

    public static boolean backClicked;

    public InfoModel(GameFrame owner, Dimension area){

        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addMouseListener(new InfoController());
        this.addMouseMotionListener(new InfoController());

        setOwner(owner);

        menuFace = new Rectangle(new Point(0,0),area);
        this.setPreferredSize(area);

        Dimension btnDim = new Dimension(area.width / 3, area.height / 12);
        backButton = new Rectangle(btnDim);

    }

    public static GameFrame getOwner(){
        return owner;
    }

    public void setOwner(GameFrame owner){
        InfoModel.owner = owner;
    }

    public void paint(Graphics g){
        new InfoView(g);
    }
}