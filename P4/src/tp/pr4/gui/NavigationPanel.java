package tp.pr4.gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import tp.pr4.Direction;
import tp.pr4.Place;
import static tp.pr4.Constants.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.net.URL;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 4
 * 
 */

public class NavigationPanel extends JPanel {

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

		URL urlImage = MainWindow.class.getResource("images/walleNorth.png");
		if (urlImage == null)
			JOptionPane.showMessageDialog(this, IMAGES_DONT_LOAD);

		robotImage = new ImageIcon(urlImage);
		lbRobotIcon = new JLabel(robotImage);

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
	 * 
	 * Update Walle image depends of robot's direction
	 * 
	 * @param lookingDirection
	 * 			is direction that robot is looking
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
	 * Create initial place of robot at navigation panel at swing mode
	 * 
	 * @param actualPlace
	 * 			place where robot is
	 */
	
	public void setInitialPlace(Place actualPlace) {
		this.placeCell[row][col].setPlace(actualPlace);
		this.actualPlaceCell = this.placeCell[row][col];
		this.actualPlaceCell.enterPlace();
		updateLog();
	}

	/**
	 * Update log panel with information about this place
	 * 
	 * @param actualPlace
	 * 			place where robot is
	 */

	public void showActualPlaceLog(Place actualPlace) {
		txtLog.setText(actualPlace.toString());
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

	public void updateCity(Place actualPlace, Direction lookingDirection) {
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
	}

}
