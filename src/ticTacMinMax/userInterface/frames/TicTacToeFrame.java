package ticTacMinMax.userInterface.frames;

import java.awt.HeadlessException;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import ticTacMinMax.TicTacToe;
import ticTacMinMax.userInterface.contentPanes.TicTacToePane;

// TODO Remove suppress warning.
@SuppressWarnings("serial")
public class TicTacToeFrame extends JFrame {
	private static final TicTacToeFrame INSTANCE = new TicTacToeFrame();

	private static TicTacToePane contentPane;

	private TicTacToeFrame() throws HeadlessException {
		// Create Frame with title.
		super("Tic Tac Toe");

		// TODO Make size configurable
		setSize(500, 500);
		setResizable(false);

		// The Window listener stop the program when the window is disposed.
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		WindowCloser theWatcher = new WindowCloser();
		addWindowListener(theWatcher);

		// Uses a separate class for the content pane.
		contentPane = TicTacToePane.getInstance();
		setContentPane(contentPane);

		setVisible(true);
	}

	public static TicTacToeFrame getInstance() {
		return INSTANCE;
	}
}

/**
 * Separate Class to handle window closing.
 */
class WindowCloser implements WindowListener {

	protected WindowCloser() {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
		TicTacToe.exit(false);
	}

	@Override
	public void windowClosing(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

}
