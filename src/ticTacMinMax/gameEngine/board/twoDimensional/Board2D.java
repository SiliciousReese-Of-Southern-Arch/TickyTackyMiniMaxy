package ticTacMinMax.gameEngine.board.twoDimensional;

import ticTacMinMax.gameEngine.board.GameBoard;
import ticTacMinMax.stream.StreamManager;
import ticTacMinMax.userInterface.SwingManager;
import ticTacMinMax.userInterface.contentPanes.TicTacToePane;

public class Board2D extends GameBoard {
	/* Board constants */

	/* This must be a positive integer. It is usually three. */
	public static final int BOARD_DIMENSION = StreamManager.getInstance()
			.getInt("Board_Size");
	public static final int BOARD_SIZE = BOARD_DIMENSION * BOARD_DIMENSION;

	private static TicTacToePane boardGUI = SwingManager.getInstance()
			.getFrame().getContentPain();

	public static String TIE_GAME_TEXT = "YOU BOTH LOSE!!!";

	/**
	 * The variable to store the board in. The inner array is a 2 element array.
	 * The first element is whether a piece is placed at the given location. The
	 * second is whether the piece placed at the location is Player 1.
	 */
	private boolean[][][] board;

	/*
	 * Keeps track of how many moves have been made. Used to determine if the
	 * board is full.
	 */
	private int numMoves;

	private boolean playerOneTurn;

	/**
	 * A TicTacToe board.
	 * 
	 * @throws InvalidBoardToken
	 *             Throws an exception if the board tokens initialize
	 *             incorrectly.
	 */
	public Board2D() {
		/* create board. Initialize to false. */
		board = new boolean[BOARD_DIMENSION][BOARD_DIMENSION][2];
		for (int i = 0; i < BOARD_DIMENSION; i++)
			for (int j = 0; j < BOARD_DIMENSION; j++) {
				/*
				 * Although the board array has two elements in the inner-most
				 * array, the second element is only used if the first is true.
				 */
				board[i][j][0] = false;
			}

		numMoves = 0;

		playerOneTurn = true;
	}

	public Board2D(Board2D parent) {
		boolean[][][] clone = new boolean[Board2D.BOARD_DIMENSION][Board2D.BOARD_DIMENSION][2];

		for (int i = 0; i < Board2D.BOARD_DIMENSION; i++)
			for (int j = 0; j < Board2D.BOARD_DIMENSION; j++) {
				clone[i][j][0] = parent.board[i][j][0];
				clone[i][j][1] = parent.board[i][j][1];
			}

		board = clone;

		numMoves = parent.numMoves;

		playerOneTurn = parent.playerOneTurn;
	}

	/**
	 * place a piece at the given location on the board.
	 * 
	 * @param playerNumber
	 *            Either player one or player two. This determines the symbol to
	 *            place in the board array.
	 * @param repaint
	 *            True if you want to force the board to repaint the screen
	 * @throws InvalidMoveExeption
	 *             If the location is already taken.
	 */
	public void placePiece(BoardLocation2D loc, boolean repaint) {
		int row = loc.row();
		int column = loc.column();
		if (!board[column][row][0]) {
			board[column][row][0] = true;
			board[column][row][1] = playerOneTurn;
		} else
			throw new UnsupportedOperationException("Location not empty."
					+ board[column][row] + " placed at " + column + ", " + row);

		numMoves++;

		playerOneTurn = !playerOneTurn;

		if (repaint)
			boardGUI.repaint();
	}

	/**
	 * Determine if a piece is placed at a given location.
	 * 
	 * @return True if there is already a piece placed at the given location.
	 */
	public boolean isPieceAt(BoardLocation2D loc) {
		return board[loc.column()][loc.row()][0];
	}

	public boolean isPlayerXAt(BoardLocation2D loc) {
		return isPieceAt(loc) && board[loc.column()][loc.row()][1];
	}

	public boolean isPlayerOAt(BoardLocation2D loc) {
		return isPieceAt(loc) && !board[loc.column()][loc.row()][1];
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
	 * Check each column, called by the isGameWon() method.
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
		boolean playerToken;

		for (j = 0; j < BOARD_DIMENSION && !win; j++)
			if (board[0][j][0]) {
				playerToken = board[0][j][1];
				row = 0;
				for (i = 0; i < BOARD_DIMENSION && board[i][j][0]
						&& board[i][j][1] == playerToken; i++)
					row++;
				win = (row == BOARD_DIMENSION);
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
		boolean playerToken;

		for (i = 0; i < BOARD_DIMENSION && !win; i++)
			if (board[i][0][0]) {
				playerToken = board[i][0][1];
				row = 0;
				for (j = 0; j < BOARD_DIMENSION && board[i][j][0]
						&& board[i][j][1] == playerToken; j++)
					row++;
				win = (row == BOARD_DIMENSION);
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
		boolean playerToken;

		// Top left to bottom right.
		if (board[0][0][0]) {
			playerToken = board[0][0][1];
			// First location already checked.
			row = 1;
			for (i = 1; i < BOARD_DIMENSION; i++)
				// If j were used, i and j would always be the same.
				if (board[i][i][0] && board[i][i][1] == playerToken)
					row++;
			win = (row == BOARD_DIMENSION);
		}

		// Bottom left to top right.
		if (!win && board[0][BOARD_DIMENSION - 1][0]) {
			playerToken = board[0][BOARD_DIMENSION - 1][1];
			// First location already checked.
			row = 1;
			for (i = 1, j = BOARD_DIMENSION - 2; i < BOARD_DIMENSION && j >= 0
					&& board[i][j][0]
					&& playerToken == board[i][j][1]; i++, j--)
				row++;
			win = (row == BOARD_DIMENSION);
		}

		return win;
	}

	/** @return True if every space on the board has a non-empty value. */
	public boolean isFull() {
		return numMoves == BOARD_SIZE;
	}
}
