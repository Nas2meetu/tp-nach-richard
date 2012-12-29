package tp.pr2;


import java.util.Scanner;
import static tp.pr2.Constants.*;

/**
*
* @author Ignacio Cerda Sanchez
* @author Ricardo Eugui Fernandez
* @version 1
* 
*/

public class RobotEngine {

	private Place actualPlace;
	private Direction lookingDirection;
	private City cityMap;
	private int contFuel;
	private int contRecycledMaterial;
	private ItemContainer container;
	
	
	/**
	 * 
	 * Creates a new Robot Engine
	 * @param initialPlace is the initial place of the robot
	 * @param direction is the default direction
	 * @param cityMap is the map where the robot lives
	 * 
	 **/
	
	public RobotEngine(City city, Place initialPlace, Direction direction) {
		this.cityMap = city;
		this.actualPlace = initialPlace;
		this.lookingDirection = direction;
		this.container = new ItemContainer();
		this.contFuel = INITIAL_POWER;
		this.contRecycledMaterial = INITIAL_GARBAGE;
	}

	/**
	 * 
	 * @return lookingDirection is direction that Robot is looking at
	 * 
	 */
	public Direction getDirection() {
		return lookingDirection;
	}
	
	/**
	 * Is the Start game, show initial information and finish information if Player win game
	 */

	public void startEngine() {
		
		Scanner read = new Scanner(System.in);
		Instruction instruction = new Instruction();
		
		
		System.out.println(actualPlace.toString() + LINE_SEPARATOR + 
				POWER + contFuel + LINE_SEPARATOR + RECICLED_MATERIAL + contRecycledMaterial +
				LINE_SEPARATOR + LOOKING_DIRECTION + lookingDirection) ;

		while (!isEndGame(instruction) && contFuel>0) {
			System.out.print(PROMPT);
			instruction = Interpreter.generateInstruction(read.nextLine().toLowerCase());
			
			if (instruction.isValid()) {
				processInstruction(instruction);
			}else
				System.out.println(BAD_INSTRUCTION+LINE_SEPARATOR);
		}read.close();
		if (contFuel<=0)
			System.out.println(END_FUEL);
		else
			System.out.println(END_GAME);

	}
	
	/**
	 * 
	 * @param instruction is a command that Robot processes
	 * @return isEndGame is one of possible ways to finish game
	 * 
	 */

	private boolean isEndGame(Instruction instruction) {
		return instruction.equals("QUIT") || actualPlace.isSpaceship();
	}
	
	/**
	 * @param instruction is a command that Robot processes
	 * 
	 */

	public void processInstruction(Instruction instruction) {
	
		
		switch (instruction.getAction()) {

		case MOVE:
			executeMoveAction();
			break;

		case TURN:
			executeTurnAction(instruction);
			break;
			
		case HELP:
			executeHelpAction(instruction);
			break;

		case QUIT:
			System.out.println(QUIT);
			System.exit(0);
			
		case UNKNOWN:
			break;
			
		case PICK:
			executePickAction(instruction);
			break;
		
		case OPERATE:
			executeOperateAction(instruction);
			break;

		case SCAN:
			executeScanAction(instruction);
			break;
				
		}
	}
	
	private void executeOperateAction(Instruction instruction) {
		Item item = container.getItem(instruction.getId());
		if (item == null)
			System.out.println(ITEM_CANT_USED + instruction.getId() + " in my inventory");
		else if (item.use(this, actualPlace) || !item.canBeUsed()) {
			System.out.println(POWER + contFuel + LINE_SEPARATOR + RECICLED_MATERIAL + contRecycledMaterial);
			container.pickItem(instruction.getId());
			System.out.println(ITEM_CANT_USED + instruction.getId() + " in my inventory");
		} else
			System.out.println(ITEM_PROBLEMS + instruction.getId());
	}
		/*if(instruction.getId()!=""){
			Item item = container.getItem(instruction.getId());
			if (item == null)
				System.out.println(ITEM_CANT_USED + instruction.getId() + " in my inventory");
			else{
				if (!item.use(this ,actualPlace))
					container.pickItem(instruction.getId());
			}
			System.out.println(POWER + contFuel + LINE_SEPARATOR + RECICLED_MATERIAL + contRecycledMaterial);
		}*/
	


	private void executeScanAction(Instruction instruction) {
		if(instruction.getId()==""){
			System.out.println(container.showItems());
		}
		else{
			Item item = container.getItem(instruction.getId());
			if (item!=null)
				System.out.println(WALLE_SAYS + item.toString());
			else
				System.out.println(SCAN_NO_ITEM);
		}
		
	}

	private void executePickAction(Instruction instruction) {
		Item item = actualPlace.pickItem(instruction.getId());
		
		if(item == null)
			System.out.println(PLACE_NO_ITEM + instruction.getId());
		else if(container.addItem(item)){
			System.out.println(CONTAINER_ITEM + instruction.getId());
			}else
				System.out.println(CONTAINER_REPEAT_ITEM + instruction.getId());
		
	}
	/**
	 * Robot moves or not
	 */

	private void executeMoveAction() {
			
		if (getHeadingStreet()==null){
			System.out.println(NO_STREET);
		}
		else if (getHeadingStreet().isOpen()){
			actualPlace = getHeadingStreet().nextPlace(actualPlace);
			addFuel(-5);
			System.out.println(MOVE + lookingDirection);
			System.out.println(actualPlace.toString() + LINE_SEPARATOR + 
								LOOKING_DIRECTION + lookingDirection + LINE_SEPARATOR);
				
		}else
			System.out.println(STREET_CLOSE);
	}

	/**
	 * Robot turns or not
	 * @param instruction is a command that Robot processes
	 * 
	 */
	
	private void executeTurnAction(Instruction instruction) {
		switch (instruction.getRotation()) {
		case LEFT:
			lookingDirection = lookingDirection.turnLeft();
			addFuel(-1);
			System.out.println(POWER + contFuel + LINE_SEPARATOR + RECICLED_MATERIAL 
					+ contRecycledMaterial + LINE_SEPARATOR + LOOKING_DIRECTION + lookingDirection);
			break;
		case RIGHT:
			lookingDirection = lookingDirection.turnRight();
			addFuel(-1);
			System.out.println(POWER + contFuel + LINE_SEPARATOR + RECICLED_MATERIAL 
					+ contRecycledMaterial + LINE_SEPARATOR + LOOKING_DIRECTION + lookingDirection);
			break;
		case UNKNONW:
			break;
		}
	}

	/**
	 * The Robot gives information about his instructions
	 * @param interpreter is a command that Robot interpreters
	 */
	
	private void executeHelpAction(Instruction instruction) {
		System.out.println(HELP);
	}

	
	public void addFuel(int newFuel) {
		this.contFuel += newFuel;
	}
	
	
	public void addRecycledMaterial(int newMaterial) {
		this.contRecycledMaterial += newMaterial;
		
	}
	
	public int getFuel() {
		return contFuel;
	}
	

	public int getRecycledMaterial() {
		return contRecycledMaterial;
	}

	public Street getHeadingStreet() {
		return cityMap.lookForStreet(actualPlace, lookingDirection);
			
	}
}
