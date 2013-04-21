package tp.pr4.instructions;

import static tp.pr4.Constants.*;

import java.util.StringTokenizer;

import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;
import tp.pr4.instructions.exceptions.*;
import tp.pr4.items.ItemContainer;

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
		this.navigation = navigation;
		this.robotContainer = robotContainer;

	}

	/**
	 * 
	 * Execute DROP instruction Verify if item isn't null, Place hasn't got
	 * this item and put into Place.
	 */

	@Override
	public void execute() throws InstructionExecutionException {
		if (id != null && robotContainer.containsItem(id))
			if (!navigation.findItemAtCurrentPlace(id)) {
				navigation.getCurrentPlace().addItem(
						robotContainer.pickItem(id));
				navigation.updatePlace();
				System.out.println(PLACE_ITEM + id);
			} else
				throw new InstructionExecutionException(PLACE_REPEAT_ITEM + id);
		else
			throw new InstructionExecutionException(CONTAINER_NO_ITEM + id
					+ ".");
		robotContainer.updateInventory();
	}
}
