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
    private static long elapsedTime;
    private static boolean isPaused;

    public Timer(int x, int y, Font font, Color color) {
        this.x = x;
        this.y = y;
        this.font = font;
        this.color = color;
        this.startTime = 0;
        this.elapsedTime = 0;
        this.isPaused = false;
    }

    public void startTimer() {
        if (!isPaused) {
            startTime = System.currentTimeMillis();
        } else {
            long pauseTime = System.currentTimeMillis() - elapsedTime;
            startTime = System.currentTimeMillis() - pauseTime;
            isPaused = false;
        }
    }

    public void pauseTimer() {
        if (!isPaused) {
            elapsedTime = System.currentTimeMillis() - startTime;
            isPaused = true;
        }
    }

    public void continueTimer() {
        if (isPaused) {
            startTime = System.currentTimeMillis() - elapsedTime;
            isPaused = false;
        }
    }

    public static long getElapsedTime() {
        if (isPaused) {
            return elapsedTime;
        } else {
            return System.currentTimeMillis() - startTime;
        }
    }

    public static void draw(Graphics g) {
        g.setColor(color);
        g.setFont(font);
        g.drawString("Time: " + getElapsedTime() / 1000 + " seconds", x, y);
    }
}
