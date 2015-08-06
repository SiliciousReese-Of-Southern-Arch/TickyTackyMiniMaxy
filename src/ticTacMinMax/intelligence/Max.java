package ticTacMinMax.intelligence;

import ticTacMinMax.TicTacToePlayer;

/**
 * MAX is my mini-max Tic-Tac-Toe algorithm.
 */
public class Max implements TicTacToePlayer {
	@Override
	public int[] takeTurn(int playerNumber) {
		System.out.println("Machine is thinking...");
		BestMoveFinder moveFinder =
				new BestMoveFinder(new TestBoard(), playerNumber, 0);

		int[] point = moveFinder.getBestPoint();

		return point;
	}

	@Override
	public void victory(int playerNumber) {
		System.out.println("MACHINE> WIN FOR THE MACHINE!!!");
	}

	@Override
	public void defeat(int playerNumber) {
		// This is really a bug in the program because the algorithm should only
		// ever win or tie, never lose.
		System.out.println("MACHINE> 3RR0R... HUMAN VICTORY");
	}

	@Override
	public void tie(int playerNumber) {
		System.out.println("MACHINE> Good Game.");
	}
}
