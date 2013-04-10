package tp.pr4.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tp.pr4.City;
import tp.pr4.gui.InstructionPanel;
import tp.pr4.gui.MainWindow;
import tp.pr4.gui.NavigationPanel;
import tp.pr4.gui.RobotPanel;
import tp.pr4.instructions.DropInstruction;
import tp.pr4.instructions.Instruction;
import tp.pr4.instructions.exceptions.InstructionExecutionException;
import tp.pr4.items.Item;

public class ListenDrop implements ActionListener {

	private RobotPanel robotPanel;
	private City city;
	private NavigationPanel navPanel;
	private InstructionPanel instrucPanel;

	public ListenDrop(RobotPanel robotPanel, City city,
			NavigationPanel navPanel, InstructionPanel instrucPanel) {
		this.robotPanel = robotPanel;
		this.city = city;
		this.navPanel = navPanel;
		this.instrucPanel = instrucPanel;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int row = robotPanel.getTable().getSelectedRow();
		if (row == -1)
			JOptionPane.showMessageDialog(instrucPanel,
					"El item no ha sido seleccionado");
		else {
			Item itemDrop = robotPanel.getContainer2().get(row);
			DropInstruction dropInstruction = new DropInstruction(
					itemDrop.getId());
			robotPanel.getRobot().communicateRobot(dropInstruction);
			instrucPanel.getLastInstructions().add(dropInstruction);
		}
	}

}
