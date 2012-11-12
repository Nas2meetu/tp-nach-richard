package tp.pr1;

/**
* 
* @author Ignacio Cerda Sanchez
* @author Ricardo Eugui Fernandez
* @version 1
*
* @param  
* 
* @return
*/

public class Instruction {
	
	/**
	 * Receive and execute instructions
	 * @return
	 */

	private Action action;
	private Rotation rotation;

	public Instruction(Action action) {
		this.action = action;
		this.rotation = Rotation.UNKNONW;
	}

	public Instruction(Action action, Rotation rotation) {
		this.action = action;
		this.rotation = rotation;
	}

	public Instruction() {
		this.action = Action.UNKNOWN;
		this.rotation = Rotation.UNKNONW;
	}

	public boolean isValid() {
		boolean value ;
		if (action.equals(Action.UNKNOWN) || (action.equals(Action.TURN) && rotation.equals(Rotation.UNKNONW))) {
			value = false;
		} else {
			value = true;
		}
		return value;
	}

	public Action getAction() {	
		return action;
	}

	public Rotation getRotation() {
		return rotation;
	}

}
