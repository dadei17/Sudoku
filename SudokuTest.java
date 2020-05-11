import junit.framework.TestCase;

public class SudokuTest extends TestCase {
	public void testHardGrid1() {
		Sudoku grid = new Sudoku(Sudoku.hardGrid);
		
		int[][] answerGrid = Sudoku.textToGrid(
				"3 7 5 1 6 2 4 8 9"+ 
				"8 6 1 4 9 3 5 2 7" +
				"2 4 9 7 8 5 1 6 3"+
				"4 9 3 8 5 7 6 1 2"+ 
				"7 1 6 2 4 9 8 3 5"+
				"5 2 8 3 1 6 7 9 4"+
				"6 5 7 9 2 1 3 4 8"+
				"1 8 2 5 3 4 9 7 6"+
				"9 3 4 6 7 8 2 5 1" );
		String starterGrid = grid.toString();
		System.out.print(starterGrid);
		int numSol = grid.solve();
		
		System.out.println("solutions:" + numSol);
		assertEquals(numSol,1); 		
		System.out.println("elapsed:" + grid.getElapsed() + "ms");
		
		String solved = grid.getSolutionText();
		System.out.println(solved);
		int[][] s = Sudoku.textToGrid(solved); 
		for(int i =0; i<9; i++) { 
			for(int j=0; j<9; j++) { 
				assertEquals(s[i][j],answerGrid[i][j]);
			}  
		}  
	} 
	
	public void testGrid2() {
		String BrokenGridInText = (
				"3 0 0 0 0 0 0 8 0"+
				"0 0 1 0 9 3 0 0 0"+
				"0 4 0 7 8 0 0 0 3"+
				"0 9 3 8 0 0 0 1 2"+
				"0 0 0 0 4 0 0 0 0"+
				"5 2 0 0 0 6 7 9 0"+
				"6 0 0 0 2 1 0 4 0"+
				"0 0 0 5 3 0 9 0 0"+ 
				"0 3 0 0 0 0 0 5 9");
		Sudoku grid = new Sudoku (BrokenGridInText);
		assertEquals(0,grid.solve());
	}
	
	public void testGrid3() {
		Sudoku.main(null);
		int[][] myPrivateGrid = Sudoku.textToGrid(
				"0 0 0 1 0 0 0 8 0"+
				"0 0 1 0 9 3 0 0 0"+
				"0 4 0 7 8 0 0 0 3"+
				"0 9 3 8 0 0 0 1 2"+
				"0 0 0 0 0 0 0 0 0"+
				"5 2 0 0 0 6 8 3 0"+
				"6 0 0 0 2 1 0 4 0"+ 
				"0 0 0 6 3 0 9 0 0"+
				"0 0 0 0 0 0 0 0 1");
		Sudoku grid = new Sudoku (myPrivateGrid);
		assertEquals(2,grid.solve());
	}
	
	public void testGrid4() {
		int[][] myPrivateGrid = Sudoku.textToGrid(
				"7 5 4 2 6 9 6 8 1"+
				"6 8 0 0 0 0 3 2 4"+
				"1 2 3 4 8 6 0 7 5"+
				"4 1 5 7 9 3 2 6 0"+
				"3 7 0 0 1 0 0 0 0"+
				"0 6 2 0 4 0 1 3 0"+
				"0 9 7 3 6 0 4 0 0 "+ 
				"0 3 0 9 2 4 7 5 6"+
				"2 4 6 1 5 0 8 9 3");
		 
		String solvedGrid = (
				"7 5 4 2 6 9 6 8 1\n"+
				"6 8 9 5 7 1 3 2 4\n"+
				"1 2 3 4 8 6 9 7 5\n"+
				"4 1 5 7 9 3 2 6 8\n"+
				"3 7 8 6 1 2 5 4 9\n"+
				"9 6 2 8 4 5 1 3 7\n"+
				"5 9 7 3 6 8 4 1 2\n"+ 
				"8 3 1 9 2 4 7 5 6\n"+
				"2 4 6 1 5 7 8 9 3\n"); 
		 
		Sudoku grid = new Sudoku (myPrivateGrid);
		assertEquals(1,grid.solve());
		assertTrue(grid.getSolutionText().equals(solvedGrid));
	}
	
	public void testGrid5() {
		int[][] myPrivateGrid = Sudoku.textToGrid(
				"3 0 0 7 0 0 0 5 2"+
				"7 8 0 0 6 5 0 4 1"+
				"6 0 1 2 4 0 7 0 9"+
				"2 0 9 3 0 4 1 0 0"+
				"1 4 0 6 2 7 8 9 3"+
				"8 7 0 5 9 1 4 2 6"+
				"5 0 8 0 0 0 9 6 4"+ 
				"9 1 0 4 0 6 2 0 8"+
				"4 2 6 8 3 9 5 0 7");
		
		String solvedGrid = (
				"3 9 4 7 1 8 6 5 2\n"+
				"7 8 2 9 6 5 3 4 1\n"+
				"6 5 1 2 4 3 7 8 9\n"+
				"2 6 9 3 8 4 1 7 5\n"+
				"1 4 5 6 2 7 8 9 3\n"+
				"8 7 3 5 9 1 4 2 6\n"+
				"5 3 8 1 7 2 9 6 4\n"+ 
				"9 1 7 4 5 6 2 3 8\n"+
				"4 2 6 8 3 9 5 1 7\n"); 
		 
		Sudoku grid = new Sudoku (myPrivateGrid);
		assertEquals(1,grid.solve());
			
		assertTrue(grid.getSolutionText().equals(solvedGrid));
	}
	
}
