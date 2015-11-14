package ticTacMinMax.gameEngine.board.twoDimensional;

public class BoardLocation2D {
	private int col;
	private int ro;

	/** Creates a location for use with the board object and assure validity.
	 *
	 * @throws ArrayIndexOutOfBoundsException
	 *             if the location is not inside of the board. */
	public BoardLocation2D(int column, int row) {
		col = column;
		ro = row;
		if (col > Board2D.BOARD_DIMENSION || ro > Board2D.BOARD_DIMENSION)
			throw new ArrayIndexOutOfBoundsException(
					"The location is outside of the board");
	}

	public int column() {
		return col;
	}

	public int row() {
		return ro;
	}
}
