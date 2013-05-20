package tp.pr5.items;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 5
 * 
 *          The garbage is a type of item that generates recycled material after
 *          using it. The garbage can be used only once. After using it, it must
 *          be removed from the robot inventory
 */

public class Garbage extends Item {

	private int garbage;
	private boolean canBeUse;

	/**
	 * 
	 * Constructor of three parameters to define garbage of robot.
	 * 
	 * @param id
	 *            is a reference to identify an item.
	 * @param description
	 *            is description of item.
	 * @param recycledMaterial
	 *            count of recycle material.
	 * 
	 */

	public Garbage(String id, String description, int recycledMaterial) {
		super(id, description);
		this.garbage = recycledMaterial;
		canBeUse = true;

	}

	/**
	 * Garbage can be used only once
	 * 
	 * Return a public methods (garbage and canBeUse) of a private attribute
	 * (canBeUse).
	 * 
	 * Verify if robot can be recycled garbage or not.
	 * 
	 * return true if the item has not been used yet.
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
	 * The garbage generates recycled material for the robot that uses it
	 * 
	 * Verify if garbage can be recycle or not.
	 * 
	 * robot - the robot engine. navigation - the navigation module
	 * 
	 * return true if the garbage was transformed in recycled material.
	 * 
	 */

	public boolean use(RobotEngine robot, NavigationModule navigation) {
		if (canBeUsed()) {
			robot.addRecycledMaterial(garbage);
			canBeUse = false;
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
