package Model;

import Controller.GameFrame;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class InfoModelTest {
    InfoModel infoModel = new InfoModel(new GameFrame(), new Dimension());//run the class to test the functionalities

    @Test
    void getOwner() {
        infoModel.setOwner(new GameFrame());
        assertEquals(new GameFrame(),InfoModel.getOwner());
    }

    @Test
    void getBackButton() {
        InfoModel.setBackButton(new Rectangle());
        assertEquals(new Rectangle(),InfoModel.getBackButton());
    }

    @Test
    void setBackButton() {
        InfoModel.setBackButton(new Rectangle());
        assertEquals(new Rectangle(),InfoModel.getBackButton());
    }

    @Test
    void getMenuFace() {
        InfoModel.setMenuFace(new Rectangle());
        assertEquals(new Rectangle(),InfoModel.getMenuFace());
    }

    @Test
    void setMenuFace() {
        InfoModel.setMenuFace(new Rectangle());
        assertEquals(new Rectangle(),InfoModel.getMenuFace());
    }

    @Test
    void setOwner() {
        infoModel.setOwner(new GameFrame());
        assertEquals(new GameFrame(),InfoModel.getOwner());
    }
}