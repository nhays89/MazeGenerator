
public class Maze {
	
	private String[][] myMaze;
	private int myWidth;
	private int myDepth;
	
	/**
	 * Creates a 2d maze of size n by m and with the debug flag set to true it 
	 * will show the steps of maze creation. 
	 * @param width the width of the maze 
	 * @param depth the depth of the maze
	 * @param debug the value of debug
	 */
	public Maze(int width, int depth, boolean debug){
		myWidth = width;
		myDepth = depth;
		myMaze = new String[myWidth * 2 + 1][myDepth * 2 + 1];
		createMaze();
	
	}
	
	
	
	private void createMaze(){
		System.out.println(myMaze.length);
		/*for(int i = 0; i < myMaze.length; i++) {
			for(int j = 0; j < )
		}*/
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*private void generateMaze(int row, int col) {
	int[][] locations = getValidLocs(row, col);
	if (locations.length < 1) {
		myMaze[row][col] = " ";
		return;
	}
	
	for (int i = 0; i < locations.length; i++) {
		myMaze[locations[i][0]][locations[i][1]] = "V";
		generateMaze(locations[i][0], locations[i][1]);
		
	}
	
}*/
	
	
	
	
	
	
	
	
	private void generateMaze(int row, int col) {
		
	}
	
	
	private int[][] getValidLocs(int row, int col) {
		int[][] validLocs = new int[4][4];
		int arrIndex = 0;
		
		// checks North
		if (row - 2 > 0 && myMaze[row - 2][col] == " ") {
			validLocs[arrIndex][0] = row - 2;
			validLocs[arrIndex][1] = col;
			arrIndex++;
		}
		
		// checks East
		if (col + 2 < myWidth * 2 + 1 && myMaze[row][col + 2] == " ") {
			validLocs[arrIndex][0] = row;
			validLocs[arrIndex][1] = col + 2;
			arrIndex++;
		}
		
		// checks South
		if (row + 2 < myDepth * 2 + 1 && myMaze[row + 2][col] == " ") {
			validLocs[arrIndex][0] = row + 2;
			validLocs[arrIndex][1] = col;
			arrIndex++;
		}
		
		// checks West
		if (col - 2 > 0 && myMaze[row][col - 2] == " ") {
			validLocs[arrIndex][0] = row;
			validLocs[arrIndex][1] = col - 2;
			arrIndex++;
		}
		
		// randomizes locations
		
		
		return validLocs;
	}
	
	
	
	/*
	 * base case: no valid directions
	 * check and choose direction
	 * 		- choose random direction
	 * 		- can navigate to the direction
	 * 
	 * 
	 * for (int i = 0; i < array.length; i++) {
			recursive_func(array[i]);
		}
	 * */
	
	
	
	
	
	
}
