package tp.pr3;

import static tp.pr3.Constants.*;

import tp.pr3.instructions.testprofesor.TurnInstructionTest;
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
		 Item item = actualPlace.getItem(it.getId());
		 if(item == null)
		     System.out.println(CONTAINER_NO_ITEM + it.getId());
		 else if(actualPlace.addItem(it)){
			 robotContainer.pickItem(item.getId());
		     System.out.println(PLACE_ITEM + it.getId());
		 }else
		         System.out.println(PLACE_REPEAT_ITEM + item.getId());
	}

	public boolean findItemAtCurrentPlace(String id) {
		return (actualPlace.getItem(id) != null && actualPlace.getItem(id)
				.getId().equalsIgnoreCase(id));
	}

	public void move() throws InstructionExecutionException {
		if (getHeadingStreet()==null){
            throw new InstructionExecutionException(NO_STREET);
    }
    else if (getHeadingStreet().isOpen()){
            getHeadingStreet().nextPlace(.getCurrentPlace());
            robot.addFuel(-5);
            System.out.println(MOVE + etCurrentHeading());
            System.out.println(getCurrentPlace().toString() +
                            POWER2 + robot.getFuel() + LINE_SEPARATOR + RECICLED_MATERIAL + robot.getRecycledMaterial() +
                            LINE_SEPARATOR + LOOKING_DIRECTION + navigation.getCurrentHeading());
                   
    }else
            System.out.println(STREET_CLOSE);
	}

	public Item pickItemFromCurrentPlace(String id) {

		String idItem = actualPlace.getItem(id).getId();

		if (idItem.equals(id)) {
			return actualPlace.pickItem(id);
		} else
			return null;
	}

}
