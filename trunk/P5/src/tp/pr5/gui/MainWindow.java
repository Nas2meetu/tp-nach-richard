package tp.pr5.gui;

import static tp.pr5.Constants.END_FUEL;
import static tp.pr5.Constants.END_SPACESHIP;
import static tp.pr5.Constants.QUIT;
import static tp.pr5.Constants.TITLE_GAME;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import tp.pr5.RobotEngineObserver;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 5
 * 
 *          This class creates the window for the Swing interface. The
 *          MainWindow contains the following components:
 * 
 *          A menu with the QUIT action An Action panel with several buttons to
 *          perform MOVE, TURN, OPERATE, PICK, and DROP actions. Additionally it
 *          has a combo box of turn rotations and a text field to write the name
 *          of the item that the robot wants to pick from the current place A
 *          RobotPanel that displays the robot information (fuel and recycled
 *          material) and the robot inventory, which shows a table with item
 *          names and descriptions that the robot carries. The user can select
 *          the items contained in the inventory in order to DROP or OPERATE an
 *          item A NavigationPanel that represents the city. It shows the places
 *          that the robot has visited and an icon that represents the robot
 *          heading. The robot heading is updated when the user performs a TURN
 *          action. The visible places are updated when the robot performs a
 *          MOVE action. Once a place is visited, the user can click on it in
 *          order to display the place description (similar to the RADAR
 *          command). An InfoPanel that displays information about different
 *          events that occur during the game
 * 
 *          This window implements the GameObserver interface in order to be
 *          notified about the game events.
 */

public class MainWindow extends JFrame implements RobotEngineObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NavigationPanel navigationPanel;
	private InstructionPanel instructionsPanel;
	private InfoPanel infoPanel;
	private GUIController guiController;
	private JPanel mainPanel;
	private boolean end = false;

	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	/**
	 * Creates the window and the panels using Swing Components. It stores a
	 * reference to the RobotEngine object and provides the panels to the robot
	 * engine in order to communicate the simulation events.
	 * 
	 * @param guiController
	 *            The RobotEngine that receives the instructions performed by
	 *            the action panel
	 */

	public MainWindow(GUIController guiController) {

		super(TITLE_GAME);
		this.guiController = guiController;
		this.setPreferredSize(new Dimension(900, 700));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainPanel = new JPanel(new BorderLayout());

		createMenu();

		RobotPanel robotPanel = new RobotPanel();
		this.navigationPanel = new NavigationPanel();
		this.infoPanel = new InfoPanel();
		instructionsPanel = new InstructionPanel(guiController, robotPanel,
				this);
		JSplitPane SuperiorPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				instructionsPanel, robotPanel);

		mainPanel.add(SuperiorPanel, BorderLayout.NORTH);

		JSplitPane inferiorPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				SuperiorPanel, this.navigationPanel);
		mainPanel.add(inferiorPanel, BorderLayout.CENTER);
		mainPanel.add(infoPanel, BorderLayout.SOUTH);

		this.add(mainPanel);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		guiController.registerRobotObserver(infoPanel);
		guiController.registerRobotObserver(this);
		guiController.registerRobotObserver(robotPanel);
		guiController.registerInventoryObserver(infoPanel);
		guiController.registerInventoryObserver(robotPanel);
		guiController.registerNavObserver(infoPanel);
		guiController.registerNavObserver(navigationPanel);

	}

	/**
	 * Create dialog menu for window
	 */

	private void createMenu() {
		JMenuBar mbMenu = new JMenuBar();
		this.setJMenuBar(mbMenu);
		JMenu mDialogs = new JMenu("File");
		mbMenu.add(mDialogs);
		initializeMenu(mDialogs);
	}

	/**
	 * 
	 * Quit from menu of window
	 * 
	 * @param mDialogs
	 *            menu for window
	 * 
	 */

	private void initializeMenu(JMenu mDialogs) {

		JMenuItem mQuit = new JMenuItem("Quit");
		mDialogs.add(mQuit);
		mQuit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Game Over");
				System.exit(0);

			}
		});
	}

	/**
	 * 
	 * Return a public method (end) of a private attribute (isEnd).
	 * 
	 * @return isEnd if game finish or not.
	 * 
	 */

	public boolean isEnd() {
		return end;
	}

	/**
	 * The robot engine informs that the robot has shut down (because it has
	 * arrived at the spaceship or it has run out of fuel)
	 * 
	 * Show different message dialog at interface mode if robot is at space ship
	 * or robot hasn't got fuel
	 * 
	 * @param ship
	 *            boolean true if the robot shuts down because it has arrived at
	 *            the spaceship or false if it has run out of fuel
	 */

	public void engineOff(boolean ship) {
		if (ship) {
			JOptionPane.showMessageDialog(rootPane, END_SPACESHIP);
			end = true;
		} else {
			JOptionPane.showMessageDialog(rootPane, END_FUEL);
			end = true;
		}
		this.instructionsPanel.setEnabled(false);
	}

	/**
	 * The robot engine informs that the help has been requested
	 */

	@Override
	public void communicationCompleted() {
		JOptionPane.showMessageDialog(rootPane, QUIT);

	}

	/**
	 * The robot engine informs that the help has been requested
	 * 
	 * @param help
	 *            A string with information help
	 */

	@Override
	public void communicationHelp(String help) {
		// Not use

	}

	/**
	 * The robot engine informs that it has raised an error
	 * 
	 * @param msg
	 *            Error message
	 */

	@Override
	public void raiseError(String msg) {
		JOptionPane.showMessageDialog(rootPane, msg);

	}

	/**
	 * The robot engine informs that the robot wants to say something
	 * 
	 * @param message
	 *            The robot message
	 */

	@Override
	public void robotSays(String message) {
		infoPanel.robotSays(message);
	}

	/**
	 * The robot engine informs that the fuel and/or the amount of recycled
	 * material has changed
	 * 
	 * @param fuel
	 *            Current amount of fuel
	 * @param recycledMaterial
	 *            Current amount of recycled material
	 */

	@Override
	public void robotUpdate(int fuel, int recycledMaterial) {
		// Not use
	}

}
