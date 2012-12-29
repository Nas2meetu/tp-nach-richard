package tp.pr2;
import static tp.pr2.Constants.*;

public abstract class ExpirationItem extends Item {

	private int times;

	/**
	 * Constructor of Expiration Item.
	 * 
	 * @param id
	 *            Identifier
	 * @param description
	 *            Description of the Item.
	 * @param times
	 */
	public ExpirationItem(String id, String description, int times) {

		super(id, description);
		this.times = times;
	}

	/**
	 * Default constructor of an Expiration Item There are defined the ID and
	 * the Description of the Item. The number of times that the item can be
	 * used is defined as a constant 10
	 * 
	 * @param id
	 * @param description
	 */
	public ExpirationItem(String id, String description) {

		super(id, description);
		this.times = DEFAULT_TIMES;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tp.pr2.Item#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + "//" + " times = " + this.times + LINE_SEPARATOR;
	}

	/**
	 * Returns true if this object can be used at least one time.
	 */
	public boolean canBeUsed() {

		if (this.times > 0)
			return true;
		else
			return false;
	}

	public boolean use(RobotEngine robot, Place where) {

		this.times -= DEFAULT_TIMES;
		return true;
	}

}
