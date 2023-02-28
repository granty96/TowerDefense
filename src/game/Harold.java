/*
 * @author James Donaldson
 * @version 4/12/2022
 * 
 * Class for Harold Enemy
 * Artwork by Caleb Dean
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class Harold extends Enemy
{
	
	private GameControl control;
	private double percentTraveled;
	private String imageState;
	private int counter;
	private Point pos;
	private int life;
	private int updateCount;
	private LifeBar lifeBar;


	
	public Harold(GameState state,GameControl control)
	{
		super(state);
		this.pos = state.getPath().locatePosition(0);
		this.counter = 0;
		this.imageState = "resources/harold1.png";
		this.life = 5;
		updateCount = 0;
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
				state.changeMoney(50);
				state.changeScore(500);
				state.addAnimatable(new Explosion(state,(double)this.pos.x,(double)this.pos.y,this));
			}
			
			//determine whether to move up or down and update position
			this.percentTraveled+=0.0005;
				if(this.percentTraveled >= 1) 
				{
					state.changeLives(-10);
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
			
			if(updateCount == 400)
			{
				Harold h = new Harold(state,control);
				h.percentTraveled = -0.01;
				state.addAnimatable(h);
			}
		}
	}
	
	public void draw (Graphics g, GameView view)
	{
		if(control.getGameStart())
			view.drawCenteredImage(g,this.imageState,new Point(this.pos.x,this.pos.y-33));
	}
	
	public Point getPos()
	{
		return this.pos;
	}
	
	public double getPercentTraveled()
	{
		return this.percentTraveled;
	}
	
	public String getImage(int counter)
	{
		if((counter >= 0 && counter <= 10) || (counter >50 && counter <=60))
			return "resources/harold1.png";
		if((this.counter > 10 && counter <=20) || (counter > 40 && counter <= 50))
			return "resources/harold2.png";
		if(counter > 20 && counter <= 40)
			return "resources/harold3.png";
		else
			return null;
	}

	@Override
	protected void setLife(int Amount) 
	{
		this.life += Amount;
		
	}

	@Override
	protected int getLife()
	{
		return this.life;
	}
}
