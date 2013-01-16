package tp.pr3;
import static tp.pr3.Constants.*;

/**
*
* @author Ignacio Cerda Sanchez
* @author Ricardo Eugui Fernandez
* @version 3
*
*/

public class CodeCard extends Item {

    private String code;
   
    /**
     *
     * Constructor of three parameters to define CodeCard.
     *
 * @param id is a reference to identify an item.
 * @param description is description of item.
     * @param code is a password to open or close a street.
     *
     */
   
    public CodeCard(String id, String description, String code) {
            super(id, description);
            this.code = code;
    }
   
    /**
     * This item always can be used, override method Item class
     */
    @Override
    public boolean canBeUsed() {
            return true;
    }
   
    /**
     *
     * Verify if codeCard can be use or not in a place.
     *
     */
   
    public boolean use(RobotEngine robot, Place where) {
   
            if ((this.code == null) || (where == null)){
        return false;
    }if (robot.getHeadingStreet() != null && this.code.equals(robot.getHeadingStreet().getCode())){
        if (robot.getHeadingStreet().isOpen())
            robot.getHeadingStreet().close(this);
        else
            robot.getHeadingStreet().open(this);
        return true;
    }else{
            System.out.println(ITEM_PROBLEMS + getId().toLowerCase());
        return false;
    }
}
   
    /**
     *
     * Return a public method (code) of a private attribute Code.
     *
     * @return code is a password to open a street
     */
   
    public String getCode() {
            return code;
    }

}
