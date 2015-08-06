package ticTacMinMax.board.twoDimensional;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import ticTacMinMax.TicTacToe;

// TODO Merge with other board classes
// TODO Serialize?
@SuppressWarnings("serial")
public class Graphical2DBoard extends Rectangle {
	private static final Graphical2DBoard INSTANCE = new Graphical2DBoard();

	private static final Color GRID_COLOR = Color.BLACK;
	private static final int GRID_WIDTH = 20;

	private Graphical2DBoard() {
		// TODO Create instance fields, if necessary.
	}

	public static Graphical2DBoard getInstance() {
		return INSTANCE;
	}

	/**
	 * Draw the board.
	 * 
	 * @param g
	 *            The graphics object to draw the board on.
	 * @param board
	 *            The board to be used for determine which locations have been
	 *            used.
	 */
	public void draw(Graphics g, GameBoard2D board) {
		// Draw board grid.
		g.setColor(GRID_COLOR);
		Rectangle[][] grid =
				drawRectangles(g.getClipBounds().width, GRID_WIDTH);
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < GameBoard2D.BOARD_LENGTH - 1; j++) {
				// TODO Fix rectangle usage
				g.drawRect(grid[i][j].x, grid[i][j].y, grid[i][j].width,
						grid[i][j].height);
			}
		}

		// Draw x's and o's
		Point[][] points = getTextLocations(g.getClipBounds().width);
		drawImages(g, GameBoard2D.getInstance().board, points);
	}

	/**
	 * @param boardLength
	 *            The length of the board.
	 * @param rectWidth
	 *            The width of the grid lines.
	 * @return Four rectangles to be used as the grid of the board.
	 */
	private Rectangle[][] drawRectangles(int boardLength, int rectWidth) {
		// Create the rectangle array.
		Rectangle[][] rectangles =
				new Rectangle[2][GameBoard2D.BOARD_LENGTH - 1];

		/*
		 * Creates the four rectangles. The location of the top-left most point
		 * is the only real calculation performed. One of the coordinates is
		 * always 0. The other coordinate is determined by first adding 1 to
		 * the location of the rectangle in the rectangle array. Then
		 * multiplying that by the length of the of the board divided by three.
		 * Finally half of the width of the rectangle is subtracted from that.
		 */
		for (int i = 0; i < GameBoard2D.BOARD_LENGTH - 1; i++) {
			int x, y;
			// Vertical rectangles.
			x =
					((i + 1) * (boardLength / GameBoard2D.BOARD_LENGTH)
							- (rectWidth / (GameBoard2D.BOARD_LENGTH - 1)));
			rectangles[0][i] = new Rectangle(x, 0, rectWidth, boardLength);

			// Horizontal rectangles.
			y =
					(((i + 1) * boardLength) / GameBoard2D.BOARD_LENGTH)
							- (rectWidth / (GameBoard2D.BOARD_LENGTH - 1));
			rectangles[1][i] = new Rectangle(0, y, boardLength, rectWidth);
		}

		return rectangles;
	}

	// TODO rename
	private Point[][] getTextLocations(int boardLength) {
		Point[][] points =
				new Point[Board2D.BOARD_LENGTH][Board2D.BOARD_LENGTH];

		for (int i = 0; i < Board2D.BOARD_LENGTH; i++) {
			for (int j = 0; j < Board2D.BOARD_LENGTH; j++) {
				points[i][j] =
						new Point((i * boardLength) / Board2D.BOARD_LENGTH,
								(j * boardLength) / Board2D.BOARD_LENGTH);
			}
		}

		return points;
	}

	/**
	 * Draws the X's and O's on the graphics object.
	 * 
	 * @param g
	 *            The graphics object to draw pictures on.
	 * @param boardArray
	 *            The character array representing the board.
	 * @param points
	 *            The locations to draw the images at.
	 * @throws FileNotFoundException
	 *             Throws an error if the image can't be opened correctly.
	 */
	private void drawImages(Graphics g, char[][] boardArray, Point[][] points) {
		// TODO Consider a more flexible way to find the image.
		String imageLoc = "res" + File.separator + "symbols.png";

		// The image has to default to null.
		BufferedImage fullImage = null, xImage, oImage;

		try {
			fullImage =
					ImageIO.read(TicTacToe.class.getClassLoader()
							.getResourceAsStream(imageLoc));
		} catch (IOException | IllegalArgumentException e) {
			// If the image loading fails the program is fucked...
			e.printStackTrace();
			System.err.println("Image file not found!");
			TicTacToe.exit(true);
		}

		// Separate the image into x and o
		xImage =
				fullImage.getSubimage(0, 0, fullImage.getWidth() / 2,
						fullImage.getHeight());
		oImage =
				fullImage.getSubimage(fullImage.getWidth() / 2, 0,
						fullImage.getWidth() / 2, fullImage.getHeight());

		// TODO Find image length
		int imageLength = (int) points[0][0].distance(points[0][1]);

		// Clean up and document.
		for (int i = 0; i < Board2D.BOARD_LENGTH; i++) {
			for (int j = 0; j < Board2D.BOARD_LENGTH; j++) {
				if (Board2D.PLAYER_1_TOKEN == boardArray[i][j])
					g.drawImage(xImage, points[i][j].x, points[i][j].y,
							imageLength, imageLength, null);
				else if (Board2D.PLAYER_2_TOKEN == boardArray[i][j])
					g.drawImage(oImage, points[i][j].x, points[i][j].y,
							imageLength, imageLength, null);
			}
		}
	}
}
