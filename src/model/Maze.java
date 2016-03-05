package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * Model for generating random maze with equal likelihood of occurrence.
 * 
 * @author Nicholas Hays & Ethan Rowell
 * @version March 5, 2016
 * Assignment 5: Maze Generator
 * Presented For: Dr. Chris Marriott
 */
public class Maze {
	
	/**
	 * The two-dimensional maze of characters. 
	 */
	private String[][] myMaze;
	
	/**
	 * The width (number of columns) of the maze. 
	 */
	private int myWidth;
	
	/**
	 * The depth (number of rows) of the maze. 
	 */
	private int myDepth;
	
	/**
	 * The starting coordinates of the maze search. 
	 */
	private int[] sourceCoords; 
	
	/**
	 * The ending coordinates of the maze search. 
	 */
	private int[] destinationCoords; 
	
	/**
	 * Stack of coordinates of the shortest path from source to destination. 
	 */
	private Stack<int[]> shortestPathLocs;
	
	/**
	 * Stack of all visited coordinates of the maze. 
	 */
	private Stack<int[]> visitedLocs;
	
	/**
	 * Determines if the search has reached the destination coordinate. 
	 */
	private boolean reachedDestination;
	
	/**
	 * Determines if the debug mode is set. 
	 */
	private boolean debugOn;

	/**
	 * Creates a 2d maze of size n by m. With the debug flag set to true it will
	 * show the steps of maze creation.
	 * 
	 * @param width the width of the maze
	 * @param depth the depth of the maze
	 * @param debug the value of debug
	 */
	public Maze(int depth, int width, boolean debug) {
		myWidth = width;
		myDepth = depth;
		debugOn = debug;
		myMaze = new String[myDepth * 2 + 1][myWidth * 2 + 1];
		shortestPathLocs = new Stack<int[]>();
		visitedLocs = new Stack<int[]>();
		createMaze();
		init();
		buildMaze(sourceCoords[0], sourceCoords[1]);
		replaceMazeChars();
	}

	/**
	 * Initializes the characters of the maze. 
	 */
	private void createMaze() {
		for (int i = 0; i < myDepth * 2 + 1; i++) {
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
	
	/**
	 * Initializes source and destination coordinates.
	 */
	private void init() {
		sourceCoords = new int[2];
		destinationCoords = new int[2];
		sourceCoords[0] = 1;
		sourceCoords[1] = 1;
		destinationCoords[0] = myDepth * 2 - 1;
		destinationCoords[1] = myWidth * 2 - 1;
		myMaze[0][1] = " ";
		myMaze[1][1] = "V";
		myMaze[myDepth * 2][myWidth * 2 - 1] = " ";
	}

	/**
	 * Recursively traverses a 2 dimensional grid visiting each coordinate until a specified
	 * destination cell has been visited. Keeps track of shortest path to destination coordinate
	 * using stack by pushing each visited coordinate onto shortest path stack, and popping 
	 * coordinate if a return from buildmaze(r,c) occurs while the destination coordinate has not yet
	 * been visited.
	 * 
	 * @param r the row the start the traversal within the grid
	 * @param c the column to start the traversal within the grid
	 */
	private void buildMaze(int r, int c) {
		hasReachedDestination(r, c);
		int[] visitedCoords = { r, c };
		visitedLocs.push(visitedCoords);
		if (!validate(r, c)) {
			return;
		}
		ArrayList<int[]> myCurrentList = getValidLocs(r, c);
		int[] myCurrentCoords = { r, c };
		if (!reachedDestination) {
			int[] coord = { myCurrentCoords[0], myCurrentCoords[1] };
			shortestPathLocs.push(coord);
		}
		if (debugOn) {
			display();
			System.out.println();
		}
		for (int i = 0; i < myCurrentList.size(); i++) {
			if (validate(myCurrentCoords[0], myCurrentCoords[1])) {
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
	
	/**
	 * Determines if the row and column of the current coordinate being visited 
	 * matches the destination row and column. 
	 * 
	 * @param r the row to check equality
	 * @param c the column to check equality
	 */
	private void hasReachedDestination(int r, int c) {
		if (r == destinationCoords[0] && c == destinationCoords[1]) {
			int[] coord = { r, c };
			shortestPathLocs.push(coord);
			reachedDestination = true;
		}
	}
	
	/**
	 * Seeks to find neighboring coordinates surrounding the current 
	 * coordinate that have not yet been visited. 
	 * 
	 * @param r the current row. 
	 * @param c the current column.
	 * @return a value indicating if the current coordinate has neighboring cells that
	 * have not yet been visited. 
	 */
	private boolean validate(int r, int c) {
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
	
	/**
	 * Provides a list of unvisited adjacent coordinates surrounding the current coordinate. 
	 * 
	 * @param row the current row
	 * @param col the current column
	 * @return list of available coordinates surrounding current cell in matrix that
	 * have not yet been visited. 
	 */
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
	
	/**
	 * Compares each coordinate on the visited location stack with each
	 * coordinate on the shortest path stack to determine its respective
	 * character representation.
	 * If a coordinate is in the visited location stack but not in the shortest
	 * path stack its symbol is a " " character, and if a coordinate
	 * is in both stacks then it's symbol is a "+" character.  
	 */
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
	 * Prints the contents of the current maze. 
	 */
	public void display() {
		for (int i = 0; i < myMaze.length; i++) {
			for (int j = 0; j < myMaze[i].length; j++) {
				System.out.print(myMaze[i][j]);
			}
			System.out.print("\n");
		}
	}

}
