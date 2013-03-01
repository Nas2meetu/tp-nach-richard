package tp.pr3;

import static tp.pr3.Constants.*;
import tp.pr3.intructions.exceptions.*;
import tp.pr3.items.Item;
import tp.pr3.items.ItemContainer;

public class NavigationModule {

	private Place actualPlace;
	private Direction lookingDirection;
	private City cityMap;
	private RobotEngine robot;
	private ItemContainer robotContainer;

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
		this.robot = new RobotEngine(cityMap, actualPlace, lookingDirection);
		robot.getContainer().pickItem(it.getId());
	}

	public boolean findItemAtCurrentPlace(String id) {
		return actualPlace.existItem(id);
	}

	public void move() throws InstructionExecutionException {
		this.robot = new RobotEngine(cityMap, actualPlace, lookingDirection);
		if (getHeadingStreet() == null) {
			throw new InstructionExecutionException(NO_STREET);
		} else if (getHeadingStreet().isOpen()) {
			actualPlace = getHeadingStreet().nextPlace(actualPlace);
			robot.addFuel(-5);
			System.out.println(MOVE + lookingDirection);
			System.out.println(actualPlace.toString() + POWER2
					+ robot.getFuel() + LINE_SEPARATOR + RECICLED_MATERIAL
					+ robot.getRecycledMaterial() + LINE_SEPARATOR
					+ LOOKING_DIRECTION + lookingDirection);

		} else
			throw new InstructionExecutionException(STREET_CLOSE);

	}

	public void pickItemAtCurrentPlace(Item it) {
		Item item = actualPlace.getItem(it.getId());
		if (item == null)
			System.out.println(PLACE_NO_ITEM + it.getId());
		else if (robotContainer.addItem(it)) {
			actualPlace.pickItem(item.getId());
			System.out.println(CONTAINER_ITEM + it.getId());
		} else
			System.out.println(CONTAINER_REPEAT_ITEM + item.getId());
	}

}
