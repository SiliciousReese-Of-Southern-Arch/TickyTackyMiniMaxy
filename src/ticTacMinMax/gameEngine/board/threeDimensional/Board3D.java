package ticTacMinMax.gameEngine.board.threeDimensional;

import java.util.Arrays;

import ticTacMinMax.gameEngine.board.GameBoard;
import ticTacMinMax.stream.StreamManager;

public class Board3D extends GameBoard {
	// Board constants
	// This must be a positive integer. It is usually three.
	public static int BOARD_LENGTH = Integer
			.parseInt(StreamManager.getInstance().getRawConfig("Board_Size"));
	public static int BOARD_SIZE = BOARD_LENGTH * BOARD_LENGTH * BOARD_LENGTH;
	public static final char PLAYER_1_TOKEN = "O".charAt(0);
	public static final char PLAYER_2_TOKEN = "X".charAt(0);
	public static final char BLANK_SPACE = "_".charAt(0);

	// The variable to store the board in.
	protected char[][][] board;

	/**
	 * A TicTacToe board.
	 * 
	 * @throws InvalidBoardToken
	 *             Throws an exception if the board tokens initialize
	 *             incorrectly.
	 */
	public Board3D() {
		board = initializeBoard();
	}

	/** Initialize the board to the blank value. */
	protected static final char[][][] initializeBoard() {
		char[][][] newBoard = new char[BOARD_LENGTH][BOARD_LENGTH][BOARD_LENGTH];

		for (int i = 0; i < BOARD_LENGTH; i++)
			for (int j = 0; j < BOARD_LENGTH; j++)
				for (int k = 0; k < BOARD_LENGTH; k++)
					newBoard[i][j][k] = BLANK_SPACE;

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
	public void placePieceAt(int column, int row, int aisle, int playerNumber) {
		if (playerNumber == 1)
			board[column][row][aisle] = PLAYER_1_TOKEN;
		else
			board[column][row][aisle] = PLAYER_2_TOKEN;
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
	public boolean isPiecePlacedAt(int column, int row, int aisle) {
		return board[column][row][aisle] != BLANK_SPACE;
	}

	/**
	 * @return True if there is a winner. This can easily be used to determine
	 *         the winner by running this check immediately when a player places
	 *         piece because only that play can be the winner.
	 */
	public boolean isGameWon() {
		// Use smaller methods to check each of the winning conditions.
		// TODO Implementing the 3-D version of this method will truly be a
		// bitch...
		return true;// (checkColumns() || checkRows() || checkDiagonals());
	}

	/** @return True if every space on the board has a non-empty value. */
	public boolean isFull() {
		// Default to true.
		boolean full = true;

		// Loop through the board array until a blank space is found, if one is
		// never found, the board is full.
		for (int i = 0; i < BOARD_LENGTH && full; i++)
			for (int j = 0; j < BOARD_LENGTH && full; j++)
				for (int k = 0; k < BOARD_LENGTH; k++)
					full = !(board[i][j][k] == BLANK_SPACE);

		return full;
	}

	/** @return A copy of the board. */
	public char[][][] getCopyOfBoard() {
		char[][][] boardCopy = new char[BOARD_LENGTH][BOARD_LENGTH][BOARD_LENGTH];

		for (int i = 0; i < BOARD_LENGTH; i++)
			for (int j = 0; j < BOARD_LENGTH; j++)
				boardCopy[i][j] = Arrays.copyOf(board[i][j], board.length);

		return boardCopy;
	}

}
