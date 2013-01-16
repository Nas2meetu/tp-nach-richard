package tp.pr3;


import java.util.Scanner;
import static tp.pr3.Constants.*;

/**
*
* @author Ignacio Cerda Sanchez
* @author Ricardo Eugui Fernandez
* @version 3
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
     *
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
     *
     * Is the Start game, show initial information and finish information and if Player win or lost game.
     *
     */

    public void startEngine() {
           
            Scanner read = new Scanner(System.in);
            Instruction instruction = new Instruction();
           
           
            System.out.println(actualPlace.toString() + LINE_SEPARATOR +
                            POWER2 + contFuel + LINE_SEPARATOR + RECICLED_MATERIAL + contRecycledMaterial +
                            LINE_SEPARATOR + LOOKING_DIRECTION + lookingDirection) ;

            while (!isEndGame(instruction) && contFuel>0) {
                    System.out.print(PROMPT);
                    instruction = Interpreter.generateInstruction(read.nextLine().toLowerCase());
                   
                    if ((instruction == null) || instruction.isValid()) {
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
     * Verified if game has finished.
     *
     * @param instruction is a command that Robot processes
     * @return isEndGame is one of possible ways to finish game
     *
     */

    private boolean isEndGame(Instruction instruction) {
            return instruction.equals("QUIT") || actualPlace.isSpaceship();
    }
   
    /**
     *
     * Process valid instructions.
     *
     * @param instruction is a command that Robot processes.
     *
     */
   

public void processInstruction(Instruction instruction) {
   
   
            switch (instruction.getAction()) {

            case DROP:
                    executeDropAction(instruction);
                    break;
           
            case HELP:
                    executeHelpAction(instruction);
                    break;

            case MOVE:
                    executeMoveAction();
                    break;
           
            case OPERATE:
                    executeOperateAction(instruction);
                    break;
           
            case PICK:
                    executePickAction(instruction);
                    break;
           
            case QUIT:
                    executeQuit();
   
            case RADAR:
                    executeRadarAction(instruction);
                    break;
           
            case SCAN:
                    executeScanAction(instruction);
                    break;
           
            case TURN:
                    executeTurnAction(instruction);
                    break;
           
            case UNKNOWN:
                    break;
                   
    }
}

/**
 *
 * Execute DROP action, with this action Robot takes an item of Wall·E container
 * ,delete of Wall·E container and put into Wall·E street.
 *
 * @param instruction is a command that Robot processes.
 *
 */

private void executeDropAction(Instruction instruction) {
         Item item = container.getItem(instruction.getId());
 if(item == null)
     System.out.println(CONTAINER_NO_ITEM + instruction.getId());
 else if(actualPlace.addItem(item)){
         container.pickItem(instruction.getId());
     System.out.println(PLACE_ITEM + instruction.getId());
 }else
         System.out.println(PLACE_REPEAT_ITEM + instruction.getId());
       
}

    /**
     *
     * Robot gives information about his instructions
     *
     * @param instruction is a command that Robot interpreters
     *
     */
   
    private void executeHelpAction(Instruction instruction) {
            System.out.println(HELP);
    }

    /**
     *
     * Execute MOVE action, Robot moves or not.
     *
     */

    private void executeMoveAction() {
                   
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
   
    /**
     *
     * Execute OPERATE action, with this action Robot can
     * use an item of Wall·E container.
     *
     * @param instruction is a command that Robot processes.
     *
     */
   
    private void executeOperateAction(Instruction instruction) {
            Item item = container.getItem(instruction.getId());
            if (item != null && item.canBeUsed()){
                    item.use(this, actualPlace);
                   
            }
            else
                    System.out.println(ITEM_PROBLEMS + instruction.getId() + " in my inventory");
            if (item!=null && !item.canBeUsed()) {
        container.pickItem(instruction.getId());
        System.out.println(ITEM_CANT_USED+instruction.getId()+" in my inventory");
            }
    }      
   
    /**
     *
     * Execute PICK action, with this action Robot takes an item of street
     * ,delete of street and put into Wall·E container.
     *
     * @param instruction is a command that Robot processes.
     *
     */

     private void executePickAction(Instruction instruction) {
     Item item = actualPlace.getItem(instruction.getId());
     if(item == null)
         System.out.println(PLACE_NO_ITEM + instruction.getId());
     else if(container.addItem(item)){
             actualPlace.pickItem(instruction.getId());
         System.out.println(CONTAINER_ITEM + instruction.getId());
     }else
             System.out.println(CONTAINER_REPEAT_ITEM + instruction.getId());
}
   
 /**
 *
 * Execute EXIT action to finish game.
 *
 */

private void executeQuit() {
               System.out.println(QUIT);
               System.exit(0);
}

    /**
*
* Execute RADAR action, with this action Robot can
* revise all items of street.
*      
* @param instruction is a command that Robot processes.
*
*/
 
private void executeRadarAction(Instruction instruction) {
           
                    System.out.println(WALLE_SAYS + actualPlace.toString());

}                  
   
    /**
     *
     * Execute SCAN action, with this action Robot can
     * revise one or all items of Wall·E container.
     *
     * @param instruction is a command that Robot processes.
     *
     */

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
   

    /**
     *
     * Execute TURN action, Robot turns or not.
     *
     * @param instruction is a command that Robot processes
     *
     */
   
    private void executeTurnAction(Instruction instruction) {
            switch (instruction.getRotation()) {
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


    /**
     *
     * Robot increase his fuel counter.
     *
     * @param newFuel is one unit of energy.
     */
   
    public void addFuel(int newFuel) {
            this.contFuel += newFuel;
            if (contFuel<0){
                    contFuel=0;
            }
    }
   
    /**
     *
     * Robot increase his recycled material counter.
     *
     * @param newMaterial is one unit of recycled material.
     */
   
    public void addRecycledMaterial(int newMaterial) {
            this.contRecycledMaterial += newMaterial;
           
    }
   
    /**
     *
     * Return a public method (contFuel) of a private attribute (Fuel).
     *
     * @return contFuel is a counter of energy.
     *
     */
   
    public int getFuel() {
            return contFuel;
    }
   

    /**
     *
     * Return a public method (contRecyledMaterial) of a private attribute (RecycledMaterial).
     *
     * @return ContRecycledMaterial is counter of recycled material.
     *
     */
   
    public int getRecycledMaterial() {
            return contRecycledMaterial;
    }

    /**
     *
     * Return a public method (actualPlace and lookingDirection) of a private attribute (HeadingStreet).
     *
     * @return lookForStreet is the actual place and direction where Robot is looking at.
     *
     */
   
    public Street getHeadingStreet() {
            return cityMap.lookForStreet(actualPlace, lookingDirection);
                   
    }
}
