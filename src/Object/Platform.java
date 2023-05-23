package src.Object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import static src.Game.Game.game_Height;
import static src.Game.Game.game_Width;

public class Platform extends GameObject {

    private static List<Rectangle> platformHitboxes = new ArrayList<>();

    public Platform(float x, float y, int width, int height) {    
       super(x, y, width, height);
    }

    public void drawPlatform(Graphics g) {
        g.setColor(Color.red);
        g.drawRect((int) x, (int) y, width, height);
    }

    public static List<Rectangle> platformHitboxList() {
        return platformHitboxes;
    }

    public static void addPlatformHitbox(Rectangle hitbox) {
        platformHitboxes.add(hitbox);
    }

    public static float getY() {
        return game_Height - (game_Height/5);
    }

    public float getHeight() {
        return height;
    }
}
