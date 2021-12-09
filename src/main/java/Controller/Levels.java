package Controller;

import Model.BrickModel;
import Model.CementBrickModel;
import Model.ClayBrickModel;
import Model.SpeedUpBrickModel;
import Model.SteelBrickModel;
import Model.SuperBrickModel;
import Model.Wall;

import java.awt.*;

/**
 * to set up the bricks according to the level
 */
public class Levels {
    private static final int LEVELS_COUNT = 6;

    private static final int CLAY = 1;
    private static final int STEEL = 2;
    private static final int CEMENT = 3;
    private static final int SUPER = 4;
    private static final int SPEEDUP = 5;

    private BrickModel[][] levels;
    private static int level;
    private Wall wall;

    /**
     * draw the brick for the level
     * @param drawArea area of brick
     * @param brickCount number of brick
     * @param lineCount line count
     * @param brickDimensionRatio brick ratio
     * @param wall Wall
     */
    public Levels(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Wall wall){
        levels = makeLevels(drawArea,brickCount,lineCount,brickDimensionRatio);
        level = 0;
        this.wall = wall;
    }

    /**
     * draw single type of brick
     * @param drawArea area of rectangle
     * @param brickCnt brick count
     * @param lineCnt line count
     * @param brickSizeRatio brick ratio
     * @return brick model
     */
    private BrickModel[] makeSingleTypeLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio){
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        BrickModel[] tmp  = new BrickModel[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            double x = (i % brickOnLine) * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);
            tmp[i] = makeBrick(p,brickSize, Levels.CLAY);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = new ClayBrickModel(p,brickSize);
        }
        return tmp;

    }

    /**
     * draw two type of bricks
     * @param drawArea area of rectangle
     * @param brickCnt brick count
     * @param lineCnt line count
     * @param brickSizeRatio brick ratio
     * @param typeA first type of brick
     * @param typeB second type of brick
     * @return brick model
     */
    private BrickModel[] makeChessboardLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB){
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        int centerLeft = brickOnLine / 2 - 1;
        int centerRight = brickOnLine / 2 + 1;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        BrickModel[] tmp  = new BrickModel[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            int posX = i % brickOnLine;
            double x = posX * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);

            boolean b = ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && posX > centerLeft && posX <= centerRight));
            tmp[i] = b ?  makeBrick(p,brickSize,typeA) : makeBrick(p,brickSize,typeB);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = makeBrick(p,brickSize,typeA);
        }
        return tmp;
    }

    /**
     * make the levels
     * @param drawArea area of rectangle
     * @param brickCount brick count
     * @param lineCount line count
     * @param brickDimensionRatio brick ratio
     * @return brick model
     */
    private BrickModel[][] makeLevels(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio){
        BrickModel[][] tmp = new BrickModel[LEVELS_COUNT][];
        tmp[0] = makeSingleTypeLevel(drawArea,brickCount,lineCount,brickDimensionRatio);
        tmp[1] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,CEMENT);
        tmp[2] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,STEEL);
        tmp[3] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,STEEL,CEMENT);
        tmp[4] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,SUPER,CEMENT);
        tmp[5] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,SUPER,SPEEDUP);
        return tmp;
    }

    /**
     * make the brick
     * @param point  point of brick
     * @param size size of brick
     * @param type type of brick
     * @return brick model
     */
    private BrickModel makeBrick(Point point, Dimension size, int type){
        BrickModel out;
        if (type == CLAY) {
            out = new ClayBrickModel(point, size);
        } else if (type == STEEL) {
            out = new SteelBrickModel(point, size);
        } else if (type == CEMENT) {
            out = new CementBrickModel(point, size);
        } else if (type == SUPER) {
            out = new SuperBrickModel(point, size);
        } else if (type == SPEEDUP) {
            out = new SpeedUpBrickModel(point, size);
        } else {
            throw new IllegalArgumentException(String.format("Unknown Type:%d\n", type));
        }
        return  out;
    }

    /**
     * go to next level
     */
    public void nextLevel(){
        wall.setBricks(levels[level++]);
        wall.setBrickCount(wall.getBricks().length);
    }

    /**
     * check whether there is still higher level
     * @return boolean
     */
    public boolean hasLevel(){
        return level < levels.length;
    }

    /**
     * get the level
     * @return int
     */
    public static int getLevel(){
        return level;
    }
}
