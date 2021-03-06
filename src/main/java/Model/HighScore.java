package Model;

import Controller.GameFrame;
import Controller.HighScoreController;
import Controller.Time;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Display high score.
 * Record high score.
 * @author Yong Wei Hian
 * @since 12/11/2021
 * @version 1.2
 */
public class HighScore extends JComponent {

    private static final String TITLE = "High Score";
    private static final String BACK_TEXT = "Back";

    private BasicStroke borderStoke;

    private Rectangle menuFace;
    private static Rectangle backButton;

    private static GameFrame owner;

    private Font titleFont;
    private Font textFont;
    private Font buttonFont;

    private static boolean backBtnClicked;

    private static int i;
    private static int[][] highScore;

    /**
     * Set up the high score function
     * @param owner Game frame
     * @param area Dimension of high score
     */
    public HighScore(GameFrame owner, Dimension area){
        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addMouseListener(new HighScoreController());
        this.addMouseMotionListener(new HighScoreController());

        setOwner(owner);

        menuFace = new Rectangle(new Point(0,0),area);
        this.setPreferredSize(area);

        Dimension btnDim = new Dimension(area.width / 3, area.height / 13);
        setBackButton(new Rectangle(btnDim));

        borderStoke = new BasicStroke(2);

        highScore = new int[10][3];

        buttonFont = new Font("Monospaced",Font.PLAIN,20);
        titleFont = new Font("Monospaced",Font.BOLD,60);
        textFont = new Font("Monospaced",Font.PLAIN,20);

        readFile();
    }

    /**
     * Read the high score file to get the data
     */
    public static void readFile(){
        i = 0;
        try{
            File file = new File("src/main/resources/highscore.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                int j;
                for (j = 0; j < 3; j++){
                    highScore[i][j] = scanner.nextInt();
                }
                i++;
            }
            scanner.close();
        } catch (FileNotFoundException e){
            System.out.println("Error in displaying high score.");
            e.printStackTrace();
        }
    }

    /**
     * Write high score into the high score file
     */
    public static void writeFile(){
        try {
            FileWriter writer = new FileWriter("src/main/resources/highscore.txt");
            for(i = 0; i < 9; i++){
                if(highScore[i][0] == 0 && highScore[i][1] == 0 && highScore[i][2] == 0){
                    break;
                }
                writer.write( "\n" + highScore[i][0] + "\t" + highScore[i][1] + "\t" + highScore[i][2]);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Sort the score according to score and time
     */
    public static void sortScore() {
        int[] temp;
        if (highScore[8][0] < Wall.getTotalBrickDestroyed()) {
            highScore[8][0] = Wall.getTotalBrickDestroyed();
            highScore[8][1] = Time.getMinutes();
            highScore[8][2] = Time.getSeconds();
        }

        for (i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (highScore[j][0] > highScore[i][0]) {
                    temp = highScore[i];
                    highScore[i] = highScore[j];
                    highScore[j] = temp;
                }
            }
        }

        for (i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (highScore[j][0] == highScore[i][0] && highScore[j][1] < highScore[i][1]) {
                    temp = highScore[i];
                    highScore[i] = highScore[j];
                    highScore[j] = temp;
                }
            }
        }

        for (i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (highScore[j][0] == highScore[i][0] && highScore[j][1] == highScore[i][1] && highScore[j][2] < highScore[i][2]) {
                    temp = highScore[i];
                    highScore[i] = highScore[j];
                    highScore[j] = temp;
                }
            }
        }
        writeFile();
    }

    /**
     * Get game frame
     * @return Game frame
     */
    public static GameFrame getOwner() {
        return owner;
    }

    /**
     * Set game frame
     * @param owner Game frame
     */
    public static void setOwner(GameFrame owner) {
        HighScore.owner = owner;
    }

    /**
     * Display the high score screen
     * @param g Graphics
     */
    public void paint(Graphics g){
        drawMenu((Graphics2D)g);
    }

