import java.sql.*;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class sqlTable extends AbstractTableModel{
	private static int cols = 3;
	private Vector<Vector<Object>> grid;
	private connectToServer conn;
	private String mas[]; 
	
	public sqlTable() throws SQLException, ClassNotFoundException {
		grid = new Vector<Vector<Object>>();
		conn = new connectToServer();
		mas = new String[3];
		mas[0] = "metropolis";
		mas[1] = "continent"; 
		mas[2] = "population";
	}
	
	public void add(Object name, Object state, Object pop) throws SQLException {
		grid.clear();
		Vector<Object> v = new Vector<Object>(); 
		v.add(name);
		v.add(state);
		v.add(pop);
		grid.add(v); 
		String update = "insert into metropolises values (" + "\"" + name + "\""+  "," + "\""+ state +"\""+ "," + pop + ");";
		conn.getState().executeUpdate(update);
		fireTableDataChanged();
	}
	
	@Override
	public String getColumnName(int column) {
		return mas[column];
	}
	

	@Override
	public int getRowCount() {
		return grid.size(); 
	}


	@Override
	public int getColumnCount() {
		return cols;
	}


	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return grid.get(rowIndex).get(columnIndex);
	} 
	
	public void printInAplication(String st) throws SQLException {  
		grid.clear();
		ResultSet rs = conn.getState().executeQuery(st);  
		while(rs.next()) {
			String s = rs.getString("metropolis");
			String c = rs.getString("continent");
			long i = rs.getInt("population"); 
			Vector<Object> v = new Vector<Object>();
			v.add(s);
			v.add(c);
			v.add(i);
			grid.add(v);
		}
		fireTableDataChanged();
	}
	
	public Vector<Vector<Object>> getGrid(){
		return grid;
	}
}
