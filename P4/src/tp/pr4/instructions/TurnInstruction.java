package tp.pr4.instructions;


import java.util.StringTokenizer;
import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;
import tp.pr4.Rotation;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.instructions.exceptions.WrongInstructionFormatException;
import tp.pr4.items.ItemContainer;

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
	 * Method receives complete engine and use part of configureContext 
	 * depends of the instruction needs.
	 * 
	 * engine robot engine
     * navigation information about map (actualPlace, currentHeading, rotation...)
     * robotContainer inventory of robot 
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
		{
			robot.addFuel(-5);
			robot.printRobotState();
			
		}else
			new InstructionExecutionException();
	}

}
