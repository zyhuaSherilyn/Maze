import java.util.ArrayList;
import java.util.Scanner;

/**
 * Keeps track of the information of a Maze object including position and the
 * number of moves at a particular position
 * @author Sherilyn
 * @version March 26, 2015
 */
public class Maze
{
	private int fewestMoves;
	// The maze grid
	private char[][] grid;
	private int[][] minMovesTo;
	// Parameters for the size of the grid
	int noOfRows;
	int noOfCols;

	/**
	 * Constructs a Maze object from the given file. Creates an ArrayList
	 * containing the contents of the maze.
	 * @param inFile the scanner that is used to read the file
	 */
	public Maze(Scanner inFile)
	{
		// Creates an ArrayList to store the information in the given file
		noOfRows = inFile.nextInt();
		noOfCols = inFile.nextInt();
		fewestMoves = noOfRows * noOfCols;
		inFile.nextLine();
		ArrayList<String> lines = new ArrayList<String>();

		// Adds lines of characters from the file to the ArrayList
		for (int n = 0; n < noOfRows; n++)
		{
			lines.add(inFile.nextLine());
		}
		// Creates a new 2D Array from the ArrayList
		grid = new char[lines.size()][];
		// Creates a new 2D Array to keep track of the minimum number of moves
		// it takes to get to certain positions on the grid
		minMovesTo = new int[noOfRows][noOfCols];
		int row = 0;
		for (String nextLine : lines)
		{
			// Converts the Strings within each line to characters and adds
			// them to the board
			grid[row] = nextLine.toCharArray();
			for (int m = 0; m < minMovesTo[row].length; m++)
				// Sets every number in minMovesTo to the size of the grid
				minMovesTo[row][m] = fewestMoves;
			row++;
		}
	}

	/**
	 * Searches the maze for the starting position in the first column. When
	 * found, calls the searchAround method.
	 * @return the fewest number of moves if there is a solution; -1 if no
	 *         solution is found
	 */
	public int solve()
	{
		for (int row = 0; row < grid.length; row++)
		{
			if (grid[row][0] == 'S')
			{
				// Once the starting position is found, the searchAround method
				// is called
				searchAround(row, 0, 0);

				// Returns the least number of moves if a solution is found
				if (fewestMoves != noOfRows * noOfCols)
					return fewestMoves;
			}
		}
		return -1;
	}

	/**
	 * Recursively searches for the shortest path that can be taken from the
	 * starting position to the final position
	 * @param row the current row on the grid
	 * @param col the current column on the grid
	 * @param numOfMoves the number of moves that has been taken at the current
	 *            position
	 */
	public void searchAround(int row, int col, int numOfMoves)
	{
		if (row >= grid.length || row < 0 || col >= grid[0].length
				|| col < 0)
			// Ends method if the starting position is out of the board
			return;
		if (grid[row][col] == 'X' || grid[row][col] == '+'
				|| numOfMoves >= minMovesTo[row][col])
			// Ends method either when the current position is a wall or marked,
			// or when the number of moves it takes to come to this position
			// exceeds the minimum number of moves for this position
			return;
		if (grid[row][col] == 'F')
		{
			// Updates the fewest number of moves if a smaller number is found
			// and stops the method
			if (numOfMoves < fewestMoves)
				fewestMoves = numOfMoves;
			return;
		}

		// Marks the current position on the grid
		grid[row][col] = '+';
		// Updates the minimum number of moves taken to get to this position if
		// the current number of moves is less than it
		if (numOfMoves < minMovesTo[row][col])
			minMovesTo[row][col] = numOfMoves;

		numOfMoves++;
		// Search all four directions (horizontally and vertically)
		searchAround(row, col + 1, numOfMoves);
		searchAround(row + 1, col, numOfMoves);
		searchAround(row, col - 1, numOfMoves);
		searchAround(row - 1, col, numOfMoves);

		// Unmarks the current position on the grid
		grid[row][col] = '-';
	}
}
