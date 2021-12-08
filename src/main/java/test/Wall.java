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
package test;

import java.awt.*;
import java.awt.geom.Point2D;


public class Wall {

    private Rectangle area;

    private BrickModel[] brickModels;
    private BallModel ballModel;
    private Player player;

    private Point startPoint;
    private int brickCount;
    private int ballCount;
    private boolean ballLost;
    private static int totalBrickDestroyed = 0;

    public Wall(Rectangle drawArea, Point ballPos){

        this.startPoint = new Point(ballPos);

        ballCount = 3;
        ballLost = false;

        makeBall(ballPos);

        getBall().setSpeed(3,-5);

        player = new Player((Point) ballPos.clone(),150,10, drawArea);

        area = drawArea;


    }

    public static int getTotalBrickDestroyed() {
        return totalBrickDestroyed;
    }

    public static void setTotalBrickDestroyed(int totalBrickDestroyed) {
        Wall.totalBrickDestroyed = totalBrickDestroyed;
    }

    private void makeBall(Point2D ballPos){
        ballModel = new RubberBall(ballPos);
    }

    public void move(){
        player.move();
        ballModel.move();
    }

    public void findImpacts(){
        if(getPlayer().impact(getBall())){
            getBall().reverseY();
        }
        else if(impactWall()){
            /*for efficiency reverse is done into method impactWall
            * because for every brick program checks for horizontal and vertical impacts
            */
            setBrickCount(getBrickCount() - 1);
            setTotalBrickDestroyed(getTotalBrickDestroyed() + 1);
        }
        else if(impactBorder()) {
            getBall().reverseX();
        }
        else if(getBall().getPosition().getY() < area.getY()){
            getBall().reverseY();
        }
        else if(getBall().getPosition().getY() > area.getY() + area.getHeight()){
            ballCount--;
            ballLost = true;
        }
    }

    private boolean impactWall(){
        for(BrickModel b : brickModels){
            //Vertical Impact
            //Horizontal Impact
            switch (b.findImpact(ballModel)) {
                case BrickModel.UP_IMPACT -> {
                    ballModel.reverseY();
                    return b.setImpact(getBall().getDown(), Crack.getUp());
                }
                case BrickModel.DOWN_IMPACT -> {
                    ballModel.reverseY();
                    return b.setImpact(getBall().getUp(), Crack.getDown());
                }
                case BrickModel.LEFT_IMPACT -> {
                    ballModel.reverseX();
                    return b.setImpact(getBall().getRight(), Crack.getRight());
                }
                case BrickModel.RIGHT_IMPACT -> {
                    ballModel.reverseX();
                    return b.setImpact(getBall().getLeft(), Crack.getLeft());
                }
            }
        }
        return false;
    }

    private boolean impactBorder(){
        Point2D p = ballModel.getPosition();
        return ((p.getX() < area.getX()) ||(p.getX() > (area.getX() + area.getWidth())));
    }

    public int getBrickCount(){
        return brickCount;
    }

    public int getBallCount(){
        return ballCount;
    }

    public boolean isBallLost(){
        return ballLost;
    }

    public void ballReset(){
        player.moveTo(startPoint);
        ballModel.moveTo(startPoint);

        ballModel.setSpeed(3,-5);
        ballLost = false;
    }

    public void wallReset(){
        for(BrickModel b : brickModels)
            b.repair();
        brickCount = brickModels.length;
        ballCount = 3;
    }

    public boolean ballEnd(){
        return ballCount == 0;
    }

    public boolean isDone(){
        return brickCount == 0;
    }

    public void setBallXSpeed(int s){
        ballModel.setXSpeed(s);
    }

    public void setBallYSpeed(int s){
        ballModel.setYSpeed(s);
    }

    public void resetBallCount(){
        ballCount = 3;
    }

    public void setBricks(BrickModel[] brickModels) {
        this.brickModels = brickModels;
    }

    public void setBrickCount(int brickCount){
        this.brickCount = brickCount;
    }

    public BrickModel[] getBricks() {
        return brickModels;
    }

    public BallModel getBall(){
        return ballModel;
    }

    public Player getPlayer() {
        return player;
    }
}
