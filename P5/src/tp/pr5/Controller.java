package tp.pr5;

import tp.pr5.items.InventoryObserver;

public class Controller {

	protected RobotEngine robot;

	public Controller(RobotEngine robot) {
		this.robot = robot;
	}

	public void registerInventoryObserver(InventoryObserver param) {
		robot.addItemContainerObserver(param);
	}

	public void registerRobotObserver(RobotEngineObserver param) {
		robot.addEngineObserver(param);
	}

	public void registerNavObserver(NavigationObserver param) {
		robot.addNavigationObserver(param);
	}

	public void startController() {
		robot.requestStart();
	}

}
