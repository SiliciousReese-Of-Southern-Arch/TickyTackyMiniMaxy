package ticTacMinMax.userInterface;

import java.awt.GraphicsEnvironment;
import javax.swing.SwingUtilities;

import ticTacMinMax.TicTacToe;
import ticTacMinMax.userInterface.frames.TicTacToeFrame;

/** Handles threading of the GUI. */
public class SwingManager {
	private static final SwingManager INSTANCE = new SwingManager();

	/* TODO Consider changing value at runtime */
	/** The time in milliseconds to wait if the frame is not ready. */
	private static final int SLEEP_WAIT_MILLIS = 600;

	private TicTacToeFrame frame;

	private SwingManager() {
		if (GraphicsEnvironment.isHeadless()) {
			System.err.println("Gui not supported, exiting program.");
			TicTacToe.exit(true);
		} else {
			System.out.println("Starting GUI.");
			/* Run Swing in it's own thread. */
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

	/**
	 * Temporary method while preparing to create API for entire TicTacToeFrame
	 * from this class.
	 */
	public TicTacToeFrame getFrame() {
		while (frame == null) {
			System.out.println("Waiting " + SLEEP_WAIT_MILLIS
					+ " milliseconds for tic-tac-toe frame.");
			try {
				Thread.sleep(SLEEP_WAIT_MILLIS);
			} catch (InterruptedException e) {
				System.err.println("Sleep exited early");
				e.printStackTrace();
			}
		}

		return frame;
	}
}
