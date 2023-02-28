/**
 * Tower defense 'control' class.  This class encapsulates all the logic and setup
 * for the game, including creation of state and view classes, listener creation,
 * timers, game start and game over logic, etc.
 * 
 * @author James Donaldson 4/5/2022
 * @version 
 */
package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class GameControl implements Runnable, ActionListener
{
	// Fields 
	
	private GameView view;
	private GameState state;
	private HashMap<String, BufferedImage> images;
	private Timer timer;
	private long timeSinceLast;
	private boolean gameStart;
	// Constructor
	
	/**
	 * Starts the game initialization process.
	 */
	public GameControl ()
	{
		System.out.println("Starting the game...");
		
		// The constructor is called from main, so it is executing in the main thread.
		// All GUI work needs to be done by the GUI thread.  Make a call to ask the
		// GUI thread to run this object, then simply return to main.  The remainder
		// of the game setup will take place when the 'run' method is called by
		// the GUI thread (later).
		
		SwingUtilities.invokeLater(this);
		
		// Nothing else for the main thread to do -- simply return.
	}
	

	/**
	 * This is the first function invoked (called) by the GUI thread.
	 * Set up the game.
	 */
	public void run ()
	{
		images = new HashMap<String, BufferedImage>();
		
		// Set up game objects.
		
		state = new GameState(this);
		view = new GameView(state, this); 
		this.gameStart = false;
		
		//build animatable objects
		state.addAnimatable(new StartScreen(state,this));
		
		//add mouse listeners
		
		view.addMouseListener(state);
		view.addMouseMotionListener(state);

		state.addAnimatable(new Background(this));
		state.addAnimatable(new Menu(this, state));
		state.addAnimatable(new Gilbert(state,this));
		state.addAnimatable(new Harold(state,this));

		//set up timer
		timer = new Timer(16,this);
		timer.start();
	}
	
	public void actionPerformed (ActionEvent e)
	{
		state.updateAll(1.0/60.0);
		view.repaint();
		
	}
	
	public BufferedImage loadImage(String filename)
	{
			BufferedImage image;
			if(images.containsKey(filename))
			{
				image = (BufferedImage)(images.get(filename));
				return image;
			}
			else
			{
				try 
				{
					ClassLoader loader = this.getClass().getClassLoader();
					InputStream is = loader.getResourceAsStream(filename);
					image = javax.imageio.ImageIO.read(is);
					images.put(filename, image);
					return image;
				}
				catch (IOException e)
				{
					System.out.println("Error loading image");
					return null;
				}
			}
	}
	
	public boolean getGameStart()
	{
		return this.gameStart;
	}
	public void startGame()
	{
		this.gameStart = true;
	}
}
