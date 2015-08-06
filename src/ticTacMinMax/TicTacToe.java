package ticTacMinMax;

import java.util.Scanner;

import ticTacMinMax.board.twoDimensional.GameBoard2D;
import ticTacMinMax.exceptions.InvalidMoveExeption;

/**
 * Main class for Tic-Tac-Toe game.
 * 
 * @author SiliciousReese
 * @version 1.1.0-beta
 */
public class TicTacToe {
	public static final int SUCCESS_EXIT_CODE = 0;
	public static final int FAILURE_EXIT_CODE = -1;

	public static Scanner in;

	private static GameBoard2D board;

	/**
	 * Start a game of Tic Tac Toe.
	 */
	public static void main(String[] args) {
		System.out.println("Starting game...");
		in = new Scanner(System.in);
		board = GameBoard2D.getInstance();

		gameLoop();

		exit(false);
	}

	/**
	 * Loops until the player does not enter "y".or "yes"
	 */
	private static void gameLoop() {
		// True until the user stops playing.
		boolean replay = true;

		while (replay) {
			// Run the game.

			// TODO The game should have it's own thread.
			try {
				board.gamePlay();
			} catch (InvalidMoveExeption invalidMove) {
				invalidMove.printStackTrace();
				exit(true);
			}

			// Replay test.
			// TODO Research propper use of input scanner.
			in.reset();
			System.out.println("Play again (y/N)?");
			String playAgain = "y";
			in = new Scanner(System.in);
			playAgain = in.next();
			if (playAgain.equals("y") || playAgain.equals("yes")) {
				replay = true;
				board.resetBoard();
			} else
				replay = false;
		}
	}

	/**
	 * Cleans up and exits the program. The program is exited with a failure
	 * code if error is true.
	 * 
	 * @param error
	 *            Should be true if there was a problem.
	 */
	public static void exit(boolean error) {
		int exitCode = SUCCESS_EXIT_CODE;

		// TODO Clean up game, make sure streams are closed.
		in.close();

		if (error) {
			System.out.println("Exiting abnormally.");
			exitCode = FAILURE_EXIT_CODE;
		} else
			System.out.println("Exiting normally.");

		System.exit(exitCode);
	}
}
