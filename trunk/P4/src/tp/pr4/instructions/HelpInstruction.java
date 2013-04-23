package tp.pr4.instructions;

import java.util.StringTokenizer;

import javax.swing.undo.CannotUndoException;

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

public class HelpInstruction implements Instruction {

	private RobotEngine robot;
	
	/**
	 * Read a string with an action, compare if this action is correct 
	 * and generate DropInstruction, else throw an exception.
	 */
	
	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {

		StringTokenizer st = new StringTokenizer(cad, " ");
		String words = st.nextToken().toUpperCase();
		if ((words.equals("HELP")) || (words.equals("AYUDA"))) {
			if (!st.hasMoreTokens())
				return new HelpInstruction();
			else
				throw new WrongInstructionFormatException();
		} else
			throw new WrongInstructionFormatException();
	}

	/**
	 * Show information about HELP instruction syntax.
	 */
	
	@Override
	public String getHelp() {
		return "HELP | AYUDA";
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
		this.robot = engine;

	}
	
	/**
	 * Execute HELP instruction
	 */
	
	@Override
	public void execute() throws InstructionExecutionException {
		robot.requestHelp();
	}

	@Override
	public void undo() throws CannotUndoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canUndo() {
		// TODO Auto-generated method stub
		return false;
	}

}
