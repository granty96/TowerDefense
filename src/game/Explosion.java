/*
 * @author James Donaldson
 * @version 4/26/2022
 * 
 * creates explosion effect on enemy death 
 * 
 * Artwork by Caleb Dean & James Donaldson
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class Explosion extends Effect
{

	private double timer;
	private Enemy e;
	public Explosion(GameState state, double x, double y,Enemy e)
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
		if(timer < 0.1)
			view.drawCenteredImage(g, "resources/explosion.png", new Point((int)x,(int)y));
		else if (timer <0.20)
			view.drawCenteredImage(g, "resources/explosion2.png", new Point((int)x,(int)y));
		else if (timer < 0.30)
			view.drawCenteredImage(g, "resources/explosion.png", new Point((int)x,(int)y));
		else if (timer <0.40)
			view.drawCenteredImage(g, "resources/explosion2.png", new Point((int)x,(int)y));
		else if (timer <0.5)
			view.drawCenteredImage(g, "resources/explosion3.png", new Point((int)x,(int)y));
		if(e instanceof Gilbert)
		{
			view.drawCenteredImage(g, "resources/scoreEffect1.png", new Point((int)x,(int)y - 10));
			view.drawCenteredImage(g, "resources/scoreEffect1.png", new Point(760,125));
		}
		if(e instanceof Harold)
		{
			view.drawCenteredImage(g, "resources/scoreEffect2.png", new Point((int)x,(int)y - 10));
			view.drawCenteredImage(g, "resources/scoreEffect2.png", new Point(760,125));
		}
		
		
	}

}
