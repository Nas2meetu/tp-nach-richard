package tp.pr1;

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
	
	/**
	 * Constructor of one parameter action
	 * @param action is an action make by Robot
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
	
	/**
	 * Is method to known if action and rotation is valid
	 * @return isValid if action isn't Unknown or turn rotation isn't Unknown
	 */

	public boolean isValid() {
	
		return !((action.equals(Action.UNKNOWN) ||
				  (action.equals(Action.TURN) && rotation.equals(Rotation.UNKNONW))));
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

}
