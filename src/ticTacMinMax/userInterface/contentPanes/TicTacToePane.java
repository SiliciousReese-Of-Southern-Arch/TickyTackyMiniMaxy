package ticTacMinMax.userInterface.contentPanes;

import java.awt.Graphics;

import javax.swing.JDesktopPane;

import ticTacMinMax.board.twoDimensional.GameBoard2D;
import ticTacMinMax.board.twoDimensional.Graphical2DBoard;

// TODO remove suppress warning.
@SuppressWarnings("serial")
public class TicTacToePane extends JDesktopPane {
	// TODO Add mouse listener to allow the player to click on the tile he wants
	// to place a piece at.
	private static final TicTacToePane INSTANCE = new TicTacToePane();

	private TicTacToePane() {
		super();
	}

	public static TicTacToePane getInstance() {
		return INSTANCE;
	}

	@Override
	public void paint(Graphics g){
		super.paint(g);
		Graphical2DBoard.getInstance().draw(g, GameBoard2D.getInstance());
	}
}
