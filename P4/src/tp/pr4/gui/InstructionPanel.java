package tp.pr4.gui;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import tp.pr4.City;
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
import tp.pr4.items.Item;

public class InstructionPanel extends JPanel {

	private City city;
	private ArrayList<Instruction> lastInstructions;
	private JComboBox<Rotation> cbDirections;
	private RobotEngine robot;
	private JTextField txtBox = new JTextField(10);
	private RobotPanel robotPanel;

	public InstructionPanel(RobotEngine robot, RobotPanel robotPanel) {

		super();
		this.robot = robot;
		this.robotPanel = robotPanel;
		this.setBorder(new TitledBorder("Instructions"));
		this.setLayout(new GridLayout(4, 2, 3, 3));
		NavigationPanel navPanel = new NavigationPanel();

		JButton btDrop = new JButton("DROP");
		JButton btMove = new JButton("MOVE");
		JButton btOperate = new JButton("OPERATE");
		JButton btPick = new JButton("PICK");
		JButton btQuit = new JButton("QUIT");
		JButton btTurn = new JButton("TURN");

		cbDirections = new JComboBox<Rotation>(Rotation.values());
		this.setBorder(BorderFactory.createTitledBorder("Instructions"));

		this.add(btMove);
		this.add(btQuit);
		this.add(btTurn);
		this.add(cbDirections);
		this.add(btPick);
		this.add(txtBox);
		this.add(btDrop);
		this.add(btOperate);

		initDropButton(btDrop, robotPanel, navPanel);
		initMoveButton(btMove, navPanel);
		initTurnButton(btTurn, robotPanel, navPanel);
		initPickButton(btPick, navPanel, robotPanel, txtBox);
		initOperateButton(btOperate, robotPanel, navPanel);

		btQuit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				int result = JOptionPane.showConfirmDialog(
						(Component) arg0.getSource(), "Finish game?");
				if (result == JOptionPane.YES_OPTION) {
					System.exit(0);
				} else if (result == JOptionPane.NO_OPTION) {
					System.out.println("Do nothing");
				}

			}
		});

	}

	public String getText() {
		return txtBox.getText();
	}

	private void initDropButton(JButton btDrop, final RobotPanel robotPanel, NavigationPanel navPanel) {
		btDrop.setToolTipText("Drops the selected item from the inventory");
		btDrop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String id = robotPanel.getSelectedItem();
				if (id != null) {
					Instruction dropInstruction = new DropInstruction(id);
					robot.communicateRobot(dropInstruction);
					
				}

			}
		});
	}

	private void initTurnButton(JButton btTurn, RobotPanel robotPanel,
			NavigationPanel navPanel) {
		btTurn.setToolTipText("Turn robot direction");
		btTurn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Instruction turnInstruction = new TurnInstruction(cbDirections
						.getItemAt(cbDirections.getSelectedIndex()));
				robot.communicateRobot(turnInstruction);
			}
		});
	}

	private void initMoveButton(JButton btMove, NavigationPanel navPanel) {
		btMove.setToolTipText("Move robot");
		btMove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Instruction moveInstruction = new MoveInstruction();
				robot.communicateRobot(moveInstruction);
			}
		});

	}

	private void initPickButton(JButton btPick, NavigationPanel navPanel,
			RobotPanel robotPanel, final JTextField txtBox) {

		btPick.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = txtBox.getText();
				Instruction pickInstruction = new PickInstruction(id);
				robot.communicateRobot(pickInstruction);
			}
		});

	}

	private void initOperateButton(JButton btOperate,
			final RobotPanel robotPanel, NavigationPanel navPanel) {
		btOperate.setToolTipText("Use item from robot inventory");
		btOperate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String id = robotPanel.getSelectedItem();
				Instruction operateInstruction = new OperateInstruction(id);
				robot.communicateRobot(operateInstruction);
				
			}

		});

	}

	public ArrayList<Instruction> getLastInstructions() {
		return lastInstructions;
	}

	public enum Directions {
		LEFT, RIGHT;
	}

}
