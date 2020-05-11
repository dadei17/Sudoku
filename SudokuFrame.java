import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;


 public class SudokuFrame extends JFrame {
	 
	 JTextArea puz,sol;

	public SudokuFrame() {  
		super("Sudoku Solver"); 
		
		setLayout(new BorderLayout(4, 4)); 
		
		puz = new JTextArea(15, 20);
		puz.setBorder(new TitledBorder("Puzzle"));
		add(puz, BorderLayout.WEST);
		
		sol = new JTextArea(15, 20); 
		sol.setBorder(new TitledBorder("Solution")); 
		add(sol, BorderLayout.EAST);	
		
		Box box = Box.createHorizontalBox();
        JButton checkButton = new JButton("Check");
        box.add(checkButton);
        JCheckBox autoChBox  = new JCheckBox("Auto Check");
        box.add(autoChBox);
        autoChBox.setSelected(true);
        add(box,BorderLayout.SOUTH); 
        
        checkButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				solutionText();
			}
		});
        
        puz.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(autoChBox.isSelected()) solutionText();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(autoChBox.isSelected()) solutionText();
			} 
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				if(autoChBox.isSelected()) solutionText(); 
			}
		});
		 
		//Could do this:
		setLocationByPlatform(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();  
		setVisible(true);
	}
	
	
	private void solutionText() {
		String res = "";
		try {
			Sudoku sud = new Sudoku(puz.getText());
			int solvedNum = sud.solve();
			if(solvedNum > 0) {
				res = sud.getSolutionText()  + "solution:" + solvedNum + "\n" + "elapsed:" + sud.getElapsed() + "ms" +"\n";
			} 
		} catch (Exception e) { 
			res = "Parsing problem"; 
		}
		sol.setText(res);
	}
	
	
	public static void main(String[] args) {
		// GUI Look And Feel
		// Do this incantation at the start of main() to tell Swing
		// to use the GUI LookAndFeel of the native platform. It's ok
		// to ignore the exception.
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ignored) { }
		
		SudokuFrame frame = new SudokuFrame();
	}

}
