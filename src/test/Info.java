package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;


public class Info extends JComponent implements MouseListener, MouseMotionListener {

    private static final String GREETINGS = "Welcome to Brick Destroy";
    private static final String GAME_TITLE = "Info";
    private static final String CREDITS = "Version 1.2";
    private static final String GAME_DETAILS1 = "Player need to destroy all the bricks without running out of balls.";
    private static final String GAME_DETAILS2 = "This game consist of 4 levels. Level 1 with all clay bricks.";
    private static final String GAME_DETAILS3 = "Level 2 with clay and cement bricks. Level 3 with clay and steel bricks.";
    private static final String GAME_DETAILS4 = "Level 4 with cement and super bricks. Each level will be given 3 balls.";
    private static final String BRICK_DETAILS1 = "Clay brick can withstand 1 impact. Cement brick can withstand 2 impacts.";
    private static final String BRICK_DETAILS2 = "Steel brick can withstand 1 impact but only have 0.4 to receive an impact.";
    private static final String BRICK_DETAILS3 = "Super brick can withstand 2 impact but only have 0.2 to receive an impact.";
    private static final String BACK_TEXT = "Back";

    private static final Color BG_COLOR = Color.GRAY.darker();
    private static final Color TEXT_COLOR = Color.GREEN;
    private static final Color CLICKED_BUTTON_COLOR = BG_COLOR.brighter();
    private static final Color CLICKED_TEXT = Color.WHITE;

    private Rectangle menuFace;
    private Rectangle backButton;

    private BasicStroke borderStoke;

    private Font greetingsFont;
    private Font gameTitleFont;
    private Font creditsFont;
    private Font infoFont;
    private Font buttonFont;

    private GameFrame owner;

    private boolean backClicked;


    public Info(GameFrame owner,Dimension area){

        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.owner = owner;



        menuFace = new Rectangle(new Point(0,0),area);
        this.setPreferredSize(area);

        Dimension btnDim = new Dimension(area.width / 3, area.height / 12);
        backButton = new Rectangle(btnDim);

        borderStoke = new BasicStroke();

        greetingsFont = new Font("Noto Mono",Font.PLAIN,25);
        gameTitleFont = new Font("Noto Mono",Font.BOLD,40);
        creditsFont = new Font("Monospaced",Font.PLAIN,10);
        buttonFont = new Font("Monospaced",Font.PLAIN,30);
        infoFont = new Font("Monospaced",Font.PLAIN,15);



    }


    public void paint(Graphics g){
        drawMenu((Graphics2D)g);
    }


    public void drawMenu(Graphics2D g2d){

        drawContainer(g2d);

        /*
        all the following method calls need a relative
        painting directly into the HomeMenu rectangle,
        so the translation is made here so the other methods do not do that.
         */
        Color prevColor = g2d.getColor();
        Font prevFont = g2d.getFont();

        double x = menuFace.getX();
        double y = menuFace.getY();

        g2d.translate(x,y);

        //methods calls
        drawText(g2d);
        drawButton(g2d);
        //end of methods calls

        g2d.translate(-x,-y);
        g2d.setFont(prevFont);
        g2d.setColor(prevColor);
    }

    private void drawContainer(Graphics2D g2d){
        Color prev = g2d.getColor();

        g2d.setColor(BG_COLOR);
        g2d.fill(menuFace);

        Stroke tmp = g2d.getStroke();

        g2d.draw(menuFace);

        g2d.setStroke(borderStoke);
        g2d.draw(menuFace);

        g2d.setStroke(tmp);

        g2d.setColor(prev);
    }

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
        Rectangle2D brickDetail1Rect = infoFont.getStringBounds(BRICK_DETAILS1,frc);
        Rectangle2D brickDetail2Rect = infoFont.getStringBounds(BRICK_DETAILS2,frc);
        Rectangle2D brickDetail3Rect = infoFont.getStringBounds(BRICK_DETAILS3,frc);

        int sX,sY;

        sX = (int)(menuFace.getWidth() - greetingsRect.getWidth()) / 2;
        sY = (int)(menuFace.getHeight() / 8);

        g2d.setFont(greetingsFont);
        g2d.drawString(GREETINGS,sX,sY);

        sX = (int)(menuFace.getWidth() - gameTitleRect.getWidth()) / 2;
        sY += (int) gameTitleRect.getHeight() * 1.1;//add 10% of String height between the two strings

        g2d.setFont(gameTitleFont);
        g2d.drawString(GAME_TITLE,sX,sY);

        sX = (int)(menuFace.getWidth() - creditsRect.getWidth()) / 2;
        sY += (int) creditsRect.getHeight() * 1.1;

        g2d.setFont(creditsFont);
        g2d.drawString(CREDITS,sX,sY);

        sX = (int)(menuFace.getWidth() - gameDetail1Rect.getWidth()) / 2;
        sY += (int) (menuFace.getHeight() / 10);

        g2d.setFont(infoFont);
        g2d.drawString(GAME_DETAILS1,sX,sY);

        sX = (int)(menuFace.getWidth() - gameDetail2Rect.getWidth()) / 2;
        sY += (int) gameDetail2Rect.getHeight() * 1.1;

        g2d.drawString(GAME_DETAILS2,sX,sY);

        sX = (int)(menuFace.getWidth() - gameDetail3Rect.getWidth()) / 2;
        sY += (int) gameDetail3Rect.getHeight() * 1.1;

        g2d.drawString(GAME_DETAILS3,sX,sY);

        sX = (int)(menuFace.getWidth() - gameDetail4Rect.getWidth()) / 2;
        sY += (int) gameDetail4Rect.getHeight() * 1.1;

        g2d.drawString(GAME_DETAILS4,sX,sY);

        sX = (int)(menuFace.getWidth() - brickDetail1Rect.getWidth()) / 2;
        sY += (int) brickDetail1Rect.getHeight() * 1.1;

        g2d.drawString(BRICK_DETAILS1,sX,sY);

        sX = (int)(menuFace.getWidth() - brickDetail2Rect.getWidth()) / 2;
        sY += (int) brickDetail2Rect.getHeight() * 1.1;

        g2d.drawString(BRICK_DETAILS2,sX,sY);

        sX = (int)(menuFace.getWidth() - brickDetail3Rect.getWidth()) / 2;
        sY += (int) brickDetail3Rect.getHeight() * 1.1;

        g2d.drawString(BRICK_DETAILS3,sX,sY);
    }

    private void drawButton(Graphics2D g2d){

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D backRect = buttonFont.getStringBounds(BACK_TEXT,frc);

        g2d.setFont(buttonFont);

        int x = (menuFace.width - backButton.width) / 2;
        int y =(int) ((menuFace.height - backButton.height) * 0.8);

        backButton.setLocation(x,y);

        x = (int)(backButton.getWidth() - backRect.getWidth()) / 2;
        y = (int)(backButton.getHeight() - backRect.getHeight()) / 2;

        x += backButton.x;
        y += backButton.y + (backButton.height * 0.8);

        if(backClicked){
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(backButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(BACK_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(backButton);
            g2d.drawString(BACK_TEXT,x,y);
        }

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(backButton.contains(p)){
            owner.backtoHomeMenu();

        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(backButton.contains(p)){
            backClicked = true;
            repaint(backButton.x,backButton.y,backButton.width+1,backButton.height+1);
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(backClicked ){
            backClicked = false;
            repaint(backButton.x,backButton.y,backButton.width+1,backButton.height+1);
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }


    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(backButton.contains(p))
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());

    }
}

