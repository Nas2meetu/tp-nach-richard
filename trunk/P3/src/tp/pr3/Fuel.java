package tp.pr3;

/**
*
* @author Ignacio Cerda Sanchez
* @author Ricardo Eugui Fernandez
* @version 1
*
*/



public class Fuel extends Item {        
    
    private int power;
    private int times;

    public Fuel(String id, String description, int power, int times){
            super(id, description);
            this.power = power;
            this.times = times;
    }
   
    public boolean canBeUsed() {
            return (this.times > 0);
    }

    public boolean use(RobotEngine robot, Place where) {
            boolean used = false;
            if (canBeUsed()){
            	robot.addFuel(this.power);
                this.times--;
                used = true;
            }return used;
           
    }
   
    public int getTimes() {
            return times;
    }

    public int getPower() {
            return power;
    }
   

    private String fueltoString(){
            return " power = "+ power + "," + " times = " + times;
    }
    public String toString(){
            return (super.toString() + "//" + fueltoString());
    }
   
}
