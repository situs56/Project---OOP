package src.UI;

import java.awt.Color;
import java.awt.Graphics;

public class VolumeButton extends PauseButton {
    private static final int V_WIDTH = 29;
    public static final int V_HEIGHT = 44;
    public static final int S_WIDTH = 350;
    private boolean mouseOver, mousePressed;
    private int buttonX, minX, maxX;

    public VolumeButton(int x, int y, int width, int height) {
        super(x + width / 2, y, V_WIDTH, height);
        bounds.x -= V_WIDTH / 2;
        buttonX = x + width / 2;
        this.x = x;
        this.width = width;
        minX = x + V_WIDTH / 2;
        maxX = x + width - V_WIDTH / 2;
    }
    /*
     * public void update() {
     * index = 0;
     * if (mouseOver)
     * index = 1;
     * if (mousePressed)
     * index = 2;
     * 
     * }
     */

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLUE);
        g.fillRect(buttonX - V_WIDTH / 2, y, V_WIDTH, height);
    }

    public void changeX(int x) {
        if (x < minX)
            buttonX = minX;
        else if (x > maxX)
            buttonX = maxX;
        else
            buttonX = x;

        bounds.x = buttonX - V_WIDTH / 2;

    }

    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }

    private boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

}