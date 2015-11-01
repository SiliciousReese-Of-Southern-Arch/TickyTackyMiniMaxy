package ticTacMinMax.stream.files;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
	private Properties properties;

	public Configuration(InputStream defaultConfig, InputStream userConfig)
			throws IOException {
		Properties defaults = new Properties();
		// Initialize properties with default values then write over then with
		// the config file.
		defaults.load(defaultConfig);
		properties = new Properties(defaults);
		properties.load(userConfig);
	}

	/**
	 * @param propertyName
	 *            Key value in config file.
	 * @return The property as defined in the config file. If the property is
	 *         not found this will return null.
	 */
	public String getProperty(String propertyName) {
		return properties.getProperty(propertyName);
	}

	/**
	 * 
	 * @return
	 */
	public boolean isValid() {
		// TODO Program config validity check.
		boolean isValid = true;

		return isValid;
	}
}
