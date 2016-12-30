package terrapin;

// Collect terrapin states on a LIFO stack.

import java.util.ArrayDeque;

public class TerrapinStack extends ArrayDeque<Terrapin> {        
	/**
	 * Save Terrapin state on an internal stack.
	 * Includes location, rotation, drawColor, and drawing (boolean) state.
	 *
	 * @param t
	 *            Terrapin whose state will be saved
         *
	 * @see terrapin.TerrapinStack#pop()
	 */	
	public void push(Terrapin t) {
	        // Don't save the terrapin object, as it could be modified.
	        // Instead, copy it.
	        this.addFirst(new Terrapin(t));
	}
	
	/**
	 * Restore Terrapin state from internal stack.
	 * Includes location, rotation, drawColor, and drawing (boolean) state.
	 *
         * @return a Terrapin with the saved/restored state 
	 *
	 * @see terrapin.TerrapinStack#push(Terrapin)
	 */	
	public Terrapin pop() {
                // TODO: should we check for empty, and print a helpful warning?
                // Or just let exceptions happen?
                return this.removeFirst();
	}
}
