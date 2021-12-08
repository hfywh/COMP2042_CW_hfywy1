package test;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;

public class GameBoardView extends JComponent {

    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;

    public GameBoardView(Graphics g){
        paint(g);
    }

    public void paint(Graphics g){

        Graphics2D g2d = (Graphics2D) g;

        clear(g2d);

        g2d.setColor(Color.BLUE);
        g2d.drawString(GameBoardModel.getMessage(),250,225);
        g2d.drawString(GameBoardModel.getTimeMessage(),250,245);
        g2d.drawString(GameBoardModel.getBrickMessage(),250,265);

        drawBall(GameBoardModel.getWall().getBall(),g2d);

        for(Brick b : GameBoardModel.getWall().getBricks())
            if(!b.isBroken())
                drawBrick(b,g2d);

        drawPlayer(GameBoardModel.getWall().getPlayer(),g2d);

        if(GameBoardModel.isShowPauseMenu())
            drawMenu(g2d);

        Toolkit.getDefaultToolkit().sync();
    }

    private void clear(Graphics2D g2d){
        Color tmp = g2d.getColor();
        g2d.setColor(GameBoardModel.getBackgroundColor());
        g2d.fillRect(0,0,getWidth(),getHeight());
        g2d.setColor(tmp);
    }

    private void drawBrick(Brick brick,Graphics2D g2d){
        Color tmp = g2d.getColor();

        g2d.setColor(brick.getInnerColor());
        g2d.fill(brick.getBrick());

        g2d.setColor(brick.getBorderColor());
        g2d.draw(brick.getBrick());


        g2d.setColor(tmp);
    }

    private void drawBall(Ball ball,Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = ball.getBallFace();

        g2d.setColor(ball.getInnerColor());
        g2d.fill(s);

        g2d.setColor(ball.getBorderColor());
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    private void drawPlayer(Player p,Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = p.getPlayerFace();
        g2d.setColor(Player.INNER_COLOR);
        g2d.fill(s);

        g2d.setColor(Player.BORDER_COLOR);
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    private void drawMenu(Graphics2D g2d){
        obscureGameBoard(g2d);
        drawPauseMenu(g2d);
    }

    private void obscureGameBoard(Graphics2D g2d){

        Composite tmp = g2d.getComposite();
        Color tmpColor = g2d.getColor();

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.55f);
        g2d.setComposite(ac);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,DEF_WIDTH,DEF_HEIGHT);

        g2d.setComposite(tmp);
        g2d.setColor(tmpColor);
    }

    private void drawPauseMenu(Graphics2D g2d){
        Font tmpFont = g2d.getFont();
        Color tmpColor = g2d.getColor();


        g2d.setFont(GameBoardModel.getMenuFont());
        g2d.setColor(GameBoardModel.getMenuColor());

        if(GameBoardModel.getStrLen() == 0){
            FontRenderContext frc = g2d.getFontRenderContext();
            GameBoardModel.setStrLen(GameBoardModel.getMenuFont().getStringBounds(GameBoardModel.getPause(),frc).getBounds().width);
        }

        int x = (this.getWidth() - GameBoardModel.getStrLen()) / 2;
        int y = this.getHeight() / 10;

        g2d.drawString(GameBoardModel.getPause(),x,y);

        x = this.getWidth() / 8;
        y = this.getHeight() / 4;


        if(GameBoardModel.getContinueButtonRect() == null){
            FontRenderContext frc = g2d.getFontRenderContext();
            GameBoardModel.setContinueButtonRect(GameBoardModel.getMenuFont().getStringBounds(GameBoardModel.getContinue(),frc).getBounds());
            GameBoardModel.getContinueButtonRect().setLocation(x,y-GameBoardModel.getContinueButtonRect().height);
        }

        g2d.drawString(GameBoardModel.getContinue(),x,y);

        y *= 2;

        if(GameBoardModel.getRestartButtonRect() == null){
            GameBoardModel.setRestartButtonRect((Rectangle) GameBoardModel.getContinueButtonRect().clone());
            GameBoardModel.getRestartButtonRect().setLocation(x,y-GameBoardModel.getRestartButtonRect().height);
        }

        g2d.drawString(GameBoardModel.getRestart(),x,y);

        y *= 3.0/2;

        if(GameBoardModel.getHomeMenuButtonRect() == null){
            GameBoardModel.setHomeMenuButtonRect((Rectangle) GameBoardModel.getContinueButtonRect().clone());
            GameBoardModel.getHomeMenuButtonRect().setLocation(x,y-GameBoardModel.getHomeMenuButtonRect().height);
        }

        g2d.drawString(GameBoardModel.getHomeMenu(),x,y);



        y *= 1.25;

        if(GameBoardModel.getExitButtonRect() == null){
            GameBoardModel.setExitButtonRect((Rectangle) GameBoardModel.getContinueButtonRect().clone());
            GameBoardModel.getExitButtonRect().setLocation(x,y-GameBoardModel.getExitButtonRect().height);
        }

        g2d.drawString(GameBoardModel.getExit(),x,y);



        g2d.setFont(tmpFont);
        g2d.setColor(tmpColor);
    }
}