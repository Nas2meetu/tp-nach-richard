package tp.pr3.instructions;

import static tp.pr3.Constants.*;
import java.util.StringTokenizer;
import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.Item;
import tp.pr3.items.ItemContainer;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 3
 * 
 */

public class PickInstruction implements Instruction {

	private NavigationModule navigation;
	private String id;
	private ItemContainer robotContainer;

	public PickInstruction(String token2) {
		this.id = token2;

	}

	public PickInstruction() {

	}
	
	/**
	 * Read a string with an action, compare if this action is correct 
	 * and generate PickInstruction, else throw an exception.
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
	 * Execute PICK instruction
	 * Verify if item isn't null and if place has got thi item.
	 */
	
	@Override
	public void execute() throws InstructionExecutionException {
		Item item = navigation.getCurrentPlace().getItem(id);
	     if(item == null)
	         throw new InstructionExecutionException(PLACE_NO_ITEM + id);
	     else if(robotContainer.addItem(item)){
	             navigation.pickItemAtCurrentPlace(item);
	         System.out.println(CONTAINER_ITEM + id);
	     }else
	         throw new InstructionExecutionException(CONTAINER_REPEAT_ITEM + id);
	}

}