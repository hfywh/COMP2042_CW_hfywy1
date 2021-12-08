package test;

import java.awt.*;
import java.awt.geom.Point2D;

public class SpeedUpBrickModel extends BrickModel {

    private static final Color DEF_INNER = Color.GREEN.brighter();
    private static final Color DEF_BORDER = Color.GREEN.darker();
    private static final int CLAY_STRENGTH = 1;

    public SpeedUpBrickModel(Point point, Dimension size){
        super(point,size,DEF_BORDER,DEF_INNER,CLAY_STRENGTH);
    }

    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    @Override
    public Shape getBrick() {
        return super.getbrickFace();
    }

    @Override
    public boolean setImpact(Point2D point , int dir){
        if(super.isBroken())
            return false;
        else {
            impact();
            GameBoardModel.getWall().setBallYSpeed(-10);
        }
        return super.isBroken();
    }

}
