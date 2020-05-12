import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

public class application extends JFrame {
	
	private JTable table;
	private sqlTable tableSql;
	private JTextField metrop,conti,popul;
	private JButton addB,searchB;
	private JComboBox<Object> popOption,matchOption;
	
	public application() throws SQLException, ClassNotFoundException {
		super("Metropolis Viewer");
		setPreferredSize(new Dimension(600,450));
		  
		tableSql = new sqlTable();
		table = new JTable(tableSql);
		setLayout(new BorderLayout(4, 4));
		JScrollPane centerTable = new JScrollPane(table);
		add(centerTable,BorderLayout.CENTER); 
		
		JPanel pan = new JPanel(); 
		pan.add(new JLabel("Metropolis:"));
		metrop = new JTextField(10);
		pan.add(metrop);
		pan.add(new JLabel("Continent:"));
		conti = new JTextField(10);
		pan.add(conti);
		pan.add(new JLabel("Population:"));
		popul = new JTextField(10);
		pan.add(popul);
		add(pan,BorderLayout.NORTH);
		
		Box mainBox = Box.createVerticalBox(); 
	
		addB = new JButton("Add");
		mainBox.add(addB); 
		searchB = new JButton("Search");
		mainBox.add(searchB);
		 
		Box searchOptionBox = Box.createVerticalBox();
		searchOptionBox.setBorder(new TitledBorder("Search Options"));
		 
		String [] popSt = new String[2];
		popSt[0] = "POPULATION_LARGER";
		popSt[1] = "POPULATION_SMALLER"; 
	    popOption = new JComboBox<Object>(popSt);
	    searchOptionBox.add(popOption); 
	     
	    String [] matchSt = new String[2]; 
	    matchSt[0] = "EXACT_MATCH";
	    matchSt[1] = "PARTIAL_MATCH";
	    matchOption = new JComboBox<Object>(matchSt);
	    searchOptionBox.add(matchOption); 
	     
	    mainBox.add(searchOptionBox);
	    mainBox.add(Box.createVerticalStrut(30000)); 
	    add(mainBox,BorderLayout.EAST);
		
		setLocationByPlatform(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();   
		setVisible(true);
		
		addB.addActionListener(new ActionListener() {	
			@Override 
			public void actionPerformed(ActionEvent e) {
				try {
					tableSql.add(metrop.getText(), conti.getText(), popul.getText());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			} 
		}); 
		
		searchB.addActionListener(new ActionListener() { 
			@Override 
			public void actionPerformed(ActionEvent e) {
				String sqlString = "SELECT * FROM metropolises WHERE metropolis = metropolis";
				if(!metrop.getText().equals("") ) {
					if(matchOption.getSelectedIndex()==0) {  
						sqlString += " and metropolis = " + "\"" + metrop.getText() + "\""; 
					}else {
						sqlString += " and metropolis like " + "\"%" + metrop.getText() + "%\""; 
					} 
				}
				if(!conti.getText().equals("") ) { 
					if(matchOption.getSelectedIndex()==0) {  
						sqlString += " and continent = " + "\"" + conti.getText() + "\"";
					}else {
						sqlString += " and continent like " + "\"%" + conti.getText() + "%\"";
					}   
				}
				if(!popul.getText().equals("")) {
					if(popOption.getSelectedIndex() == 0) { 
						sqlString += " and population >= " + popul.getText();
					}else {
						sqlString += " and population < " + popul.getText();
					}
				}
				try {
					tableSql.printInAplication(sqlString);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		}); 
		
	}
	
	
	
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// GUI Look And Feel
		// Do this incantation at the start of main() to tell Swing
		// to use the GUI LookAndFeel of the native platform. It's ok
		// to ignore the exception.
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ignored) { }
		
		application frame = new application();
	}
}
