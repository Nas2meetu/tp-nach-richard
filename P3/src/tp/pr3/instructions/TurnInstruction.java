package tp.pr3.instructions;

import static tp.pr3.Constants.LINE_SEPARATOR;
import static tp.pr3.Constants.LOOKING_DIRECTION;
import static tp.pr3.Constants.POWER2;
import static tp.pr3.Constants.RECYCLED_MATERIAL;

import java.util.StringTokenizer;

import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.Rotation;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.ItemContainer;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 3
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
		robot.addFuel(-5);
		System.out.println(LOOKING_DIRECTION + navigation.getCurrentHeading()
				+ LINE_SEPARATOR + POWER2 + robot.getFuel() + LINE_SEPARATOR
				+ RECYCLED_MATERIAL + robot.getRecycledMaterial());

	}

}
