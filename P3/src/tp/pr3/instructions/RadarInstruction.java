package tp.pr3.instructions;

import static tp.pr3.Constants.*;
import java.util.StringTokenizer;
import tp.pr3.NavigationModule;
import tp.pr3.Place;
import tp.pr3.RobotEngine;
import tp.pr3.intructions.exceptions.InstructionExecutionException;
import tp.pr3.intructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.ItemContainer;

/**
*
* @author Ignacio Cerda Sanchez
* @author Ricardo Eugui Fernandez
* @version 3
*
*/

public class RadarInstruction implements Instruction {
	
	private Place actualPlace;
	private NavigationModule navigation;
	
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
	@Override
	public String getHelp() {
		return "RADAR";
	}

	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		this.navigation=navigation;

	}

	@Override
	public void execute() throws InstructionExecutionException {
		if (navigation.getCurrentPlace()!=null)
			System.out.println(WALLE_SAYS + navigation.getCurrentPlace().toString());
		else
			throw new InstructionExecutionException();
	}

}
