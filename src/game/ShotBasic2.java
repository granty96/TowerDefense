/*
 * @author James Donaldson
 * @version 4/26/2022
 * 
 * creates shot effect from tower2
 * 
 * Artwork by Caleb Dean & James Donaldson
 */
package game;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Vector;

public class ShotBasic2 extends Effect
{
	
	//Fields\

	private double dx;
	private double dy;
	private double age;
	
	
	public ShotBasic2 (GameState state, Point pos,int dx, int dy)
	{
		super(state, pos.x,pos.y);
		this.dx = dx;
		this.dy = dy;
		age = 0;
	}

	
	@Override
	public void update(double timeElapse)
	{
		age += timeElapse;
		if (age > 1.0)
		{
			state.removeAnimatable(this);
		}
		x += dx*timeElapse;
		y += dy*timeElapse;
		
		Point p = new Point((int)x,(int)y);
		Enemy e = state.findNearestEnemy(p);
		if(e.getPos().distance(p) < 10)
		{
			e.setLife(-1);
			state.addAnimatable(new SmallExplosion(state,x,y));
			state.removeAnimatable(this);
			
		}
		
		
	}

	@Override
	public void draw(Graphics g, GameView view)
	{
		if (age < 0.2)
			view.drawCenteredImage(g, "resources/shot2_1.png", new Point((int) x, (int) y));
		else if(age < 0.4)
			view.drawCenteredImage(g, "resources/shot2_2.png", new Point((int) x, (int) y));
		else if(age < 0.6)
			view.drawCenteredImage(g, "resources/shot2_1.png", new Point((int) x, (int) y));
		else if(age < 0.8)
			view.drawCenteredImage(g, "resources/shot2_2.png", new Point((int) x, (int) y));
		else if(age < 1)
			view.drawCenteredImage(g, "resources/shot2_1.png", new Point((int) x, (int) y));
			
	}
	
}
