/*
 * @author James Donaldson
 * @version 4/26/2022
 * 
 * draws and updates life bar base on enemy
 * 
 * Artwork by Caleb Dean & James Donaldson
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class LifeBar implements Animatable
{

	private int life;
	private Enemy e;
	private GameState state;
	private Point pos;
	private String imageState;
	private GameControl control;
	
	public LifeBar(GameState state, Enemy e,GameControl control)
	{
		this.e = e;
		this.state = state;
		this.control = control;
		if(e instanceof Gilbert)
			this.pos = new Point(e.getPos().x,e.getPos().y-35);
		if(e instanceof Harold)
			this.pos = new Point(e.getPos().x,e.getPos().y-50);
		this.imageState = "resources/lifeBarFull.png";		
	}

	@Override
	public void update(double timeElapse) 
	{
		
		this.life = e.getLife();
		if(e.getLife() <= 0)
			state.removeAnimatable(this);
		if(e instanceof Gilbert)
		{
			this.pos = new Point(e.getPos().x,e.getPos().y-35);
			if(e.getLife() == 2)
			{
				this.imageState = "resources/lifeBarFull.png";
			}
			else if (e.getLife() ==1)
			{
				this.imageState = "resources/lifeBarHalf.png";
			}
		}
		if(e instanceof Harold)
		{
			this.pos = new Point(e.getPos().x,e.getPos().y-60);
			if(e.getLife() ==5)
			{
				this.imageState = "resources/lifeBarFull.png";
			}
			if(e.getLife() ==4)
			{
				this.imageState = "resources/lifeBar80.png";
			}
			if(e.getLife() ==3)
			{
				this.imageState = "resources/lifeBar60.png";
			}
			if(e.getLife() ==2)
			{
				this.imageState = "resources/lifeBar40.png";
			}
			if(e.getLife() ==1)
			{
				this.imageState = "resources/lifeBar20.png";
			}
		}
	}

	@Override
	public void draw(Graphics g, GameView view) 
	{
		if(control.getGameStart())
			view.drawCenteredImage(g, imageState, pos);
	}

}
