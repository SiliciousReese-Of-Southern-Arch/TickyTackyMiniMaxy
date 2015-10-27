package ticTacMinMax;

import ticTacMinMax.board.twoDimensional.Board2D;

/**
 * Interface for human and AI player.
 */
public abstract class TicTacToePlayer {
	protected Board2D gameBoard;

	protected int playerOrder;

	public TicTacToePlayer(int turnOrder) {
		playerOrder = turnOrder;
		gameBoard = Board2D.getGameBoard();
	}

	/**
	 * Take a turn
	 * 
	 * @param playerNumber
	 *            The number of the player, should be either 1 or 2.
	 * @return the position the player would like to place a piece at.
	 */
	public abstract void takeTurn();

	/**
	 * Perform game over actions on tie.
	 */
	public static void tie() {
		System.out.println("Cat's game.");
	}

	/**
	 * Perform game over actions on win.
	 */
	public abstract void victory();

	/**
	 * Perform game over actions on lose.
	 */
	public abstract void defeat();

}
