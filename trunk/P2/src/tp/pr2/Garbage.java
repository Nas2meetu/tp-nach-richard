package tp.pr2;

/**
*
* @author Ignacio Cerda Sanchez
* @author Ricardo Eugui Fernandez
* @version 1
*
*/

import static tp.pr2.Constants.*;

public class Garbage extends Item {	
	
	private int garbage;

	public Garbage(String id, String description, int recycledMaterial){
		super(id, description);
		this.garbage = INITIAL_GARBAGE;
	}
	
	public boolean canBeUsed() {
		return (garbage > 0);
	}
	
	public int getGarbage() {
		return garbage;
	}
	
	public void totalGarbage(int newMaterial){
		garbage+= newMaterial;
	}

	public boolean use(RobotEngine engine, Place place) {
		return false;
	}
	
	public String toString(){
			return (super.toString() + LINE_SEPARATOR + garbage); 
		}
}
