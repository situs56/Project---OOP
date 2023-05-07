package src.Object;

import java.awt.Rectangle;

public class GameObject {
    
    protected float x;
    protected float y;
    protected int height;
    protected int width;
    protected static Rectangle hitBox;

    public GameObject(float x, float y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }
}
