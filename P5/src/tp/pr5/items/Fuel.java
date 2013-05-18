package tp.pr5.items;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 3
 * 
 */

public class Fuel extends Item {

	private int power;
	private int times;

	/**
	 * 
	 * Constructor of four parameters to define energy of Robot.
	 * 
	 * @param id is a reference to identify an item.       
	 * @param description is description of item.         
	 * @param power unit of energy.       
	 * @param times number of uses for this item.
	 *            
	 */

	public Fuel(String id, String description, int power, int times) {
		super(id, description);
		this.power = power;
		this.times = times;
	}

	/**
	 * 
	 * If item can be used or not.
	 * 
	 */

	public boolean canBeUsed() {
		return (this.times > 0);
	}

	/**
	 * 
	 * Verify if item can be used and if it return true use the item.
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
	 * Show power and times of Robot.
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
