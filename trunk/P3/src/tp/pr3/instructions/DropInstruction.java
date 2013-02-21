package tp.pr3.instructions;

import static tp.pr3.Constants.*;
import java.util.StringTokenizer;
import tp.pr3.NavigationModule;
import tp.pr3.Place;
import tp.pr3.RobotEngine;
import tp.pr3.intructions.exceptions.*;
import tp.pr3.items.Item;
import tp.pr3.items.ItemContainer;

/**
*
* @author Ignacio Cerda Sanchez
* @author Ricardo Eugui Fernandez
* @version 3
*
*/

public class DropInstruction implements Instruction {

	private String id;
	private ItemContainer container;
	private Place actualPlace;

	public DropInstruction(String token2) {
		this.id = token2;
	}

	public DropInstruction() {
		
	}

	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {

		StringTokenizer st = new StringTokenizer(cad, " ");
		String words = st.nextToken().toUpperCase();
		if ((words.equals("DROP")) || (words.equals("SOLTAR"))) {
			if (st.hasMoreTokens()) {
				String token2 = st.nextToken();
				if (!st.hasMoreTokens())
					return new DropInstruction(token2);
				else 
					throw new WrongInstructionFormatException();
			} else
				throw new WrongInstructionFormatException();
		} else
			throw new WrongInstructionFormatException();

	}

	@Override
	public String getHelp() {
		return "DROP | SOLTAR <id>";
	}

	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void execute() throws InstructionExecutionException {
		 Item item = container.getItem(id);
		 if(item == null)
		     System.out.println(CONTAINER_NO_ITEM + id);
		 else if(actualPlace.addItem(item)){
		         container.pickItem(id);
		     System.out.println(PLACE_ITEM + id);
		 }else
		         System.out.println(PLACE_REPEAT_ITEM + id);
		       

	}
}
