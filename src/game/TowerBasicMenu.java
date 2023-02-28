/*
 * @author James Donaldson
 * @version 4/26/2022
 * 
 * towerbasicmenu object
 * 
 * Artwork by Caleb Dean & James Donaldson
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class TowerBasicMenu extends Tower
{
	private GameControl control;
	//Fields
	public TowerBasicMenu (GameState state,GameControl control, Point pos)
	{
		super(state, pos);
		this.control = control;
	}
	
	@Override
	public void update(double timeElapse) 
	{
		if(state.getMouseClicked())
		{
	
			int deltaX = Math.abs(pos.x-state.getMouseX());
			int deltaY = Math.abs(pos.y-state.getMouseY());
			if (deltaX < 40 && deltaY < 40 && state.getMoney() >=100)
			{
				//Tower clicked on
				state.consumeClick();
				state.changeMoney(-100);
				state.addAnimatable(new MoneyEffect(state,pos.x,pos.y,this));
				state.addAnimatable(new TowerBasicMoving(state,control, new Point(state.getMouseX(),state.getMouseY())));
			}
		}
	}

	@Override
	public void draw(Graphics g, GameView view)
	{
		view.drawCenteredImage(g,"resources/basic1.png",pos);
	}

	@Override
	protected Point getPos() {
		// TODO Auto-generated method stub
		return this.pos;
	}
	
}
