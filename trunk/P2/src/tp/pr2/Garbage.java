package tp.pr2;

import static tp.pr2.Constants.*;

public class Garbage extends Item {	
	
	private int recycledMaterial;

	public Garbage(String id, String description, int recycledMaterial){
		super(id, description);
		this.recycledMaterial = recycledMaterial;
	}
	
	public boolean canBeUsed() {
		return canBeUsed();
	}

	public boolean use(RobotEngine engine, Place place) {
		return false;
	}
	
	public String toString(){
			return (super.toString() + LINE_SEPARATOR + recycledMaterial); 
		}
}
