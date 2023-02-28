/*
 * @author James Donaldson
 * @version 4/26/2022
 * 
 * draws Game Over screen 
 * 
 * Artwork by Caleb Dean & James Donaldson
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class GameOver implements Animatable {

	
	
	@Override
	public void update(double timeElapse)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics g, GameView view) 
	{
		view.drawCenteredImage(g, "resources/GameOver.png", new Point(400,300));
	}

}
