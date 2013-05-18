package tp.pr5.instructions;

import static tp.pr5.Constants.*;

import java.util.StringTokenizer;
import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.*;
import tp.pr5.items.ItemContainer;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 4
 * 
 */

public class DropInstruction implements Instruction {

	private NavigationModule navigation;
	private String id;
	private ItemContainer robotContainer;
	private RobotEngine robot;

	public DropInstruction(String id) {
		this.id = id;
	}

	public DropInstruction() {

	}

	/**
	 * Read a string with an action, compare if this action is correct and
	 * generate DropInstruction, else throw an exception.
	 */

	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {

		StringTokenizer st = new StringTokenizer(cad, " ");
		String words = st.nextToken().toUpperCase();
		if ((words.equals("DROP")) || (words.equals("SOLTAR"))) {
			if (st.hasMoreTokens()) {
				id = st.nextToken();
				if (!st.hasMoreTokens())
					return new DropInstruction(id);
				else
					throw new WrongInstructionFormatException();
			} else
				throw new WrongInstructionFormatException();
		} else
			throw new WrongInstructionFormatException();

	}

	/**
	 * Show information about DROP instruction syntax.
	 */

	@Override
	public String getHelp() {
		return "DROP | SOLTAR <id>";
	}

	/**
	 * 
	 * Set the execution context. The method receives the entire engine (engine,
	 * navigation and the robot container) even though the actual implementation
	 * of execute() may not require it.
	 * 
	 * engine The robot engine navigation The information about the game, i.e.,
	 * the places, current direction and current heading to navigate
	 * robotContainer The inventory of the robot
	 * 
	 */

	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		this.robot = engine;
		this.navigation = navigation;
		this.robotContainer = robotContainer;

	}

	/**
	 * 
	 * Execute DROP instruction Verify if item isn't null, Place hasn't got this
	 * item and put into Place.
	 */

	@Override
	public void execute() throws InstructionExecutionException {
		if (id != null && robotContainer.containsItem(id))
			if (!navigation.findItemAtCurrentPlace(id)) {
				navigation.dropItemAtCurrentPlace(robotContainer.pickItem(id));
				robot.saySomething(WALLE_SAYS + DROP_ITEM + id + LINE_SEPARATOR);
				robotContainer.updateInventory();
			} else
				throw new InstructionExecutionException(PLACE_REPEAT_ITEM + id);
		else
			throw new InstructionExecutionException(ITEM_NOT_EXIST + id + ".");

	}

}
