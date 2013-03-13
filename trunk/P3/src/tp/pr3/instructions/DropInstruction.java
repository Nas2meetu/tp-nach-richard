package tp.pr3.instructions;

import static tp.pr3.Constants.*;
import java.util.StringTokenizer;
import tp.pr3.NavigationModule;
import tp.pr3.RobotEngine;
import tp.pr3.instructions.exceptions.*;
import tp.pr3.items.ItemContainer;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 3
 * 
 */

public class DropInstruction implements Instruction {

	private NavigationModule navigation;
	private String id;
	private ItemContainer robotContainer;

	private DropInstruction(String id) {
		this.id = id;
	}

	public DropInstruction() {

	}

	/**
	 * Read a string with an action, compare if this action is correct and
	 * generate DropInstruction, else throw an exception.
	 */

	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {

		StringTokenizer st = new StringTokenizer(cad, " ");
		String words = st.nextToken().toUpperCase();
		if ((words.equals("DROP")) || (words.equals("SOLTAR"))) {
			if (st.hasMoreTokens()) {
				id = st.nextToken();
				if (!st.hasMoreTokens())
					return new DropInstruction(id);
				else
					throw new WrongInstructionFormatException();
			} else
				throw new WrongInstructionFormatException();
		} else
			throw new WrongInstructionFormatException();

	}

	/**
	 * Show information about DROP instruction syntax.
	 */

	@Override
	public String getHelp() {
		return "DROP | SOLTAR <id>";
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see tp.pr3.instructions.Instruction#configureContext(tp.pr3.RobotEngine,
	 *      tp.pr3.NavigationModule, tp.pr3.items.ItemContainer)
	 */

	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		this.navigation = navigation;
		this.robotContainer = robotContainer;

	}

	/**
	 * 
	 * Execute DROP instruction Verified if item isn't null, Place hasn't got
	 * this item and put into Place.
	 */

	@Override
	public void execute() throws InstructionExecutionException {
		if (id != null && robotContainer.containsItem(id))
			if (!navigation.findItemAtCurrentPlace(id)) {
				navigation.getCurrentPlace().addItem(
						robotContainer.pickItem(id));
				System.out.println(PLACE_ITEM + id);
			} else
				throw new InstructionExecutionException(PLACE_REPEAT_ITEM + id);
		else
			throw new InstructionExecutionException(CONTAINER_NO_ITEM + id
					+ ".");
	}
}
