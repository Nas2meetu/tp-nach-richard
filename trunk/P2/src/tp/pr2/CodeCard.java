package tp.pr2;



/**
*
* @author Ignacio Cerda Sanchez
* @author Ricardo Eugui Fernandez
* @version 1
*
*/

public class CodeCard extends Item {

	private String code;

	public CodeCard(String id, String description, String code) {
		super(id, description);
		this.code = code;
	}
	
	@Override
	public boolean canBeUsed() {
		return true;
	}

	public boolean use(RobotEngine robot, Place where) {
        if ((this.code == null) || (where == null)){
            return false;
        }if (robot.getHeadingStreet() != null && this.code.equals(robot.getHeadingStreet().getCode())){
            if (robot.getHeadingStreet().isOpen())
                robot.getHeadingStreet().close(this);
            else
                robot.getHeadingStreet().open(this);
            return true;
        }else
            return false;
           
        }

	public String getCode() {
		return code;
	}

}
