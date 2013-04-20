package tp.pr4.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextArea;
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

	private boolean visited;
	private Place place;
	private NavigationPanel navPanel;

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

	}

	private void initialize() {

		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (place != null)
					navPanel.showCurrentPlaceLog();
			}
		});
	}
	
	public String toString(){
		return place.toString();
	}

}
