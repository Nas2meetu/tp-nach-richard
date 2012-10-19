package pr1;

public class Place {
	
	private String name;
	private boolean isSpaceShip;
	private String description;
	
	public Place() {
		this.name = name;
		this.isSpaceShip = false;
		this.description = description;
		
	}

	public String getName() {
		return name;
	}

	
	public boolean isSpaceship(){
		return isSpaceShip;
	}
	
	public String toString(){
		//return name;
		return description;
	}
}
