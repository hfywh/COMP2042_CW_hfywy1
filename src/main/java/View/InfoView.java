package View;

import Model.InfoModel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;

/**
 * Main function of this class is to display info scene.
 * @author Yong Wei Hian
 * @since 12/11/2021
 * @version 1.2
 */
public class InfoView implements ImageObserver {

    private static final String GREETINGS = "Welcome to Brick Destroy";
    private static final String GAME_TITLE = "Info";
    private static final String CREDITS = "Version 1.2";
    private static final String GAME_DETAILS1 = "Player need to destroy all the bricks without running out of balls.";
    private static final String GAME_DETAILS2 = "This game consist of 5 levels. Level 1 with all clay bricks.";
    private static final String GAME_DETAILS3 = "Level 2 with clay and cement bricks. Level 3 with clay and steel bricks.";
    private static final String GAME_DETAILS4 = "Level 4 with cement and steel bricks. Level 5 with cement and super bricks.";
    private static final String GAME_DETAILS5 = "Level 6 with speed up and super bricks. Each level will be given 3 balls.";
    private static final String BRICK_DETAILS1 = "Clay brick can withstand 1 impact. Cement brick can withstand 2 impacts.";
    private static final String BRICK_DETAILS2 = "Steel brick can withstand 1 impact but only have 0.4 to receive an impact.";
    private static final String BRICK_DETAILS3 = "Super brick can withstand 2 impact but only have 0.4 to receive an impact.";
    private static final String BRICK_DETAILS4 = "Speed Up brick can withstand 1 impact but will double the ball speed";
    private static final String BRICK_DETAILS5 = "once it is broke. The effect will not be superimpose and";
    private static final String BRICK_DETAILS6 = "have effect on current ball only.";
    private static final String BACK_TEXT = "Back";

    private static final Color BG_COLOR = Color.GRAY.darker();
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color CLICKED_BUTTON_COLOR = BG_COLOR.brighter();
    private static final Color CLICKED_TEXT = Color.RED;

    private Font greetingsFont;
    private Font gameTitleFont;
    private Font creditsFont;
    private Font infoFont;
    private Font buttonFont;

    /**
     * Display info scene
     * @param g Graphics
     */
    public InfoView(Graphics g){

        greetingsFont = new Font("Noto Mono",Font.PLAIN,25);
        gameTitleFont = new Font("Noto Mono",Font.BOLD,40);
        creditsFont = new Font("Monospaced",Font.PLAIN,10);
        buttonFont = new Font("Monospaced",Font.PLAIN,30);
        infoFont = new Font("Monospaced",Font.PLAIN,15);


        paint(g);
    }

    /**
     * Display info scene
     * @param g Graphics
     */
    public void paint(Graphics g){
        drawMenu((Graphics2D)g);
    }


    /**
     * Draw menu
     * @param g2d Graphics
     */
    public void drawMenu(Graphics2D g2d){

        drawContainer(g2d);

        /*
        all the following method calls need a relative
        painting directly into the HomeMenu rectangle,
        so the translation is made here so the other methods do not do that.
         */
        Color prevColor = g2d.getColor();
        Font prevFont = g2d.getFont();

        double x = InfoModel.getMenuFace().getX();
        double y = InfoModel.getMenuFace().getY();

        g2d.translate(x,y);

        //methods calls
        drawText(g2d);
        drawButton(g2d);
        //end of methods calls

        g2d.translate(-x,-y);
        g2d.setFont(prevFont);
        g2d.setColor(prevColor);
    }

    /**
     * Display background
     * @param g2d Graphics
     */
    private void drawContainer(Graphics2D g2d){
        BufferedImage infoBackground;
        try {
            infoBackground = ImageIO.read(getClass().getResource("/info.jpg"));
        } catch (IOException e){
            throw new RuntimeException(e);
        }

        g2d.drawImage(infoBackground, 0, 0, 700, 450, this);
    }

