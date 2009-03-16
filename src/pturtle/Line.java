// $Id$

package pturtle;

import processing.core.PApplet;

/**
 * Line class, stored by HistoryTurtle.
 * 
 * @author George Bashi
 * @author Ollie Glass
 */
public class Line {
	/** From location in x axis. */
	public int fromX;
	/** From location in y axis. */
	public int fromY;
	/** To location in x axis. */
	public int toX;
	/** To location in y axis. */
	public int toY;
	/**
	 * Colour to draw line in, as a colour created with
	 * {@link processing.core.PApplet#color(int, int, int)}.
	 */
	public int drawColor;
	/** PApplet to draw to. */
	private final PApplet applet;
	
	/**
	 * Create a new line.
	 * 
	 * @param applet
	 *            PApplet to draw to.
	 * @param fromX
	 *            from location in x axis.
	 * @param fromY
	 *            from location in y axis.
	 * @param toX
	 *            to location in x axis.
	 * @param toY
	 *            to location in y axis.
	 */
	public Line(PApplet applet, int fromX, int fromY, int toX, int toY, int drawColor) {
		this.applet = applet;
		this.fromX = fromX;
		this.fromY = fromY;
		this.toX = toX;
		this.toY = toY;
		this.drawColor = drawColor;
	}
	
	/**
	 * Draw this line.
	 */
	void draw() {
		applet.stroke(drawColor);
		applet.line(fromX, fromY, toX, toY);
	}
}
