package ticTacMinMax.files;

import java.io.File;

// TODO Remove suppress warning.
@SuppressWarnings("serial")
public class GameFolder extends File {

	public GameFolder() {
		super(System.getProperties().getProperty("user.home"));
	}

	public static void removeAllGameFiles() {
		// TODO Write remove files method
	}

}
