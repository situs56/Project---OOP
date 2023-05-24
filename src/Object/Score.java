package src.Object;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Score {
    private static int score;
    private static Font font;
    private static Color color;
    private static int x;
    private static int y;

    public Score(int x, int y, Font font, Color color) {
        this.x = x;
        this.y = y;
        this.font = font;
        this.color = color;
        this.score = 0;
    }

    public void ScoreIncrease(int Increase) {
        score += Increase;
    }

    public static void draw(Graphics g) {
        g.setColor(color);
        g.setFont(font);
        g.drawString("Score: " + score, x, y);
    }

    public void reset() {
        score = 0;
    }
}
