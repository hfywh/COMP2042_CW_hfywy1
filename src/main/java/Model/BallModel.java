package Model;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

/**
 * Created by filippo on 04/09/16.
 * BallModel create a model of the ball
 * @author Yong Wei Hian
 * @since 12/11/2021
 * @version 1.2
 */
abstract public class BallModel {

    private Shape ballFace;

    private Point2D center;

    private Point2D up;
    private Point2D down;
    private Point2D left;
    private Point2D right;

    private Color border;
    private Color inner;

    private int speedX;
    private int speedY;

    /**
     * Get the properties of the ball
     * @param center center of the ball
     * @param radiusA X-radius of ball
     * @param radiusB Y-radius of ball
     * @param inner inner color of ball
     * @param border border color of ball
     */
    public BallModel(Point2D center, int radiusA, int radiusB, Color inner, Color border){
        this.center = center;

        setUp();
        setDown();
        setLeft();
        setRight();

        setUpLocation(radiusB);
        setDownLocation(radiusB);

        setLeftLocation(radiusA);
        setRightLocation(radiusA);


        ballFace = makeBall(center,radiusA,radiusB);
        this.border = border;
        this.inner  = inner;
        setXSpeed(0);
        setYSpeed(0);
    }

    /**
     * make a ball
     * @param center center of the ball
     * @param radiusA X-radius of ball
     * @param radiusB Y-radius of ball
     * @return shape of ball
     */
    protected abstract Shape makeBall(Point2D center,int radiusA,int radiusB);

    /**
     * move the ball
     */
    public void move(){
        RectangularShape tmp = (RectangularShape) ballFace;
        center.setLocation((center.getX() + speedX),(center.getY() + speedY));
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        setPoints(w,h);


        ballFace = tmp;
    }

    /**
     * set speed of ball
     * @param x X-speed of ball
     * @param y Y-speed of ball
     */
    public void setSpeed(int x,int y){
        setXSpeed(x);
        setYSpeed(y);
    }

    /**
     * set X-speed of ball
     * @param s X-speed of ball
     */
    public void setXSpeed(int s){
        speedX = s;
    }

    /**
     * set Y-speed of ball
     * @param s Y-speed of ball
     */
    public void setYSpeed(int s){
        speedY = s;
    }

    /**
     * reverse X-speed of wall
     */
    public void reverseX(){
        speedX *= -1;
    }

    /**
     * reverse Y-speed of wall
     */
    public void reverseY(){
        speedY *= -1;
    }

    /**
     * get border color of ball
     * @return border color of ball
     */
    public Color getBorderColor(){
        return border;
    }

    /**
     * get inner color of ball
     * @return inner color of ball
     */
    public Color getInnerColor(){
        return inner;
    }

    /**
     * get position of ball
     * @return center of ball
     */
    public Point2D getPosition(){
        return center;
    }

    /**
     * get shape of ball
     * @return shape of ball
     */
    public Shape getBallFace(){
        return ballFace;
    }

    /**
     * move to point p
     * @param p point
     */
    public void moveTo(Point p){
        center.setLocation(p);

        RectangularShape tmp = (RectangularShape) ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        ballFace = tmp;
    }

    /**
     * set point of ball
     * @param width width
     * @param height height
     */
    private void setPoints(double width,double height){
        setUpLocation(height);
        setDownLocation(height);

        setLeftLocation(width);
        setRightLocation(width);
    }

    /**
     * get X-speed of ball
     * @return X-speed of ball
     */
    public int getSpeedX(){
        return speedX;
    }

    /**
     * get Y-speed of ball
     * @return Y-speed of ball
     */
    public int getSpeedY(){
        return speedY;
    }

    /**
     * set up location of ball
     * @param radiusB Y-radius of ball
     */
    public void setUpLocation(double radiusB){
        getUp().setLocation(center.getX(),center.getY()-(radiusB / 2));
    }

    /**
     * set down location of ball
     * @param radiusB Y-radius of ball
     */
    public void setDownLocation(double radiusB){
        getDown().setLocation(center.getX(),center.getY()+(radiusB / 2));
    }

    /**
     * set left location of ball
     * @param radiusA X-radius of ball
     */
    public void setLeftLocation(double radiusA){
        getLeft().setLocation(center.getX()-(radiusA /2),center.getY());
    }

    /**
     * set right location of ball
     * @param radiusA X-radius of ball
     */
    public void setRightLocation(double radiusA){
        getRight().setLocation(center.getX()+(radiusA /2),center.getY());
    }

    /**
     * set up point of ball
     */
    public void setUp(){
        this.up = new Point2D.Double();
    }

    /**
     * get up point of ball
     * @return up point of ball
     */
    public Point2D getUp(){
        return up;
    }

    /**
     * set down point of ball
     */
    public void setDown(){
        this.down = new Point2D.Double();
    }

    /**
     * get down point of ball
     * @return down point of ball
     */
    public Point2D getDown(){
        return down;
    }

    /**
     * set left point of ball
     */
    public void setLeft(){
        this.left = new Point2D.Double();
    }

    /**
     * get left point of ball
     * @return left point of ball
     */
    public Point2D getLeft(){
        return left;
    }

    /**
     * set right point of ball
     */
    public void setRight(){
        this.right = new Point2D.Double();
    }

    /**
     * get right point of ball
     * @return right point of ball
     */
    public Point2D getRight(){
        return right;
    }
}
