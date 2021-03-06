package tp.pr4.instructions;

import static tp.pr4.Constants.*;
import java.util.StringTokenizer;
import tp.pr4.NavigationModule;
import tp.pr4.RobotEngine;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.instructions.exceptions.WrongInstructionFormatException;
import tp.pr4.items.Item;
import tp.pr4.items.ItemContainer;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 4
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
					throw new WrongInstructionFormatException();
			} else
				throw new WrongInstructionFormatException();
		} else
			throw new WrongInstructionFormatException();
	}
	
	/**
	 * Show information about OPERATE instruction syntax.
	 */

	@Override
	public String getHelp() {
		return "OPERATE | OPERAR <id>";
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
		this.navigation = navigation;
		this.robot = engine;
		this.robotContainer = robotContainer;
	}
	
	/**
	 * Execute OPERATE instruction
	 * Verify if item isn't null and can be used.
	 */
	
	@Override
	public void execute() throws InstructionExecutionException {
		
		Item item = robotContainer.getItem(id);
		if (item != null && item.canBeUsed()
				&& robotContainer.getId(item).equalsIgnoreCase(id)) {
			if (!item.use(robot, navigation))
				throw new InstructionExecutionException(ITEM_PROBLEMS + id);
		}
		if (item != null && !item.canBeUsed()) {
			robotContainer.pickItem(id);
			robotContainer.updateInventory();
			System.out.println(ITEM_CANT_USED + id + IN_MY_INVENTORY);
		} else if (item==null)
			throw new InstructionExecutionException(ITEM_PROBLEMS + id);
		
	}

}
