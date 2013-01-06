package tp.pr2;


/**
*
* @author Ignacio Cerda Sanchez
* @author Ricardo Eugui Fernandez
* @version 1
*
*/


public abstract class Item {

	private String id;
	private String description;

	
	/**
	 * 
	 * Constructor of two parameters to define an item.
	 * 
	 * @param id is a reference to identify an item.
	 * @param description is description of item.
	 */
	
	public Item(String id, String description) {
		this.id = id;
		this.description = description;
	
	}

	public abstract boolean canBeUsed();
	
	public abstract boolean use(RobotEngine robot, Place place);
	
	/**
	 * 
	 * Return a public method (id) of a private attribute (Id).
	 * 
	 * @return id is a reference to identify an item.
	 */

	public String getId() {
		return this.id;
	}
	
	/**
	 * 
	 * Return information about an item: id (reference to identify an item)
	 * + description (description of item).
	 * 
	 */
	
	public String toString() {
		return  this.id +": " + this.description;
	}
	
	/**
	 * 
	 * Show description about item.
	 * 
	 * @return description is description of item.
	 */

	public String getDescription() {
		return description;
	}

}
