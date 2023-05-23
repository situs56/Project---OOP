package src.Object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Platform2 extends GameObject {

    private static List<Rectangle> platform2Hitboxes = new ArrayList<>();

    public Platform2(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    public void drawPlatform2(Graphics g) {
        g.setColor(Color.black);
        g.fillRect((int) x, (int) y, width, height);
    }

    public static List<Rectangle> platform2HitboxList() {
        return platform2Hitboxes;
    }

    public static void addPlatform2Hitbox(Rectangle hitbox) {
        platform2Hitboxes.add(hitbox);
    }
}