    /**
     * Display all the text
     * @param g2d Graphics
     */
    private void drawText(Graphics2D g2d){

        g2d.setColor(TEXT_COLOR);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D greetingsRect = greetingsFont.getStringBounds(GREETINGS,frc);
        Rectangle2D gameTitleRect = gameTitleFont.getStringBounds(GAME_TITLE,frc);
        Rectangle2D creditsRect = creditsFont.getStringBounds(CREDITS,frc);
        Rectangle2D gameDetail1Rect = infoFont.getStringBounds(GAME_DETAILS1,frc);
        Rectangle2D gameDetail2Rect = infoFont.getStringBounds(GAME_DETAILS2,frc);
        Rectangle2D gameDetail3Rect = infoFont.getStringBounds(GAME_DETAILS3,frc);
        Rectangle2D gameDetail4Rect = infoFont.getStringBounds(GAME_DETAILS4,frc);
        Rectangle2D gameDetail5Rect = infoFont.getStringBounds(GAME_DETAILS5,frc);
        Rectangle2D brickDetail1Rect = infoFont.getStringBounds(BRICK_DETAILS1,frc);
        Rectangle2D brickDetail2Rect = infoFont.getStringBounds(BRICK_DETAILS2,frc);
        Rectangle2D brickDetail3Rect = infoFont.getStringBounds(BRICK_DETAILS3,frc);
        Rectangle2D brickDetail4Rect = infoFont.getStringBounds(BRICK_DETAILS4,frc);
        Rectangle2D brickDetail5Rect = infoFont.getStringBounds(BRICK_DETAILS5,frc);
        Rectangle2D brickDetail6Rect = infoFont.getStringBounds(BRICK_DETAILS6,frc);

        int sX,sY;

        sX = (int)(InfoModel.getMenuFace().getWidth() - greetingsRect.getWidth()) / 2;
        sY = (int)(InfoModel.getMenuFace().getHeight() / 8);

        g2d.setFont(greetingsFont);
        g2d.drawString(GREETINGS,sX,sY);

        sX = (int)(InfoModel.getMenuFace().getWidth() - gameTitleRect.getWidth()) / 2;
        sY += (int) gameTitleRect.getHeight() * 1.1;//add 10% of String height between the two strings

        g2d.setFont(gameTitleFont);
        g2d.drawString(GAME_TITLE,sX,sY);

        sX = (int)(InfoModel.getMenuFace().getWidth() - creditsRect.getWidth()) / 2;
        sY += (int) creditsRect.getHeight() * 1.1;

        g2d.setFont(creditsFont);
        g2d.drawString(CREDITS,sX,sY);

        sX = (int)(InfoModel.getMenuFace().getWidth() - gameDetail1Rect.getWidth()) / 2;
        sY += (int) (InfoModel.getMenuFace().getHeight() / 10);

        g2d.setFont(infoFont);
        g2d.drawString(GAME_DETAILS1,sX,sY);

        sX = (int)(InfoModel.getMenuFace().getWidth() - gameDetail2Rect.getWidth()) / 2;
        sY += (int) gameDetail2Rect.getHeight() * 1.1;

        g2d.drawString(GAME_DETAILS2,sX,sY);

        sX = (int)(InfoModel.getMenuFace().getWidth() - gameDetail3Rect.getWidth()) / 2;
        sY += (int) gameDetail3Rect.getHeight() * 1.1;

        g2d.drawString(GAME_DETAILS3,sX,sY);

        sX = (int)(InfoModel.getMenuFace().getWidth() - gameDetail4Rect.getWidth()) / 2;
        sY += (int) gameDetail4Rect.getHeight() * 1.1;

        g2d.drawString(GAME_DETAILS4,sX,sY);

        sX = (int)(InfoModel.getMenuFace().getWidth() - gameDetail5Rect.getWidth()) / 2;
        sY += (int) gameDetail5Rect.getHeight() * 1.1;

        g2d.drawString(GAME_DETAILS5,sX,sY);

        sX = (int)(InfoModel.getMenuFace().getWidth() - brickDetail1Rect.getWidth()) / 2;
        sY += (int) brickDetail1Rect.getHeight() * 1.1;

        g2d.drawString(BRICK_DETAILS1,sX,sY);

        sX = (int)(InfoModel.getMenuFace().getWidth() - brickDetail2Rect.getWidth()) / 2;
        sY += (int) brickDetail2Rect.getHeight() * 1.1;

        g2d.drawString(BRICK_DETAILS2,sX,sY);

        sX = (int)(InfoModel.getMenuFace().getWidth() - brickDetail3Rect.getWidth()) / 2;
        sY += (int) brickDetail3Rect.getHeight() * 1.1;

        g2d.drawString(BRICK_DETAILS3,sX,sY);

        sX = (int)(InfoModel.getMenuFace().getWidth() - brickDetail4Rect.getWidth()) / 2;
        sY += (int) brickDetail4Rect.getHeight() * 1.1;

        g2d.drawString(BRICK_DETAILS4,sX,sY);

        sX = (int)(InfoModel.getMenuFace().getWidth() - brickDetail5Rect.getWidth()) / 2;
        sY += (int) brickDetail5Rect.getHeight() * 1.1;

        g2d.drawString(BRICK_DETAILS5,sX,sY);

        sX = (int)(InfoModel.getMenuFace().getWidth() - brickDetail6Rect.getWidth()) / 2;
        sY += (int) brickDetail6Rect.getHeight() * 1.1;

        g2d.drawString(BRICK_DETAILS6,sX,sY);
    }

    /**
     * Display all the buttons
     * @param g2d Graphics
     */
    private void drawButton(Graphics2D g2d){

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D backRect = buttonFont.getStringBounds(BACK_TEXT,frc);

        g2d.setFont(buttonFont);

        int x = (InfoModel.getMenuFace().width - InfoModel.getBackButton().width) / 2;
        int y =(int) ((InfoModel.getMenuFace().height - InfoModel.getBackButton().height) * 0.95);

        InfoModel.getBackButton().setLocation(x,y);

        x = (int)(InfoModel.getBackButton().getWidth() - backRect.getWidth()) / 2;
        y = (int)(InfoModel.getBackButton().getHeight() - backRect.getHeight()) / 2;

        x += InfoModel.getBackButton().x;
        y += InfoModel.getBackButton().y + (InfoModel.getBackButton().height * 0.8);

        if(InfoModel.backClicked){
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(InfoModel.getBackButton());
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(BACK_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(InfoModel.getBackButton());
            g2d.drawString(BACK_TEXT,x,y);
        }

    }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return false;
    }

}