package ticTacMinMax.gameEngine.players.intelligence;

import ticTacMinMax.gameEngine.board.twoDimensional.Board2D;
import ticTacMinMax.gameEngine.board.twoDimensional.BoardLocation2D;

public class BestMoveFinder {
	/* TODO Create a way to limit the recursion depth. This will prevent a
	 * possible memory bounds exception and prevent the algorithm from spending
	 * way too much time trying to get a solution at the start of the game. */

	/* Constants for giving value to various outcomes */
	/** Highest score */
	public static final int WIN_SCORE = 1;
	/** Neutral score */
	public static final int TIE_SCORE = 0;
	/** Initial value, must be the lowest possible score. */
	public static final int UNDEFINED = -2 * WIN_SCORE;

	/** @param startingBoard
	 *            The board to find the best move for.
	 * @return The optimal move for the given board, or the top-right location
	 *         if the board is full. */
	public static BoardLocation2D getBestPoint(Board2D startingBoard,
			int currentPlayer) {
		/* Start with undefined so that any valid move will be higher. */
		int highScore = UNDEFINED;
		int currentScore;
		BoardLocation2D loc = new BoardLocation2D(0, 0);

		/* for each board location place a piece, and if the score is higher
		 * than the previous high, save it. Exits early if the score is a win. */
		for (int i = 0; i < Board2D.BOARD_DIMENSION && highScore < WIN_SCORE; i++)
			for (int j = 0; j < Board2D.BOARD_DIMENSION
					&& highScore < WIN_SCORE; j++) {
				currentScore = UNDEFINED;
				BoardLocation2D newPiece = new BoardLocation2D(i, j);

				/* Get the score for the current location. Score is defaulted to
				 * UNDEFINED if there is already a piece at the location. */
				if (!startingBoard.isPieceAt(newPiece)) {
					Board2D testBoard = new Board2D(startingBoard);
					currentScore = TIE_SCORE;

					testBoard.placePiece(newPiece, currentPlayer, false);

					if (testBoard.isGameWon())
						currentScore = WIN_SCORE;
					else if (!testBoard.isFull())
						/* Place piece as next player. If they win invert the
						 * score */
						currentScore = getBestScore(testBoard,
								-currentPlayer + 1) * -1;
				}

				if (currentScore > highScore) {
					/* Saves the highest scoring location. This will always run
					 * once to ensure the result is an empty location */
					highScore = currentScore;
					loc = new BoardLocation2D(i, j);
				}

			}

		return loc;
	}

	/** @param board
	 *            Board to place piece at. A copy of the board is used so it
	 *            will not be modified inside the method.
	 * @param player
	 *            The current player.
	 * @return The highest score or TIE__SCORE if board is full. */
	private static int getBestScore(Board2D board, int player) {
		/* Works similarly to the getBestPoint() method. */

		int newScore = TIE_SCORE;
		int highestScore = UNDEFINED;

		for (int i = 0; i < Board2D.BOARD_DIMENSION && highestScore < WIN_SCORE; i++)
			for (int j = 0; j < Board2D.BOARD_DIMENSION
					&& highestScore < WIN_SCORE; j++)
				if (!board.isPieceAt(new BoardLocation2D(i, j))) {
					Board2D testBoard = new Board2D(board);

					testBoard.placePiece(new BoardLocation2D(i, j), player,
							false);

					if (testBoard.isGameWon())
						newScore = WIN_SCORE;
					else if (testBoard.isFull())
						newScore = TIE_SCORE;
					else
						newScore = getBestScore(testBoard, -player + 1) * -1;

					if (newScore > highestScore)
						highestScore = newScore;
				}

		return highestScore;
	}
	// @SuppressWarnings("unused")
	// private static int getScoreNotRecurse(Board2D board, int player,
	// int[] columns) {
	// /* TODO Create non-recursive algorithm to optimize */
	// Board2D[] boards = new Board2D[Board2D.BOARD_SIZE];
	// int[] rows = new int[Board2D.BOARD_SIZE];
	// int[] coulumns = new int[Board2D.BOARD_SIZE];
	// int[] highScore = new int[Board2D.BOARD_SIZE];
	//
	// int b = 2;
	// boards[0] = board;
	// columns[0] = 0;
	// rows[0] = 0;
	// highScore[0] = TIE_SCORE;
	// int newScore = 0;
	//
	// /* Stop when the column of the original board is at the end. */
	// while (rows[0] < 3) {
	// b--;
	// boards[b] = new Board2D(boards[b - 1]);
	//
	// BoardLocation2D loc = new BoardLocation2D(columns[b], rows[b]);
	// if (!boards[b].isPieceAt(loc)) {
	//
	// boards[b].placePiece(loc, player);
	// if (boards[b].isGameWon())
	// newScore = WIN_SCORE;
	// else if (!boards[b].isFull())
	// newScore = TIE_SCORE;
	//
	// if (-newScore > highScore[b - 1]) {
	// } else {
	// b += 2;
	// boards[b] = new Board2D(boards[b - 1]);
	// rows[b] = 0;
	// columns[b] = 0;
	// highScore[b] = UNDEFINED;
	// }
	//
	// }
	//
	// }
	//
	// return -1;
	// }
}
