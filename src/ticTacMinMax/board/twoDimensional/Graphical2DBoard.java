package ticTacMinMax.board.twoDimensional;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;

// TODO Merge with other board class
// TODO Serialize?
// TODO UNIMPLEMENTED
@SuppressWarnings("serial")
public class Graphical2DBoard extends Rectangle {
	private static final Graphical2DBoard INSTANCE = new Graphical2DBoard();
	
	private Rectangle[] lines;

	private Graphical2DBoard() {
		// TODO Auto-generated constructor stub
	}
	
	public static Graphical2DBoard getInstance() {
		return INSTANCE;
	}
}
