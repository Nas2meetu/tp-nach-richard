package tp.pr4.gui;

import static tp.pr4.Constants.END_FUEL;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import static tp.pr4.Constants.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
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
	private InventoryTableModel tbInventoryModel;
	// private int fuel = INITIAL_POWER;
	// private int garbage = INITIAL_GARBAGE;
	private JLabel fuelLabel;

	public RobotPanel() {
		super();
		this.setBorder(new TitledBorder("Robot Info"));
		this.setLayout(new BorderLayout());

		JPanel pContInfo = new JPanel();

		fuelLabel = new JLabel("Fuel: ");
		this.lbFuel = new JLabel(INITIAL_POWER.toString());
		pContInfo.add(fuelLabel);
		pContInfo.add(lbFuel);

		JLabel gargabeLabel = new JLabel("Recycled: ");
		this.lbRecycledMaterial = new JLabel(INITIAL_GARBAGE.toString());
		pContInfo.add(gargabeLabel);
		pContInfo.add(lbRecycledMaterial);

		tbInventoryModel = new InventoryTableModel();
		tbInventory = new JTable(tbInventoryModel);

		this.add(pContInfo, BorderLayout.NORTH);
		this.add(new JScrollPane(this.tbInventory), BorderLayout.CENTER);
		this.setPreferredSize(new Dimension(280, 115));
	}

	public void setFuel(Integer fuel) {
		lbFuel.setText(fuel.toString());
		if (fuel == 0) {
			JOptionPane.showMessageDialog(this, END_FUEL);
			System.exit(0);
		}
	}

	public void setGarbage(int garbage) {
		lbRecycledMaterial.setText(Integer.toString(garbage));
	}

	class InventoryTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		public String[] cols = { "Id", "Description" };

		public String[][] data;

		protected void setData(String[][] data) {
			this.data = data;
		}

		public InventoryTableModel() {
			data = new String[0][0];
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
		public String getValueAt(int rowIndex, int columnIndex) {
			// Item i = data.get(rowIndex);
			// if(columnIndex==0) return i.getId();
			// else return i.getDescription();
			return data[rowIndex][columnIndex];
		}

		public void updateData(String[][] items) {
			if (items == null)
				data = new String[0][0];
			else
				data = items;
			this.fireTableDataChanged();
		}

	}

	/*
	 * public void updateTable(ArrayList<Item> containerTable) {
	 * tbInventoryModel.setData(containerTable);
	 * tbInventoryModel.fireTableDataChanged(); }
	 */
	// public ArrayList<Item> getContainer2() {
	// return container2;
	// }

	public String getSelectedItem() {

		int row = tbInventory.getSelectedRow();
		String itemSelected = null;
		if (row == -1)
			JOptionPane.showMessageDialog(this,
					"El item no ha sido seleccionado", "Error",
					JOptionPane.ERROR_MESSAGE);
		else
			itemSelected = tbInventory.getValueAt(row, 0).toString();
		return itemSelected;

	}

	public void updateTable(String[][] items) {
		tbInventoryModel.updateData(items);
		// tbInventoryModel.setData(items);
		// tbInventoryModel.fireTableDataChanged();

	}

}
