package ticTacMinMax.gameEngine.players;

import ticTacMinMax.gameEngine.GameManager;
import ticTacMinMax.gameEngine.board.twoDimensional.Board2D;
import ticTacMinMax.gameEngine.board.twoDimensional.BoardLocation2D;

/** Interface for human and AI player. */
public abstract class TicTacToePlayer {
	protected Board2D gameBoard;

	protected int playerOrder;

	private static final String TIE_GAME_TEXT = "Cat's game.";

	public TicTacToePlayer(int turnOrder) {
		playerOrder = turnOrder;
		gameBoard = GameManager.getInstance().getBoard();
	}

	/** Take a turn
	 * 
	 * @param playerNumber
	 *            The number of the player, should be either 1 or 2.
	 * @return TODO
	 * @return the position the player would like to place a piece at. */
	public abstract BoardLocation2D getMove();

	/** Perform game over actions on win. */
	public abstract void victory();

	/** Perform game over actions on lose. */
	public abstract void defeat();

	/** Perform game over actions on tie. this is the same for all players. */
	public static final void tie() {
		System.out.println(TIE_GAME_TEXT);
	}

}
