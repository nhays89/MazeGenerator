/**
 * 
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * 
 * @author Nicholas Hays & Ethan Rowell
 *
 */
public class Maze {

	private String[][] myMaze;
	private int myWidth;
	private int myDepth;
	private int[] sourceCoords; // represent the starting loc
	private int[] destinationCoords; // represent the ending loc
	private Stack<int[]> shortestPathLocs;
	private Stack<int[]> visitedLocs;
	private boolean reachedDestination;

	/**
	 * Creates a 2d maze of size n by m. With the debug flag set to true it will
	 * show the steps of maze creation.
	 * 
	 * @param widththe width of the maze
	 * @param depth the depth of the maze
	 * @param debug
	 *            the value of debug
	 */
	public Maze(int width, int depth, boolean debug) {
		myWidth = width;
		myDepth = depth;
		//myMaze = new String[width * 2 + 1][depth * 2 + 1];
		myMaze = new String[myDepth * 2 + 1][myWidth * 2 + 1];
		shortestPathLocs = new Stack<int[]>();
		visitedLocs = new Stack<int[]>();
		createMaze();
		init();
		buildMaze(sourceCoords[0], sourceCoords[1]);
		//printShortestPath(shortestPathLocs, "shortest path locs");
		//printShortestPath(visitedLocs, "visited locs");
		replaceMazeChars();
		printMaze();
	}

	private void printShortestPath(Stack<int[]> stack, String message) {
		Stack<int[]> tempStack = new Stack<>();
		while (!stack.isEmpty()) {
			int[] coords = stack.peek();
			System.out.print(message + "(" + coords[0] + "," + coords[1] + ") ");
			tempStack.push(stack.pop());
		}
		while (!tempStack.isEmpty()) {
			stack.push(tempStack.pop());
		}
	}

	private void replaceMazeChars() {
		Stack<int[]> tempStack = new Stack<>();
		while (!visitedLocs.isEmpty()) {
			boolean isFound = false;
			int[] vLocs = visitedLocs.pop();
			while (!shortestPathLocs.isEmpty()) {
				int[] sLocs = shortestPathLocs.pop();
				if (vLocs[0] == sLocs[0] && vLocs[1] == sLocs[1]) {
					myMaze[sLocs[0]][sLocs[1]] = "+";
					isFound = true;
					break;
				} else {
					tempStack.push(sLocs);
				}
			}
			if (!isFound) {
				myMaze[vLocs[0]][vLocs[1]] = " ";
			}
			while (!tempStack.isEmpty()) {
				shortestPathLocs.push(tempStack.pop());
			}
		}
	}

	/**
	 * 
	 * @param r
	 * @param c
	 */
	private void buildMaze(int r, int c) {
		hasReachedDestination(r, c);
		int[] visitedCoords = { r, c };
		visitedLocs.push(visitedCoords);
		if (!canPass(r, c)) {
			return;
		}
		int[] myCurrentCoords = { r, c };
		System.out.println();
		//printShortestPath(shortestPathLocs, "shortest path locs");
		if (!reachedDestination) {
			int[] coord = { myCurrentCoords[0], myCurrentCoords[1] };
			shortestPathLocs.push(coord);
		}
		ArrayList<int[]> myCurrentList = getValidLocs(r, c);
		for(int i = 0; i < myCurrentList.size(); i++) {

		}
		printMaze();
		System.out.println();
		for (int i = 0; i < myCurrentList.size(); i++) {
			if (revalidate(myCurrentCoords[0], myCurrentCoords[1])) {
				int difference;
				if (myCurrentCoords[0] == myCurrentList.get(i)[0]) {
					if (myCurrentCoords[1] > myCurrentList.get(i)[1]) {
						difference = myCurrentCoords[1] - 1;
					} else {
						difference = myCurrentCoords[1] + 1;
					}
					myMaze[myCurrentList.get(i)[0]][difference] = " ";

				} else {
					if (myCurrentCoords[0] > myCurrentList.get(i)[0]) {
						difference = myCurrentCoords[0] - 1;
					} else {
						difference = myCurrentCoords[0] + 1;
					}
					myMaze[difference][myCurrentList.get(i)[1]] = " ";
				}
				myMaze[myCurrentList.get(i)[0]][myCurrentList.get(i)[1]] = "V";		
				buildMaze(myCurrentList.get(i)[0], myCurrentList.get(i)[1]);
			}
		}
		if (!reachedDestination) {
			shortestPathLocs.pop();
		}
	}

