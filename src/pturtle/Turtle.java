// $Id$

/*
    This file is part of PTurtle.

    PTurtle is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    PTurtle is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with PTurtle.  If not, see <http://www.gnu.org/licenses/>.
*/

package pturtle;

import java.util.List;
import processing.core.PApplet;

/**
 * Turtle class, making ideas from the LOGO teaching language available in Processing.
 * 
 * @author George Bashi
 * @author Ollie Glass
 */
public class Turtle {
	/** x location on screen. */
	public int x;
	/** y location on screen. */
	public int y;
	/** Colour of line drawn by Turtle (as a Processing color). */
	public int drawColor;
	/** If false, the Turtle moves but does not leave a trail. */
	public boolean drawing = true;
	/** The angle (in radians) that the Turtle is facing. */
	private float rotation;
	/** The PApplet to render to. */
	public final PApplet applet;
	
	/**
	 * Standard constructor, creates a Turtle in the middle of the screen which
	 * draws in white.
	 * 
	 * @param applet
	 *            PApplet to render to.
	 */
	public Turtle(PApplet applet) {
		this.applet = applet;
		x = applet.width / 2;
		y = applet.height / 2;
		drawColor = applet.color(255, 255, 255);
	}
	
	/**
	 * "Copy" constructor, creates an identical Turtle to the one passed in.
	 * 
	 * @param t
	 *            Turtle to copy.
	 */
	public Turtle(Turtle t) {
		applet = t.applet;
		x = t.x;
		y = t.y;
		drawColor = t.drawColor;
		drawing = t.drawing;
		rotation = t.rotation;
	}
	
	/**
	 * Move Turtle backward.
	 * 
	 * @param amount
	 *            number of pixels to move by.
	 */
	public void backward(int amount) {
		forward(-amount);
	}
	
	/**
	 * Move Turtle forward.
	 * 
	 * @param amount
	 *            number of pixels to move by.
	 */
	public void forward(int amount) {
		int newX, newY;
		newX = x + PApplet.round(amount * PApplet.cos(rotation));
		newY = y + PApplet.round(amount * PApplet.sin(rotation));
		moveTo(newX, newY);
	}
	
	/**
	 * Get the distance between this Turtle and point (x,y).
	 * 
	 * @param otherX
	 *            location in x axis.
	 * @param otherY
	 *            location in y axis.
	 * @return distance in pixels.
	 */
	public int getDistance(int otherX, int otherY) {
		return (int) Math.sqrt(Math.pow((otherX - x), 2) + Math.pow((otherY - y), 2));
	}
	
	/**
	 * Get the distance between this Turtle and another.
	 * 
	 * @param t
	 *            the other turtle.
	 * @return distance in pixels.
	 */
	public int getDistance(Turtle t) {
		return getDistance(t.x, t.y);
	}
	
	/**
	 * Get the nearest (Euclidean distance) Turtle from a List.
	 * 
	 * @param turtles
	 *            the list.
	 * @return the nearest turtle.
	 */
	public Turtle getNearest(List<Turtle> turtles) {
		Turtle nearest = null;
		int nearestDist = Integer.MAX_VALUE;
		
		for (Turtle t : turtles) {
			int newDist = getDistance(t.x, t.y);
			if (newDist < nearestDist) {
				nearest = t;
				nearestDist = newDist;
			}
		}
		
		return nearest;
	}
	
	/**
	 * Get the angle that the Turtle is facing.
	 * 
	 * @return angle in degrees.
	 */
	public int getRotation() {
		return (int) PApplet.degrees(rotation);
	}
	
	/**
	 * Turn the Turtle left.
	 * 
	 * @param amount
	 *            angle in degrees.
	 */
	public void left(int amount) {
		rotation -= PApplet.radians(amount);
	}
	
