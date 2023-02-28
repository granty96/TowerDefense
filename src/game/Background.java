/*
 * @author James Donaldson
 * @version 4/12/2022
 * 
 * draws background
 * 
 * Artwork by Caleb Dean
 */

package game;

import java.awt.Graphics;
import java.awt.Point;

public class Background implements Animatable
{
	private GameControl control;
	
	public Background(GameControl control)
	{
		this.control = control;
	}
	public void update (double timeElapse)
	{
		//Nothing to move
	}
	public void draw (Graphics g, GameView view)
	{
		if(control.getGameStart())
		{
			Point pos = new Point(300,300);
			view.drawCenteredImage(g, "resources/path.jpg",pos);
		}
	} 
}
