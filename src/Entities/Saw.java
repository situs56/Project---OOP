package src.Entities;

import java.awt.Graphics;

import src.Object.Platform;

import static src.Game.Game.game_Height;
import static src.Game.Game.game_Width;

public class Saw extends Entity {

    private TrapManager trapManager;
    private Platform platform;

    private float speed = 0.5f;
    private boolean reversed = false;

    public Saw(float x, float y, int width, int height, TrapManager trapManager) {
        super(x, y, width, height);
        this.trapManager = trapManager;
    }

    void updateSaw() {
        updatePos();
        updateHitBox();
    }

    public void updatePos() {
        if (!reversed) {
            if (x <= 0 && y <= platform.getY() - height) {
                y += speed;
            } else if (x <= game_Width - width && y >= platform.getY() - height) {
                x += speed;
            } else if (x >= game_Width - width && y > 0) {
                y -= speed;
            } if (y <= 0) 
                reversed = true;
        }

        if (reversed) {
            if (x >= game_Width - width && y <= platform.getY() - height) {
                y += speed;
            } else if (x >= 0 && y >= platform.getY() - height) {
                x -= speed;
            } else if (x <= 0 && y > 0) {
                y -= speed;
            } if (y <= 0) 
                reversed = false;
        }
    }

    public void draw(Graphics g) {
        g.drawRect((int) x, (int) y, width, height);
        drawHitBox(g);
    }
}
