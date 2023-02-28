/*
 * @author James Donaldson
 * @version 4/12/2022
 * 
 * GameView is responsible for drawing the panel and the contents inside
 * Artwork by Caleb Dean
 */ 
package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameView extends JPanel
{
	// Fields
	
	private GameState state;
	private GameControl control;
	// Constructor 
	
	/** 
	 * draws and sets up main panel
	 * 
	 * @param s a GameState object
	*
	*/
	
	public GameView (GameState state,GameControl control)
	{
		
		// Keep track of the game's state object.
		
		this.state = state;
		this.control = control;
		
		// Build the frame and the panel, then put 'this' object in it.
		// First, use inherited methods to set a new pixel size for this object.
		
		this.setMinimumSize(new Dimension(800,600));
		this.setMaximumSize(new Dimension(800,600));
		this.setPreferredSize(new Dimension(800,600));
		
		// Make the JFrame, ask it to exit the application when closed.
		
		JFrame frame = new JFrame ("Robots vs. Towers");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Put 'this' JPanel into the frame.
		
		frame.setContentPane(this);
		
		// Reallocate frame space (called packing), then show it.
		
		frame.pack(); 
		frame.setVisible(true);
	}
	
	/**
	 * 
	 *  @param g a Graphics object telling the state class where to draw
	*/
	
	public void paint (Graphics g)
	{
		state.drawAll(g, this);  // We'll do all the work in drawAll in this checkpoint
	}
	
	public void drawCenteredImage (Graphics g, String name, Point pos) 
	{
		BufferedImage image = control.loadImage(name);
		g.drawImage(image,pos.x-image.getWidth()/2,pos.y-image.getHeight()/2,null);
	}
	
	public void drawCenteredAndSized (Graphics g, String name, Point pos,int width, int height)
	{
		BufferedImage image = control.loadImage(name);
		int x = pos.x-image.getWidth()/2;
		int y = pos.y-image.getHeight()/2;
		Image resized = image.getScaledInstance(width, height,0);
		g.drawImage(resized,x,y,null);
	}

}
