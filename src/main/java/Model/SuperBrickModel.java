package Model;

import Controller.Crack;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * Make model of super brick.
 * @author Yong Wei Hian
 * @since 12/11/2021
 * @version 1.2
 */
public class SuperBrickModel extends BrickModel {
    private static final Color DEF_INNER = new Color(105, 136, 227);
    private static final Color DEF_BORDER = new Color(33, 81, 179);
    private static final int SUPER_STRENGTH = 2;
    private static final double SUPER_PROBABILITY = 0.4;

    private Random rnd;
    private Crack crack;
    private Shape brickFace;
    private Double randomNumber;

    /**
     * Get properties of super brick
     * @param point point of brick
     * @param size size of brick
     */
    public SuperBrickModel(Point point, Dimension size){
        super(point,size,DEF_BORDER,DEF_INNER,SUPER_STRENGTH);
        rnd = new Random();
        crack = new Crack(DEF_CRACK_DEPTH,DEF_STEPS, this);
        brickFace = super.getbrickFace();
    }

    /**
     * make the model of brick
     * @param pos position of brick
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
     * @param dir direction of brick
     * @return boolean
     */
    @Override
    public boolean setImpact(Point2D point, int dir) {
        if(super.isBroken())
            return false;
        setRandomNumber(rnd.nextDouble());
        impact(point, dir);
        if(!super.isBroken()){
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
     * Update status of brick
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

    /**
     * Determine whether the brick receive impact
     * @param point Point of brick
     * @param dir Direction of impact
     */
    public void impact(Point2D point, int dir){
        if(getRandomNumber() < SUPER_PROBABILITY){
            super.impact();
            crack.makeCrack(point,dir);
        }
    }

    public Double getRandomNumber() {
        return randomNumber;
    }

    public void setRandomNumber(Double randomNumber) {
        this.randomNumber = randomNumber;
    }
}
