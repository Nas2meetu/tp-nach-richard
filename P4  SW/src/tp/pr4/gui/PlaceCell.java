package tp.pr4.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import tp.pr4.Place;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 4
 * 
 */

public class PlaceCell extends JButton {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	private Place place = null;
	private NavigationPanel navPanel;
	boolean active = false;

	public PlaceCell(NavigationPanel navigation) {
		this.navPanel = navigation;
		initialize();
	}

	/**
	 * PlaceCell default constructor
	 */

	public PlaceCell() {
		super();
		setOpaque(true);
		setBorderPainted(true);

	}

	/**
	 * Setter for room
	 * 
	 * @param room
	 *            the room to set
	 * 
	 */
	public void setPlace(Place place) {
		this.place = place;
		this.setText(place.getPlaceName());

	}

	/**
	 * Initialize cell of place
	 */

	private void initialize() {

		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (place != null)
					navPanel.showActualPlaceLog(place);
			}
		});
	}

	/**
	 * 
	 */

	public String toString() {
		return place.toString();
	}

	/**
	 * Update state of cell if robot is in it
	 */

	public void enterPlace() {
		active = true;
		updateCellStyle();
	}

	/**
	 * Update state of cell if robot leaves it
	 */

	public void leavePlace() {
		active = false;
		updateCellStyle();
	}

	/**
	 * Change color of cell depends of the state (unknown, active, visited,
	 * exit)
	 */

	public void updateCellStyle() {

		if (place.isSpaceship()) {
			setBackground(Color.red);
		} else if (active) {
			setBackground(Color.green);
		} else
			setBackground((Color.gray));
	}

}