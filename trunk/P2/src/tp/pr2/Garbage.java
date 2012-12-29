package tp.pr2;


/**
*
* @author Ignacio Cerda Sanchez
* @author Ricardo Eugui Fernandez
* @version 1
*
*/



public class Garbage extends Item {	
	
	private int garbage;
	private boolean canBeUse;

	public Garbage(String id, String description, int recycledMaterial){
		super(id, description);
		this.garbage = recycledMaterial;
		canBeUse = true;
		
	}
	
	public boolean canBeUsed() {
		return (garbage > 0 && canBeUse);
		
	}
	
	public int getGarbage() {
		return garbage;
	}
	
	

	public boolean use(RobotEngine robot, Place place) {
		if (canBeUsed()){
            robot.addRecycledMaterial(garbage);
            canBeUse = false;
            return true;
		}return false; 
	}
	
	private String garbagetoString(){
		return " recycled material = " + garbage;
	}
	
	public String toString(){
		return (super.toString() + "//" + garbagetoString());
	}
}
