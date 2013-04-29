package tp.pr4.gui;

import java.awt.Component;
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
import tp.pr4.RobotEngine;
import tp.pr4.Rotation;
import static tp.pr4.Constants.*;
import tp.pr4.instructions.DropInstruction;
import tp.pr4.instructions.Instruction;
import tp.pr4.instructions.MoveInstruction;
import tp.pr4.instructions.OperateInstruction;
import tp.pr4.instructions.PickInstruction;
import tp.pr4.instructions.TurnInstruction;
import tp.pr4.instructions.exceptions.InstructionExecutionException;

public class InstructionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JComboBox<Rotation> cbDirections;
	private RobotEngine robot;
	private JTextField txtBox;
	private RobotPanel robotPanel;

	/**
	 * 
	 * Panel with instruction buttons
	 * 
	 * @param robot
	 * @param robotPanel
	 *            displays information about the robot and its inventory
	 */

	public InstructionPanel(RobotEngine robot, RobotPanel robotPanel) {

		super();
		this.robot = robot;
		this.robotPanel = robotPanel;
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
				String id;
				id = robotPanel.getSelectedItem();
				if (id != null) {
					Instruction dropInstruction = new DropInstruction(id);
					try {
						robot.communicateRobot(dropInstruction);
					} catch (InstructionExecutionException e1) {
						e1.printStackTrace();
					}
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
				Instruction moveInstruction = new MoveInstruction();
				try {
					robot.communicateRobot(moveInstruction);
				} catch (InstructionExecutionException e1) {
					JOptionPane.showMessageDialog(getRootPane(),
							e1.getMessage());
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
				String id;
				id = robotPanel.getSelectedItem();
				if (id != null) {
					Instruction operateInstruction = new OperateInstruction(id);
					try {
						robot.communicateRobot(operateInstruction);
					} catch (InstructionExecutionException e1) {
						JOptionPane.showMessageDialog(getRootPane(),
								e1.getMessage());
					}
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
				String id = txtBox.getText();
				if (!id.equals("")) {
					Instruction pickInstruction = new PickInstruction(id);
					try {
						robot.communicateRobot(pickInstruction);
					} catch (InstructionExecutionException e1) {
						JOptionPane.showMessageDialog(getRootPane(),
								e1.getMessage());
					}
				} else
					JOptionPane
							.showMessageDialog(getRootPane(), NO_WRITTE_ITEM);
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

				int result = JOptionPane.showConfirmDialog(
						(Component) arg0.getSource(), "Finish game?");
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
				Instruction turnInstruction = new TurnInstruction(cbDirections
						.getItemAt(cbDirections.getSelectedIndex()));
				try {
					robot.communicateRobot(turnInstruction);
				} catch (InstructionExecutionException e1) {
					JOptionPane.showConfirmDialog(getRootPane(), e1.toString());
				}
			}
		});
	}

	
}
