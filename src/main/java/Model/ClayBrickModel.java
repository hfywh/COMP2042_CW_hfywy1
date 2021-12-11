package Model;

import java.awt.*;


/**
 * Created by filippo on 04/09/16.
 * Make model of clay brick.
 * @author Yong Wei Hian
 * @since 12/11/2021
 * @version 1.2
 */
public class ClayBrickModel extends BrickModel {

    private static final Color DEF_INNER = new Color(178, 34, 34).darker();
    private static final Color DEF_BORDER = Color.GRAY;
    private static final int CLAY_STRENGTH = 1;


    /**
     * get properties of brick
     * @param point point of brick
     * @param size size of brick
     */
    public ClayBrickModel(Point point, Dimension size){
        super(point,size,DEF_BORDER,DEF_INNER,CLAY_STRENGTH);
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
     * get the model of brick
     * @return shape of brick
     */
    @Override
    public Shape getBrick() {
        return super.getbrickFace();
    }


}
