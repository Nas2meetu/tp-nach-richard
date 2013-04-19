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

	private JTextArea textArea;
	private boolean visited;
	private Place place;
	private NavigationPanel navPanel;

	public PlaceCell(Place place, JTextArea textArea) {

		super();
		setOpaque(true);
		setBorderPainted(true);
		this.place = place;
		this.textArea = textArea;
		this.visited = false;
		this.initialize();

	}

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

	public Place getPlace() {
		if (place != null) {
			return place;
		} else
			return null;
	}

	public void setVisited() {
		if (place != null) {
			this.setText(place.getPlaceName());
			textArea.setText(place.getDescription());

		}
		visited = true;
	}

	public void setNotVisited() {

		this.setText(null);
		visited = false;
	}

	public boolean isVisited() {
		return visited;
	}

	private void initialize() {

		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (visited)
					// textArea.setText(place.getDescription());
					navPanel.showCurrentPlaceLog();
			}
		});
	}

}
