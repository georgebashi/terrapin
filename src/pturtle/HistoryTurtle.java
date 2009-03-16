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

import java.util.ArrayList;
import processing.core.PApplet;

/**
 * HistoryTurtle extends Turtle to record lines drawn in the lines array. These
 * can be edited so as to change what has already been drawn. <strong>This class
 * is much, much slower than the normal Turtle class</strong>.
 * 
 * @author George Bashi
 * @author Ollie Glass
 */
public class HistoryTurtle extends Turtle {
	/** The lines previously drawn */
	public ArrayList<Line> lines = new ArrayList<Line>();
	
	/**
	 * Create a new turtle
	 * 
	 * @param applet
	 *            the PApplet to draw to.
	 */
	public HistoryTurtle(PApplet applet) {
		super(applet);
		applet.registerDraw(this);
	}
	
	/**
	 * Copy a turtle
	 * 
	 * @param parent
	 *            Turtle to copy
	 */
	public HistoryTurtle(Turtle parent) {
		super(parent);
		applet.registerDraw(this);
	}
	
	/**
	 * Draw method. You shouldn't call this unless you are using
	 * {@link processing.core.PApplet#noLoop()}, as Processing will do so
	 * automatically.
	 */
	public void draw() {
		int i = 0;
		for (int j = lines.size(); i < j; ++i) {
			lines.get(i).draw();
		}
	}
	
	/**
	 * Override {@link pturtle.Turtle#moveTo(int, int)} to record lines.
	 * 
	 * @param x
	 *            location in x axis
	 * @param y
	 *            location in y axis
	 */
	@Override
	protected void moveTo(int x, int y) {
		if (drawing) {
			lines.add(new Line(applet, this.x, this.y, x, y, drawColor));
		}
		
		this.x = x;
		this.y = y;
	}
}
