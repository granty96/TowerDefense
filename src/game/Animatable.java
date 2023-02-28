/*
 * @author James Donaldson
 * @version 4/12/2022
 * 
 * Creates Animatable Interface
 */

package game;

import java.awt.Graphics;

public interface Animatable 
{
	public void update (double timeElapse);
	public void draw (Graphics g, GameView view);	
}
