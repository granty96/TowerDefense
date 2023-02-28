/*
 * @author James Donaldson
 * @version 4/12/2022
 * 
 * loads path file, draws, and calculates path
 * 
 */
package game;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Scanner;

public class Path 
{
	/** This constructor does the following:  
     *     - It reads a number of coordinates, n, from the scanner. 
     *     - It creates new array(s) (or an ArrayList) to hold the path coordinates,
     *          and stores it in the field in 'this' object.
     *     - It loops n times, each time scanning a coordinate x,y pair, creating an
     *         object to represent the coordinate (if needed), and stores the coordinate
     *         in the array(s) or ArrayList.
     * 
     * @param s  a Scanner set up by the caller to provide a list of coordinates
     */
	
	// Fields to store the path and its length
	private int n;
	private int[] yPath = new int[n];
	private int[] xPath = new int[n];
	private Point[] points = new Point[n];
	
	// Constructor - loads the path from the file
	public Path (Scanner File) 
	{
		this.n = File.nextInt();
		this.xPath = new int[n];
		this.yPath = new int[n];
		this.points = new Point[n];
			
		for(int i = 0; i < this.n ;i++)
		{
			this.xPath[i] = File.nextInt(); 
			this.yPath[i] = File.nextInt();
			this.points[i] = new Point(this.xPath[i],this.yPath[i]);
		}		
	}
	
	
	/** This constructor does the following:  
     *     - It reads a number of coordinates, n, from the scanner. 
     *     - It creates new array(s) (or an ArrayList) to hold the path coordinates,
     *          and stores it in the field in 'this' object.
     *     - It loops n times, each time scanning a coordinate x,y pair, creating an
     *         object to represent the coordinate (if needed), and stores the coordinate
     *         in the array(s) or ArrayList.
     * 
     * @param s  a Scanner set up by the caller to provide a list of coordinates
     */
	public void drawPath(Graphics g) 
	{
		for(int i = 0; i< this.points.length-1;i++)
		{
			g.setColor(Color.RED);
			g.drawLine(this.points[i].x,this.points[i].y,this.points[i+1].x,this.points[i+1].y);
		}
	}
	
	// Locate - takes a percentage p and returns a Point (x,y) of where percentage p is on the path
	/** 
	 * Given a percentage between 0% and 100%, this method calculates
	 * the location along the path that is exactly this percentage
	 * along the path. The location is returned in a Point object
	 * (integer x and y), and the location is a screen coordinate.
	 * 
	 * If the percentage is less than 0%, the starting position is
	 * returned. If the percentage is greater than 100%, the final
	 * position is returned.
	 * 
	 * If students don't want to use Point objects, they may 
	 * write or use another object to represent coordinates. 
	 *
	 * Caution: Students should never directly return a Point object
	 * from a path list. It could be changed by the outside caller.
	 * Instead, always create and return a new point objects as
	 * the result from this method.
	 * 
	 * @param percentTraveled a distance along the path
	 * @return the screen coordinate of this position along the path
	 */
	 public Point locatePosition(double percentTraveled)
	 {
		if (percentTraveled < 1)
		{
			double totalPathLength = 0;
			double[] xLengths = new double[this.n];
			double[] yLengths = new double[this.n];
			double[] lengths = new double[this.n];
			for(int i =0; i < (this.n)-1;i++) 
			{
				xLengths[i] = (double)(this.points[i].x-this.points[i+1].x);
				yLengths[i] = (double)(this.points[i].y-this.points[i+1].y);
				lengths[i] = Math.sqrt(Math.pow(xLengths[i],2 )+ Math.pow(yLengths[i],2));
				totalPathLength += lengths[i];
			}
			double newLength = totalPathLength*percentTraveled;
			double lengthTraveled = 0;
			int counter = 0;
			while ((lengthTraveled + lengths[counter]) < newLength)
			{
				lengthTraveled += lengths[counter];
				counter++;
			}
			double distanceLeft = newLength-lengthTraveled;
			double segmentPercentage = distanceLeft/lengths[counter];
			Point newPos = new Point((int)((1.0-segmentPercentage)*points[counter].x + segmentPercentage*points[counter+1].x),(int)(((1.0-segmentPercentage)*points[counter].y)+(segmentPercentage*points[counter+1].y)));
			return newPos;
		}
		else 
		{
			Point newPos = new Point(this.points[this.n-1].x,this.points[this.n-1].y);
			return newPos;
		}
	 }
	
}
