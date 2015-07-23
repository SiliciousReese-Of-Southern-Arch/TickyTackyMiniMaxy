package ticTacMinMax.board.twoDimensional;

import java.util.Arrays;

import ticTacMinMax.TicTacToe;
import ticTacMinMax.board.exceptions.InvalidBoardToken;

public class Board2D {
	// Board constants
	// This must be a positive integer. It is usually three.
	public static final int BOARD_LENGTH = TicTacToe.getBoardLength();
	public static final int BOARD_SIZE = BOARD_LENGTH * BOARD_LENGTH;
	public static final char PLAYER_1_TOKEN = "O".charAt(0);
	public static final char PLAYER_2_TOKEN = "X".charAt(0);
	public static final char BLANK_SPACE = "_".charAt(0);

	// The variable to store the board in.
	protected char[][] board;

	/**
	 * A TicTacToe board.
	 * 
	 * @throws InvalidBoardToken
	 *             Throws an exception if the board tokens initialize
	 *             incorrectly.
	 */
	public Board2D() throws InvalidBoardToken {
		board = initializeBoard();

		if (PLAYER_1_TOKEN == PLAYER_2_TOKEN || PLAYER_1_TOKEN == BLANK_SPACE
				|| PLAYER_2_TOKEN == BLANK_SPACE)
			throw new InvalidBoardToken("Invalid board tokens used...");
	}

	/**
	 * Initialize the board to the blank value.
	 */
	protected static final char[][] initializeBoard() {
		char[][] newBoard = new char[BOARD_LENGTH][BOARD_LENGTH];

		for (int i = 0; i < BOARD_LENGTH; i++)
			for (int j = 0; j < BOARD_LENGTH; j++)
				newBoard[i][j] = BLANK_SPACE;

		return newBoard;
	}

	/**
	 * place a piece at the given location on the board
	 * 
	 * @param column
	 *            The column number to place the piece at. The columns start at
	 *            one.
	 * @param row
	 *            The row number to place the piece at. The rows start at one.
	 * @param playerNumber
	 *            Either player one or player two. This determines the symbol to
	 *            place in the board array.
	 */
	public void placePieceAt(int column, int row, int playerNumber) {
		if (playerNumber == 1)
			board[column][row] = PLAYER_1_TOKEN;
		else
			board[column][row] = PLAYER_2_TOKEN;
	}

	/**
	 * Determine if a piece is placed at a given location.
	 * 
	 * @param column
	 *            The column number to place the piece at. The columns start at
	 *            one.
	 * @param row
	 *            The row number to place the piece at. The rows start at one.
	 * @return True if there is already a piece placed at the given location.
	 */
	public boolean isPiecePlacedAt(int column, int row) {
		return board[column][row] != BLANK_SPACE;
	}

	/**
	 * @return True if there is a winner. This can easily be used to determine
	 *         the winner by running this check immediately when a player places
	 *         piece because only that play can be the winner.
	 */
	public boolean isGameWon() {
		// Use smaller methods to check each of the winning conditions.
		return (checkColumns() || checkRows() || checkDiagonals());
	}

	/**
	 * Check the each column, called by the isGameWon() method.
	 * 
	 * @return True if any of the columns have all of the same player tokens.
	 */
	private boolean checkColumns() {
		// Win defaults to false, row stores the number of consecutive tokens, i
		// is the column number in the board array and j is the column in the
		// board array. playerToken holds the token that is being checked with
		// the board character array for a winning condition.
		boolean win = false;
		int row, i, j;
		char playerToken;

		for (j = 0; j < BOARD_LENGTH && !win; j++)
			if (board[0][j] != BLANK_SPACE) {
				playerToken = board[0][j];
				row = 0;
				for (i = 0; i < BOARD_LENGTH && board[i][j] == playerToken; i++)
					row++;
				win = (row == BOARD_LENGTH);
			}

		return win;
	}

	/**
	 * Check the each row, called by the isGameWon() method.
	 * 
	 * @return True if any of the rows have all of the same player tokens.
	 */
	private boolean checkRows() {
		// Win defaults to false, row stores the number of consecutive tokens, i
		// is the column number in the board array and j is the column in the
		// board array. playerToken holds the token that is being checked with
		// the board character array for a winning condition.
		boolean win = false;
		int row, i, j;
		char playerToken;

		for (i = 0; i < BOARD_LENGTH && !win; i++)
			if (board[i][0] != BLANK_SPACE) {
				playerToken = board[i][0];
				row = 0;
				for (j = 0; j < BOARD_LENGTH && board[i][j] == playerToken; j++)
					row++;
				win = (row == BOARD_LENGTH);
			}

		return win;
	}

	/**
	 * Check the each diagonal, called by the isGameWon() method.
	 * 
	 * @return True if either of the diagonals have all the same player tokens.
	 */
	private boolean checkDiagonals() {
		// Win defaults to false, row stores the number of consecutive tokens, i
		// is the column number in the board array and j is the column in the
		// board array. playerToken holds the token that is being checked with
		// the board character array for a winning condition.
		boolean win = false;
		int row, i, j;
		char playerToken;

		// Top left to bottom right.
		if (board[0][0] != BLANK_SPACE) {
			playerToken = board[0][0];
			// First location already checked.
			row = 1;
			for (i = 1; i < BOARD_LENGTH; i++)
				// If j were used, i and j would always be the same.
				if (board[i][i] == playerToken)
					row++;
			win = (row == BOARD_LENGTH);
		}

		// Bottom left to top right.
		if (!win && board[0][BOARD_LENGTH - 1] != BLANK_SPACE) {
			playerToken = board[0][BOARD_LENGTH - 1];
			// First location already checked.
			row = 1;
			for (i = 1, j = BOARD_LENGTH - 2; i < BOARD_LENGTH && j >= 0
					&& playerToken == board[i][j]; i++, j--)
				row++;
			win = (row == BOARD_LENGTH);
		}

		return win;
	}

	/**
	 * @return True if every space on the board has a non-empty value.
	 */
	public boolean isFull() {
		// Default to true.
		boolean full = true;

		// Loop through the board array until a blank space is found, if one is
		// never found, the board is full.
		for (int i = 0; i < BOARD_LENGTH && full; i++)
			for (int j = 0; j < BOARD_LENGTH && full; j++)
				full = !(board[i][j] == BLANK_SPACE);

		return full;
	}

	/**
	 * @return A copy of the board.
	 */
	public char[][] getCopyOfBoard() {
		char[][] boardCopy = new char[BOARD_LENGTH][BOARD_LENGTH];

		for (int i = 0; i < BOARD_LENGTH; i++)
			boardCopy[i] = Arrays.copyOf(board[i], board.length);

		return boardCopy;
	}
}
