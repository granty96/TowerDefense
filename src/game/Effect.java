/*
 * @author James Donaldson
 * @version 4/26/2022
 * 
 * Effect Superclass 
 * 
 * Artwork by Caleb Dean & James Donaldson
 */
package game;

import java.awt.Point;

public abstract class Effect implements Animatable
{
	protected GameState state;
	protected double x, y;
	
	public Effect(GameState state, double x, double y)
	{
		this.state = state;
		this.x = x;
		this.y =y;
	}
}
