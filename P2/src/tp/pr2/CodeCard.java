package tp.pr2;

public class CodeCard extends Item {

	private String code;

	public CodeCard(String id, String description, String code) {
		super(id, description);
		this.code = code;
	}
	
	public boolean canBeUsed() {
		return true;
	}

	public boolean use(RobotEngine engine, Place place) {
		return false;
	}


	public String getCode() {
		return code;
	}
	
	
	

}
