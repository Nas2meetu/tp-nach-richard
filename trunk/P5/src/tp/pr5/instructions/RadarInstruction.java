package tp.pr5.instructions;

import static tp.pr5.Constants.*;

import java.util.StringTokenizer;

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

public class RadarInstruction implements Instruction {
	
	private NavigationModule navigation;
	private RobotEngine robot;
	
	/**
	 * Read a string with an action, compare if this action is correct 
	 * and generate RadarInstruction, else throw an exception.
	 */
	
	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {

		StringTokenizer st = new StringTokenizer(cad, " ");
		String words = st.nextToken().toUpperCase();
		if(words.equals("RADAR")){
			if (!st.hasMoreTokens())
				return new RadarInstruction();
			else
				throw new WrongInstructionFormatException(BAD_INSTRUCTION);
		}
		else
			throw new WrongInstructionFormatException(BAD_INSTRUCTION);
	}
	
	/**
	 * Show information about RADAR instruction syntax.
	 */
	
	@Override
	public String getHelp() {
		return "RADAR";
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
		this.navigation=navigation;
		this.robot= engine;

	}
	/**
	 * Execute RADAR instruction.
	 */

	@Override
	public void execute() throws InstructionExecutionException {
//		if (navigation.getCurrentPlace()!=null)
//			robot.printCurrentPlace();
//		else
//			throw new InstructionExecutionException();
	}

}
