package Model;

import Model.BrickModel;

import java.awt.*;
import java.awt.Point;


/**
 * Created by filippo on 04/09/16.
 *
 */
public class ClayBrickModel extends BrickModel {

    private static final Color DEF_INNER = new Color(178, 34, 34).darker();
    private static final Color DEF_BORDER = Color.GRAY;
    private static final int CLAY_STRENGTH = 1;






    public ClayBrickModel(Point point, Dimension size){
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


}