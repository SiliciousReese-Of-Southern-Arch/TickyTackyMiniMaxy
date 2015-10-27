package ticTacMinMax.userInterface;

import javax.swing.SwingUtilities;

import ticTacMinMax.userInterface.frames.TicTacToeFrame;

/**
 * Handles threading of the GUI.
 */
public class SwingLoader {
	private static final SwingLoader INSTANCE = new SwingLoader();

	private SwingLoader() {
		System.out.println("Created Swing Loader.");
	}

	public static SwingLoader getInstance() {
		return INSTANCE;
	}

	/**
	 * Start the GUI.
	 */
	public void startSwing() {
		// Run Swing in it's own thread.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				System.out.println("Started Swing in separate thread");
				TicTacToeFrame.getInstance();
			}
		});
	}
}
