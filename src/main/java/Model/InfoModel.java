package Model;

import Controller.GameFrame;
import Controller.InfoController;
import View.InfoView;

import javax.swing.*;
import java.awt.*;

/**
 * Consist of data regarding info scene
 */
public class InfoModel extends JComponent{

    private static Rectangle menuFace;
    private static Rectangle backButton;

    private static GameFrame owner;

    public static boolean backClicked;

    /**
     * Set up the info scene
     * @param owner Game frame
     * @param area Dimension of info
     */
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

    /**
     * Get game frame
     * @return Game frame
     */
    public static GameFrame getOwner(){
        return owner;
    }

    /**
     * Get back button
     * @return Back button
     */
    public static Rectangle getBackButton() {
        return backButton;
    }

    /**
     * Set back button
     * @param backButton Back button
     */
    public static void setBackButton(Rectangle backButton) {
        InfoModel.backButton = backButton;
    }

    /**
     * Get size of buttons
     * @return Size of buttons
     */
    public static Rectangle getMenuFace() {
        return menuFace;
    }

    /**
     * Set size of buttons
     * @param menuFace Size of buttons
     */
    public static void setMenuFace(Rectangle menuFace) {
        InfoModel.menuFace = menuFace;
    }

    /**
     * Set game frame
     * @param owner Game frame
     */
    public void setOwner(GameFrame owner){
        InfoModel.owner = owner;
    }

    /**
     * Display info scene
     * @param g Graphics
     */
    public void paint(Graphics g){
        new InfoView(g);
    }
}