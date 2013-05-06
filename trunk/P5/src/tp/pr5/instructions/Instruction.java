package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;

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

