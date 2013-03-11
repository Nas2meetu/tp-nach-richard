package tp.pr3;

import static tp.pr3.Constants.*;
import tp.pr3.instructions.exceptions.*;
import tp.pr3.items.Item;

public class NavigationModule {

	private Place actualPlace;
	private Direction lookingDirection;
	private City cityMap;
	private RobotEngine robot;

	public NavigationModule(City city, Place currentPlace) {

		this.cityMap = city;
		this.actualPlace = currentPlace;

	}

	public void initHeading(Direction heading) {
		this.lookingDirection = heading;
	}

	public boolean atSpaceship() {
		return actualPlace.isSpaceship();
	}

	public void rotate(Rotation rotation) {

		if (rotation.equals(Rotation.LEFT))
			lookingDirection = lookingDirection.turnLeft();
		else
			lookingDirection = lookingDirection.turnRight();
	}

	public City getCityMap() {
		return cityMap;
	}

	public Direction getCurrentHeading() {
		return lookingDirection;
	}

	public Place getCurrentPlace() {
		return actualPlace;
	}

	public Street getHeadingStreet() {
		return cityMap.lookForStreet(actualPlace, lookingDirection);

	}

	public void dropItemAtCurrentPlace(Item it) {
		//this.robot = new RobotEngine(cityMap, actualPlace, lookingDirection);
		robot.getContainer().pickItem(it.getId());
	}

	public boolean findItemAtCurrentPlace(String id) {
		return actualPlace.existItem(id);
	}

	public void move() throws InstructionExecutionException {

		if (getHeadingStreet() == null) {
			throw new InstructionExecutionException(NO_STREET);
		} else if (getHeadingStreet().isOpen()) {
			actualPlace = getHeadingStreet().nextPlace(actualPlace);
		} else
			throw new InstructionExecutionException(STREET_CLOSE);

	}

	public void pickItemAtCurrentPlace(Item it) {
		Item item = actualPlace.getItem(it.getId());
		if (item != null) {
			actualPlace.pickItem(it.getId());
		}
	}

}
