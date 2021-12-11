package Model;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * Created by filippo on 04/09/16.
 * BrickModel to create a model of brick.
 * @author Yong Wei Hian
 * @since 12/11/2021
 * @version 1.2
 */
abstract public class BrickModel {

    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;


    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;

    private static Random rnd;

    private Shape brickFace;

    private Color border;
    private Color inner;

    private int fullStrength;
    private int strength;

    private boolean broken;


    /**
     * Get properties of brick model
     * @param pos position of brick
     * @param size size of brick
     * @param border border color of brick
     * @param inner inner color of brick
     * @param strength strength of brick
     */
    public BrickModel(Point pos, Dimension size, Color border, Color inner, int strength){
        rnd = new Random();
        broken = false;
        brickFace = makeBrickFace(pos,size);
        this.border = border;
        this.inner = inner;
        this.fullStrength = this.strength = strength;

    }

    /**
     * make the model of brick
     * @param pos position of brick
     * @param size size of brick
     * @return shape of brick
     */
    protected abstract Shape makeBrickFace(Point pos,Dimension size);

    /**
     * Use to check is there any impact
     * @param point point of brick
     * @param dir direction of brick
     * @return boolean
     */
    public  boolean setImpact(Point2D point , int dir){
        if(broken)
            return false;
        impact();
        return  broken;
    }

    /**
     * get the model of brick
     * @return shape of brick
     */
    public abstract Shape getBrick();


    /**
     * get border color of brick
     * @return border color of brick
     */
    public Color getBorderColor(){
        return  border;
    }

    /**
     * get inner color of brick
     * @return inner color of brick
     */
    public Color getInnerColor(){
        return inner;
    }


    /**
     * find impact from ball
     * @param b model of ball
     * @return direction of impact
     */
    public final int findImpact(BallModel b){
        if(broken)
            return 0;
        int out  = 0;
        if(brickFace.contains(b.getRight()))
            out = LEFT_IMPACT;
        else if(brickFace.contains(b.getLeft()))
            out = RIGHT_IMPACT;
        else if(brickFace.contains(b.getUp()))
            out = DOWN_IMPACT;
        else if(brickFace.contains(b.getDown()))
            out = UP_IMPACT;
        return out;
    }

    /**
     * check the state of brick
     * @return break of brick
     */
    public final boolean isBroken(){
        return broken;
    }

    /**
     * repair the brick
     */
    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    /**
     * brick receive impact
     */
    public void impact(){
        strength--;
        broken = (strength == 0);
    }


    /**
     * get the shape of brick
     * @return the shape of brick
     */
    public Shape getbrickFace() {
        return brickFace;
    }

    /**
     * random number
     * @return random number
     */
    public static Random getRandom() {
        return rnd;
    }
}





