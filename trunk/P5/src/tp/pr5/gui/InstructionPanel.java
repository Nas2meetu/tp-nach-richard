package tp.pr5.gui;

import static tp.pr5.Constants.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import tp.pr5.RobotEngineObserver;
import tp.pr5.Rotation;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 5
 * 
 */

public class InstructionPanel extends JPanel implements RobotEngineObserver {

	private static final long serialVersionUID = 1L;
	private JComboBox<Rotation> cbDirections;
	private GUIController guiController;
	private JTextField txtBox;
	private MainWindow mainWindow;

	/**
	 * 
	 * Panel with instruction buttons
	 * 
	 * @param guiController
	 * @param robotPanel
	 *            displays information about the robot and its inventory
	 * 
	 */

	public InstructionPanel(GUIController guiController, RobotPanel robotPanel,
			MainWindow mainWindow) {

		super();
		this.guiController = guiController;
		this.mainWindow = mainWindow;
		this.setBorder(new TitledBorder("Instructions"));
		this.setLayout(new GridLayout(4, 2, 3, 3));
		NavigationPanel navPanel = new NavigationPanel();

		// Create buttons

		JButton btDrop = new JButton("DROP");
		JButton btMove = new JButton("MOVE");
		JButton btOperate = new JButton("OPERATE");
		JButton btPick = new JButton("PICK");
		JButton btQuit = new JButton("QUIT");
		JButton btTurn = new JButton("TURN");

		cbDirections = new JComboBox<Rotation>(Rotation.values());
		this.setBorder(BorderFactory.createTitledBorder("Instructions"));
		txtBox = new JTextField(10);

		// Add buttons to instructionPanel

		this.add(btMove);
		this.add(btQuit);
		this.add(btTurn);
		this.add(cbDirections);
		this.add(btPick);
		this.add(txtBox);
		this.add(btDrop);
		this.add(btOperate);

		// Initialize buttons

		initDropButton(btDrop, robotPanel, navPanel);
		initMoveButton(btMove, navPanel);
		initTurnButton(btTurn, robotPanel, navPanel);
		initPickButton(btPick, navPanel, robotPanel, txtBox);
		initOperateButton(btOperate, robotPanel, navPanel);
		initQuitButton(btQuit);

	}

	/**
	 * 
	 * Creates an actionListener, receive an actionEvent to drop instruction and
	 * execute drop instruction
	 * 
	 * @param btDrop
	 *            button to drop instruction
	 * @param robotPanel
	 *            displays information about the robot and its inventory
	 * @param navPanel
	 *            displays the information about the robot heading and the city
	 *            that is traversing
	 */

	private void initDropButton(JButton btDrop, final RobotPanel robotPanel,
			NavigationPanel navPanel) {
		btDrop.setToolTipText("Drops the selected item from the inventory");
		btDrop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!mainWindow.isEnd()) {
					String id = robotPanel.getSelectedItem();
					if (id != null) {
						guiController.dropPressed(id);
					} else
						JOptionPane.showMessageDialog(mainWindow,
								NO_ITEM_CHOOSE);
				}
			}

		});
	}

	/**
	 * 
	 * Creates an actionListener, receive an actionEvent to move instruction and
	 * execute move instruction
	 * 
	 * @param btMove
	 *            button to move instruction
	 * @param navPanel
	 *            displays the information about the robot heading and the city
	 *            that is traversing
	 */

	private void initMoveButton(JButton btMove, NavigationPanel navPanel) {
		btMove.setToolTipText("Move robot");
		btMove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!mainWindow.isEnd()) {
					guiController.movePressed();
				}

			}
		});

	}

	/**
	 * 
	 * Creates an actionListener, receive an actionEvent to operate instruction
	 * and execute operate instruction
	 * 
	 * @param btOperate
	 *            button to operate instruction
	 * @param robotPanel
	 *            displays information about the robot and its inventory
	 * @param navPanel
	 *            displays the information about the robot heading and the city
	 *            that is traversing
	 */

	private void initOperateButton(JButton btOperate,
			final RobotPanel robotPanel, NavigationPanel navPanel) {
		btOperate.setToolTipText("Use item from robot inventory");
		btOperate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!mainWindow.isEnd()) {
					String id;
					id = robotPanel.getSelectedItem();
					if (id != null) {
						guiController.operatePressed(id);
					} else
						JOptionPane.showMessageDialog(mainWindow,
								NO_ITEM_CHOOSE);
				}
			}

		});

	}

	/**
	 * 
	 * Creates an actionListener, receive an actionEvent to pick instruction and
	 * execute pick instruction
	 * 
	 * @param btPick
	 *            button to pick instruction
	 * @param navPanel
	 *            displays the information about the robot heading and the city
	 *            that is traversing
	 * @param robotPanel
	 *            displays information about the robot and its inventory
	 * @param txtBox
	 *            box with item name
	 */

	private void initPickButton(JButton btPick, NavigationPanel navPanel,
			RobotPanel robotPanel, final JTextField txtBox) {
		btPick.setToolTipText("Pick an item from place");
		btPick.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!mainWindow.isEnd()) {
					String id = txtBox.getText();
					if (!id.equals("")) {
						guiController.pickPressed(id);
					} else
						JOptionPane.showMessageDialog(getRootPane(),
								NO_WRITTE_ITEM);
				}
			}
		});

	}

	/**
	 * 
	 * Creates an actionListener, receive an actionEvent to quit instruction and
	 * execute quit instruction
	 * 
	 * @param btQuit
	 *            button to quit instruction
	 */

	private void initQuitButton(JButton btQuit) {
		btQuit.setToolTipText("Quits the actual game");
		btQuit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				int result = JOptionPane.showConfirmDialog(mainWindow,
						"Finish game?", TITLE_GAME, JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					System.exit(0);
				} else if (result == JOptionPane.NO_OPTION) {
				}
			}
		});
	}

	/**
	 * Creates an actionListener, receive an actionEvent to turn instruction and
	 * execute turn instruction
	 * 
	 * @param btTurn
	 *            button to turn instruction
	 * @param robotPanel
	 *            displays information about the robot and its inventory
	 * @param navPanel
	 *            displays the information about the robot heading and the city
	 *            that is traversing
	 */

	private void initTurnButton(JButton btTurn, RobotPanel robotPanel,
			NavigationPanel navPanel) {
		btTurn.setToolTipText("Turn robot direction");

		btTurn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!mainWindow.isEnd()) {
					guiController.turnPressed(cbDirections
							.getItemAt(cbDirections.getSelectedIndex()));
				}
			}
		});

	}

	/**
	 * The robot engine informs that the help has been requested
	 */
	@Override
	public void communicationCompleted() {
		// Not use

	}

	/**
	 * The robot engine informs that the help has been requested
	 */
	@Override
	public void communicationHelp(String help) {
		// Not use

	}

	/**
	 * The robot engine informs that the robot has shut down (because it has
	 * arrived at the spaceship or it has run out of fuel)
	 */
	@Override
	public void engineOff(boolean atShip) {
		mainWindow.engineOff(atShip);

	}

	/**
	 * The robot engine informs that it has raised an error
	 */
	@Override
	public void raiseError(String msg) {
		// Not used

	}

	/**
	 * The robot engine informs that the robot wants to say something
	 */
	@Override
	public void robotSays(String message) {
		// Not used

	}

	/**
	 * The robot engine informs that the fuel and/or the amount of recycled
	 * material has changed
	 */
	@Override
	public void robotUpdate(int fuel, int recycledMaterial) {
		// Not used

	}

}
