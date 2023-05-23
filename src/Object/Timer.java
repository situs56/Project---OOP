package src.Object;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Timer {
    private static Font font;
    private static Color color;
    private static int x;
    private static int y;
    private static long startTime;


    public Timer(int x, int y, Font font, Color color) {
        this.x = x;
        this.y = y;
        this.font = font;
        this.color = color;
        this.startTime = 0;
    }

    public void startTimer() {
        startTime = System.currentTimeMillis();
    }

    public static long elapsedTime() {
        long currentTime = System.currentTimeMillis();
        return currentTime - startTime;
    }

    public static void draw(Graphics g) {
        g.setColor(color);
        g.setFont(font);
        g.drawString("Time: " + elapsedTime() / 1000 + " seconds", x, y);
    }
}
