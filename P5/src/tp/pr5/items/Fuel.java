package tp.pr5.items;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 5
 * 
 *          An item that represents fuel. This item can be used at least once
 *          and it provides power energy to the robot. When the item is used the
 *          configured number of times, then it must be removed from the robot
 *          inventory
 */

public class Fuel extends Item {

	private int power;
	private int times;

	/**
	 * 
	 * Constructor of four parameters to define energy of Robot.
	 * 
	 * @param id
	 *            is a reference to identify an item.
	 * @param description
	 *            is description of item.
	 * @param power
	 *            unit of energy.
	 * @param times
	 *            number of uses for this item.
	 * 
	 */

	public Fuel(String id, String description, int power, int times) {
		super(id, description);
		this.power = power;
		this.times = times;
	}

	/**
	 * 
	 * Fuel can be used as many times as it was configured If item can be used
	 * or not.
	 * 
	 * return true if the item still can be used
	 */

	public boolean canBeUsed() {
		return (this.times > 0);
	}

	/**
	 * 
	 * Verify if item can be used and if it return true use the item.
	 * 
	 * robot - The robot that is going to use the fuel navigation. - The place
	 * where the fuel is going to be used.
	 * 
	 * return true if the fuel has been used.
	 * 
	 */

	public boolean use(RobotEngine robot, NavigationModule navigation) {
		boolean used = false;
		if (canBeUsed()) {
			robot.addFuel(this.power);
			this.times--;
			used = true;
		}
		return used;

	}

	/**
	 * 
	 * Return a public method (times) of a private attribute (Times).
	 * 
	 * @return times number of uses for this item.
	 */

	public int getTimes() {
		return times;
	}

	/**
	 * 
	 * Return a public method (power) of a private attribute (Power).
	 * 
	 * @return power unit of energy.
	 */

	public int getPower() {
		return power;
	}

	/**
	 * 
	 * Generates a String with the Item description Show power and times of
	 * Robot.
	 * 
	 * @return power + times.
	 */

	private String fueltoString() {
		return " power = " + power + "," + " times = " + times;
	}

	public String toString() {
		return (super.toString() + "//" + fueltoString());
	}

}
