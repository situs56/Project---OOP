package src.Entities;

import static src.Game.Game.game_Height;
import static src.Game.Game.game_Width;

import java.awt.Graphics;

public class Ball extends Entity {

    private float speed = 0.5f;

    public Ball(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    public void updateBall() {
        updatePos();
        updateHitBox();
    }

    public void updatePos() {
        x += speed;
        y += speed;

        if (x > game_Width) {
            x = -width;
        } else if (x < -width) {
            x = game_Width;
        }

        if (y > game_Height) {
            y = -height;
        } else if (y < -height) {
            y = game_Height;
        }
    }

    public void draw(Graphics g) {
        drawHitBox(g);
    }
}
