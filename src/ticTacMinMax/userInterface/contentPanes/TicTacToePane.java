package ticTacMinMax.userInterface.contentPanes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import javax.swing.JDesktopPane;
import javax.swing.event.MouseInputListener;

import ticTacMinMax.gameEngine.board.twoDimensional.GraphicalBoard;

// TODO remove suppress warning.
@SuppressWarnings("serial")
public class TicTacToePane extends JDesktopPane {
	private static final Color BACK_COLOR = Color.CYAN;

	private int[] gridClick;
	private GraphicalBoard drawingBoard;

	public TicTacToePane() {
		super();

		setBackground(BACK_COLOR);

		drawingBoard = new GraphicalBoard();

		MouseInputListener mouseListener = getMouseReader();
		addMouseListener(mouseListener);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Threading.
		super.paint(g);

		drawingBoard.drawBoard(g);
	}

	public int[] getSelection() {
		int[] clickCopy = new int[2];

		// Block until a tile is clicked.
		while (gridClick == null) {
			try {
				Thread.sleep(0, 1);
			} catch (InterruptedException e) {
				/* Are there any systems where this could actually happen? A
				 * nanosecond of sleep gets interrupted? I can think of a system
				 * that does not allow sleeping, and immediately interrupts the
				 * thread, but are there any real life examples of this?
				 * Seriously, contact me if you have an answer.
				 * github.com/SiliciousReese. */
				System.err.println("Exited early from a NANOSECOND of sleep!");
				e.printStackTrace();
			}
		}
		synchronized (gridClick) {
			clickCopy = Arrays.copyOf(gridClick, 2);

			// TODO NEVER USE NULL ASSIGNMENT!!!
			gridClick = null;
		}
		;

		return clickCopy;
	}

	private MouseInputListener getMouseReader() {
		MouseInputListener listener = new MouseInputListener() {
			public void mouseClicked(MouseEvent e) {
				gridClick = drawingBoard.getMouseClick(e);
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
				gridClick = drawingBoard.getMouseClick(e);
			}

			public void mouseDragged(MouseEvent e) {
			}

			public void mouseMoved(MouseEvent e) {
			}
		};

		return listener;
	}
}
