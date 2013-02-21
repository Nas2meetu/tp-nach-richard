package tp.pr3.instructions;

import static tp.pr3.Constants.*;
import java.util.StringTokenizer;
import tp.pr3.NavigationModule;
import tp.pr3.Place;
import tp.pr3.RobotEngine;
import tp.pr3.intructions.exceptions.InstructionExecutionException;
import tp.pr3.intructions.exceptions.WrongInstructionFormatException;
import tp.pr3.items.Item;
import tp.pr3.items.ItemContainer;

/**
*
* @author Ignacio Cerda Sanchez
* @author Ricardo Eugui Fernandez
* @version 3
*
*/

public class OperateInstruction implements Instruction {

	private ItemContainer container;
	private String id;
	private Place actualPlace;
	
	public OperateInstruction(String token2) {
		this.id = token2;
	}
	
	public OperateInstruction() {
		
	}

	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {
	
		StringTokenizer st = new StringTokenizer(cad, " ");
		String words = st.nextToken().toUpperCase();
		if ((words.equals("OPERATE")) || (words.equals("OPERAR"))) {
			if (st.hasMoreTokens()) {
				String token2 = st.nextToken();
				if (!st.hasMoreTokens())
					return new OperateInstruction(token2);
				else 
					throw new WrongInstructionFormatException();
			} else
				throw new WrongInstructionFormatException();
		} else
			throw new WrongInstructionFormatException();
	}

	@Override
	public String getHelp() {
		return "OPERATE | OPERAR <id>";
	}

	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void execute() throws InstructionExecutionException {
		
		 Item item = container.getItem(id);
         if (item != null && item.canBeUsed()){
                 item.use(this, actualPlace);
                
         }
         else
                 System.out.println(ITEM_PROBLEMS + id + " in my inventory");
         if (item!=null && !item.canBeUsed()) {
     container.pickItem(id);
     System.out.println(ITEM_CANT_USED+id+" in my inventory");
         }

	}

}
