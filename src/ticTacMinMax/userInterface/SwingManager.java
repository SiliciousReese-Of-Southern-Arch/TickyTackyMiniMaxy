package ticTacMinMax.userInterface;

import java.awt.GraphicsEnvironment;

import javax.swing.SwingUtilities;

import ticTacMinMax.TicTacToe;
import ticTacMinMax.userInterface.frames.TicTacToeFrame;

/** Handles threading of the GUI. */
public class SwingManager {
	private static final SwingManager INSTANCE = new SwingManager();

	private TicTacToeFrame frame;

	private SwingManager() {
		if (GraphicsEnvironment.isHeadless()) {
			System.err.println("Gui not supported, exiting program.");
			TicTacToe.exit(true);
		} else {
			System.out.println("Starting GUI.");
			// Run Swing in it's own thread.
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					frame = new TicTacToeFrame();
				}
			});
		}

	}

	public static SwingManager getInstance() {
		return INSTANCE;
	}

	/** Temporary method while preparing to create API for entire TicTacToeFrame
	 * from this class. */
	public TicTacToeFrame getFrame() {
		return frame;
	}
}
