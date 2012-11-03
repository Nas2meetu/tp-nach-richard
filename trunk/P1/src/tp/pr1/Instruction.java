package tp.pr1;

public class Instruction {

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
	
		if (action.equals(Action.UNKNOWN) || (action.equals(Action.TURN) && rotation.equals(Rotation.UNKNONW))) {
			return false;
		} else {
			return true;
		}
	}

	public Action getAction() {	
		return action;
	}

	public Rotation getRotation() {
		return rotation;
	}

}
