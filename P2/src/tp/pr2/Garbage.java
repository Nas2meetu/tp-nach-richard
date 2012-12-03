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
	
	private int recycledMaterial;

	public Garbage(String id, String description, int recycledMaterial){
		super(id, description);
		this.recycledMaterial = recycledMaterial;
	}
	
	public boolean canBeUsed() {
		return (recycledMaterial > 0);
	}
	
	public int getRecycledMaterial() {
		return recycledMaterial;
	}
	
	public void totalGarbage(int newRecycledMaterial){
		recycledMaterial+= newRecycledMaterial;
	}

	public boolean use(RobotEngine engine, Place place) {
		return false;
	}
	
	public String toString(){
			return (super.toString() + LINE_SEPARATOR + recycledMaterial); 
		}
}
