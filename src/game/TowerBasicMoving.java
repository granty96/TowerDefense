/*
 * @author James Donaldson
 * @version 4/26/2022
 * 
 * towerbasicmoving object
 * 
 * Artwork by Caleb Dean & James Donaldson
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class TowerBasicMoving extends Tower
{
	
	//Fields
	private GameControl control;
	
	public TowerBasicMoving (GameState state,GameControl control, Point pos)
	{
		super(state, pos);
		this.control = control;
		this.pos = pos;
	}
	
	@Override
	public void update(double timeElapse) 
	{
		this.pos.x = state.getMouseX();
		this.pos.y = state.getMouseY();
		if (state.getMouseClicked())
		{
			state.consumeClick();

			if(control.loadImage("resources/pathMask.png").getRGB(this.pos.x,this.pos.y) == -16777216)
			{
				return;
			}
			else
			{
				state.addAnimatable(new TowerBasic(state,pos));
				state.removeAnimatable(this);
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
