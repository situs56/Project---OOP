package src.Entities;

import java.awt.Graphics;

public class Cannon extends Entity {

    public Cannon(float x, float y, int width, int height) {
        super(x, y, width, height);
    }
    
    public void draw(Graphics g) {
        g.drawRect((int) x, (int) y, width, height);
    }
}
