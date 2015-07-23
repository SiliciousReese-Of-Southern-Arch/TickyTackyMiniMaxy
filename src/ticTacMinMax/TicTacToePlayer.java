package ticTacMinMax;

/**
 * Interface for human and AI player.
 */
public interface TicTacToePlayer {
	/**
	 * Take a turn
	 * 
	 * @param playerNumber
	 *            The number of the player, should be either 1 or 2.
	 * @return the position the player would like to place a piece at.
	 */
	public int[] takeTurn(int playerNumber);

	/**
	 * Perform game over actions on win.
	 */
	public void victory(int playerNumber);

	/**
	 * Perform game over actions on lose.
	 */
	public void defeat(int playerNumber);

	/**
	 * Perform game over actions on tie.
	 */
	public void tie(int playerNumber);

}
