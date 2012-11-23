package tp.pr2;

/**
*
* @author Ignacio Cerda Sanchez
* @author Ricardo Eugui Fernandez
* @version 1
*/

public class Instruction {
	
	/**
     * Receive and execute instructions
     */

	private Action action;
	private Rotation rotation;
	private String itemName;
	
	/**
	 * Constructor of one parameter action
	 * @param action is an action made by Robot
	 */

	public Instruction(Action action) {
		this.action = action;
		this.rotation = Rotation.UNKNONW;
	}
	
	/**
	 * Constructor of two parameter action, includes turn and direction to rotation
	 * @param action is an action to turn Robot
	 * @param rotation is rotation of Robot left or right
	 *  
	 */

	public Instruction(Action action, Rotation rotation) {
		this.action = action;
		this.rotation = rotation;
	}
	
	/**
	 * Default Constructor without parameters 
	 */

	public Instruction() {
		this.action = Action.UNKNOWN;
		this.rotation = Rotation.UNKNONW;
	}
	
	public Instruction(Action action, String itemName) {
		this.action = action;
		this.itemName = itemName;
		
	}

	/**
	 * Is a method to know if action and rotation are valids
	 * @return isValid if action is not Unknown or turn rotation is not Unknown
	 */

	public boolean isValid() {
	
		return !((action.equals(Action.UNKNOWN) ||
				  (action.equals(Action.TURN) && rotation.equals(Rotation.UNKNONW))/* ||
				  (action.equals(Action.PICK) && itemName.equals(null)*/));//mirar cuando el pick cogido es inexistente
	}
	
	/**
	 * 
	 * @return action
	 * 
	 */

	public Action getAction() {	
		return action;
	}
	
	/**
	 * 
	 * @return rotation
	 * 
	 */

	public Rotation getRotation() {
		return rotation;
	}

	public String getId() {
		return itemName;
	}

}
