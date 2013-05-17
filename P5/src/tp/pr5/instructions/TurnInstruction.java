package tp.pr5.instructions;


import java.util.StringTokenizer;
import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.Rotation;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 4
 * 
 */

public class TurnInstruction implements Instruction {

	private Rotation rotation;
	private RobotEngine robot;
	private NavigationModule navigation;

	public TurnInstruction(Rotation token2) {
		this.rotation = token2;
	}

	public TurnInstruction() {

	}

	/**
	 * Read a string with an action, compare if this action is correct and
	 * generate TurnInstruction, else throw an exception.
	 */

	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {

		StringTokenizer st = new StringTokenizer(cad, " ");
		String words = st.nextToken().toUpperCase();
		if ((words.equals("TURN")) || (words.equals("GIRAR"))) {
			if (st.hasMoreTokens()) {
				String token2 = st.nextToken().toUpperCase();
				if (st.hasMoreTokens())
					throw new WrongInstructionFormatException();
				else if ((token2.equals("LEFT"))
						|| (token2.equals("IZQUIERDA")))
					return new TurnInstruction(Rotation.LEFT);
				else if ((token2.equals("RIGHT")) || (token2.equals("DERECHA")))
					return new TurnInstruction(Rotation.RIGHT);
				else
					throw new WrongInstructionFormatException();
			}
			throw new WrongInstructionFormatException();
		} else
			throw new WrongInstructionFormatException();

	}

	/**
	 * Show information about TURN instruction syntax.
	 */

	@Override
	public String getHelp() {
		return "TURN | GIRAR <LEFT|RIGHT>";
	}

	/**
	 * 
	 * Set the execution context. The method receives the entire engine 
	 * (engine, navigation and the robot container) even though the actual implementation
	 *  of execute() may not require it.
	 * 
	 * engine 
	 * 		The robot engine
     * navigation 
     * 		The information about the game, i.e., the places, current direction and 
     * 		current heading to navigate
     * robotContainer  
     * 		The inventory of the robot 
	 * 
	 */

	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		this.robot = engine;
		this.navigation = navigation;

	}

	/**
	 * Execute TURN instruction.
	 */

	@Override
	public void execute() throws InstructionExecutionException {
		navigation.rotate(rotation);
		if(rotation!=Rotation.UNKNONW)
			robot.addFuel(-5);
		else
			new InstructionExecutionException();
	}

}
