/*
 * @author James Donaldson
 * @version 4/26/2022
 * 
 * Enemy Superclass 
 * 
 * Artwork by Caleb Dean & James Donaldson
 */
package game;

import java.awt.Point;

public abstract class Enemy implements Animatable
{
	//Fields
	
	protected GameState state;
	
	//Constructor
	
	public Enemy(GameState state)
	{
		this.state = state;
	}
	
	protected abstract Point getPos();
	
	protected abstract double getPercentTraveled();
	
	protected abstract void setLife(int Amount);
	
	protected abstract int getLife();
}
