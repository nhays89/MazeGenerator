package view;

import java.util.Random;

import model.Maze;

/**
 * Main controller for testing mazes. 
 * 
 * @author Nicholas Hays & Ethan Rowell
 * @version March 5, 2016
 * Assignment 5: Maze Generator
 * Presented For: Dr. Chris Marriott
 */
public class Main {

	public static void main(String[] args) {
		Random rand = new Random();
		
		/* tests random mazes */
		for (int i = 0; i < 5; i++) {
			testSize(rand.nextInt(15) + 1, rand.nextInt(15) + 1, rand.nextBoolean());
			System.out.println();
		}
		
		/* tests display method */
		Maze testMaze = new Maze(rand.nextInt(10) + 1, rand.nextInt(10) + 1, false);
		System.out.println("tests display");
		testMaze.display();
	}

	public static void testSize(int depth, int width, boolean debug) {
		System.out.println("Tests a " + depth + " x " + width + " maze with debug set to " + debug);
		new Maze(depth, width, debug).display();
	}

}
