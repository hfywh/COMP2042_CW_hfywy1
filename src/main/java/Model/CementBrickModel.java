package Model;

import Controller.Crack;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;


/**
 * Make the model of cement brick
 */
public class CementBrickModel extends BrickModel {


    private static final Color DEF_INNER = new Color(147, 147, 147);
    private static final Color DEF_BORDER = new Color(217, 199, 175);
    private static final int CEMENT_STRENGTH = 2;

    private Crack crack;
    private Shape brickFace;

    /**
     * get properties of brick
     * @param point point of brick
     * @param size size of brick
     */
    public CementBrickModel(Point point, Dimension size){
        super(point,size,DEF_BORDER,DEF_INNER,CEMENT_STRENGTH);
        crack = new Crack(DEF_CRACK_DEPTH,DEF_STEPS, this);
        brickFace = super.getbrickFace();
    }

    /**
     * make the shape of brick
     * @param pos  position of brick
     * @param size size of brick
     * @return shape of brick
     */
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    /**
     * Use to check is there any impact
     * @param point point of brick
     * @param dir   direction of brick
     * @return impact of brick
     */
    @Override
    public boolean setImpact(Point2D point, int dir) {
        if(super.isBroken())
            return false;
        super.impact();
        if(!super.isBroken()){
            crack.makeCrack(point,dir);
            updateBrick();
            return false;
        }
        return true;
    }


    /**
     * get the model of brick
     * @return shape of brick
     */
    @Override
    public Shape getBrick() {
        return brickFace;
    }

    /**
     * Update the state of brick
     */
    private void updateBrick(){
        if(!super.isBroken()){
            GeneralPath gp = crack.draw();
            gp.append(super.getbrickFace(),false);
            brickFace = gp;
        }
    }

    /**
     * Repair the brick
     */
    public void repair(){
        super.repair();
        crack.reset();
        brickFace = super.getbrickFace();
    }
}
