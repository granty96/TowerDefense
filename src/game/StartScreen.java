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

public class StartScreen implements Animatable
{
	private GameControl control;
	private GameState state;
	private Point pos;
	private boolean hovering;
	private String imageState;
	
	public StartScreen(GameState state, GameControl control)
	{
		this.state = state;
		this.imageState = "resources/startScreen.png"; 
		this.control = control;
		
	}
	public void update (double timeElapse)
	{
		
		if (state.getMouseY() > 429 && state.getMouseY() < 543 && state.getMouseX() > 146 && state.getMouseX() < 459)
		{
			this.imageState = "resources/startScreen2.png";
			if (state.getMouseClicked())
			{
				state.removeAnimatable(this);
				control.startGame();
			}
		}
		else
		{
			this.imageState = "resources/startScreen.png";
		}
	}
	public void draw (Graphics g, GameView view)
	{
		if(! control.getGameStart())
			view.drawCenteredImage(g, imageState ,new Point(400,300));
		else
			state.removeAnimatable(this);
	} 
}
