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

import Controller.Crack;
import Controller.PlayerController;

import java.awt.*;
import java.awt.geom.Point2D;


/**
 * Set the scene for each level.
 * @author Yong Wei Hian
 * @since 12/11/2021
 * @version 1.2
 */
public class Wall {

    private Rectangle area;

    private BrickModel[] brickModels;
    private BallModel ballModel;
    private PlayerController playerController;

    private Point startPoint;
    private int brickCount;
    private int ballCount;
    private boolean ballLost;
    private static int totalBrickDestroyed = 0;

    /**
     * Make ball, bricks and player
     * @param drawArea Area of player
     * @param ballPos Position of ball
     */
    public Wall(Rectangle drawArea, Point ballPos){

        this.startPoint = new Point(ballPos);

        ballCount = 3;
        ballLost = false;

        makeBall(ballPos);

        getBall().setSpeed(3,-5);

        playerController = new PlayerController((Point) ballPos.clone(),150,10, drawArea);

        area = drawArea;


    }

    /**
     * Get the total brick destroyed
     * @return Total brick destroyed
     */
    public static int getTotalBrickDestroyed() {
        return totalBrickDestroyed;
    }

    /**
     * Set the total brick destroyed
     * @param totalBrickDestroyed Total brick destroyed
     */
    public static void setTotalBrickDestroyed(int totalBrickDestroyed) {
        Wall.totalBrickDestroyed = totalBrickDestroyed;
    }

    /**
     * Make ball
     * @param ballPos Position of ball
     */
    private void makeBall(Point2D ballPos){
        ballModel = new RubberBall(ballPos);
    }

    /**
     * Move the player and move the ball
     */
    public void move(){
        playerController.move();
        ballModel.move();
    }

    /**
     * Find impact of the ball
     */
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

    /**
     * Change the moving direction of the ball if impact on the bricks
     * @return impact on brick
     */
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

    /**
     * Change the moving direction of the ball if impact on the border
     * @return impact on border
     */
    private boolean impactBorder(){
        Point2D p = ballModel.getPosition();
        return ((p.getX() < area.getX()) ||(p.getX() > (area.getX() + area.getWidth())));
    }

    /**
     * Get number of bricks
     * @return Number of bricks
     */
    public int getBrickCount(){
        return brickCount;
    }

    /**
     * Get number of balls
     * @return Number of balls
     */
    public int getBallCount(){
        return ballCount;
    }

    /**
     * Change the number of ball
     * @return Loss of ball
     */
    public boolean isBallLost(){
        return ballLost;
    }

    /**
     * Reset the ball to starting position
     */
    public void ballReset(){
        playerController.moveTo(startPoint);
        ballModel.moveTo(startPoint);

        ballModel.setSpeed(3,-5);
        ballLost = false;
    }

    /**
     * Reset all the bricks
     */
    public void wallReset(){
        for(BrickModel b : brickModels)
            b.repair();
        brickCount = brickModels.length;
        ballCount = 3;
    }

    /**
     * Game over
     * @return End of ball
     */
    public boolean ballEnd(){
        return ballCount == 0;
    }

    /**
     * Check if all the levels are completed
     * @return Level finished
     */
    public boolean isDone(){
        return brickCount == 0;
    }

    /**
     * Set X-speed of ball
     * @param s X-speed of ball
     */
    public void setBallXSpeed(int s){
        ballModel.setXSpeed(s);
    }

    /**
     * Set Y-speed of ball
     * @param s Y-speed of ball
     */
    public void setBallYSpeed(int s){
        ballModel.setYSpeed(s);
    }

    /**
     * Reset number of balls
     */
    public void resetBallCount(){
        ballCount = 3;
    }

    /**
     * Make bricks
     * @param brickModels Model of brick
     */
    public void setBricks(BrickModel[] brickModels) {
        this.brickModels = brickModels;
    }

    /**
     * Set number of bricks
     * @param brickCount Number of bricks
     */
    public void setBrickCount(int brickCount){
        this.brickCount = brickCount;
    }

    /**
     * Get brick model
     * @return Model of brick
     */
    public BrickModel[] getBricks() {
        return brickModels;
    }

    /**
     * Get ball model
     * @return Model of ball
     */
    public BallModel getBall(){
        return ballModel;
    }

    /**
     * Get player
     * @return Player
     */
    public PlayerController getPlayer() {
        return playerController;
    }
}
