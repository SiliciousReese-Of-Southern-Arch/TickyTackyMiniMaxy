package ticTacMinMax.files;

import java.io.File;
import java.util.Properties;

// TODO remove suppress warning
@SuppressWarnings("serial")
public class Configuration extends Properties {
	private static final Configuration INSTANCE = new Configuration();

	// The location of the default configuration file. This line would be so
	// much shorter if Windows was POSIX compliant :(.
	// TODO use location
	@SuppressWarnings("unused")
	private static final String DEFAULT_CONFIG_LOCATION =
			("res" + File.separator + "DefaultConfig.txt");

	// TODO use resource stream for jar
	// TODO The default should be copied to a users home directory
	// TODO Not sure if this is the best way to get the location of the
	// config file...
	// private static File defaultConfig =
	// new File(TicTacToe.class.getClass().getClassLoader()
	// .getResource(DEFAULT_CONFIG_LOCATION).getFile());
	// // TODO Create and use a game folder.
	// private static File config =
	// new File(System.getProperty("user.home") + File.separator
	// + "ticConf.txt");

	// These will be removed when this class is ready.
	@Deprecated
	public static boolean IS_PLAYER_ONE_HUMAN = true;
	@Deprecated
	public static boolean IS_PLAYER_TWO_HUMAN = false;
	@Deprecated
	public static boolean IS_THREE_DIMENSIONAL = false;
	@Deprecated
	public static int MAX_SEARCH_DEPTH = 10;
	@Deprecated
	public static int BOARD_LENGTH = 3;
	@Deprecated
	public static long MAX_SEARCH_TIME = 40000;

	private Configuration() {
		// Call the Properties constructor with a default properties list.
		super(getDefaults());

		// TODO Attempt to recover from errors.
		// TODO Rename stream variable.
		// try {
		// FileInputStream stream = new FileInputStream(config);
		// load(stream);
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// } catch (IOException e) {
		// e.printStackTrace();
		// } catch (SecurityException e) {
		// e.printStackTrace();
		// } catch (IllegalArgumentException e) {
		// e.printStackTrace();
		// }
	}

	public static Configuration getInstance() {
		return INSTANCE;
	}

	private static Properties getDefaults() {
		Properties defaultProperties = new Properties();

		// TODO Attempt to recover from errors.
		// TODO Rename stream variable.
		// try {
		// FileInputStream stream = new FileInputStream(defaultConfig);
		// defaultProperties.load(stream);
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// } catch (IOException e) {
		// e.printStackTrace();
		// } catch (SecurityException e) {
		// e.printStackTrace();
		// } catch (IllegalArgumentException e) {
		// e.printStackTrace();
		// }

		return defaultProperties;
	}

	// Methods to get game variables.
	// TODO Use get method from properties class.
	public static boolean isPlayerOneHuman() {
		return IS_PLAYER_ONE_HUMAN;
	}

	public static boolean isPlayerTwoHuman() {
		return IS_PLAYER_TWO_HUMAN;
	}

	public static boolean isThreeDimensional() {
		return IS_THREE_DIMENSIONAL;
	}

	public static int getMaxSearchDepth() {
		return MAX_SEARCH_DEPTH;
	}

	public static int getBoardLength() {
		return BOARD_LENGTH;
	}

	public static long getMaxSearchTime() {
		return MAX_SEARCH_TIME;
	}

}
