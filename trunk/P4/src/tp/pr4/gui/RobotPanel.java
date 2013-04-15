package tp.pr4.gui;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import tp.pr4.RobotEngine;
import tp.pr4.items.Item;


/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 4
 * 
 */

public class RobotPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lbFuel, lbRecycledMaterial;
	private JTable tbInventory;
	private RobotEngine robot;
	private Inventory tableInventory;
	private String fuel;
	
	//ItemContainer containerAux = new ItemContainer();
	private ArrayList<Item> container1 = new ArrayList<>();
	private ArrayList<Item> container2 = new ArrayList<>();

	public RobotPanel() {
		super();
		this.setBorder(new TitledBorder("Robot Info"));
		this.setLayout(new BorderLayout());

		JLabel fuelLabel = new JLabel("Fuel");
		JLabel gargabeLabel = new JLabel("Recycled");

		this.lbFuel = new JLabel(fuel);
		this.lbRecycledMaterial = new JLabel("0");

		String[][] inventory = { { "Newspapers", "News on sport"}};

		tbInventory = new JTable(new Inventory(inventory));
		JPanel pContInfo = new JPanel();

		pContInfo.add(fuelLabel);
		pContInfo.add(lbFuel);
		pContInfo.add(gargabeLabel);
		pContInfo.add(lbRecycledMaterial);

		this.add(pContInfo, BorderLayout.NORTH);
		this.add(new JScrollPane(this.tbInventory), BorderLayout.CENTER);
		this.setPreferredSize(new Dimension(280, 115));
	}
	
	public void setFuel(int fuel) {
		lbFuel.setText(Integer.toString(fuel));
	}
	
	public void setGarbage(int garbage) {
		lbRecycledMaterial.setText(Integer.toString(garbage));
	}
	
	

	public RobotEngine getRobot() {
		return robot;
	}



	class Inventory extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		public String[] cols = { "Id", "Description" };

		public String[][] data;

		public Inventory(String[][] data) {
			this.data = data;
		}

		@Override
		public String getColumnName(int col) {
			return cols[col];
		}

		@Override
		public int getRowCount() {
			return data.length;
		}

		@Override
		public int getColumnCount() {
			return cols.length;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			return data[rowIndex][columnIndex];
		}

	}
	
	public JTable getTable() {
		return tbInventory;
	}
	
	
	public void addItem(Item item) {
		container1.add(item);
		this.setInventory(container1);
	}

	public ArrayList<Item> getContainer1() {
		return container1;
	}

	//public ArrayList<Item> getContainer2() {
	//	return container2;
	//}
	
	Item getSelectedItem()
	{
		int row = this.getTable().getSelectedRow();
		String itemSelected = null;
		if (row == -1)
			JOptionPane.showMessageDialog(this, "El item no ha sido seleccionado");
		else
			Item itemSelected = tableInventory.getValueAt(row, 0);
		return itemSelected;
	}

	private void setInventory(ArrayList<Item> container12) {
		container2.clear();
		container2.addAll(container1);
		tableInventory.fireTableDataChanged();
	}

	

}
