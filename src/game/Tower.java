/*
 * @author James Donaldson
 * @version 4/26/2022
 * 
 * tower superclass
 * 
 * Artwork by Caleb Dean & James Donaldson
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public abstract class Tower implements Animatable
{
	//Fields
	
	protected Point pos;
	protected GameState state;
	
	//Constructor
	
	public Tower (GameState state, Point pos)
	{
		this.state = state;
		this.pos = pos;
	}
	
	protected abstract Point getPos();
	
	
}
