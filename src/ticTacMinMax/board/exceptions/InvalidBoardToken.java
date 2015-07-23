package ticTacMinMax.board.exceptions;

@SuppressWarnings("serial")
public class InvalidBoardToken extends RuntimeException {
	public InvalidBoardToken(String explanation) {
		super(explanation);
	}
}
