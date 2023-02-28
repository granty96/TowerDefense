/*
 * @author James Donaldson
 * @version 4/12/2022
 * 
 * Creates and updates menu
 * Artwork by Caleb Dean
 */ 

package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

public class Menu implements Animatable
{
	private GameControl control;
	private GameState state;

	private int lifeCounter;
	private int score;
	private int money;
	private Font font;
	
	//Constructor
	public Menu(GameControl control, GameState state) 
	{
		this.control = control;
		this.state = state;
		this.lifeCounter = state.getLives();
		this.score = state.getScore();
		this.money = state.getMoney();
		try 
		{
			this.font = Font.createFont(Font.TRUETYPE_FONT,new File("resources/showg.TTF"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("resources/showg.TTF")));
		}
		catch(IOException | FontFormatException e)
		{
			
		}

	}
	
	/**
	 * Update the menu
	 */
	public void update (double timeElapse)
	{
		if(control.getGameStart())
		{

			this.lifeCounter = state.getLives();
			this.money = state.getMoney();
			this.score = state.getScore();
			state.addAnimatable(new TowerBasicMenu(state,control, new Point(695,218)));
			state.addAnimatable(new Tower2Menu(state,control, new Point(695, 360)));
	
		}
	}
	 /**
	  * Draws the menu
	  */
	public void draw (Graphics g, GameView view)
	{
		if(control.getGameStart())
		{
			g.clearRect(600, 0, 200, 600);
			view.drawCenteredImage(g, "resources/menu.png", new Point(698,300));
			g.setColor(Color.GREEN);
			g.setFont(new Font("showg.TTF", 1, 40));
			g.drawString(""+this.lifeCounter, 700, 62);
			g.setColor(new Color(0, 150, 0));
			g.drawString(""+this.money, 700, 115);
			g.setFont(new Font("showg.TTF", 1, 50));
			g.drawString(""+this.score, 610, 570);
		}
	}
	
}
