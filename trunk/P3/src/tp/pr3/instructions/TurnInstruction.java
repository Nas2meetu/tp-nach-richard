package tp.pr3.instructions;

import static tp.pr3.Constants.LINE_SEPARATOR;
import static tp.pr3.Constants.LOOKING_DIRECTION;
import static tp.pr3.Constants.POWER2;
import static tp.pr3.Constants.RECICLED_MATERIAL;

import java.util.StringTokenizer;

import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.Rotation;
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

public class TurnInstruction implements Instruction {

	private String id;
	
	public TurnInstructionLeft(String token2) {
		this.id = token2;
	}
	public TurnInstructionRight(String token2) {
		this.id = token2;
	}

	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {

		StringTokenizer st = new StringTokenizer(cad, " ");
		String words = st.nextToken().toUpperCase();
		if ((words.equals("TURN")) || (words.equals("GIRAR"))) {
			if (st.hasMoreTokens()){
                String token2 = st.nextToken().toUpperCase();
                if (st.hasMoreTokens())
                	throw new WrongInstructionFormatException();
                else if ((token2.equals("LEFT")) || (token2.equals("IZQUIERDA"))) 
                		return new TurnInstructionLeft(token2);
                else if ((token2.equals("RIGHT")) || (token2.equals("DERECHA")))
                        return new TurnInstructionRight(token2);
                else
                	throw new WrongInstructionFormatException();

        } else throw new WrongInstructionFormatException();

	}

	@Override
	public String getHelp() {
		return "TURN | GIRAR <LEFT|RIGHT>";
	}

	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void execute() throws InstructionExecutionException {
        switch (id) {
        case LEFT:
                lookingDirection = lookingDirection.turnLeft();
                addFuel(-1);
                System.out.println(POWER2 + contFuel + LINE_SEPARATOR + RECICLED_MATERIAL
                                + contRecycledMaterial + LINE_SEPARATOR + LOOKING_DIRECTION + lookingDirection);
                break;
        case RIGHT:
                lookingDirection = lookingDirection.turnRight();
                addFuel(-1);
                System.out.println(POWER2 + contFuel + LINE_SEPARATOR + RECICLED_MATERIAL
                                + contRecycledMaterial + LINE_SEPARATOR + LOOKING_DIRECTION + lookingDirection);
                break;
        case UNKNONW:
                break;
        }

}
