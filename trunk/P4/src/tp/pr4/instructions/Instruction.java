package tp.pr4.instructions;

import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;
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

/**
 * Interface with parse, getHelp, configureContext and execute heads
 */

public interface Instruction {
	
	public Instruction parse(String cad) throws WrongInstructionFormatException;
	
	public String getHelp();
	
	public void configureContext(RobotEngine engine, NavigationModule navigation, ItemContainer robotContainer);
	
	public void execute() throws InstructionExecutionException;

}

