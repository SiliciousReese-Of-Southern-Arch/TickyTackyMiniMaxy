package ticTacMinMax;

import java.io.IOException;

import ticTacMinMax.board.twoDimensional.Board2D;
import ticTacMinMax.exceptions.InvalidMoveExeption;
import ticTacMinMax.intelligence.Max;
import ticTacMinMax.stream.StreamManager;
import ticTacMinMax.userInterface.SwingLoader;

/**
 * Main class for Tic-Tac-Toe game.
 * 
 * @author SiliciousReese
 * @version 2.0-beta
 */
public class TicTacToe {
	public static final int SUCCESS_EXIT_CODE = 0;
	public static final int FAILURE_EXIT_CODE = -1;

	private static StreamManager streams;
	private static SwingLoader gui;
	private static Board2D boardGame;

	/**
	 * Start a game of Tic Tac Toe.
	 */
	public static void main(String[] args) {
		System.out.println("Starting game...");

		streams = StreamManager.getInstance();
		gui = SwingLoader.getInstance();
		boardGame = Board2D.getGameBoard();

		gui.startSwing();

		playGame();

		exit(false);
	}

	/**
	 * Cleans up and exits the program. The program is exited with a failure
	 * code if error is true.
	 * 
	 * @param error
	 *            Should be true if there was a problem.
	 */
	public static void exit(boolean error) {
		System.out.println("Exiting");
		int exitCode = SUCCESS_EXIT_CODE;

		try {
			streams.closeAllStreams();
		} catch (IOException e) {
			error = true;
			e.printStackTrace();
			System.err.println("Error while closing streams!!!");
		}

		if (error) {
			System.out.println("Exiting abnormally.");
			exitCode = FAILURE_EXIT_CODE;
		} else
			System.out.println("Exiting normally.");

		System.exit(exitCode);
	}

	/**
	 * Loops until the player does not enter "y" or "yes"
	 */
	private static void playGame() {
		// True until the user stops playing.
		boolean replay;

		do {
			// Run the game.
			// TODO The game should have it's own thread.
			try {
				// Initialize variables.
				boolean win = false;

				TicTacToePlayer[] players = getPlayers();

				// Game loop. Continues until there is a winner or there are no
				// more places to place at.
				while (!(win || boardGame.isFull()))
					// Player turns. i is the counter for the player.
					for (int i = 0; i < 2 && !win && !boardGame.isFull(); i++) {
						players[i].takeTurn();

						// Check if player won.
						if (boardGame.isGameWon()) {
							win = true;
							int nextplayer = (i * -1) + 1;
							players[i].victory();
							players[nextplayer].defeat();
						}
					}
				if (!win)
					TicTacToePlayer.tie();
			} catch (InvalidMoveExeption invalidMove) {
				invalidMove.printStackTrace();
				exit(true);
			}

			// TODO Make game re-playable.
			replay = false;
		} while (replay);
	}

	private static TicTacToePlayer[] getPlayers() {
		TicTacToePlayer[] players = new TicTacToePlayer[2];

		// Determine which players are human.
		boolean player1Human =
				Boolean.parseBoolean(StreamManager.getInstance()
						.getSetting("Player_1_Human"));
		boolean player2Human =
				Boolean.parseBoolean(StreamManager.getInstance()
						.getSetting("Player_2_Human"));

		// Initialize both players.
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
