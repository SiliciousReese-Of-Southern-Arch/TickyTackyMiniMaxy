package ticTacMinMax.gameEngine.players.intelligence;

import ticTacMinMax.gameEngine.GameManager;
import ticTacMinMax.gameEngine.board.twoDimensional.BoardLocation2D;
import ticTacMinMax.gameEngine.players.TicTacToePlayer;

/** MAX is my mini-max Tic-Tac-Toe algorithm. */
public class Max extends TicTacToePlayer {
	public Max(int turnOrder) {
		super(turnOrder);
	}

	@Override
	public BoardLocation2D getMove() {
		BoardLocation2D loc = BestMoveFinder.getBestPoint(GameManager
				.getInstance().getBoard(), playerOrder);

		gameBoard.placePiece(loc, playerOrder);
		return loc;
	}

	@Override
	public void victory() {
		System.out.println("WIN FOR THE MACHINE!!!");
	}

	@Override
	public void defeat() {
		/* This is really a bug in the program because the algorithm should only
		 * ever win or tie, never lose. */
		System.out.println("3RR0R... HUMAN VICTORY");
	}
}
