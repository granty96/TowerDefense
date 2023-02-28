/*
 * @author James Donaldson
 * @version 4/26/2022
 * 
 * tower2menu object
 * 
 * Artwork by Caleb Dean & James Donaldson
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class Tower2Menu extends Tower
{
	private GameControl control;
	//Fields
	public Tower2Menu (GameState state,GameControl control, Point pos)
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
			if (deltaX < 40 && deltaY < 40 && state.getMoney() >= 500)
			{
				//Tower clicked on
				state.consumeClick();
				state.changeMoney(-500);
				state.addAnimatable(new MoneyEffect(state,pos.x,pos.y,this));
				state.addAnimatable(new Tower2Moving(state,control, new Point(state.getMouseX(),state.getMouseY())));
			}
		}
	}

	@Override
	public void draw(Graphics g, GameView view)
	{
		view.drawCenteredImage(g,"resources/tower2.png",pos);
	}

	@Override
	protected Point getPos() {
		// TODO Auto-generated method stub
		return this.pos;
	}
	
}
