/*
 * @author James Donaldson
 * @version 4/26/2022
 * 
 * creates smallExplostion effect when damaging enemy
 * 
 * Artwork by Caleb Dean & James Donaldson
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class SmallExplosion extends Effect
{

	private double timer;
	public SmallExplosion(GameState state, double x, double y)
	{
		super(state, x, y);
		this.timer = 0;
	}

	@Override
	public void update(double timeElapse) 
	{
		timer+=timeElapse;
		if(timer > 0.5)
		{
			state.removeAnimatable(this);
		}
	}

	@Override
	public void draw(Graphics g, GameView view) 
	{
		Point p = new Point((int)x,(int)y);
		Enemy e = state.findNearestEnemy(p);
		int mod=0;
		if(e instanceof Gilbert)
		{
			mod = -10;
		}
		if(e instanceof Harold)
		{
			mod = -30;
		}
		
		try {
			view.drawCenteredImage(g, "resources/smallExplosion.png", new Point(((int)(e.getPos().x)-(int)(Math.random()*10)) ,((int)(e.getPos().y-(int)Math.random()*10+mod))));
		}
		catch(NullPointerException f)
		{
			return;
		}
	}

}
