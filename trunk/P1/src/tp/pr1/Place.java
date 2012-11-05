package tp.pr1;
import static tp.pr1.Constants.LINE_SEPARATOR;

public class Place {

	private String placeName;
	private boolean isSpaceship;
	private String placeDescription;

	public Place(String placeName, boolean isSpaceship, String placeDescription) {
		this.placeName = placeName;
		this.isSpaceship = isSpaceship;
		this.placeDescription = placeDescription;
	}

	public boolean isSpaceship() {
		return isSpaceship;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return  placeName + LINE_SEPARATOR + placeDescription;
	}
	
	

}
