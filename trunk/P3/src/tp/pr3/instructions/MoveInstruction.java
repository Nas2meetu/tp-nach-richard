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
	
	private Place actualPlace;
	private Direction lookingDirection;
	private Integer contFuel;
	private Integer contRecycledMaterial;
	private City cityMap;

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
		// TODO Auto-generated method stub

	}

	@Override
	public void execute() throws InstructionExecutionException {
		if (getHeadingStreet()==null){
            System.out.println(NO_STREET);
    }
    else if (getHeadingStreet().isOpen()){
            actualPlace = getHeadingStreet().nextPlace(actualPlace);
            addFuel(-5);
            System.out.println(MOVE + lookingDirection);
            System.out.println(actualPlace.toString() +
                            POWER2 + contFuel + LINE_SEPARATOR + RECICLED_MATERIAL + contRecycledMaterial +
                            LINE_SEPARATOR + LOOKING_DIRECTION + lookingDirection) ;
                   
    }else
            System.out.println(STREET_CLOSE);
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
