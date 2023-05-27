package src.UI;
import java.awt.Graphics;
import java.awt.Rectangle;

import src.Gamestate.Gamestate;

public class MenuButton
{
        private int xPos, yPos, rowIndex;
        private Gamestate state;
        private Rectangle bounds;
        private boolean mouseOver, mousePressed;

        public MenuButton(int xPos, int yPos, int rowIndex, Gamestate state)
        {
            this.xPos =xPos;
            this.yPos = yPos;
            this.rowIndex=rowIndex;
            this.state=state;
            initBounds();
        }
        public void draw(Graphics g)
       { g.drawRect(xPos,yPos,300,100);
    }
    private void initBounds()
    {bounds = new Rectangle(xPos,yPos,200,200);}
    public boolean isMouseOver()
    {return mouseOver;}
    public void setMouseOver(boolean mouseOver)
    {this.mouseOver=mouseOver;}
    public boolean isMousePressed()
    {return mousePressed;}
    public void setMOusePressed(boolean mousePressed)
    {this.mousePressed = mousePressed;}
    public void applyGamestate()
    {Gamestate.state = state;}
    public void resetBools(){
        mouseOver = false;
        mousePressed =false;
    }
    public Rectangle getBounds() {
		return bounds;
	}
    public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}

} 