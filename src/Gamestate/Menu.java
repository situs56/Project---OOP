package src.Gamestate;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import src.Game.Game;
import src.UI.MenuButton;

public class Menu extends State implements Methods {
    private MenuButton[] buttons = new MenuButton[1];

    public Menu(Game game) {
        super(game);
        loadButtons(); 
        //TODO Auto-generated constructor stub
    }
    private void loadButtons() {
		buttons[0] = new MenuButton(100,300,0,Gamestate.PLAYING);
    }
    @Override
    public void update() {
        // TODO Auto-generated method stub
    }

    @Override
    public void draw(Graphics g) {
     for(MenuButton mb : buttons)
        mb.draw(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER);
            Gamestate.state = Gamestate.PLAYING;
    }
    public void mouseDragged(MouseEvent e)
    {};

    @Override
    public void keyReleased(KeyEvent e) {
      
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mousePressed(MouseEvent e) 
    {for (MenuButton mb : buttons) {
        if (isIn(e, mb)) {
            mb.setMousePressed(true);
        }
    }

}

@Override
public void mouseReleased(MouseEvent e) {
    for (MenuButton mb : buttons) {
        if (isIn(e, mb)) {
            if (mb.isMousePressed())
                mb.applyGamestate();
            break;
        }
    }

    resetButtons();

}
private void resetButtons() {
    for (MenuButton mb : buttons)
        mb.resetBools();

}

@Override
	public void mouseMoved(MouseEvent e) {
		for (MenuButton mb : buttons)
			mb.setMouseOver(false);

		for (MenuButton mb : buttons)
			if (isIn(e, mb)) {
				mb.setMouseOver(true);
				break;
			}

	}
}
