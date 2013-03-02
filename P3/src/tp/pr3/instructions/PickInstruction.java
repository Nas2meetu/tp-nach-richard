package tp.pr3.instructions;

import static tp.pr3.Constants.*;
import java.util.StringTokenizer;
import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.intructions.exceptions.InstructionExecutionException;
import tp.pr3.intructions.exceptions.WrongInstructionFormatException;
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
					throw new WrongInstructionFormatException(BAD_INSTRUCTION);
			} else
				throw new WrongInstructionFormatException(BAD_INSTRUCTION);
		} else
			throw new WrongInstructionFormatException(BAD_INSTRUCTION);
	}

	@Override
	public String getHelp() {
		return "PICK | COGER <id>";
	}

	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		this.navigation = navigation;
		this.robotContainer = robotContainer;
	}

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