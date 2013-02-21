package tp.pr3.instructions;
import static tp.pr3.Constants.*;

import java.util.StringTokenizer;

import tp.pr3.City;
import tp.pr3.Direction;
import tp.pr3.NavigationModule;
import tp.pr3.Place;
import tp.pr3.RobotEngine;
import tp.pr3.Street;
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

public class MoveInstruction implements Instruction {
	
	private NavigationModule navigation;
	private RobotEngine robot;

	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		
		StringTokenizer st = new StringTokenizer(cad, " ");
		String words = st.nextToken().toUpperCase();
		if ((words.equals("MOVE")) || (words.equals("MOVER"))) {
			if (!st.hasMoreTokens())
				return new MoveInstruction();
			else
				throw new WrongInstructionFormatException();
		}
		else
			throw new WrongInstructionFormatException();
	}

	@Override
	public String getHelp() {
		return "MOVE | MOVER";
	}

	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		this.navigation=navigation;
		this.robot=engine;

	}

	@Override
	public void execute() throws InstructionExecutionException {
		navigation.move();
}

    public void addFuel(int newFuel) {
        this.contFuel += newFuel;
        if (contFuel<0){
                contFuel=0;
        }
}

	public Street getHeadingStreet() {
        return cityMap.lookForStreet(actualPlace, lookingDirection);
	}

}
