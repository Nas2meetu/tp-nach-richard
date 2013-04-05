package tp.pr4.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

/**
 * 
 * @author Ignacio Cerda Sanchez
 * @author Ricardo Eugui Fernandez
 * @version 4
 * 
 */

public class RobotPanel extends JPanel {

	JLabel lbFuel, lbRecycledMaterial;
	JTable tbInventory;

	public RobotPanel() {
		super();
		this.setBorder(new TitledBorder("Robot Info"));
		this.setLayout(new BorderLayout());

		JLabel fuelLabel = new JLabel("Fuel");
		JLabel fuelGarbage = new JLabel("Recycled");

		this.lbFuel = new JLabel("65");
		this.lbRecycledMaterial = new JLabel("0");

		String[][] inventory = { { "newspapers", "News on Sport" },
				{ "petrol", "fuel" } };

		tbInventory = new JTable(new inventory(inventory));
		JPanel pContInfo = new JPanel();

		pContInfo.add(fuelLabel);
		pContInfo.add(lbFuel);
		pContInfo.add(fuelGarbage);
		pContInfo.add(lbRecycledMaterial);

		this.add(pContInfo, BorderLayout.NORTH);
		this.add(new JScrollPane(this.tbInventory), BorderLayout.CENTER);
		this.setPreferredSize(new Dimension(280, 115));
	}

	class inventory extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		public String[] cols = { "Id", "Description" };

		public String[][] data;

		public inventory(String[][] data) {
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

}
