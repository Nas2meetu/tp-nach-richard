package tp.pr3.items;

import static tp.pr3.Constants.*;
import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 3
 * 
 */

public class Garbage extends Item {

	private int garbage;
	private boolean canBeUse;

	/**
	 * 
	 * Constructor of three parameters to define garbage of robot.
	 * 
	 * @param id is a reference to identify an item.         
	 * @param description is description of item.       
	 * @param recycledMaterial count of recycle material.           
	 * 
	 */

	public Garbage(String id, String description, int recycledMaterial) {
		super(id, description);
		this.garbage = recycledMaterial;
		canBeUse = true;

	}

	/**
	 * 
	 * Return a public methods (garbage and canBeUse) of a private attribute
	 * (canBeUse).
	 * 
	 * Verify if robot can be recycled garbage or not.
	 * 
	 */

	public boolean canBeUsed() {
		return (garbage > 0 && canBeUse);

	}

	/**
	 * 
	 * Return a public method (garbage) of a private attribute (Garbage).
	 * 
	 * @return garbage count of garbage to recycle.
	 */

	public int getGarbage() {
		return garbage;
	}

	/**
	 * 
	 * Verify if garbage can be recycle or not.
	 * 
	 */

	public boolean use(RobotEngine robot, NavigationModule navigation) {
		if (canBeUsed()) {
			robot.addRecycledMaterial(garbage);
			canBeUse = false;
			System.out.println(POWER2 + robot.getFuel() + LINE_SEPARATOR
					+ RECYCLED_MATERIAL + robot.getRecycledMaterial());
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * Show number of garbage was recycled and number of garbage to recycle.
	 * 
	 * @return recycled material + garbage.
	 */

	private String garbagetoString() {
		return " recycled material = " + garbage;
	}

	public String toString() {
		return (super.toString() + "//" + garbagetoString());
	}
}
