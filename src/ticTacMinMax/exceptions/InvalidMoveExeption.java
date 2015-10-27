package ticTacMinMax.exceptions;

// TODO Is this the best exception to extend?
@SuppressWarnings("serial")
public class InvalidMoveExeption extends UnsupportedOperationException {
	/**
	 * Default exception.
	 */
	public InvalidMoveExeption() {
		super();
	}

	/**
	 * Exception with a message.
	 * 
	 * @param message
	 *            The message to be displayed if the exception is caught.
	 */
	public InvalidMoveExeption(String message) {
		super(message);
	}
}
