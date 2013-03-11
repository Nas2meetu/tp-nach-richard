package tp.pr3.instructions;

import static tp.pr3.Constants.*;

import java.util.StringTokenizer;
import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.instructions.exceptions.InstructionExecutionException;
import tp.pr3.instructions.exceptions.WrongInstructionFormatException;
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

	private NavigationModule navigation;
	private String id;
	private RobotEngine robot;
	private ItemContainer robotContainer;

	public OperateInstruction(String id) {
		this.id = id;
	}

	public OperateInstruction() {

	}
	
	/**
	 * Read a string with an action, compare if this action is correct 
	 * and generate OperateInstruction, else throw an exception.
	 */

	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {

		StringTokenizer st = new StringTokenizer(cad, " ");
		String words = st.nextToken().toUpperCase();

		if ((words.equals("OPERATE")) || (words.equals("OPERAR"))) {
			if (st.hasMoreTokens()) {
				String id = st.nextToken();
				if (!st.hasMoreTokens())
					return new OperateInstruction(id);
				else
					throw new WrongInstructionFormatException(BAD_INSTRUCTION);
			} else
				throw new WrongInstructionFormatException(BAD_INSTRUCTION);
		} else
			throw new WrongInstructionFormatException(BAD_INSTRUCTION);
	}
	
	/**
	 * Show information about OPERATE instruction syntax.
	 */

	@Override
	public String getHelp() {
		return "OPERATE | OPERAR <id>";
	}

	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		this.navigation = navigation;
		this.robot = engine;
		this.robotContainer = robotContainer;
	}
	
	/**
	 * Execute OPERATE instruction
	 * Verified if item isn't null and can be used.
	 */
	
	@Override
	public void execute() throws InstructionExecutionException {
		
		Item item = robotContainer.getItem(id);
		if (item != null && item.canBeUsed()
				&& robotContainer.getId(item).equalsIgnoreCase(id)) {
			if (!item.use(robot, navigation))
				throw new InstructionExecutionException(ITEM_PROBLEMS);
		}
		if (item != null && !item.canBeUsed()) {
			robotContainer.pickItem(id);
			System.out.println(ITEM_CANT_USED + id + IN_MY_INVENTORY);
		} else if (item==null)
			throw new InstructionExecutionException(ITEM_PROBLEMS);
	}

}
