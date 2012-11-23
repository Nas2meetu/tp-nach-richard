package tp.pr2;

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
		return canBeUsed();
	}

	public boolean use(RobotEngine engine, Place place) {
		return false;
	}
	
	public String fueltoString(){
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
