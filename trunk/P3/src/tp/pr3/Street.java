package tp.pr3;

import tp.pr3.items.CodeCard;

/**
*
* @author Ignacio Cerda Sanchez
* @author Ricardo Eugui Fernandez
* @version 3
*
*/


public class Street {

    private Place sourcePlace;
    private Direction direction;
    private Place targetPlace;
    private boolean isOpen;
    private String code;
   
   
   
    /**
     *
     * Constructor of three parameters to create Streets.
     *
     * @param sourcePlace is one side of street.
     * @param direction is default direction.
     * @param targetPlace is opposite side of street.
     *
     */

    public Street(Place sourcePlace, Direction direction, Place targetPlace) {
            this.sourcePlace = sourcePlace;
            this.direction = direction;
            this.targetPlace = targetPlace;
            this.isOpen = true;
            this.code = null;

    }
   
    /**
     *
     * Constructor of five parameters to create Streets.
     *
     * @param sourcePlace is one side of street.
     * @param direction is default direction.
     * @param targetPlace is opposite side of street.
     * @param Open is default street situation.
     * @param code is default code.
     */
   
    public Street(Place sourcePlace, Direction direction, Place targetPlace, boolean Open, String code) {
            this.sourcePlace = sourcePlace;
            this.direction = direction;
            this.targetPlace = targetPlace;
            this.isOpen = Open;
            this.code = code;

    }
   
    /**
     *
     * Define possible next place to Robot.
     *
     * @param place is place where Robot is.
     * @return nextPlace is place where Robot can move.
     */


    public Place nextPlace(Place place) {
            Place nextPlace = null;
            if (place.equals(sourcePlace)) {
                    nextPlace = targetPlace;
            } else if (place.equals(targetPlace)) {
                    nextPlace = sourcePlace;
            }else
                    nextPlace=null;

            return nextPlace;
    }
   
    /**
     *
     * @param place is place where Robot is  --o esta o la del nextplace--
     * @param fromDirection is direction where Robot comes
     * @return ComeOutFrom is correct place that Robot moves
     */

    public boolean comeOutFrom(Place place, Direction fromDirection) {

            return ((comeOutFromSourcePlace(place, fromDirection)
                            || comeOutFromTargetPlace(place, fromDirection)));
           
    }
   
    /**
     *
     * @param place is place where Robot is
     * @param fromDirection is direction where Robot comes
     * @return comeOutFromTargetPlace is Target place where's Robot comes
     *
     */

    private boolean comeOutFromTargetPlace(Place place, Direction fromDirection) {
            return fromDirection.equals(direction.opposite()) && targetPlace.equals(place);
    }
   
    /**
     *
     * @param place is place where Robot is
     * @param fromDirection is direction where Robot comes
     * @return comeOutFromSourcePlace is Source place where's Robot comes
     *
     */
    private boolean comeOutFromSourcePlace(Place place, Direction fromDirection) {
            return place.equals(sourcePlace) && direction.equals(fromDirection);
    }

    /**
     * Return a public method (isOpen) of a private attribute (isOpen).
     *
     * @return isOpen
     */
   
    public boolean isOpen() {
            return isOpen;
    }

    /**
     *
     * If code of codeCard equal than code of street open the street
     *
     * @param card is a CodeCard of Wall·E container.
     * @return isOpen if street is open or not
     */
   
    public boolean open(CodeCard card) {
            if (card.getCode().equals(code)){
                    isOpen= true;
            }else
                    isOpen = false;
            return isOpen;
    }
   
    public String getCode() {
            return code;
    }

    /**
     *
     * If isOpen is true and CodeCard of Wall·E container is equal than code of street
     * return false, because street is open not close.
     *
     * @param card is a CodeCard of Wall·E container.
     * @return isOpen is false if street is open.
     */
   
    public boolean close(CodeCard card){
           
            if(isOpen && card.getCode().equals(code)){
                    isOpen = false;
            }
            return !isOpen;
    }

}

