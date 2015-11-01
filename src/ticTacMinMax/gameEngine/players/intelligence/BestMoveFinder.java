package ticTacMinMax.gameEngine.players.intelligence;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import ticTacMinMax.gameEngine.board.twoDimensional.Board2D;
import ticTacMinMax.stream.StreamManager;

public class BestMoveFinder {
	private static long WAIT_TIME_MILLISECONDS = Long.parseLong(StreamManager
			.getInstance().getRawConfig("Max_Search_Time"));

	private ForkJoinPool testThreads = new ForkJoinPool();
	private ScorePosition[][] trials = new ScorePosition[Board2D.BOARD_DIMENSION][Board2D.BOARD_DIMENSION];

	protected TestBoard board;
	private int player;
	private int depth;

	public BestMoveFinder(TestBoard testBoard, int playerNumber, int threadDepth) {
		board = testBoard;
		player = playerNumber;
		depth = threadDepth;
	}

	public int getBestScore() {
		// Initialize score..
		int score = -1;

		notSewing();

		for (int i = 0; i < Board2D.BOARD_DIMENSION; i++)
			for (int j = 0; j < Board2D.BOARD_DIMENSION; j++)
				if (trials[i][j] != null && trials[i][j].getScore() > score)
					score = trials[i][j].getScore();

		return score;
	}

	public int[] getBestPoint() {
		int[] point;

		sewing();

		boolean[][] possiblePoints = getHighestScoringPoint();

		point = pickFirstPoint(possiblePoints);

		return point;
	}

	private boolean[][] getHighestScoringPoint() {
		boolean[][] possiblePoints;

		// Get a list of valid moves. These are all the moves that are tied for
		// the highest point value.
		possiblePoints = new boolean[Board2D.BOARD_DIMENSION][Board2D.BOARD_DIMENSION];

		int highScore = Integer.MIN_VALUE;

		for (int i = 0; i < Board2D.BOARD_DIMENSION; i++)
			for (int j = 0; j < Board2D.BOARD_DIMENSION; j++)
				if (trials[i][j] != null) {
					if (trials[i][j].getScore() > highScore) {
						highScore = trials[i][j].getScore();
						i = j = 0;
						possiblePoints[i][j] = true;
					} else if (trials[i][j].getScore() == highScore)
						possiblePoints[i][j] = true;
					else
						possiblePoints[i][j] = false;
				}

		return possiblePoints;
	}

	private int[] pickFirstPoint(boolean[][] possiblePoints) {
		int[] point = new int[2];

		for (int i = 0; i < Board2D.BOARD_DIMENSION; i++) {
			for (int j = 0; j < Board2D.BOARD_DIMENSION; j++) {
				if (possiblePoints[i][j]) {
					point[0] = i;
					point[1] = j;
				}
			}
		}

		return point;
	}

	@SuppressWarnings("rawtypes")
	/**
	 * Sew the threads.
	 */
	private void sewing() {
		Future[][] threads = new Future[Board2D.BOARD_DIMENSION][Board2D.BOARD_DIMENSION];

		// Create and start a thread for each available move.
		for (int i = 0; i < Board2D.BOARD_DIMENSION; i++)
			for (int j = 0; j < Board2D.BOARD_DIMENSION; j++) {
				if (!board.isPiecePlacedAt(i, j)) {
					TestBoard testBoard = new TestBoard(board);
					trials[i][j] = new ScorePosition(testBoard, i, j, player,
							depth);
					threads[i][j] = testThreads.submit(trials[i][j]);
				} else
					// TODO Remove null assignment.
					trials[i][j] = null;
			}

		// Explicitly stop executing new threads.
		testThreads.shutdown();

		try {
			// Smother the threads if they do not finish soon enough.
			if (!testThreads.awaitTermination(WAIT_TIME_MILLISECONDS,
					TimeUnit.MILLISECONDS))
				for (int i = 0; i < Board2D.BOARD_DIMENSION; i++)
					for (int j = 0; j < Board2D.BOARD_DIMENSION; j++)
						if (threads[i][j] != null)
							threads[i][j].cancel(true);
		} catch (InterruptedException e) {
			e.printStackTrace();
			// If the test threads are interrupted make sure they and all their
			// children die suddenly.
			for (int i = 0; i < Board2D.BOARD_DIMENSION; i++)
				for (int j = 0; j < Board2D.BOARD_DIMENSION; j++)
					if (threads[i][j] != null)
						threads[i][j].cancel(true);
		}

	}

	private void notSewing() {
		// Create and start a thread for each available move.
		for (int i = 0; i < Board2D.BOARD_DIMENSION; i++)
			for (int j = 0; j < Board2D.BOARD_DIMENSION; j++) {
				if (!board.isPiecePlacedAt(i, j)) {
					TestBoard testBoard = new TestBoard(board);
					trials[i][j] = new ScorePosition(testBoard, i, j, player,
							depth);
					trials[i][j].run();
				} else
					// TODO Remove null assignment.
					trials[i][j] = null;
			}
	}
}
