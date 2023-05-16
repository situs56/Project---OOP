package src.Entities;

import java.awt.Graphics;
import java.util.Random;

import src.Game.Game;
import src.Level.Level;
import src.Object.Platform;

public class Arrow extends Entity {

    private TrapManager trapManager;
    private Random random;

    private float speed = 0.5f;

    public Arrow(float x, float y, int width, int height, TrapManager trapManager) {
        super(x, y, width, height);
        this.trapManager = trapManager;
        this.random = new Random();
    }

    public void updatePos() {
        this.y += speed; 
        if (this.y >= Game.game_Height) {
            resetPosition(); 
        }
    }

    private void resetPosition() {
        this.y = 0;
        this.x = random.nextInt(Game.game_Width - width);
    }

    public void UpdateArrow() {
        updatePos();
        updateHitBox();
    }

    public void draw(Graphics g) {
        g.drawRect((int) x, (int) y, width, height);
        drawHitBox(g);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

	public int getY() {
		return 0;
	}
}
