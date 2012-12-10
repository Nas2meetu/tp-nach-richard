package tp.pr2;

/**
*
* @author Ignacio Cerda Sanchez
* @author Ricardo Eugui Fernandez
* @version 1
*
*/

import static tp.pr2.Constants.*;

public class Fuel extends Item {	
	
	private int power;
	private int times;

	public Fuel(String id, String description, int power, int times){
		super(id, description);
		this.power = power;
		this.times = times;
	}
	
	public boolean canBeUsed() {
		return (this.times > 0);
	}

	public boolean use(RobotEngine robot, Place where) {
		
		if (canBeUsed()){
			robot.addFuel(power);
			this.times--;
			return true;
		}return false;
		
	}
	
	public int getPower() {
		return power;
	}
	
	public void totalFuel(int newPower){
		power+= newPower;
	}

	private String fueltoString(){
		return power + LINE_SEPARATOR + times;
	}
	public String toString(){
		return (super.toString() + LINE_SEPARATOR + fueltoString());
	}
	/*
		public String toString(){
			return (super.toString() + LINE_SEPARATOR + power + LINE_SEPARATOR + times; 
	 */
}
