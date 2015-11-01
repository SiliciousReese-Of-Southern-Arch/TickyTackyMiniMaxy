package ticTacMinMax.gameEngine.players.intelligence;

import ticTacMinMax.gameEngine.board.twoDimensional.BoardLocation2D;
import ticTacMinMax.gameEngine.players.TicTacToePlayer;

/** MAX is my mini-max Tic-Tac-Toe algorithm. */
public class Max extends TicTacToePlayer {
	public Max(int turnOrder) {
		super(turnOrder);
	}

	@Override
	public BoardLocation2D getMove() {
		// TODO Determine if this really needs the playerOrder
		BestMoveFinder moveFinder = new BestMoveFinder(new TestBoard(),
				playerOrder, 0);

		int[] point = moveFinder.getBestPoint();

		BoardLocation2D loc = new BoardLocation2D(point[0], point[1]);

		gameBoard.placePiece(loc, playerOrder);
		return null;
	}

	@Override
	public void victory() {
		System.out.println("WIN FOR THE MACHINE!!!");
	}

	@Override
	public void defeat() {
		// This is really a bug in the program because the algorithm should only
		// ever win or tie, never lose.
		System.out.println("3RR0R... HUMAN VICTORY");
	}
}
