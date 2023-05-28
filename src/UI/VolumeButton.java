package src.UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class VolumeButton extends PauseButton {

    private static final int V_WIDTH = 32;
    public static final int V_HEIGHT = 64;
    public static final int S_WIDTH = 350;

    private BufferedImage img, img2;
    private boolean mouseOver, mousePressed;
    private int buttonX, minX, maxX;
    private float floatValue = 0f;

    public VolumeButton(int x, int y, int width, int height) {
        super(x + width / 2, y, V_WIDTH, height);
        bounds.x -= V_WIDTH / 2;
        buttonX = x + width / 2;
        this.x = x;
        this.width = width;
        minX = x + V_WIDTH / 2;
        maxX = x + width - V_WIDTH / 2;
        ImportScrollImg();
        ImportBarImg();
    }

    public void update() {
        int index = 0;
        if (mouseOver)
            index = 1;
        if (mousePressed)
            index = 2;

    }

    public void draw(Graphics g) {
        // g.setColor(Color.RED);
        // g.fillRect(x, y, width, height);
        // g.setColor(Color.BLUE);
        // g.fillRect(buttonX - V_WIDTH / 2, y, V_WIDTH, height);
        g.drawImage(img2, x, y, width, height, null);
        g.drawImage(img.getSubimage(11, 0, 9, 32), (buttonX - V_WIDTH / 2), y, V_WIDTH, height, null);
    }

    private void ImportScrollImg() {
        InputStream is = getClass().getResourceAsStream("/res/scroll.png");
        try {
            img = ImageIO.read(is);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ImportBarImg() {
        InputStream is2 = getClass().getResourceAsStream("/res/Bar.png");
        try {
            img2 = ImageIO.read(is2);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeX(int x) {
        if (x < minX)
            buttonX = minX;
        else if (x > maxX)
            buttonX = maxX;
        else
            buttonX = x;
        updateFloatValue();
        bounds.x = buttonX - V_WIDTH / 2;

    }

    public void updateFloatValue() {
        float range = maxX - minX;
        float value = buttonX - minX;
        floatValue = value / range;
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

    public float getFloatValue() {
        return floatValue;
    }
}