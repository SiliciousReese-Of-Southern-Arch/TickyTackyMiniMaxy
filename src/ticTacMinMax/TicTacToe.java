package ticTacMinMax;

import java.io.IOException;

import ticTacMinMax.gameEngine.GameManager;
import ticTacMinMax.stream.StreamManager;

/** Main class for Tic-Tac-Toe game.
 * 
 * @author SiliciousReese
 * @version 2.2-beta */

public class TicTacToe {
	public static final int SUCCESS_EXIT_CODE = 0;
	public static final int FAILURE_EXIT_CODE = 1;

	/** Start a game of Tic Tac Toe. */
	public static void main(String[] args) {
		System.out.println("Starting game...");

		GameManager game = GameManager.getInstance();

		game.play();

		exit(false);
	}

	/** Cleans up and exits the program. The program is exited with a failure
	 * code if error is true.
	 * 
	 * @param error
	 *            True if there was a problem. */
	public static void exit(boolean error) {
		int exitCode = SUCCESS_EXIT_CODE;
		StreamManager streams = StreamManager.getInstance();

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
}
