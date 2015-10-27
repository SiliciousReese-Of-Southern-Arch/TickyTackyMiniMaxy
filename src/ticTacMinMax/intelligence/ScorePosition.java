package ticTacMinMax.intelligence;

import ticTacMinMax.board.twoDimensional.Board2D;
import ticTacMinMax.stream.StreamManager;

class ScorePosition implements Runnable {
	// Max depth is used to determine how many recursive calls this is allowed
	// to make. This number greatly impacts the runtime. The max number depends
	// on your java implementation, but I would advise against anything higher
	// than 4. This needs to be at least 3 to work correctly on a 3 by 3 board.
	public static final int MAX_DEPTH =
			Integer.parseInt(
					StreamManager.getInstance().getSetting("Max_Search_Depth"));
	private int depth;
	private int player;
	private int column;
	private int row;
	private int score;
	private TestBoard testBoard;

	ScorePosition(TestBoard newBoard, int testColumn, int testRow,
			int playerNumber, int threadDepth) {
		testBoard = newBoard;
		column = testColumn;
		row = testRow;
		player = playerNumber;
		depth = threadDepth;
		// Initialize to minimum value in case the thread does not complete.
		score = 0;
	}

	/**
	 * Store the score of the given row and column to determine the point value.
	 */
	@Override
	public void run() {
		boolean win = false;
		testBoard.placePieceAt(column, row, player);

		// If the player can win this turn score = 1.
		if (testBoard.isGameWon()) {
			win = true;
			// Best case
			score = 1;
		} else if (!testBoard.isFull()) {
			// Determine the next player. Can be simplified with algebra,
			// but this is clearer.
			int nextPlayer = (-1 * player) + 3;

			int nextDepth = depth + 1;

			// If the opponent can win this turn score = -1.
			for (int i = 0; i < Board2D.BOARD_DIMENSION && !win; i++)
				for (int j = 0; j < Board2D.BOARD_DIMENSION && !win; j++)
					if (!testBoard.isPiecePlacedAt(i, j)) {
						testBoard.placePieceAt(i, j, nextPlayer);
						if (testBoard.isGameWon()) {
							win = true;
							score = -2;
						}
						testBoard.removePieceAt(i, j);
					}

			// If there is no winner and there is an available space that has
			// not already been tested when looking for the opponents move, test
			// opponents turn and invert score.
			if (!win && testBoard.getRemainingSpaces() > 0
					&& nextDepth <= MAX_DEPTH) {
				TestBoard recursiveTestBoard = new TestBoard(testBoard);

				BestMoveFinder recursiveTestCase =
						new BestMoveFinder(recursiveTestBoard, nextPlayer,
								nextDepth);

				// Invert the score because the opponent is playing.
				score = recursiveTestCase.getBestScore() * -1;
			}

			// If there are no more available spaces after this turn and no
			// player has won, score = 0. This is the default value.
		}
	}

	public int getScore() {
		return score;
	}
}
