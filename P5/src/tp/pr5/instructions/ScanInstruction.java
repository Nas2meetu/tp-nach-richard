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

public class ScanInstruction implements Instruction {

	private String id;
	private ItemContainer robotContainer;

	public ScanInstruction(String token2) {
		this.id = token2;
	}

	public ScanInstruction() {

	}

	/**
	 * Read a string with an action, compare if this action is correct and
	 * generate ScanInstruction, else throw an exception.
	 */

	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {

		StringTokenizer st = new StringTokenizer(cad, " ");
		String words = st.nextToken().toUpperCase();
		if ((words.equals("SCAN")) || (words.equals("ESCANEAR"))) {
			if (st.hasMoreTokens()) {
				String token2 = st.nextToken();
				if (!st.hasMoreTokens())
					return new ScanInstruction(token2);
				else
					throw new WrongInstructionFormatException();
			} else
				return new ScanInstruction();
		} else
			throw new WrongInstructionFormatException();

	}

	/**
	 * Show information about SCAN instruction syntax.
	 */

	@Override
	public String getHelp() {
		return "SCAN | ESCANEAR <id>";
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
		this.robotContainer = robotContainer;

	}

	/**
	 * Execute SCAN instruction.
	 */

	@Override
	public void execute() throws InstructionExecutionException {
		if (id == null) {
			System.out.println(robotContainer.showItems());
		} else {
			//Item item = robotContainer.getItem(id);
			//if (item != null)
			//	System.out.println(WALLE_SAYS + item.toString());
			//else
				throw new InstructionExecutionException(SCAN_NO_ITEM + id);
		}
	}
}
