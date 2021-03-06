/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package Model;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;


/**
 * Make model of steel brick.
 * @author Yong Wei Hian
 * @since 12/11/2021
 * @version 1.2
 */
public class SteelBrickModel extends BrickModel {

    private static final Color DEF_INNER = new Color(203, 203, 201);
    private static final Color DEF_BORDER = Color.BLACK;
    private static final int STEEL_STRENGTH = 1;
    private static final double STEEL_PROBABILITY = 0.4;

    private Random rnd;
    private Shape brickFace;
    private Double randomNumber;

    /**
     * Get properties of steel brick
     * @param point point of brick
     * @param size size of brick
     */
    public SteelBrickModel(Point point, Dimension size){
        super(point,size,DEF_BORDER,DEF_INNER,STEEL_STRENGTH);
        rnd = new Random();
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
     * get the model of brick
     * @return shape of brick
     */
    @Override
    public Shape getBrick() {
        return brickFace;
    }

    /**
     * Use to check is there any impact
     * @param point point of brick
     * @param dir direction of brick
     * @return boolean
     */
    public  boolean setImpact(Point2D point , int dir){
        if(super.isBroken())
            return false;
        setRandomNumber(rnd.nextDouble());
        impact();
        return  super.isBroken();
    }

    /**
     * brick receive impact
     */
    public void impact(){
        if(getRandomNumber() < STEEL_PROBABILITY){
            super.impact();
        }
    }

    public Double getRandomNumber() {
        return randomNumber;
    }

    public void setRandomNumber(Double randomNumber) {
        this.randomNumber = randomNumber;
    }
}
