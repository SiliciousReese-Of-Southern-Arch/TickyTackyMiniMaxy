package ticTacMinMax;

import java.util.Scanner;

import ticTacMinMax.board.exceptions.InvalidMoveExeption;
import ticTacMinMax.board.twoDimensional.GameBoard2D;

/**
 * Play TicTacToe against another human, against the computer, or watch the
 * computer play against itself!
 * 
 * @author SiliciousReese
 * @version 1.0.0-beta
 *
 */
public class TicTacToe {
	// Game play parameters.
	// TODO Move to a text file.
	private static boolean IS_PLAYER_ONE_HUMAN = true;
	private static boolean IS_PLAYER_TWO_HUMAN = false;
	private static int MAX_SEARCH_DEPTH = 20;
	private static boolean IS_THREE_DIMENSIONAL = false;
	private static int BOARD_LENGTH = 3;
	private static long MAX_SEARCH_TIME = 15000;

	// Used for determining whether to play again.
	// TODO Should this be moved to another class?
	private static final Scanner in = new Scanner(System.in);

	/**
	 * Start a game of Tic Tac Toe
	 */
	public static void main(String[] args) {
		// TODO This method needs parts of it to be refactored.
		
		// Loop until replay is not "y"
		String replay = "y";
		while (replay.equals("y")) {
			// Loading output.
			System.out.println("Player 1 human? " + IS_PLAYER_ONE_HUMAN + ".");
			System.out.println("Player 2 human? " + IS_PLAYER_TWO_HUMAN + ".");
			System.out.println("3 Dimensional board?" + IS_THREE_DIMENSIONAL + ".");
			System.out.println("The length of the board is " + BOARD_LENGTH + ".");
			System.out.println("The max search depth is " + MAX_SEARCH_DEPTH + ".");
			System.out.println("The max search time is " + MAX_SEARCH_TIME + " milliseconds.");

			System.out.println("Starting game...");
			try {
				// Wait a seconds for the player to see the output of the
				// previous commands.
				Thread.sleep(1500);

				// start the game.
				// TODO The game should be started in a different thread, but
				// not in a try-catch.
				GameBoard2D.getInstance().gamePlay();
			} catch (InterruptedException e) {
				System.out.println("Caught interupted exeption. Feeling sleep deprived...");
			} catch (InvalidMoveExeption invalidMove) {
				invalidMove.printStackTrace();
			}

			// Replay test.
			System.out.println("Play again (Y/n)?");
			replay = in.nextLine();
			if (replay.equals("n") || replay.equals("no"))
				replay = "n";
			else
				replay = "y";
		}
	}

	// Methods to get game variables.
	// TODO Move these to the files section of the program and use config.txt
	public static boolean isPlayerOneHuman() {
		return IS_PLAYER_ONE_HUMAN;
	}
	public static boolean isPlayerTwoHuman() {
		return IS_PLAYER_TWO_HUMAN;
	}
	public static boolean isThreeDimensional() {
		return IS_THREE_DIMENSIONAL;
	}
	public static int getMaxSearchDepth() {
		return MAX_SEARCH_DEPTH;
	}
	public static int getBoardLength() {
		return BOARD_LENGTH;
	}
	public static long getMaxSearchTime() {
		return MAX_SEARCH_TIME;
	}
}
