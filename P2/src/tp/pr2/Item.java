package tp.pr2;

import static tp.pr2.Constants.LINE_SEPARATOR;

public abstract class Item {

	private String id;
	private String description;

	public Item(String id, String description) {
		this.id = id;
		this.description = description;
	
	}

	public abstract boolean canBeUsed();
	
	public abstract boolean use(RobotEngine engine, Place place);
	

	public String getId() {
		return id;
	}
	public String toString() {
		return  id + LINE_SEPARATOR + description;
	}

}