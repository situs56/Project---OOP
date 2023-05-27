package src.UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

import src.Gamestate.Gamestate;

public class MenuButton {
    private int xPos, yPos, rowIndex;
    private Gamestate state;
    private Rectangle bounds;
    private boolean mouseOver, mousePressed;

    private BufferedImage[] animations;
    private int aniTick, aniIndex, aniSpeed = 300;

    public MenuButton(int xPos, int yPos, int rowIndex, Gamestate state) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.state = state;
        initBounds();
        loadAnimations();
    }

    public void updateMenuButton() {
        updateAnimationTick();
    }

    public void draw(Graphics g) {
        // g.drawRect(xPos, yPos, 300, 100);
        // g.setColor(Color.BLACK);
        // g.drawString("Press any button to play", 300, 100);
        g.drawImage(animations[aniIndex], xPos, yPos, 300, 100, null);
    }

    private void loadAnimations() {
        InputStream is = getClass().getResourceAsStream("/res/button.png");
        try {
            BufferedImage img = ImageIO.read(is);

            animations = new BufferedImage[2];
            for (int i = 0; i < 2; i++)
                animations[i] = img.getSubimage(i * 188, 0, 188, 92);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateAnimationTick() {
        aniIndex = 0;
        if (mousePressed)
            aniIndex = 1;
    }

    private void initBounds() {
        bounds = new Rectangle(xPos, yPos, 200, 200);
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

}