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
	 */

	/*public RobotEngine(Place initialPlace, Direction direction, Street[] cityMap) {
		this.actualPlace = initialPlace;
		this.lookingDirection = direction;
		this.cityMap = cityMap;
	}
*/
	public RobotEngine(City city, Place initialPlace, Direction direction) {
		this.cityMap = city;
		this.actualPlace = initialPlace;
		this.lookingDirection = direction;
		this.contFuel = INITIAL_POWER;
		this.contRecycledMaterial = INITIAL_GARBAGE;
	}

	/**
	 * 
	 * @return lookingDirection is direction that Robot is looking at
	 * 
	 */
	/*public Direction getDirection() {
		return lookingDirection;
	}
	*/
	/**
	 * Is the Start game, show initial information and finish information if Player win game
	 */

	public void startEngine() {
		
		Scanner read = new Scanner(System.in);
		Instruction instruction = new Instruction();
		
		
		
		System.out.println(actualPlace.toString() + LINE_SEPARATOR +  
							TURN  + lookingDirection + LINE_SEPARATOR);

		while (!isEndGame(instruction)) {
			System.out.print(PROMPT);
			instruction = Interpreter.generateInstruction(read.nextLine().toUpperCase());
			
			if (instruction.isValid()) {
				processInstruction(instruction);
			}else
				System.out.println(BAD_INSTRUCTION+LINE_SEPARATOR);
		}read.close();
		
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
		
		//Interpreter interpreter = new Interpreter();
		
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
		// TODO Auto-generated method stub
		
	}

	private void executeScanAction(Instruction instruction) {
		ItemContainer container = new ItemContainer();
		if(instruction.getId()==""){
			System.out.println(CONTAINER);
			System.out.println(container.toString());
		}
		else if (instruction.getId()!=""){
			container.getItem(instruction.getId()).toString();//esto falla
			if(container.numberOfItems()==0){
				System.out.println(CONTAINER_EMPTY); 
			}
		}
		
	}//mirar esto

	private void executePickAction(Instruction instruction) {
		
			
		
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
			System.out.println(MOVE + lookingDirection);
			System.out.println(actualPlace.toString() + LINE_SEPARATOR + TURN 
							   + lookingDirection + LINE_SEPARATOR);
				
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
			System.out.println(TURN + lookingDirection + LINE_SEPARATOR);
			break;
		case RIGHT:
			lookingDirection = lookingDirection.turnRight();
			System.out.println(TURN + lookingDirection + LINE_SEPARATOR);
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
		contFuel += newFuel;
	}
	

	public void addRecycledMaterial(int newMaterial) {
		contRecycledMaterial += newMaterial;
		
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
