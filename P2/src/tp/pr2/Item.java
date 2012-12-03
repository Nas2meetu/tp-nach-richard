package tp.pr2;

/**
*
* @author Ignacio Cerda Sanchez
* @author Ricardo Eugui Fernandez
* @version 1
*
*/

import static tp.pr2.Constants.LINE_SEPARATOR;

public abstract class Item {

	private String id;
	private String description;

	public Item(String id, String description) {
		this.id = id;
		this.description = description;
	
	}

	public abstract boolean canBeUsed();
	
	public abstract boolean use(RobotEngine engine, Place place);
	

	public String getId() {
		return this.id;
	}
	public String toString() {
		return  this.id + LINE_SEPARATOR + this.description;
	}

}
