package tp.pr4.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


public class InstructionPanel extends JPanel {
	
	public InstructionPanel(){
		super();
		this.setBorder(new TitledBorder("Instructions"));
		this.setLayout(new GridLayout(4, 2, 3, 3));
		//this.setLayout(new GridLayout(4, 2));
		JButton btMove = new JButton("MOVE");
		this.add(btMove);
		JButton btQuit = new JButton("QUIT");
		this.add(btQuit);
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
		JButton btOperate = new JButton("OPERATE");
		this.add(btOperate);
						
	}
	
	public enum Directions {
		LEFT, RIGHT;
	}
	
	
	
}
