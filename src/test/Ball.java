package test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

/**
 * Created by filippo on 04/09/16.
 *
 */
abstract public class Ball {

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

    public Ball(Point2D center,int radiusA,int radiusB,Color inner,Color border){
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

    protected abstract Shape makeBall(Point2D center,int radiusA,int radiusB);

    public void move(){
        RectangularShape tmp = (RectangularShape) ballFace;
        center.setLocation((center.getX() + speedX),(center.getY() + speedY));
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        setPoints(w,h);


        ballFace = tmp;
    }

    public void setSpeed(int x,int y){
        setXSpeed(x);
        setYSpeed(y);
    }

    public void setXSpeed(int s){
        speedX = s;
    }

    public void setYSpeed(int s){
        speedY = s;
    }

    public void reverseX(){
        speedX *= -1;
    }

    public void reverseY(){
        speedY *= -1;
    }

    public Color getBorderColor(){
        return border;
    }

    public Color getInnerColor(){
        return inner;
    }

    public Point2D getPosition(){
        return center;
    }

    public Shape getBallFace(){
        return ballFace;
    }

    public void moveTo(Point p){
        center.setLocation(p);

        RectangularShape tmp = (RectangularShape) ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        ballFace = tmp;
    }

    private void setPoints(double width,double height){
        setUpLocation(height);
        setDownLocation(height);

        setLeftLocation(width);
        setRightLocation(width);
    }

    public int getSpeedX(){
        return speedX;
    }

    public int getSpeedY(){
        return speedY;
    }

    public void setUpLocation(double radiusB){
        getUp().setLocation(center.getX(),center.getY()-(radiusB / 2));
    }

    public void setDownLocation(double radiusB){
        getDown().setLocation(center.getX(),center.getY()+(radiusB / 2));
    }

    public void setLeftLocation(double radiusA){
        getLeft().setLocation(center.getX()-(radiusA /2),center.getY());
    }

    public void setRightLocation(double radiusA){
        getRight().setLocation(center.getX()+(radiusA /2),center.getY());
    }

    public void setUp(){
        this.up = new Point2D.Double();
    }

    public Point2D getUp(){
        return up;
    }

    public void setDown(){
        this.down = new Point2D.Double();
    }

    public Point2D getDown(){
        return down;
    }

    public void setLeft(){
        this.left = new Point2D.Double();
    }

    public Point2D getLeft(){
        return left;
    }

    public void setRight(){
        this.right = new Point2D.Double();
    }

    public Point2D getRight(){
        return right;
    }
}
