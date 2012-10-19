package pr1;

public class RobotEngine {
	
	private Place intialPlace;
	private Direction direction;
	private Street[] cityMap;


	public RobotEngine(Place initialPlace, Direction direction, Street cityMap){
		this.intialPlace=initialPlace;
		this.direction=direction;
		this.cityMap[0]=cityMap;
	}
	public void startEngine(){
		
	}
}
