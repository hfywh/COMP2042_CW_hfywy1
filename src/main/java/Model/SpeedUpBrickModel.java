package Model;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Make model of speed up brick.
 * @author Yong Wei Hian
 * @since 12/11/2021
 * @version 1.2
 */
public class SpeedUpBrickModel extends BrickModel {

    private static final Color DEF_INNER = Color.GREEN.brighter();
    private static final Color DEF_BORDER = Color.GREEN.darker();
    private static final int CLAY_STRENGTH = 1;

    /**
     * Get properties of speed up brick
     * @param point point of brock
     * @param size size of brick
     */
    public SpeedUpBrickModel(Point point, Dimension size){
        super(point,size,DEF_BORDER,DEF_INNER,CLAY_STRENGTH);
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
     * get the model of brick
     * @return shape of brick
     */
    @Override
    public Shape getBrick() {
        return super.getbrickFace();
    }

    /**
     * Use to check is there any impact
     * @param point point of brick
     * @param dir direction of brick
     * @return boolean
     */
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
