package tp.pr3.instructions;

import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.intructions.exceptions.InstructionExecutionException;
import tp.pr3.intructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.ItemContainer;


public interface Instruction {
	
	public Instruction parse(String cad) throws WrongInstructionFormatException;
	
	public String getHelp();
	
	public void configureContext(RobotEngine engine, NavigationModule navigation, ItemContainer robotContainer);
	
	public void execute() throws InstructionExecutionException;

}

