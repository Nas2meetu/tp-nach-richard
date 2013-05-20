package tp.pr5.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import static tp.pr5.Constants.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import tp.pr5.RobotEngineObserver;
import tp.pr5.items.InventoryObserver;
import tp.pr5.items.Item;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 5
 * 
 */

public class RobotPanel extends JPanel implements RobotEngineObserver,
		InventoryObserver {

	private static final long serialVersionUID = 1L;
	private JLabel lbFuel, lbRecycledMaterial;
	private JTable tbInventory;
	private InventoryTableModel tbInventoryModel;
	private JLabel fuelLabel;

	/**
	 * Displays information about the robot and its inventory
	 */

	public RobotPanel() {
		super();
		this.setBorder(new TitledBorder("Robot Info"));
		this.setLayout(new BorderLayout());

		JPanel pContInfo = new JPanel();

		// Fuel information

		fuelLabel = new JLabel("Fuel: ");
		this.lbFuel = new JLabel(INITIAL_POWER.toString());
		pContInfo.add(fuelLabel);
		pContInfo.add(lbFuel);

		// RecycledMaterial information

		JLabel gargabeLabel = new JLabel("Recycled: ");
		this.lbRecycledMaterial = new JLabel(INITIAL_GARBAGE.toString());
		pContInfo.add(gargabeLabel);
		pContInfo.add(lbRecycledMaterial);

		// Table with inventory items

		tbInventoryModel = new InventoryTableModel();
		tbInventory = new JTable(tbInventoryModel);

		this.add(pContInfo, BorderLayout.NORTH);
		this.add(new JScrollPane(this.tbInventory), BorderLayout.CENTER);
		this.setPreferredSize(new Dimension(280, 115));
	}

	/**
	 * 
	 * Update count of Fuel in RobotPanel
	 * 
	 * @param fuel
	 *            an item that represents fue
	 */

	public void setFuel(Integer fuel) {
		lbFuel.setText(fuel.toString());
	}

	/**
	 * 
	 * Update count of Recycled Material in RobotPanel
	 * 
	 * @param garbage
	 *            is a type of item that generates recycled material after using
	 *            it
	 */

	public void setGarbage(int garbage) {
		lbRecycledMaterial.setText(Integer.toString(garbage));
	}

	/**
	 * 
	 * Is an abstract table to RobotPanel, that implements robot's inventory and
	 * his methods.
	 * 
	 */

	class InventoryTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		public String[] cols = { "Id", "Description" };

		public String[][] data;

		/**
		 * Modify table content
		 * 
		 * @param data
		 */

		protected void setData(String[][] data) {
			this.data = data;
		}

		/**
		 * Initialize table inventory
		 */

		public InventoryTableModel() {
			data = new String[0][0];
		}

		/**
		 * Return column of selected item
		 */

		@Override
		public String getColumnName(int col) {
			return cols[col];
		}

		/**
		 * Return length of rows
		 */

		@Override
		public int getRowCount() {
			return data.length;
		}

		/**
		 * Return length of columns
		 */

		@Override
		public int getColumnCount() {
			return cols.length;
		}

		/**
		 * Return value of an specific row and column
		 */

		@Override
		public String getValueAt(int rowIndex, int columnIndex) {
			return data[rowIndex][columnIndex];
		}

		/**
		 * 
		 * Update data table
		 * 
		 * @param items
		 */

		public void updateData(String[][] items) {
			if (items == null)
				data = new String[0][0];
			else
				data = items;
			this.fireTableDataChanged();
		}

	}

	/**
	 * 
	 * Return selected item of table of robotPanel
	 * 
	 * @return itemSelected an item of table
	 */

	public String getSelectedItem() {

		int row = tbInventory.getSelectedRow();
		String itemSelected = null;
		if(row!=-1)
			itemSelected = tbInventory.getValueAt(row, 0).toString();
		return itemSelected;

	}

	/**
	 * 
	 * Update table of robotPanel and insert new items picked
	 * 
	 * @param items
	 */

	public void updateTable(String[][] items) {
		tbInventoryModel.updateData(items);

	}

	@Override
	public void inventoryChange(List<Item> inventory) {
		if (inventory.size() == 0)
			this.updateTable(null);
		else {
			String items[][] = new String[inventory.size()][2];
			int pos = 0;
			for (Item i : inventory) {
				items[pos][0] = i.getId();
				items[pos][1] = i.getDescription();
				pos++;
			}
			this.updateTable(items);
		}

	}

	@Override
	public void inventoryScanned(String inventoryDescription) {
		// Not used

	}

	@Override
	public void itemEmpty(String itemName) {
		// Not used

	}

	@Override
	public void itemScanned(String description) {
		// Not used

	}

	@Override
	public void communicationCompleted() {
		// Not used

	}

	@Override
	public void communicationHelp(String help) {
		// Not used

	}

	@Override
	public void engineOff(boolean atShip) {
		// Not used

	}

	@Override
	public void raiseError(String msg) {
		// Not used

	}

	@Override
	public void robotSays(String message) {
		// Not used

	}

	/**
	 * 
	 */

	@Override
	public void robotUpdate(int fuel, int recycledMaterial) {
		this.setFuel(fuel);
		this.setGarbage(recycledMaterial);
	}

}
