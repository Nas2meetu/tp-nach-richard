package pr1;


public class Instruction {
	private Action action;
	private Rotation rotation;
	
	
	public Instruction(){
		action = Action.UNKNOWN;
		rotation = Rotation.UNKNONW;
		
	}
	public Instruction(Action action){
		this.action = action;
	}
	public Instruction(Action action, Rotation rotation){
		this.action = action;
		this.rotation = rotation;
	}
	
	public Action getAction() {
		return action;
	}
			
	public Rotation getRotation() {
		return rotation;
	}
	
	public boolean isValid(){
		switch (this.action) {
		case UNKNOWN:
			System.out.println("says: I dont understand. Please repeat");
			return false;
		case MOVE:
			return true;
		case TURN:
			switch (this.rotation){
			case UNKNONW:
				System.out.println("says: I dont understand. Please repeat");
				return false;
			case LEFT:
				return true;
			case RIGHT:
				return true;
			}
			System.out.println("says: I dont understand. Please repeat");
			return false;
		case HELP:
			return true;
		case QUIT:
			return true;
			}
		System.out.println("says: I dont understand. Please repeat");
		return false;
	}
	
}