	private void hasReachedDestination(int r, int c) {
		if (r == destinationCoords[0] && c == destinationCoords[1]) {
			int[] coord = { r, c };
			shortestPathLocs.push(coord);
			reachedDestination = true;
		}
	}

	private boolean revalidate(int r, int c) {
		boolean canPass = false;
		// north
		if (r - 2 > 0 && myMaze[r - 2][c] == " ") {
			canPass = true;
		}
		// west
		if (c + 2 < myMaze[r].length - 1 && myMaze[r][c + 2] == " ") {
			canPass = true;
		}
		// south
		if (r + 2 < myMaze.length - 1 && myMaze[r + 2][c] == " ") {
			canPass = true;
		}
		// east
		if (c - 2 > 0 && myMaze[r][c - 2] == " ") {
			canPass = true;
		}
		return canPass;
	}

	private boolean canPass(int r, int c) {
		boolean canPass = false;
		// north
		if (r - 2 > 0 && myMaze[r - 2][c] == " ") {
			canPass = true;
		}
		// west
		if (c + 2 < myMaze[r].length - 1 && myMaze[r][c + 2] == " ") {
			canPass = true;
		}
		// south
		if (r + 2 < myMaze.length - 1 && myMaze[r + 2][c] == " ") {
			canPass = true;
		}
		// east
		if (c - 2 > 0 && myMaze[r][c - 2] == " ") {
			canPass = true;
		}
		return canPass;
	}

	private ArrayList<int[]> getValidLocs(int row, int col) {
		ArrayList<int[]> validLocs = new ArrayList<>();

		// checks North
		if (row - 2 > 0 && myMaze[row - 2][col] == " ") {
			int[] coordinates = { row - 2, col };
			validLocs.add(coordinates);
		}

		// checks East
		if (col + 2 < myWidth * 2 + 1 && myMaze[row][col + 2] == " ") {
			int[] coordinates = { row, col + 2 };
			validLocs.add(coordinates);

		}

		// checks South
		if (row + 2 < myDepth * 2 + 1 && myMaze[row + 2][col] == " ") {
			int[] coordinates = { row + 2, col };
			validLocs.add(coordinates);
		}

		// checks West
		if (col - 2 > 0 && myMaze[row][col - 2] == " ") {
			int[] coordinates = { row, col - 2 };
			validLocs.add(coordinates);
		}
		
		Collections.shuffle(validLocs);
		return validLocs;
	}

	private void init() {
		sourceCoords = new int[2];
		destinationCoords = new int[2];
		sourceCoords[0] = 1;
		sourceCoords[1] = 1;
		destinationCoords[0] = myDepth * 2 - 1;
		destinationCoords[1] = myWidth * 2 - 1;
		myMaze[0][1] = " ";
		myMaze[1][1] = "V";
		System.out.println(myMaze.length);
		/*myMaze[myMaze.length - 1][myMaze.length - 2] = " ";*/
		myMaze[myDepth * 2][myWidth * 2 -1] = " ";
	}

	private void createMaze() {
		/*for (int i = 0; i < myMaze.length; i++) {*/
			for( int i = 0; i < myDepth * 2 + 1; i++) {
			/*for (int j = 0; j < myMaze[i].length; j++) {*/
				for (int j = 0; j < myWidth * 2 + 1; j++) {
				if (i % 2 == 0) {
					myMaze[i][j] = "X";
				} else {
					if (j % 2 == 0) {
						myMaze[i][j] = "X";
					} else {
						myMaze[i][j] = " ";
					}
				}
			}
		}
	}

	private void printMaze() {
		for (int i = 0; i < myMaze.length; i++) {
			System.out.print("\n");
			for (int j = 0; j < myMaze[i].length; j++) {
				System.out.print(myMaze[i][j]);
			}
		}
	}

}
