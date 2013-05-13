package tp.pr5.instructions;

import static tp.pr5.Constants.*;

import java.util.StringTokenizer;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.Item;
import tp.pr5.items.ItemContainer;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 4
 * 
 */

public class PickInstruction implements Instruction {

	private NavigationModule navigation;
	private String id;
	private ItemContainer robotContainer;
	private RobotEngine robot;

	public PickInstruction(String token2) {
		this.id = token2;

	}


	public PickInstruction() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * Read a string with an action, compare if this action is correct and
	 * generate PickInstruction, else throw an exception.
	 */

	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {

		StringTokenizer st = new StringTokenizer(cad, " ");
		String words = st.nextToken().toUpperCase();
		if ((words.equals("PICK")) || (words.equals("COGER"))) {
			if (st.hasMoreTokens()) {
				String token2 = st.nextToken();
				if (!st.hasMoreTokens())
					return new PickInstruction(token2);
				else
					throw new WrongInstructionFormatException();
			} else
				throw new WrongInstructionFormatException();
		} else
			throw new WrongInstructionFormatException();
	}

	/**
	 * Show information about PICK instruction syntax.
	 */

	@Override
	public String getHelp() {
		return "PICK | COGER <id>";
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
		this.robot= engine;
		this.navigation = navigation;
		this.robotContainer = robotContainer;
		
	}

	/**
	 * Execute PICK instruction Verify if item isn't null and if place has got
	 * thi item.
	 */

	@Override
	public void execute() throws InstructionExecutionException {
		Item item = navigation.getCurrentPlace().getItem(id);
		if (item == null)
			throw new InstructionExecutionException(PLACE_NO_ITEM + id);
		else if (robotContainer.addItem(item)) {
			navigation.pickItemFromCurrentPlace(item);
			robotContainer.updateInventory();
			robot.saySomething(CONTAINER_ITEM + id);
		} else
			throw new InstructionExecutionException(CONTAINER_REPEAT_ITEM + id);

	}

}