	/**
	 * Move the turtle to a specified point. Used internally to allow
	 * HistoryTurtle to override this to record lines.
	 * 
	 * @param x
	 *            location in x axis
	 * @param y
	 *            location in y axis
	 */
	protected void moveTo(int x, int y) {
		if (drawing) {
			applet.stroke(drawColor);
			applet.line(this.x, this.y, x, y);
		}
		
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Move the Turtle toward point (x,y).
	 * 
	 * @param toX
	 *            location in x axis.
	 * @param toY
	 *            location in y axis.
	 * @param amount
	 *            value between 0 and 1 as a ratio of how close to move toward
	 *            point (x,y); 0 will not move the turtle, 1 will cause it to
	 *            jump straight to (x,y), 0.5f will cause it to move half way
	 *            there, etc.
	 */
	public void moveToward(int toX, int toY, float amount) {
		moveToward(toX, toY, (int) (getDistance(toX, toY) * amount));
	}
	
	/**
	 * Move the Turtle a specified number of pixels toward point (x,y).
	 * 
	 * @param toX
	 *            location in x.
	 * @param toY
	 *            location in y.
	 * @param amount
	 *            number of pixels to move toward (x,y).
	 */
	public void moveToward(int toX, int toY, int amount) {
		applet.pushMatrix();
		applet.translate(x, y);
		rotation = PApplet.atan2(toY - y, toX - x);
		applet.popMatrix();
		moveTo(toX, toY);
	}
	
	/**
	 * Move Turtle toward another.
	 * 
	 * @param t
	 *            Turtle to move towards.
	 * @param amount
	 *            value between 0 and 1 as a ratio of how close to move toward
	 *            point (x,y); 0 will not move the turtle, 1 will cause it to
	 *            jump straight to (x,y), 0.5f will cause it to move half way
	 *            there, etc.
	 *            
	 * @see pturtle.Turtle#moveToward(int, int, float)
	 */
	public void moveToward(Turtle t, float amount) {
		moveToward(t.x, t.y, amount);
	}
	
	/**
	 * Move Turtle toward another.
	 * 
	 * @param t
	 *            Turtle to move towards.
	 * @param amount
	 *            number of pixels to move toward (x,y).
	 *            
	 * @see pturtle.Turtle#moveToward(int, int, int)
	 */
	public void moveToward(Turtle t, int amount) {
		moveToward(t.x, t.y, amount);
	}
	
	/**
	 * Randomise the colour that the Turtle draws with from a set of 16
	 * hard-coded colours.
	 */
	public void randomPenColor() {
		switch ((int) applet.random(0, 15)) {
			case 0:
				setPenColor(255, 255, 255);
				break;
			case 1:
				setPenColor(0, 0, 255);
				break;
			case 2:
				setPenColor(0, 255, 0);
				break;
			case 3:
				setPenColor(0, 255, 255);
				break;
			case 4:
				setPenColor(255, 0, 0);
				break;
			case 5:
				setPenColor(255, 0, 255);
				break;
			case 6:
				setPenColor(255, 255, 0);
				break;
			case 7:
				setPenColor(180, 180, 180);
				break;
			case 8:
				setPenColor(155, 96, 59);
				break;
			case 9:
				setPenColor(197, 136, 18);
				break;
			case 10:
				setPenColor(100, 162, 64);
				break;
			case 11:
				setPenColor(120, 187, 187);
				break;
			case 12:
				setPenColor(255, 149, 119);
				break;
			case 13:
				setPenColor(144, 113, 208);
				break;
			case 14:
				setPenColor(255, 163, 0);
				break;
			case 15:
				setPenColor(183, 183, 183);
				break;
		}
	}
	
	/**
	 * Turn the Turtle right.
	 * 
	 * @param amount
	 *            angle in degrees.
	 */
	public void right(int amount) {
		rotation += PApplet.radians(amount);
	}
	
	/**
	 * Move the Turtle to an absolute location. <strong>This does not
	 * draw</strong>.
	 * 
	 * @param x
	 *            location in x axis.
	 * @param y
	 *            location in y axis.
	 */
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Set the colour the Turtle draws with.
	 * 
	 * @param color
	 *            a colour created with
	 *            {@link processing.core.PApplet#color(int, int, int)}.
	 */
	public void setPenColor(int color) {
		drawColor = color;
	}
	
	/**
	 * Set the colour the Turtle draws with.
	 * 
	 * @param r
	 *            red value, 0-255.
	 * @param g
	 *            green value, 0-255.
	 * @param b
	 *            blue value, 0-255.
	 */
	public void setPenColor(int r, int g, int b) {
		drawColor = applet.color(r, g, b);
	}
	
	/**
	 * Set the direction the Turtle is facing in to an absolute angle.
	 * 
	 * @param rotation
	 *            angle in degrees.
	 */
	public void setRotation(int rotation) {
		this.rotation = PApplet.radians(rotation);
	}
	
	/**
	 * Strafe the Turtle left.
	 * 
	 * @param amount
	 *            number of pixels to strafe Turtle.
	 */
	public void strafeLeft(int amount) {
		left(90);
		forward(amount);
		right(90);
	}
	
	/**
	 * Strafe the Turtle right.
	 * 
	 * @param amount
	 *            number of pixels to strafe Turtle.
	 */
	public void strafeRight(int amount) {
		right(90);
		forward(amount);
		left(90);
	}
	
	/**
	 * Convert the Turtle to a String representation
	 * 
	 * @return "Turtle at 100,100"
	 */
	@Override
	public String toString() {
		return "Turtle at " + x + "," + y;
	}
	
}
