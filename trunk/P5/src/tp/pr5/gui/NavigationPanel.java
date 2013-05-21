package tp.pr5.gui;

import static tp.pr5.Constants.IMAGES_DONT_LOAD;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.net.URL;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import tp.pr5.Direction;
import tp.pr5.NavigationObserver;
import tp.pr5.PlaceInfo;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 5
 * 
 *          This class is in charge of the panel that displays the information
 *          about the robot heading and the city that is traversing. It contains
 *          the grid that represents the city in the Swing interface, a text
 *          area to show the place descriptions, and a label with an icon which
 *          represents the robot heading
 * 
 *          The 11x11 grid contains PlaceCell objects and the first place starts
 *          at (5,5). This panel will update the visited places when the robot
 *          moves from one place to another. Additionally it will show the place
 *          description on a text area if the user clicks on a visited place.
 */

public class NavigationPanel extends JPanel implements NavigationObserver {

	private static final long serialVersionUID = 1L;

	private int col = 5;
	private int row = 5;

	private JLabel lbRobotIcon;
	private ImageIcon robotImage;
	private JPanel pRobotImage;
	private JTextArea txtLog;
	private URL urlImage;
	private PlaceCell[][] placeCell;
	private PlaceCell actualPlaceCell;

	/**
	 * 
	 * Displays information about the robot heading and the city that is
	 * traversing
	 * 
	 */

	public NavigationPanel() {
		super();

		this.setLayout(new BorderLayout());
		pRobotImage = new JPanel();
		pRobotImage.setLayout(new BoxLayout(pRobotImage, BoxLayout.Y_AXIS));

		urlImage = MainWindow.class.getResource("images/walleNorth.png");
		robotImage = new ImageIcon(urlImage);
		lbRobotIcon = new JLabel(robotImage);

		// Add panel robot image to panel

		pRobotImage.add(Box.createVerticalGlue());
		pRobotImage.add(lbRobotIcon);
		pRobotImage.add(Box.createVerticalGlue());

		JPanel pCity = new JPanel();
		pCity.setBorder(new TitledBorder("City Map"));
		pCity.setLayout(new GridLayout(11, 11));

		this.placeCell = new PlaceCell[11][11];

		for (int i = 0; i < 11; i++)
			for (int j = 0; j < 11; j++) {
				placeCell[i][j] = new PlaceCell(this);
				pCity.add(placeCell[i][j]);
			}
		actualPlaceCell = new PlaceCell();

		txtLog = new JTextArea();
		JPanel pLog = new JPanel(new GridLayout(1, 1));
		pLog.setBorder(new TitledBorder("Log"));
		pLog.setLayout(new BorderLayout());
		txtLog.setPreferredSize(new Dimension(10, 110));
		txtLog.setEditable(false);

		txtLog.setText("");
		pLog.add(new JScrollPane(txtLog), BorderLayout.CENTER);

		this.add(pCity, BorderLayout.CENTER);
		this.add(pRobotImage, BorderLayout.WEST);
		this.add(pLog, BorderLayout.SOUTH);

	}

	/**
	 * Notifies that the robot heading has changed
	 * 
	 * @param newHeading
	 *            New robot heading
	 */

	@Override
	public void headingChanged(Direction newHeading) {
		if (urlImage == null)
			JOptionPane.showMessageDialog(this, IMAGES_DONT_LOAD);
		else
			updateIcon(newHeading);
	}

	/**
	 * Notifies that the navigation module has been initialized
	 * 
	 * @param initialPlace
	 *            The place where the robot starts the simulation
	 * @param heading
	 *            The initial robot heading
	 */

	@Override
	public void initNavigationModule(PlaceInfo initialPlace, Direction heading) {
		setCurrentPlace(initialPlace);
	}

	/**
	 * Notifies that the place where the robot stays has changed (because the
	 * robot picked or dropped an item)
	 */

	@Override
	public void placeHasChanged(PlaceInfo placeDescription) {
		txtLog.setText(placeDescription.toString());
	}

	/**
	 * Notifies that the user requested a RADAR instruction
	 * 
	 * @param placeDescription
	 *            Information with the current place
	 */

	@Override
	public void placeScanned(PlaceInfo placeDescription) {
		// Not use
	}

	/**
	 * Notifies that the robot has arrived at a place
	 * 
	 * @param heading
	 *            The robot movement direction
	 * @param place
	 *            The place where the robot arrives
	 * 
	 */

	@Override
	public void robotArrivesAtPlace(Direction heading, PlaceInfo place) {
		updateCity(place, heading);
	}

	/**
	 * 
	 * Create initial place of robot at navigation panel at swing mode
	 * 
	 * @param actualPlace
	 *            place where robot is
	 */

	public void setCurrentPlace(PlaceInfo actualPlace) {
		this.placeCell[row][col].setPlace(actualPlace);
		this.actualPlaceCell = this.placeCell[row][col];
		this.actualPlaceCell.enterPlace();
		updateLog();
	}

	/**
	 * Update log panel with information about this place
	 * 
	 * @param actualPlace
	 *            place where robot is
	 */

	public void showActualPlaceLog(PlaceInfo actualPlace) {
		txtLog.setText(actualPlace.toString());
	}

	/**
	 * 
	 * Update Walle image depends of robot's direction
	 * 
	 * @param lookingDirection
	 *            is direction that robot is looking
	 */

	public void updateIcon(Direction lookingDirection) {

		switch (lookingDirection) {
		case NORTH:
			urlImage = MainWindow.class.getResource("images/walleNorth.png");
			robotImage = new ImageIcon(urlImage);
			lbRobotIcon.setIcon(robotImage);
			break;

		case EAST:
			urlImage = MainWindow.class.getResource("images/walleEast.png");
			robotImage = new ImageIcon(urlImage);
			lbRobotIcon.setIcon(robotImage);
			break;

		case SOUTH:
			urlImage = MainWindow.class.getResource("images/walleSouth.png");
			robotImage = new ImageIcon(urlImage);
			lbRobotIcon.setIcon(robotImage);
			break;

		case WEST:
			urlImage = MainWindow.class.getResource("images/walleWest.png");
			robotImage = new ImageIcon(urlImage);
			lbRobotIcon.setIcon(robotImage);
			break;

		default:
			break;
		}

	}

	/**
	 * 
	 * Update log panel when you it have more information
	 * 
	 */

	public void updateLog() {
		txtLog.setText(actualPlaceCell.toString());
	}

	/**
	 * 
	 * Update city map and verify if robot enter or exit from cell
	 * 
	 * @param actualPlace
	 *            place where robot is
	 * @param lookingDirection
	 *            is direction that robot is looking
	 */

	public void updateCity(PlaceInfo actualPlace, Direction lookingDirection) {

		actualPlaceCell.leavePlace();
		if (lookingDirection.equals(Direction.NORTH))
			row--;
		if (lookingDirection.equals(Direction.EAST))
			col++;
		if (lookingDirection.equals(Direction.SOUTH))
			row++;
		if (lookingDirection.equals(Direction.WEST))
			col--;
		if (this.placeCell[row][col] != null)
			this.placeCell[row][col].setPlace(actualPlace);

		actualPlaceCell = this.placeCell[row][col];
		actualPlaceCell.enterPlace();
		updateLog();
	}

}
