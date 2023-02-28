/*
 * @author James Donaldson
 * @version 4/26/2022
 * 
 * towerbasic object
 * 
 * Artwork by Caleb Dean & James Donaldson
 */
package game;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Vector;

public class TowerBasic extends Tower
{
	
	//Fields
	private double timeSinceLastShot;
	
	public TowerBasic (GameState state, Point pos)
	{
		super(state, pos);
		this.timeSinceLastShot = 0;
	}
	
	@Override
	public void update(double timeElapse) 
	{
		Enemy e = state.findNearestEnemy(pos);
		timeSinceLastShot += timeElapse;
		
		if(timeSinceLastShot < 1.0)
			return;
		
		if (e.getPos().distance(pos) < 75)
		{
			int dx;
			int dy;
			if(e instanceof Gilbert)
			{
				dx = state.getPath().locatePosition((e.getPercentTraveled() + 0.06)).x - pos.x;
				dy = state.getPath().locatePosition((e.getPercentTraveled() + 0.06)).y - pos.y;
				state.addAnimatable(new ShotBasic(state, new Point(pos.x,pos.y), dx, dy));
			}
			if(e instanceof Harold)
			{
				dx = state.getPath().locatePosition((e.getPercentTraveled() + 0.03)).x - pos.x;
				dy = state.getPath().locatePosition((e.getPercentTraveled() + 0.03)).y - pos.y;
				state.addAnimatable(new ShotBasic(state, new Point(pos.x,pos.y), dx, dy));
			}
			
			timeSinceLastShot =0;
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
	

	//public double getXSpeed()
	//{
		
	//}
	
	//public double getYSpeed()
}
