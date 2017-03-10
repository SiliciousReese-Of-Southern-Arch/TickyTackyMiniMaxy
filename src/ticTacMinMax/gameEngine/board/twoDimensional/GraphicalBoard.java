package ticTacMinMax.gameEngine.board.twoDimensional;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import ticTacMinMax.gameEngine.GameManager;
import ticTacMinMax.stream.StreamManager;

public class GraphicalBoard {
	private static final int GRID_WIDTH = 20;
	private static final int SYMBOL_PADDING = 7;

	private static final Color GRID_COLOR = Color.BLACK;
	private static final Color TILE_COLOR = Color.PINK;

	private static StreamManager streams = StreamManager.getInstance();

	/** The rectangles for the tic tac toe grid. */
	private Rectangle[][] grid;

	/** The rectangles for the spaces where the x's and o's. */
	private Rectangle[][] boardSpaces;

	/**
	 * Draws a hologram of a tic tac toe board that will give you cancer.
	 * 
	 * @param g
	 *            The graphics object from a Swing containers paint method.
	 */
	public void drawBoard(Graphics g) {
		int boardWidth = g.getClipBounds().width;

		// Create and fill each rectangle for the grid of the tic tac toe board.
		grid = createGrid(boardWidth);
		fillGrid(g);

		// Create the spaces for the x's and o's.
		boardSpaces = createTiles(boardWidth);
		fillTiles(g);

		BufferedImage xImage = streams.getX();
		BufferedImage oImage = streams.getO();

		drawSymbols(g, xImage, oImage);
	}

	public int[] getMouseClick(MouseEvent e) {
		int[] gridLocation = null;

		for (int i = 0; i < Board2D.BOARD_DIMENSION; i++) {
			for (int j = 0; j < Board2D.BOARD_DIMENSION; j++) {
				if (boardSpaces[i][j].contains(e.getPoint())) {
					gridLocation = new int[2];
					// Reversed
					gridLocation[1] = j;
					gridLocation[0] = i;

					i = j = Board2D.BOARD_DIMENSION;
				}
			}
		}

		return gridLocation;
	}

	/**
	 * @param length
	 *            The length of the board or graphics object the rectangles will
	 *            be drawn over. This will be the length of the longest sides of
	 *            each rectangle. Not to be confused with the board dimension.
	 * @return Creates each rectangle for the grid bars of the tic tac board.
	 */
	private Rectangle[][] createGrid(int length) {
		// 2 dimensions (height and width) and 1 less bar per dimension than
		// there are squares.
		Rectangle[][] rectangles = new Rectangle[2][Board2D.BOARD_DIMENSION
				- 1];

		// Create the rectangle array.
		for (int i = 0; i < Board2D.BOARD_DIMENSION - 1; i++) {
			// The x and y coordinates of the top left point calculated for each
			// rectangle.
			int x;

			// Vertical rectangles. Add one to the location in the array,
			// multiply that by the length of the board split into equal
			// portions for the dimensions of the grid, then subtract a half the
			// rectangle width. This gives the location of the left and top most
			// point of each rectangle.
			x = (((i + 1) * length) / Board2D.BOARD_DIMENSION)
					- (GRID_WIDTH / 2);

			rectangles[0][i] = new Rectangle(x, 0, GRID_WIDTH, length);
			rectangles[1][i] = new Rectangle(0, x, length, GRID_WIDTH);
		}

		return rectangles;
	}

	/**
	 * Fills the given rectangle over a graphics object with the color from the
	 * grid color field.
	 * 
	 * @param g
	 *            The graphics object to draw on.
	 */
	private void fillGrid(Graphics g) {
		g.setColor(GRID_COLOR);

		// Fill the rectangles over the graphics object.
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < Board2D.BOARD_DIMENSION - 1; j++) {
				// Get the coordinates of the rectangle.
				int x = grid[i][j].x;
				int y = grid[i][j].y;
				int width = grid[i][j].width;
				int height = grid[i][j].height;

				// Draw a Zebra.
				g.fillRect(x, y, width, height);
			}
		}
	}

	private Rectangle[][] createTiles(int boardWidth) {
		Rectangle[][] rectangles = new Rectangle[Board2D.BOARD_DIMENSION][Board2D.BOARD_DIMENSION];
		int length = boardWidth / Board2D.BOARD_DIMENSION;

		for (int i = 0; i < Board2D.BOARD_DIMENSION; i++) {
			for (int j = 0; j < Board2D.BOARD_DIMENSION; j++) {
				int x, y;

				x = (i * length) + SYMBOL_PADDING;
				y = (j * length) + SYMBOL_PADDING;

				rectangles[i][j] = new Rectangle(x, y, length - SYMBOL_PADDING,
						length - SYMBOL_PADDING);
			}
		}

		return rectangles;
	}

	/** See above rectangle filler. */
	private void fillTiles(Graphics g) {
		g.setColor(TILE_COLOR);

		for (int i = 0; i < Board2D.BOARD_DIMENSION; i++) {
			for (int j = 0; j < Board2D.BOARD_DIMENSION; j++) {
				int x = boardSpaces[i][j].x;
				int y = boardSpaces[i][j].y;
				int width = boardSpaces[i][j].width;
				int height = boardSpaces[i][j].height;

				g.fillRect(x, y, width, height);
			}
		}
	}

	private void drawSymbols(Graphics g, BufferedImage xImage,
			BufferedImage oImage) {

		Board2D board = GameManager.getInstance().getBoard();
		// TODO Clean up and document.
		for (int i = 0; i < Board2D.BOARD_DIMENSION; i++) {
			for (int j = 0; j < Board2D.BOARD_DIMENSION; j++) {
				// Determine whether either player has a piece placed at the
				// location.
				BoardLocation2D loc = new BoardLocation2D(i, j);

				if (board.isPlayerXAt(loc)) {
					int x1 = boardSpaces[i][j].x;
					int y1 = boardSpaces[i][j].y;
					int x2 = boardSpaces[i][j].x + boardSpaces[i][j].width;
					int y2 = boardSpaces[i][j].y + boardSpaces[i][j].height;

					g.drawImage(xImage, x1, y1, x2, y2, 0, 0, xImage.getWidth(),
							xImage.getHeight(), null);
				} else if (board.isPlayerOAt(loc)) {
					int x1 = boardSpaces[i][j].x;
					int y1 = boardSpaces[i][j].y;
					int x2 = boardSpaces[i][j].x + boardSpaces[i][j].width;
					int y2 = boardSpaces[i][j].y + boardSpaces[i][j].height;

					g.drawImage(oImage, x1, y1, x2, y2, 0, 0, oImage.getWidth(),
							oImage.getHeight(), null);
				}
			}
		}
	}
}
