package ticTacMinMax.exceptions;

@SuppressWarnings("serial")
public class InvalidBoardToken extends RuntimeException {
	public InvalidBoardToken(String explanation) {
		super(explanation);
	}
}
