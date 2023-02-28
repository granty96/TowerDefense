/*
 * @author James Donaldson
 * @version 4/12/2022
 * 
 * Class for Gilbert Enemy.
 * Artwork by Caleb Dean
 */

package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class Gilbert extends Enemy
{
	

	private double percentTraveled;
	private String imageState;
	private int counter;
	private Point pos;
	private double speed;
	private int updateCount;
	private int gilbertCount;
	private int life;
	private GameControl control;
	private LifeBar lifeBar;
	//private Damage damage;

	
	
	public Gilbert(GameState state, GameControl control)
	{
		super(state);
		this.pos = new Point(state.getPath().locatePosition(0.0));
		this.counter = 0;
		this.imageState = "resources/gilbert1.png";
		this.speed = speed/1000;
		this.updateCount = 0;
		this.gilbertCount = 1;
		this.life = 2;
		this.control = control;
		this.lifeBar = new LifeBar(state,this,control);
		state.addAnimatable(lifeBar);

	}
	
	public void update (double timeElapse)
	{
		if(control.getGameStart())
		{
			if (this.life <= 0)
			{
				state.removeAnimatable(this);
				state.changeMoney(10);
				state.changeScore(100);
				state.addAnimatable(new Explosion(state,(double)this.pos.x,(double)this.pos.y,this));
			}
			//determine whether to move up or down and update position
			this.percentTraveled+=0.001;
				if(this.percentTraveled >= 1)
				{
					state.changeLives(-5);
					state.addAnimatable(new DamageEffect(state, (double)this.pos.x,(double)this.pos.y,this));
					state.removeAnimatable(this);
				}
			
				
			//update position
			this.pos = state.getPath().locatePosition(this.percentTraveled);
				
			//set up counter up to 60 frames
			if (this.counter < 60) 
			{
				this.counter++; //counter updates each frame
			}
			else 
			{
				this.counter = 0;
			}
			//check which image to load
			this.imageState = getImage(this.counter);
				
			updateCount++;
				
			if(updateCount == 240)
			{
				Gilbert g = new Gilbert(state,control);
				g.percentTraveled = -0.005;
				state.addAnimatable(g);
			}
		
		}
	}
	
	public void draw (Graphics g, GameView view)
	{	
		if(control.getGameStart())
			view.drawCenteredImage(g,this.imageState,new Point(this.pos.x,this.pos.y-10));
	}
	
	public Point getPos()
	{
		return this.pos;
	}

	public int getLife()
	{
		return this.life;
	}

	public double getPercentTraveled()
	{
		return this.percentTraveled;
	}

	public String getImage(int counter)

	{
		if((counter >= 0 && counter <= 5) || (counter >55 && counter <= 60))
			return "resources/gilbert1.png";
		if((counter > 5 && counter <=10) || (counter > 50 && counter <= 55))
			return "resources/gilbert2.png";
		if((counter > 10 && counter <= 15) || (counter > 45 && counter <= 50))
			return "resources/gilbert3.png";
		if((counter > 15 && counter <=20) || (counter > 40 && counter <= 45))
			return "resources/gilbert4.png";
		if((counter > 20 && counter <= 25) || (counter > 35 && counter <= 40))
			return "resources/gilbert5.png";
		if(counter > 25 && counter <=35)
			return "resources/gilbert6.png";
		else
			return null;
	}
	
	public void setLife(int Amount)
	{
		this.life += Amount;
	}
}
