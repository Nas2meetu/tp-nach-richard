package tp.pr3;

import static tp.pr3.Constants.*;

import javax.management.InstanceAlreadyExistsException;

import tp.pr3.instructions.exceptions.*;
import tp.pr3.instructions.testprofesor.TurnInstructionTest;
import tp.pr3.items.Item;

public class NavigationModule {
	
	private Place actualPlace;
	private Direction lookingDirection;
    private City cityMap;
	private ItemContainer container;
	
	
	
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
	
	public Direction getCurrentHeading(){
		return lookingDirection;
	}
	
	public Place getCurrentPlace(){
		return actualPlace;
	}
	
	public Street getHeadingStreet() {
        return cityMap.lookForStreet(actualPlace, lookingDirection);
               
	}
	
	public void dropItemAtCurrentPlace(Item it){
		//Item itemPlace = container.getItem(it.getId());
		if(actualPlace.addItem(it))
	         container.pickItem(it.getId());
	}
	
	public boolean findItemAtCurrentPlace(String id){
		int i = 0;
		int numItems = actualPlace.getItemsInPlace().numberOfItems();
        while (i < numItems ) {
        	if (actualPlace(id)) {
        		return true;
            }
            i++;
        }
        return false;
	}
	public void move() throws InstructionExecutionException{
		if (getHeadingStreet().isOpen())
			actualPlace = getHeadingStreet().nextPlace(actualPlace);
		throw new InstructionExecutionException(STREET_CLOSE);	
	}
			
	
	public Item pickItemFromCurrentPlace(String id){
		 
		String idItem = actualPlace.getItem(id).getId();
		
		if (idItem.equals(id)){
			actualPlace.pickItem(id);
			return container.getItem(id);
		}else
	     return null;
	}
	
	    

}
