package test;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;

public class SuperBrick extends Brick {
    private static final String NAME = "Super Brick";
    private static final Color DEF_INNER = new Color(138, 229, 132);
    private static final Color DEF_BORDER = new Color(22, 106, 50);
    private static final int SUPER_STRENGTH = 2;
    private static final double SUPER_PROBABILITY = 0.2;

    private Random rnd;
    private Crack crack;
    private Shape brickFace;

    public SuperBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,SUPER_STRENGTH);
        rnd = new Random();
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
        if(rnd.nextDouble() < SUPER_PROBABILITY){
            super.impact();
            crack.makeCrack(point,dir);
        }
    }

}
