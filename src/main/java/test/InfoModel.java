package test;

import javax.swing.*;
import java.awt.*;

public class InfoModel extends JComponent{

    private static Rectangle menuFace;
    private static Rectangle backButton;

    private static GameFrame owner;

    public static boolean backClicked;

    public InfoModel(GameFrame owner, Dimension area){

        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addMouseListener(new InfoController());
        this.addMouseMotionListener(new InfoController());

        setOwner(owner);

        setMenuFace(new Rectangle(new Point(0,0),area));
        this.setPreferredSize(area);

        Dimension btnDim = new Dimension(area.width / 3, area.height / 12);
        setBackButton(new Rectangle(btnDim));

    }

    public static GameFrame getOwner(){
        return owner;
    }

    public static Rectangle getBackButton() {
        return backButton;
    }

    public static void setBackButton(Rectangle backButton) {
        InfoModel.backButton = backButton;
    }

    public static Rectangle getMenuFace() {
        return menuFace;
    }

    public static void setMenuFace(Rectangle menuFace) {
        InfoModel.menuFace = menuFace;
    }

    public void setOwner(GameFrame owner){
        InfoModel.owner = owner;
    }

    public void paint(Graphics g){
        new InfoView(g);
    }
}