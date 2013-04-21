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
	 */
	public void setPlace(Place place) {
		this.place = place;
		this.setText(place.getPlaceName());

	}

	private void initialize() {

		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (place != null)
					navPanel.showCurrentPlaceLog(place);
			}
		});
	}

	public String toString() {
		return place.toString();
	}

	public void enterPlace() {
		active=true;
		updateCellLook();
	}

	public void leavePlace() {
		active=false;
		updateCellLook();
	}
	
	  public void updateCellLook() {
         
		if (place.isSpaceship()) {
                  setBackground(Color.red);
          } else if (active) {
                  setBackground(Color.green);
          } else
                  setBackground((Color.gray));
	  }

}