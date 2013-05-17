package tp.pr5.gui;

import java.util.List;

import static tp.pr5.Constants.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import tp.pr5.Direction;
import tp.pr5.NavigationObserver;
import tp.pr5.PlaceInfo;
import tp.pr5.RobotEngineObserver;
import tp.pr5.items.InventoryObserver;
import tp.pr5.items.Item;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 5
 * 
 */

public class InfoPanel extends JPanel implements RobotEngineObserver,
		NavigationObserver, InventoryObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel lbInfo = new JLabel("Start Game");

	public InfoPanel() {
		add(lbInfo);
	}

	@Override
	public void communicationCompleted() {
		lbInfo.setText(QUIT);

	}

	@Override
	public void communicationHelp(String help) {
		// not use

	}

	@Override
	public void engineOff(boolean atShip) {
		// TODO Auto-generated method stub

	}

	@Override
	public void headingChanged(Direction newHeading) {
		lbInfo.setText(LOOKING_DIRECTION + newHeading);

	}

	@Override
	public void initNavigationModule(PlaceInfo initialPlace, Direction heading) {
		// TODO Auto-generated method stub

	}

	@Override
	public void inventoryChange(List<Item> inventory) {
		lbInfo.setText(INVENTORY_UPDATED);

	}

	@Override
	public void inventoryScanned(String inventoryDescription) {
		// not use

	}

	@Override
	public void itemEmpty(String itemName) {
		lbInfo.setText(ITEM_CANT_USED + itemName + IN_MY_INVENTORY);
	}

	@Override
	public void itemScanned(String description) {
		// not use

	}

	@Override
	public void placeHasChanged(PlaceInfo placeDescription) {
		// TODO Auto-generated method stub

	}

	@Override
	public void placeScanned(PlaceInfo placeDescription) {
		// not use

	}

	@Override
	public void raiseError(String msg) {
		lbInfo.setText(msg);

	}

	@Override
	public void robotArrivesAtPlace(Direction heading, PlaceInfo place) {
		lbInfo.setText(MOVE+ heading);

	}

	@Override
	public void robotSays(String message) {
		lbInfo.setText(message);

	}

	@Override
	public void robotUpdate(int fuel, int recycledMaterial) {
		lbInfo.setText(ROBOT_UPDATE + fuel + ","
				+ recycledMaterial + ")");

	}

}
