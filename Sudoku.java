import java.util.*;

/*
 * Encapsulates a Sudoku grid to be solved.
 * CS108 Stanford.
 */


public class Sudoku {
	
	private int[][] board; 
	
	
	
	// Provided grid data for main/testing
	// The instance variable strategy is up to you.
	
	// Provided easy 1 6 grid
	// (can paste this text into the GUI too)
	public static final int[][] easyGrid = Sudoku.stringsToGrid(
	"1 6 4 0 0 0 0 0 2",
	"2 0 0 4 0 3 9 1 0",
	"0 0 5 0 8 0 4 0 7",
	"0 9 0 0 0 6 5 0 0",
	"5 0 0 1 0 2 0 0 8",
	"0 0 8 9 0 0 0 3 0",
	"8 0 9 0 4 0 2 0 0",
	"0 7 3 5 0 9 0 0 1",
	"4 0 0 0 0 0 6 7 9");
	
	
	// Provided medium 5 3 grid
	public static final int[][] mediumGrid = Sudoku.stringsToGrid(
	 "530070000",
	 "600195000",
	 "098000060",
	 "800060003",
	 "400803001",
	 "700020006",
	 "060000280",
	 "000419005",
	 "000080079");
	
	// Provided hard 3 7 grid
	// 1 solution this way, 6 solutions if the 7 is changed to 0
	public static final int[][] hardGrid = Sudoku.stringsToGrid(
	"3 7 0 0 0 0 0 8 0",
	"0 0 1 0 9 3 0 0 0",
	"0 4 0 7 8 0 0 0 3",
	"0 9 3 8 0 0 0 1 2",
	"0 0 0 0 4 0 0 0 0",
	"5 2 0 0 0 6 7 9 0",
	"6 0 0 0 2 1 0 4 0",
	"0 0 0 5 3 0 9 0 0",
	"0 3 0 0 0 0 0 5 1");
	
	
	public static final int SIZE = 9;  // size of the whole 9x9 puzzle
	public static final int PART = 3;  // size of each 3x3 part
	public static final int MAX_SOLUTIONS = 100;
	
	// Provided various static utility methods to
	// convert data formats to int[][] grid.
	
	/**
	 * Returns a 2-d grid parsed from strings, one string per row.
	 * The "..." is a Java 5 feature that essentially
	 * makes "rows" a String[] array.
	 * (provided utility)
	 * @param rows array of row strings
	 * @return grid
	 */
	public static int[][] stringsToGrid(String... rows) {
		int[][] result = new int[rows.length][];
		for (int row = 0; row<rows.length; row++) {
			result[row] = stringToInts(rows[row]);
		}
		return result;
	}
	
	 
	/**
	 * Given a single string containing 81 numbers, returns a 9x9 grid.
	 * Skips all the non-numbers in the text.
	 * (provided utility)
	 * @param text string of 81 numbers
	 * @return grid
	 */ 
	public static int[][] textToGrid(String text) { 
		int[] nums = stringToInts(text);
		if (nums.length != SIZE*SIZE) {
			throw new RuntimeException("Needed 81 numbers, but got:" + nums.length);
		}
		
		int[][] result = new int[SIZE][SIZE];
		int count = 0;
		for (int row = 0; row<SIZE; row++) {
			for (int col=0; col<SIZE; col++) {
				result[row][col] = nums[count];
				count++;
			}
		} 
		return result;
	}
	
	
	/**
	 * Given a string containing digits, like "1 23 4",
	 * returns an int[] of those digits {1 2 3 4}.
	 * (provided utility)
	 * @param string string containing ints
	 * @return array of ints
	 */
	public static int[] stringToInts(String string) {
		int[] a = new int[string.length()];
		int found = 0;
		for (int i=0; i<string.length(); i++) {
			if (Character.isDigit(string.charAt(i))) {
				a[found] = Integer.parseInt(string.substring(i, i+1));
				found++;
			}
		}
		int[] result = new int[found];
		System.arraycopy(a, 0, result, 0, found);
		return result;
	}

	// Provided -- the deliverable main().
	// You can edit to do easier cases, but turn in
	// solving hardGrid.
	
	public static void main(String[] args) {
		Sudoku sudoku;
		sudoku = new Sudoku(hardGrid);
		
		System.out.println(sudoku); // print the raw problem
		int count = sudoku.solve();
		System.out.println("solutions:" + count);
		System.out.println("elapsed:" + sudoku.getElapsed() + "ms");
		System.out.println(sudoku.getSolutionText());
	}
	
 
	/**
	 * Sets up based on the given ints.
	 */
	public Sudoku(int[][] ints) {
		board = new int[ints.length][ints[0].length]; 
		for(int i=0; i<ints.length; i++) {
			for(int j=0; j<ints[0].length; j++) {
				board[i][j] = ints[i][j]; 
			}
		}
	}  

	public Sudoku(String text) { 
		board = textToGrid(text);
	}
	
	private HashSet<Integer> countWays (int n) {
		int col = n/9;
		int row = n%9;
		HashSet<Integer> hash = new HashSet<Integer>();
		for(int i=1; i<=board.length; i++) {
			hash.add(i);
		}
		for(int startCol=0; startCol<board.length; startCol++) {
			if(startCol == col) continue;
			Integer cur = board[startCol][row];
			if(hash.contains(cur)){
				hash.remove(cur);
			}
		}
		for(int startRow=0; startRow<board[0].length; startRow++) {
			if(startRow == row) continue;
			Integer cur = board[col][startRow]; 
			if(hash.contains(cur)){
				hash.remove(cur);
			}
		}
		int sqCol = col/3 * 3;
		int sqRow = row/3 * 3;
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				int curCol = sqCol + i;
				int curRow = sqRow + j;
				if(curCol == col && curRow == row) continue;
				Integer cur = board[curCol][curRow];
				if(hash.contains(cur)){
					hash.remove(cur);
				}
			}
		}
		return hash;
	}
	
	int [][] firstRes = new int[9][9];
	private int solved = 0;

	private void countSolve(int num) { 
		if(num == 81) {
			solved++; 
			if(solved == 1) {
				for(int i=0; i<board.length; i++) {
					for(int j=0; j<board[0].length; j++) {
						firstRes[i][j] = board[i][j];
					}
				}
			}
			return;
		}
		int col = num/9; 
		int row = num%9;
		if(board[col][row] == 0) {
			HashSet<Integer >numberOfWays = countWays(num);
			Iterator<Integer> it =numberOfWays.iterator();
			if(numberOfWays.size() == 0) return;
			while(it.hasNext()){
				int a = it.next();
				board[col][row] = a;
				countSolve(num+1);
			}	
			board[col][row] = 0;
		}else {
			countSolve(num+1);
		}
	}  
	
	long start;
	long end;
	
	/**
	 * Solves the puzzle, invoking the underlying recursive search.
	 */
	public int solve() {
		start = System.currentTimeMillis();
		countSolve(0);
		end = System.currentTimeMillis();
		return solved; 
	}
	
	private String gridToString(int[][] b) {
		String result = "";
		for(int i=0; i<b.length; i++) {
			for(int j=0; j<b[0].length; j++) {
				if(j == b[0].length - 1) {
					result += Integer.toString(b[i][j]);
				}else {
					result += Integer.toString(b[i][j]) + " ";
				}
			} 
			result += "\n"; 
		}
		return result; 
	}
	
	public String toString() {
		return gridToString(board);
	}
	
	public String getSolutionText() {
		return gridToString(firstRes);
	}
	
	public long getElapsed() {
		return end-start; 
	}

}
