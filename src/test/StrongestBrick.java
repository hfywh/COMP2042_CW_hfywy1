package test;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
//import java.util.Random;

public class StrongestBrick extends Brick{
    private static final String NAME = "Strongest Brick";
    private static final Color DEF_INNER = new Color(132, 156, 229);
    private static final Color DEF_BORDER = new Color(49, 123, 173);
    private static final int STRONGEST_STRENGTH = 4;
    //private static final double CONCRETE_PROBABILITY = 0.7;

    //private Random rnd;
    private Crack crack;
    private Shape brickFace;
    //private boolean random;

    public StrongestBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,STRONGEST_STRENGTH);
        //rnd = new Random();
        crack = new Crack(DEF_CRACK_DEPTH,DEF_STEPS, this);
        brickFace = super.getbrickFace();
    }

    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    @Override
    public boolean setImpact(Point2D point, int dir) {
        if(super.isBroken())
            return false;
        impact(point, dir);
        if(!super.isBroken()){
            //crack.makeCrack(point,dir);
            updateBrick();
            return false;
        }
        return true;
    }


    @Override
    public Shape getBrick() {
        return brickFace;
    }

    private void updateBrick(){
        if(!super.isBroken()){
            GeneralPath gp = crack.draw();
            gp.append(super.getbrickFace(),false);
            brickFace = gp;
        }
    }

    public void repair(){
        super.repair();
        crack.reset();
        brickFace = super.getbrickFace();
    }

    public void impact(Point2D point, int dir){
        super.impact();
        crack.makeCrack(point,dir);
    }

}
