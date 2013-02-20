package tp.pr3.instructions;

import static tp.pr3.Constants.*;
import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.intructions.exceptions.InstructionExecutionException;
import tp.pr3.intructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.Item;
import tp.pr3.items.ItemContainer;

public class DropInstruction implements Instruction {

	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void execute() throws InstructionExecutionException {
		 Item item = container.getItem(instruction.getId());
		 if(item == null)
		     System.out.println(CONTAINER_NO_ITEM + instruction.getId());
		 else if(actualPlace.addItem(item)){
		         container.pickItem());
		     System.out.println(PLACE_ITEM + instruction.getId());
		 }else
		         System.out.println(PLACE_REPEAT_ITEM + instruction.getId());
		       

	}

}
