package ticTacMinMax.gameEngine;

import ticTacMinMax.gameEngine.board.twoDimensional.Board2D;
import ticTacMinMax.gameEngine.players.HumanPlayer;
import ticTacMinMax.gameEngine.players.TicTacToePlayer;
import ticTacMinMax.gameEngine.players.intelligence.Max;
import ticTacMinMax.stream.StreamManager;

public class GameManager {
	private static final GameManager INSTANCE = new GameManager();

	private static StreamManager streams = StreamManager.getInstance();

	private Board2D board;

	private GameManager() {
		board = new Board2D();
	}

	public static GameManager getInstance() {
		return INSTANCE;
	}

	/** Temporary method to before force all board management tasks through this. */
	public Board2D getBoard() {
		return board;
	}

	public void play() {
		/** True if the user wants to play again. */
		boolean replay = false;

		do {
			/* Run the game. TODO The game should have it's own thread. */
			TicTacToePlayer[] players = getPlayers();
			boolean gameOver = false;
			/* The locations in the array of the two players. They alternate at
			 * the beginning of the loop. */
			int curPlayer = 1;
			int nextPlayer = 0;

			/* Game loop. Continues until there is a winner or the board is
			 * full. */
			while (!gameOver) {
				curPlayer = nextPlayer;
				/* Use algebra to alternate between 1 and 0. */
				nextPlayer = -nextPlayer + 1;

				players[curPlayer].getMove();

				/* If board is full game is over. */
				gameOver = board.isGameWon() || board.isFull();
			}

			/* Game over. */
			if (board.isGameWon()) {
				players[curPlayer].victory();
				players[nextPlayer].defeat();
			} else
				TicTacToePlayer.tie();

			/* TODO Make game re-playable. */
			replay = false;
		} while (replay);
	}

	/** @return An array of the two players. */
	private TicTacToePlayer[] getPlayers() {
		TicTacToePlayer[] players = new TicTacToePlayer[2];

		/* Determine which players are human. */
		boolean player1Human = streams.getBool("Player_1_Human");
		boolean player2Human = streams.getBool("Player_2_Human");

		/* Initialize both players. Max is the mini-max AI. */
		if (player1Human)
			players[0] = new HumanPlayer(0);
		else
			players[0] = new Max(0);

		if (player2Human)
			players[1] = new HumanPlayer(1);
		else
			players[1] = new Max(1);

		return players;
	}
}
