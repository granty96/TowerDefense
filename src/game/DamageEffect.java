/*
 * @author James Donaldson
 * @version 4/26/2022
 * 
 * creates damage effect
 * 
 * Artwork by Caleb Dean & James Donaldson
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class DamageEffect extends Effect
{

	private double timer;
	private Enemy e;
	public DamageEffect(GameState state, double x, double y,Enemy e)
	{
		super(state, x, y);
		this.timer = 0;
		this.e = e;
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

		if(e instanceof Gilbert)
		{
			view.drawCenteredImage(g, "resources/damageEffect1.png", new Point((int)x,(int)y +20));
			view.drawCenteredImage(g, "resources/damageEffect1.png", new Point(760,50));
		}
		if(e instanceof Harold)
		{
			view.drawCenteredImage(g, "resources/damageEffect2.png", new Point((int)x,(int)y +20));
			view.drawCenteredImage(g, "resources/damageEffect2.png", new Point(760,50));
		}
		
		
	}

}
