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
     * 
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
	 * 
	 * @param action
	 * @param rotation
	 */

	public Instruction(Action action, Rotation rotation) {
		this.action = action;
		this.rotation = rotation;
	}
	
	/**
	 * 
	 */

	public Instruction() {
		this.action = Action.UNKNOWN;
		this.rotation = Rotation.UNKNONW;
	}
	
	/*
	 * 
	 */

	public boolean isValid() {
	
		return !((action.equals(Action.UNKNOWN) ||
				  (action.equals(Action.TURN) && rotation.equals(Rotation.UNKNONW))));
	}
	
	/**
	 * 
	 * @return
	 */

	public Action getAction() {	
		return action;
	}
	
	/**
	 * 
	 * @return
	 */

	public Rotation getRotation() {
		return rotation;
	}

}
