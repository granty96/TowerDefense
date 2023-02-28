/*
 * @author James Donaldson
 * @version 4/12/2022
 * 
 * loads path, creates enemies and contains drawAll function
 */ 

package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import java.io.File;
import java.io.FileNotFoundException;

public class GameState implements MouseListener, MouseMotionListener
{
	// Fields
	
	private Path path;
	private GameControl control;
	private boolean gameOver;
	private int lifeCounter;
	private int money;
	private int score;
	private List<Animatable> objects;
	private List<Animatable> toAdd;
	private List<Animatable> toRemove;
	private int mouseX, mouseY;
	private boolean mouseClicked;
	private Color color;
	//private Damage damage;

	
	// Constructor
	
	public GameState (GameControl control)
	{
		this.control = control;
		//this.damage = new Damage(this);
		objects = new ArrayList<Animatable>();
		toAdd = new ArrayList<Animatable>();
		toRemove = new ArrayList<Animatable>();
		this.gameOver = false;
		this.lifeCounter = 50;
		this.money = 400;
		this.mouseX = 0;
		this.mouseY = 0;
		this.mouseClicked = false;
		this.score = 0;
		this.color = Color.black;
		
		// Build our path
		try 
		{
		File root = new File(System.getProperty("user.dir"));
		File resource =  new File(root, "src/resources/path.txt");
		Scanner File = new Scanner(resource);
		this.path = new Path(File);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Path file not found.");
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	/**
	 * Accessor to get mouseX
	 * @return int mouseX
	 */
	public int getMouseX()
	{
		return mouseX;
	}
	
	/**
	 * Accessor to get mouseY
	 * @return int mouseY
	 */
	public int getMouseY()
	{
		return mouseY;
	}
	
	/**
	 * Accessor checks if mouse is clicked
	 * @return true if mouse is clicked
	 */
	public boolean getMouseClicked()
	{
		return mouseClicked;
	}
	
	/**
	 * resets click
	 */
	public void consumeClick()
	{
		mouseClicked = false;
	}
	
	/**
	* Accessor method for path object
	 * 
	 * @return path object
	 */
	public Path getPath ()
	{
		return this.path;
	}
	
	/**
	 * Accessor Method for getting lives
	 * @return this.lives
	 */
	public int getLives()
	{
		return this.lifeCounter;
	}
	
	/**
	 * Accessor method for money field
	 * @return money
	 */
	public int getMoney()
	{
		return this.money;
	}
	
	/**
	 * Accessor method for gameOver field
	 * 
	 * @return true if game is over, false otherwise
	 */
	public boolean isGameOver()
	{
		return this.gameOver;
	}
	
	/**
	 * Accessor method for Score field
	 * @return score
	 */
	public int getScore()
	{
		return this.score;
	}

	/**
	 * Mutator method for setting lives
	 * @param lives
	 */
	public void changeLives(int amount)
	{
		lifeCounter += amount;
	}
	
	/**
	 * Mutator method for ending the game
	 * 
	 * @return game is over
	 */
	public void gameIsOver()
	{
		gameOver = true;
	}
	
	/**
	 * Mutator method for changing score
	 * @param amount
	 */
	public void changeScore(int amount)
	{
		this.score += amount;
	}
	
	/**
	 * Mutator method for changing money
	 * @param amount
	 */
	public void changeMoney(int amount)
	{
		this.money += amount;
	}
	

	/**
	 * Helper method to add Animatable to object list
	 * 
	 * @param o Animatable object to add to objects list
	 */
	public void addAnimatable(Animatable o)
	{
		toAdd.add(o);
	}
	
	/**
	 * Helper method to remove Animatable from object list
	 * 
	 * @param o Animatable object to add to objects list
	 */
	public void removeAnimatable(Animatable o)
	{
		toRemove.add(o);
	}
	
	/**
	 * Draws everything
	 * 
	 * @param g Graphics object
	 * @param view GameView object
	 */
	public void drawAll(Graphics g, GameView view)
	{
		g.clearRect(0, 0, 600, 600);
		for(Animatable a: objects)
			a.draw(g,view);
		if(gameOver)
		{
			this.addAnimatable(new GameOver());
		}

	}
	
	/**
	 * Updates the screen
	 * 
	 * @param g Graphics object
	 * @param view GameView object
	 */
	public void updateAll(double elapsedTime)
	{	
		if(! gameOver)
		{
			//Loop through all animatables
			for(Animatable a : objects)
			{
				a.update(elapsedTime);
			}
			//end game if life is 0
			if (lifeCounter <= 0)
				gameIsOver();
		}
			
		//add and remove items
		objects.removeAll(toRemove);
		objects.addAll(toAdd);
		
		//clear add and remove lists
		toAdd.clear();
		toRemove.clear();
		
		//reset mouse
			
		mouseClicked = false;
	
	}
	
	/**
	 * Finds the Closest Enemy to a tower object
	 * 
	 * @param Tower object t
	 * @return Closest Enemy Object nearest
	 */
	public Enemy findNearestEnemy(Point p)
	{
		Point tPos = p;
		Point ePos;
		Enemy nearest = null;
		double distance;
		double min = Math.sqrt(Math.pow(600, 2)+ Math.pow(600,2));
		for(Animatable a: objects)
		{
			if(a instanceof Enemy)
			{
				Enemy e  =(Enemy) a;
				ePos = e.getPos();
				distance = Math.sqrt(Math.pow((double)(ePos.x-tPos.x),2) + Math.pow((double)(ePos.y-tPos.y),2));
				if (distance < min)
				{
					min = distance;
					nearest = e;
					
				}
			}
		}
		
		return nearest;
	}


//Mouse event accessor methods	

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) 
	{
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseClicked = true;
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
