package java_assignment;

import javax.swing.table.DefaultTableModel;

public class Table_model extends DefaultTableModel{

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;		
	}
	
	
}
