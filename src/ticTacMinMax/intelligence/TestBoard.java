package ticTacMinMax.intelligence;

import ticTacMinMax.board.twoDimensional.Board2D;

public class TestBoard extends Board2D {
	private static final Board2D gameBoard2D = Board2D.getGameBoard();

	/**
	 * A temporary board used to test the effect of placing a piece on the
	 * actual game board. This defines some useful methods for better control
	 * over the board.
	 */
	public TestBoard() {
		super();
		board = gameBoard2D.getCopyOfBoard();
	}

	/**
	 * A temporary board used to test the effect of placing a piece on another
	 * testBoard.
	 * 
	 * @param parent
	 *            The board to inherit from.
	 */
	public TestBoard(Board2D parent) {
		super();
		board = parent.getCopyOfBoard();
	}

	/**
	 * Deletes the token at the given location. Inverse of placePieceAt().
	 */
	public void removePieceAt(int column, int row) {
		board[column][row] = BLANK_SPACE;
	}

	/**
	 * @return The number of spaces without pieces placed on them. If this
	 *         returns zero then isFull() will be true.
	 */
	public int getRemainingSpaces() {
		int spaces = 0;

		// Increment spaces for each non-empty space.
		for (int i = 0; i < BOARD_DIMENSION; i++)
			for (int j = 0; j < BOARD_DIMENSION; j++)
				if (board[i][j] == BLANK_SPACE)
					spaces++;

		return spaces;
	}
}
