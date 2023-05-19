package src.Object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import static src.Game.Game.game_Height;
import static src.Game.Game.game_Width;

public class Platform extends GameObject {

    public Platform(float x, float y, int height, int width) {    
       super(x, y, height, width);
    }

    public static Rectangle platformHitbox() {
        hitBox = new Rectangle(0, game_Height - (game_Height/5), game_Width, game_Height/5);
        return hitBox;
    }

    public static void drawPlatform(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(0, game_Height - (game_Height/5), game_Width, game_Height/5);
    }

    public static float getY() {
        return game_Height - (game_Height/5);
    }

    public float getHeight() {
        return height;
    }
}
