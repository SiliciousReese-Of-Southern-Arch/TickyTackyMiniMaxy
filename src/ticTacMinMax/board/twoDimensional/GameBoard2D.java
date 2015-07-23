package ticTacMinMax.board.twoDimensional;

import ticTacMinMax.HumanPlayer;
import ticTacMinMax.TicTacToe;
import ticTacMinMax.TicTacToePlayer;
import ticTacMinMax.board.exceptions.InvalidMoveExeption;
import ticTacMinMax.intelligence.Max;

public class GameBoard2D extends Board2D {
	private static final GameBoard2D INSTANCE = new GameBoard2D();
	private static String TIE_GAME_TEXT = "YOU BOTH LOSE!!!";

	private GameBoard2D() {
		super();
	}

	public static GameBoard2D getInstance() {
		return INSTANCE;
	}

	/**
	 * Draw the board to standard out.
	 */
	@Deprecated
	public void drawBoard() {
		// TODO use formated output (or just move to GUI).
		// Clear part of the screen.
		for (int i = 0; i < 50; i++)
			System.out.print("\n");

		// Draw the column numbers.
		System.out.print("  ");
		for (int i = 1; i <= BOARD_LENGTH; i++)
			System.out.print(i + "   ");

		System.out.println("\n");

		// Draw each row.
		for (int i = 0; i < BOARD_LENGTH; i++) {
			System.out.print((i + 1) + " ");
			for (int j = 0; j < BOARD_LENGTH - 1; j++)
				// Print each character
				System.out.print(board[j][i] + " | ");
			// Last character does not have pipe after it.
			System.out.println(board[BOARD_LENGTH - 1][i] + "\n");
		}

		System.out.println();
	}

	/**
	 * Reset the board to blank spaces.
	 */
	public void resetBoard() {
		board = initializeBoard();
	}

	/**
	 * The main loop of the game.
	 * 
	 * @throws InvalidMoveExeption
	 *             Thrown if there is an error in one of the player's turn
	 *             methods that tries to place a piece in an invalid location.
	 */
	public void gamePlay() throws InvalidMoveExeption {
		// Initialize variables.
		boolean win = false;
		TicTacToePlayer[] players = new TicTacToePlayer[2];

		// Initialize both players.
		if (TicTacToe.isPlayerOneHuman())
			players[0] = new HumanPlayer();
		else
			players[0] = new Max();

		if (TicTacToe.isPlayerTwoHuman())
			players[1] = new HumanPlayer();
		else
			players[1] = new Max();

		drawBoard();

		// Game loop. Continues until there is a winner or there are no more
		// places to place at.
		while (!(win || isFull()))
			// Player turns. i is the counter for the player.
			for (int i = 0; i < 2 && !win && !isFull(); i++) {
				int[] point = new int[2];
				// The number of the player is one more than the counter.
				int playerNumber = i + 1;

				point = players[i].takeTurn(playerNumber);

				// Try to place a piece. The player classes should not allow the
				// program to get this far without valid input, but the
				// redundancy should not hurt.
				if (!isPiecePlacedAt(point[0], point[1]))
					placePieceAt(point[0], point[1], playerNumber);
				else
					throw new InvalidMoveExeption(
							"Attempted to place piece at invalid index: "
									+ point[0] + " " + point[1]);

				// Redraw the board.
				drawBoard();

				// Check if player won.
				if (isGameWon()) {
					win = true;
					int nextplayer = (i * -1) + 1;
					players[i].victory(playerNumber);
					players[nextplayer].defeat(nextplayer);
				}
			}

		if (!win)
			System.out.println(TIE_GAME_TEXT);

	}
}
