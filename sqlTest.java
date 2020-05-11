import java.sql.SQLException;
import java.util.Vector;

import junit.framework.TestCase;

public class sqlTest extends TestCase {
	
	// vector adds.
	public void test1() throws ClassNotFoundException, SQLException {
		sqlTable tbl = new sqlTable();
		tbl.add("Tbilisi", "Europe", "100000");
		assertEquals(3, tbl.getColumnCount());
		assertTrue(tbl.getColumnName(2).equals("population"));
		assertEquals(tbl.getRowCount(), 1);
		assertTrue(tbl.getValueAt(0, 0).equals("Tbilisi"));
		tbl.add("Guria", "Europe", "15000");
		assertTrue(tbl.getValueAt(0, 0).equals("Guria"));
		tbl.printInAplication("SELECT * FROM metropolises WHERE metropolis = " + "\"Tbilisi" + "\";");
		Vector<Object> v = tbl.getGrid().get(0);
		assertTrue(v.get(0).equals("Tbilisi"));
	}
	
	// only metropolis like %m%
	public void test2() throws ClassNotFoundException, SQLException {
		sqlTable tbl = new sqlTable();
		tbl.printInAplication("SELECT * FROM metropolises WHERE metropolis like " + "\"%m%" + "\";");
		Vector<Object> v1 = tbl.getGrid().get(0);
		Vector<Object> v2 = tbl.getGrid().get(1);
		Vector<Object> v3 = tbl.getGrid().get(2);
		assertTrue(v1.get(0).equals("Mumbai"));
		assertTrue(v2.get(0).equals("Rome")); 
		assertTrue(v3.get(0).equals("Melbourne"));
	}
	
	// metropolis where =  population >= and ==
	public void test3() throws ClassNotFoundException, SQLException {
		sqlTable tbl = new sqlTable();
		tbl.printInAplication("SELECT * FROM metropolises WHERE metropolis = " + "\"Mumbai" + "\";");
		assertTrue(tbl.getGrid().get(0).get(1).equals("Asia"));
		tbl.printInAplication("SELECT * FROM metropolises WHERE metropolis = " + "\"New York" + "\";");
		assertTrue(tbl.getGrid().get(0).get(1).equals("North America"));
		tbl.printInAplication("SELECT * FROM metropolises WHERE population >= " + 20400000 +";");
		assertTrue(tbl.getGrid().get(0).get(0).equals("Mumbai"));
		tbl.printInAplication("SELECT * FROM metropolises WHERE population >= " + 21000000 +";");
		assertTrue(tbl.getGrid().get(0).get(0).equals("New York"));
	}
	
	// metropolis like m% and continent like %u%
	public void test4() throws ClassNotFoundException, SQLException {
		sqlTable tbl = new sqlTable();
		tbl.printInAplication("SELECT * FROM metropolises WHERE metropolis like " + "\"m%" + "\"" +  " and continent like " + "\"%u%" + "\";");
		assertTrue(tbl.getGrid().get(0).get(0).equals("Melbourne"));
	}
}
