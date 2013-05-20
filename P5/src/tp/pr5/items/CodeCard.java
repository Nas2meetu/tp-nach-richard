package tp.pr5.items;

import static tp.pr5.Constants.*;
import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 5
 * 
 *          A CodeCard can open or close the door placed in the streets. The
 *          card contains a code that must match the street code in order to
 *          perform the action.
 */

public class CodeCard extends Item {

	private String code;

	/**
	 * 
	 * Constructor of three parameters to define CodeCard.
	 * 
	 * @param id
	 *            Code card name
	 * @param description
	 *            Code card description
	 * @param code
	 *            Secret code stored in the code card
	 * 
	 */

	public CodeCard(String id, String description, String code) {
		super(id, description);
		this.code = code;
	}

	/**
	 * This item always can be used, override method Item class. A code card
	 * always can be used. Since the robot has the code card it never loses it
	 * 
	 * return true because it always can be used
	 */

	@Override
	public boolean canBeUsed() {
		return true;
	}

	/**
	 * 
	 * The method to use a code card. If the robot is in a place which contains
	 * a street in the direction he is looking at, then the street is opened or
	 * closed if the street code and the card code match.
	 * 
	 * robot - the robot engine employed to use the card. navigation - the
	 * navigation module to look for the street
	 * 
	 * return true If the code card can complete the action of opening or
	 * closing a street. Otherwise it returns false.
	 * 
	 */

	public boolean use(RobotEngine robot, NavigationModule navigation) {

		if ((this.code == null) || (navigation.getCurrentPlace() == null)) {
			return false;
		}
		if (navigation.getHeadingStreet() != null
				&& this.code.equals(navigation.getHeadingStreet().getCode())) {
			if (navigation.getHeadingStreet().isOpen())
				navigation.getHeadingStreet().close(this);
			else
				navigation.getHeadingStreet().open(this);
			return true;
		} else {
			System.out.println(ITEM_PROBLEMS + getId().toLowerCase());
			return false;
		}
	}

	/**
	 * Gets the code stored in the code card
	 * 
	 * Return a public method (code) of a private attribute Code.
	 * 
	 * @return code A String that represents the code
	 */

	public String getCode() {
		return code;
	}

}
