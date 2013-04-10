package tp.pr4.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import tp.pr4.City;
import tp.pr4.Direction;
import tp.pr4.Place;
import tp.pr4.RobotEngine;
import tp.pr4.gui.listeners.ListenDrop;
import tp.pr4.instructions.DropInstruction;
import tp.pr4.instructions.Instruction;
import tp.pr4.instructions.exceptions.InstructionExecutionException;


public class InstructionPanel extends JPanel {
	
	private RobotEngine robot;
	private NavigationPanel cityPanel;
	private City city;
	private ArrayList<Instruction> lastInstructions;
	
	

	public InstructionPanel(){
		
		super();
		this.cityPanel = new NavigationPanel();
		this.setBorder(new TitledBorder("Instructions"));
		this.setLayout(new GridLayout(4, 2, 3, 3));
		RobotPanel robotPanel = new RobotPanel();
		NavigationPanel navPanel = new NavigationPanel();
		
		JButton btMove = new JButton("MOVE");
		this.add(btMove);
		JButton btQuit = new JButton("QUIT");
		this.add(btQuit);
		
		btQuit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
			       int result = JOptionPane.showConfirmDialog((Component) arg0.getSource(),"Finish game?");
			           if (result == JOptionPane.YES_OPTION) {
			             System.exit(0);
			           } else if (result == JOptionPane.NO_OPTION) {
			             System.out.println("Do nothing");
			           }

			}
		});
		
		JButton btTurn = new JButton("TURN");
		this.add(btTurn);
		JComboBox cbDirections = new JComboBox(Directions.values()); //Saca todos los valores del enumerado
		this.setBorder(BorderFactory.createTitledBorder("Instructions"));
		this.add(cbDirections);	
		JButton btPick = new JButton("PICK");
		this.add(btPick);
		JTextField txtBox = new JTextField(10);
		this.add(txtBox);
		JButton btDrop = new JButton("DROP");
		this.add(btDrop);
		btDrop.addActionListener(new ListenDrop(robotPanel, city,
				navPanel, this));
		initDropButton(btDrop, robotPanel, navPanel);
		
		JButton btOperate = new JButton("OPERATE");
		this.add(btOperate);
						
	}
	
	private void initDropButton(JButton btDrop, RobotPanel robotPanel,
			NavigationPanel navPanel) {
		btDrop.setToolTipText("Drops the selected item from the inventory");
		btDrop.addActionListener(new ListenDrop(robotPanel, city,
				navPanel, this));
	}
	
	
	public ArrayList<Instruction> getLastInstructions() {
		return lastInstructions;
	}


	public enum Directions {
		LEFT, RIGHT;
	}
	
	
	
	
}
