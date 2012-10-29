package tp.pr1;
import static tp.pr1.Constants.*;

public class Place {
	private String name;
	private boolean isSpaceShip;
	private String description;
	
	public Place(String n, Boolean isss, String d) {
		this.name = n;
		this.isSpaceShip = isss;
		this.description = d;
		
	}
	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}



	public boolean isSpaceship(){
		return isSpaceShip;
	}
	
	@Override
	public String toString() {
		return name	 + LINE_SEPARATOR + description;
	}
	
}
