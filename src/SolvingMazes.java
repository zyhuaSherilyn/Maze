import java.io.*; // Needed for file objects
import java.util.*;

/**
 * Solving the maze problem
 * @author Ridout
 * @version October 2014
 */
public class SolvingMazes
{
	public static void main(String[] args) throws FileNotFoundException
	{
		System.out.println("Maze Program");
		Scanner mazeFile = new Scanner(new File("mazeB.txt"));

		// Read in the number of test cases
		int noOfCases = mazeFile.nextInt();
		for (int testCase = 1; testCase <= noOfCases; testCase++)
		{

			Maze nextMaze = new Maze(mazeFile);

			long start = System.nanoTime();
			int fewestMoves = nextMaze.solve();
			long stop = System.nanoTime();

			// nextMaze.displayFewestMoves ();
			if (fewestMoves > 0)
				System.out.println("Maze: " + testCase +
						": " + fewestMoves + " moves");
			else
				System.out.println("Maze: " + testCase + ": No solution");
			System.out.printf("Time %.1f ms%n", (stop - start) / 1000000.0);
		}
		mazeFile.close();
		System.out.println("Maze Program is Complete");

	} // main method
} // MazeProgram class
