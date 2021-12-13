package Controller;

import Model.BrickModel;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

/**
 * When the brick get impact but not broken, the brick crack
 * @author Yong Wei Hian
 * @since 12/11/2021
 * @version 1.2
 */
public class Crack {
    private static final int CRACK_SECTIONS = 3;
    private static final double JUMP_PROBABILITY = 0.7;

    private static final int LEFT = 10;
    private static final int RIGHT = 20;
    private static final int UP = 30;
    private static final int DOWN = 40;
    private static final int VERTICAL = 100;
    private static final int HORIZONTAL = 200;

    private final BrickModel bricks;

    private GeneralPath crack;

    private int crackDepth;
    private int steps;


    /**
     * make the brick crack
     * @param crackDepth crack depth of brick
     * @param steps steps of the brick
     * @param bricks the type of brick
     */
    public Crack(int crackDepth, int steps, BrickModel bricks){
        this.bricks = bricks;
        crack = new GeneralPath();
        this.crackDepth = crackDepth;
        this.steps = steps;

    }


    /**
     * Display the crack of the brick
     * @return crack of the brick
     */
    public GeneralPath draw(){

        return crack;
    }

    /**
     * reset the crack
     */
    public void reset(){
        crack.reset();
    }

    /**
     * Make the brick crack
     * @param point point of the brick
     * @param direction direction of the impact
     */
    public void makeCrack(Point2D point, int direction){
        Rectangle bounds = bricks.getbrickFace().getBounds();

        Point impact = new Point((int)point.getX(),(int)point.getY());
        Point start = new Point();
        Point end = new Point();


        if (direction == LEFT) {
            start.setLocation(bounds.x + bounds.width, bounds.y);
            end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
            Point tmp = makeRandomPoint(start, end, VERTICAL);
            makeCrack(impact, tmp);
        } else if (direction == RIGHT) {
            Point tmp;
            start.setLocation(bounds.getLocation());
            end.setLocation(bounds.x, bounds.y + bounds.height);
            tmp = makeRandomPoint(start, end, VERTICAL);
            makeCrack(impact, tmp);
        } else if (direction == UP) {
            Point tmp;
            start.setLocation(bounds.x, bounds.y + bounds.height);
            end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
            tmp = makeRandomPoint(start, end, HORIZONTAL);
            makeCrack(impact, tmp);
        } else if (direction == DOWN) {
            Point tmp;
            start.setLocation(bounds.getLocation());
            end.setLocation(bounds.x + bounds.width, bounds.y);
            tmp = makeRandomPoint(start, end, HORIZONTAL);
            makeCrack(impact, tmp);
        }
    }

    /**
     * Create a path between start point and end point
     * @param start start point
     * @param end end point
     */
    public void makeCrack(Point start, Point end){

        GeneralPath path = new GeneralPath();


        path.moveTo(start.x,start.y);

        double w = (end.x - start.x) / (double)steps;
        double h = (end.y - start.y) / (double)steps;

        double x,y;

        for(int i = 1; i < steps;i++){

            x = (i * w) + start.x;
            y = (i * h) + start.y + randomInBounds(crackDepth);

            if(inMiddle(i, steps))
                y += jumps(crackDepth * 5);

            path.lineTo(x,y);

        }

        path.lineTo(end.x,end.y);
        crack.append(path,true);
    }

    /**
     * Create random number
     * @param bound bound for the random integer
     * @return random bound integer
     */
    private int randomInBounds(int bound){
        int n = (bound * 2) + 1;
        return BrickModel.getRandom().nextInt(n) - bound;
    }

    /**
     * Crack in the middle
     * @param i integer
     * @param divisions division of crack
     * @return crack division
     */
    private boolean inMiddle(int i, int divisions){
        int low = (Crack.CRACK_SECTIONS / divisions);
        int up = low * (divisions - 1);

        return  (i > low) && (i < up);
    }

    /**
     * Crack if the random number more than probability
     * @param bound bound for the random integer
     * @return randomInbounds or none
     */
    private int jumps(int bound){

        if(BrickModel.getRandom().nextDouble() > Crack.JUMP_PROBABILITY)
            return randomInBounds(bound);
        return  0;

    }

    /**
     * Make a random point
     * @param from start point
     * @param to end point
     * @param direction direction
     * @return location
     */
    private Point makeRandomPoint(Point from,Point to, int direction){

        Point out = new Point();
        int pos;

        if (direction == HORIZONTAL) {
            pos = BrickModel.getRandom().nextInt(to.x - from.x) + from.x;
            out.setLocation(pos, to.y);
        } else if (direction == VERTICAL) {
            pos = BrickModel.getRandom().nextInt(to.y - from.y) + from.y;
            out.setLocation(to.x, pos);
        }
        return out;
    }

    /**
     * Get left direction
     * @return left
     */
    public static int getLeft(){
        return LEFT;
    }

    /**
     * Get right direction
     * @return right
     */
    public static int getRight(){
        return RIGHT;
    }

    /**
     * Get down direction
     * @return down
     */
    public static int getDown(){
        return DOWN;
    }

    /**
     * Get up direction
     * @return up
     */
    public static int getUp(){
        return UP;
    }
}
