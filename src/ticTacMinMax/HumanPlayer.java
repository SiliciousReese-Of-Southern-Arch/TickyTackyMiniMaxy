package ticTacMinMax;

import java.util.InputMismatchException;

import ticTacMinMax.board.twoDimensional.Board2D;
import ticTacMinMax.board.twoDimensional.GameBoard2D;

public class HumanPlayer implements TicTacToePlayer {
	private static final GameBoard2D gameBoard2D = GameBoard2D.getInstance();

	@Override
	public int[] takeTurn(int playerNumber) {
		// The players really only exist as iterations of a for loop, so there
		// was no point in creating objects for them.
		System.out.println("Human player's turn!");

		// Get the grid coordinates to place the piece at.
		int[] coordinates = getPlayerInput();

		return coordinates;
	}

	/**
	 * Get coordinates from standard in. This does error checking internally so
	 * the output will be a valid location on the board which the player is
	 * allowed to place a piece at.
	 * 
	 * @return The column and row the player placed the piece at.
	 */
	private static int[] getPlayerInput() {
		int[] coordinates = new int[2];

		// Set the column and row to initial values that will make the while
		// loop run.
		int column = -1, row = -1;

		// Set to true when a number is entered, but it is not a number in the
		// valid range.
		boolean wrongNumber = false;

		while (column < 0 || column >= Board2D.BOARD_LENGTH || row < 0
				|| row >= Board2D.BOARD_LENGTH
				|| gameBoard2D.isPiecePlacedAt(column, row)) {
			try {
				if (wrongNumber)
					System.out.println(
							"Invalid number detected, please try again.");
				else
					wrongNumber = true;
				System.out.print("Enter the column number and press enter:\n");
				column = TicTacToe.in.nextInt() - 1;

				System.out.print("Enter the row number and press enter:\n");
				row = TicTacToe.in.nextInt() - 1;
			} catch (InputMismatchException e) {
				System.out.println("Invalid input detected.");

				// No detectable number was entered. Also this prevents a second
				// error from being printed to standard out.
				wrongNumber = false;

				// Set invalid values so the loop re-loops.
				column = -1;
				row = -1;
			}
		}

		coordinates[0] = column;
		coordinates[1] = row;

		return coordinates;
	}

	@Override
	public void victory(int playerNumber) {
		System.out.println("Player " + playerNumber + " won!");
	}

	@Override
	public void defeat(int playerNumber) {
		System.out.println(
				"Player " + playerNumber + " was beat by a superior player...");
	}

	@Override
	public void tie(int playerNumber) {
		System.out.println("Cats Game!");
	}
}
