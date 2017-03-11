package ticTacMinMax.gameEngine.players;

import ticTacMinMax.gameEngine.GameManager;
import ticTacMinMax.gameEngine.board.twoDimensional.Board2D;
import ticTacMinMax.gameEngine.board.twoDimensional.BoardLocation2D;
import ticTacMinMax.userInterface.SwingManager;
import ticTacMinMax.userInterface.contentPanes.TicTacToePane;

public class HumanPlayer extends TicTacToePlayer {
	private static final Board2D gameBoard2D = GameManager.getInstance()
			.getBoard();

	public HumanPlayer() {
		super();
	}

	@Override
	public BoardLocation2D getMove() {
		// Get the grid coordinates to place the piece at.
		int[] coordinates = getPlayerInput();

		BoardLocation2D loc = new BoardLocation2D(coordinates[0],
				coordinates[1]);

		gameBoard.placePiece(loc, true);

		return null;
	}

	/**
	 * Get coordinates from standard in. This does error checking internally so
	 * the output will be a valid location on the board which the player is
	 * allowed to place a piece at.
	 * 
	 * @return The column and row the player placed the piece at.
	 */
	private int[] getPlayerInput() {
		TicTacToePane windowPane = SwingManager.getInstance().getFrame()
				.getContentPain();

		int[] coordinates = new int[2];

		// Set the column and row to initial values that will make the while
		// loop run.
		// TODO Add warning if the piece can not be placed because a piece is
		// already there.
		windowPane.repaint();
		BoardLocation2D loc;
		do {
			coordinates = windowPane.getSelection();
			loc = new BoardLocation2D(coordinates[0], coordinates[1]);
		} while (gameBoard2D.isPieceAt(loc));

		return coordinates;
	}

	@Override
	public void victory() {
		/* TODO Better player victory message. */
		System.out.println("Player won!");
	}

	@Override
	public void defeat() {
		System.out.println("Player was beat by a superior player...");
	}
}
