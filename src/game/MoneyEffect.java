/*
 * @author James Donaldson
 * @version 4/26/2022
 * 
 * draws money effect
 * 
 * Artwork by Caleb Dean & James Donaldson
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class MoneyEffect extends Effect
{

	private double timer;
	private Tower t;
	public MoneyEffect(GameState state, double x, double y,Tower t)
	{
		super(state, x, y);
		this.timer = 0;
		this.t = t;
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

		if(t instanceof TowerBasicMenu)
		{
			view.drawCenteredImage(g, "resources/moneyEffect1.png", new Point(760,125));
		}
		if(t instanceof Tower2Menu)
		{
			view.drawCenteredImage(g, "resources/moneyEffect2.png", new Point(760,100));
		}
		
		
	}

}
