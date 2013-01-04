package tp.pr3;


/**
*
* @author Ignacio Cerda Sanchez
* @author Ricardo Eugui Fernandez
* @version 3
*
*/


public abstract class Item {

	private String id;
	private String description;

	public Item(String id, String description) {
		this.id = id;
		this.description = description;
	
	}

	public abstract boolean canBeUsed();
	
	public abstract boolean use(RobotEngine robot, Place place);
	

	public String getId() {
		return this.id;
	}
	public String toString() {
		return  this.id +": " + this.description;
	}

	public String getDescription() {
		return description;
	}

}
