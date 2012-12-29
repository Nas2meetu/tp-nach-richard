package tp.pr2;
import static tp.pr2.Constants.*;

/**
*
* @author Ignacio Cerda Sanchez
* @author Ricardo Eugui Fernandez
* @version 1
*
*/



public class Fuel extends ExpirationItem {	
	
	private int power;

	public Fuel(String id, String description, int power){
		super(id, description, DEFAULT_TIMES);
		this.power = power;
	}
	
	public Fuel(String id, String description, int power, int times){
		super(id, description, times);
		this.power = power;
	}
	
	
	public boolean use(RobotEngine robot, Place where) {
		boolean used = false;
		if (canBeUsed()){
			super.use(robot, where);
			used = true;
		}else
			used = false;
		return used;
	}
	
	

	public int getPower() {
		return power;
	}
	

	/*private String fueltoString(){
		return " power = "+ power + "," + " times = " + times;
	}
	public String toString(){
		return (super.toString() + "//" + fueltoString());
	}*/
	
}