    /**
     * Draw high score menu
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

    /**
     * Get the background of high score
     * @param g2d Graphics
     */
    private void drawContainer(Graphics2D g2d){
        BufferedImage highScoreBackground;
        try {
            highScoreBackground = ImageIO.read(getClass().getResource("/highScore.jpg"));
        } catch (IOException e){
            throw new RuntimeException(e);
        }

        g2d.drawImage(highScoreBackground, 0, 0, 600, 450, this);
    }

    /**
     * Display the text in the button
     * @param g2d Graphics
     */
    private void drawText(Graphics2D g2d){

        g2d.setColor(Color.GRAY.darker());

        FontRenderContext frc = g2d.getFontRenderContext();

        String highScoreText = String.format("#%02d %dBricks %dMinutes %dSeconds", i + 1, highScore[i][0], highScore[i][1], highScore[i][2]);

        Rectangle2D titleRect = titleFont.getStringBounds(TITLE,frc);
        Rectangle2D scoreRect = textFont.getStringBounds(highScoreText,frc);

        int sX,sY;

        sX = (int)(menuFace.getWidth() - titleRect.getWidth()) / 2;
        sY = (int)(menuFace.getHeight() / 5);

        g2d.setFont(titleFont);
        g2d.drawString(TITLE,sX,sY);

        sY += (int) scoreRect.getHeight() * 0.5;

        g2d.setFont(textFont);
        g2d.setColor(Color.BLACK);
        for(i = 0; i < 8; i++){
            if(highScore[i][0] == 0 && highScore[i][1] == 0 && highScore[i][2] == 0){
                break;
            }
            sX = (int)(menuFace.getWidth() - scoreRect.getWidth()) / 2;
            sY += (int) scoreRect.getHeight() * 1.1;
            highScoreText = String.format("#%02d %02dScore %02dMin %02dSec", i+1, highScore[i][0], highScore[i][1], highScore[i][2]);
            g2d.drawString(highScoreText,sX,sY);
        }

    }

    /**
     * Display buttons
     * @param g2d Graphics
     */
    private void drawButton(Graphics2D g2d){

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D txtRect = buttonFont.getStringBounds(BACK_TEXT,frc);

        g2d.setFont(buttonFont);

        int x = (menuFace.width - backButton.width) / 2;
        int y =(int) ((menuFace.height - backButton.height) * 0.85);

        backButton.setLocation(x,y);

        x = (int)(backButton.getWidth() - txtRect.getWidth()) / 2;
        y = (int)(backButton.getHeight() - txtRect.getHeight()) / 2;

        x += backButton.x;
        y += backButton.y + (backButton.height * 0.8);

        g2d.setColor(Color.white);
        g2d.fill(backButton);
        g2d.setStroke(borderStoke);
        g2d.setColor(Color.BLACK);

        if(backBtnClicked){
            Color tmp = g2d.getColor();
            g2d.setColor(Color.RED);
            g2d.draw(backButton);
            g2d.setColor(Color.RED);
            g2d.drawString(BACK_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(backButton);
            g2d.setColor(Color.BLACK);
            g2d.drawString(BACK_TEXT,x,y);
        }
    }

    /**
     * Get back button
     * @return Back button
     */
    public static Rectangle getBackButton() {
        return backButton;
    }

    /**
     * Set back button
     * @param backButton Back button
     */
    public static void setBackButton(Rectangle backButton) {
        HighScore.backButton = backButton;
    }

    /**
     * Determine is back button being clicked
     * @return clicked of back button
     */
    public static Boolean isBackBtnClicked() {
        return backBtnClicked;
    }

    /**
     * Set the clicked of back button
     * @param backBtnClicked Clicked of back button
     */
    public static void setBackBtnClicked(Boolean backBtnClicked) {
        HighScore.backBtnClicked = backBtnClicked;
    }
}
