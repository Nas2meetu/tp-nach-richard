package tp.pr2;
import static tp.pr2.Constants.*;

/**
*
* @author Ignacio Cerda Sanchez
* @author Ricardo Eugui Fernandez
* @version 1
*
*/



public class Garbage extends ExpirationItem {	
	
	private int garbage;

	public Garbage(String id, String description, int recycledMaterial){
		super(id, description, DEFAULT_TIMES);
		this.garbage = recycledMaterial;
		
	}
	
	public boolean canBeUsed() {
		return (garbage > 0);
		
	}
	
	public int getGarbage() {
		return garbage;
	}
	
	

	public boolean use(RobotEngine robot, Place where) {
		if (canBeUsed()) {
			super.use(robot, where);
			robot.addRecycledMaterial(this.garbage);
			return true;
		} else
			return false;
	}
	
	public String toString() {
		return super.toString() + Constants.LINE_SEPARATOR;
	}
}
