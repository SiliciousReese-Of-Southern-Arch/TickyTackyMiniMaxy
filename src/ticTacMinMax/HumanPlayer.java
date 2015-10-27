package ticTacMinMax;

import ticTacMinMax.board.twoDimensional.Board2D;
import ticTacMinMax.userInterface.contentPanes.TicTacToePane;

public class HumanPlayer extends TicTacToePlayer {
	private static final Board2D gameBoard2D = Board2D.getGameBoard();

	public HumanPlayer(int turnOrder) {
		super(turnOrder);
	}

	@Override
	public void takeTurn() {
		// Get the grid coordinates to place the piece at.
		int[] coordinates = getPlayerInput();

		gameBoard.placePieceAt(coordinates[0], coordinates[1], playerOrder);
	}

	/**
	 * Get coordinates from standard in. This does error checking internally so
	 * the output will be a valid location on the board which the player is
	 * allowed to place a piece at.
	 * 
	 * @return The column and row the player placed the piece at.
	 */
	private int[] getPlayerInput() {
		TicTacToePane windowPane = TicTacToePane.getInstance();

		int[] coordinates = new int[2];

		// Set the column and row to initial values that will make the while
		// loop run.
		// TODO Add warning if the piece can not be placed because a piece is
		// already there.
		windowPane.repaint();
		do {
			coordinates = windowPane.getSelection();
		} while (gameBoard2D.isPiecePlacedAt(coordinates[0], coordinates[1]));

		return coordinates;
	}

	@Override
	public void victory() {
		System.out.println("Player " + playerOrder + " won!");
	}

	@Override
	public void defeat() {
		System.out.println(
				"Player " + playerOrder + " was beat by a superior player...");
	}
}
