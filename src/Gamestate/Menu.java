package src.Gamestate;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import src.Game.Game;

public class Menu extends State implements Methods {

    public Menu(Game game) {
        super(game);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
    }

    @Override
    public void draw(Graphics g) {
        g.drawString("MENU", Game.game_Width/2, 200);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER);
            Gamestate.state = Gamestate.PLAYING;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }
    
}
