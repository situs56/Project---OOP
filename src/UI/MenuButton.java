package src.UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

import src.Gamestate.Gamestate;

public class MenuButton {
    private int xPos, yPos;
    private Gamestate state;
    private Rectangle bounds;
    private boolean mouseOver, mousePressed;

    private BufferedImage img;

    public MenuButton(int xPos, int yPos, Gamestate state) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.state = state;
        initBounds();
        ImportImg();
    }

    public void draw(Graphics g) {
        // g.drawRect(xPos, yPos, 300, 100);
        // g.setColor(Color.BLACK);
        // g.drawString("Press any button to play", 300, 100);
        g.drawImage(img, (int) xPos, (int) yPos, 490, 192, null);
    }

    private void ImportImg() {
        InputStream is = getClass().getResourceAsStream("/res/playbutton.png");
        try {
            img = ImageIO.read(is);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initBounds() {
        bounds = new Rectangle(xPos, yPos, 490, 192);
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMOusePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public void applyGamestate() {
        Gamestate.state = state;
    }

    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public Gamestate getState() {
        return state;
    }
}