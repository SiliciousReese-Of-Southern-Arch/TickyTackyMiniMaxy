package ticTacMinMax.stream.files;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
	private Properties properties;

	public Configuration(InputStream defaultConfig, InputStream userConfig)
			throws IOException {
		Properties defaults = new Properties();
		defaults.load(defaultConfig);

		properties = new Properties(defaults);
		properties.load(userConfig);
	}

	public String getProperty(String propertyName) {
		return properties.getProperty(propertyName);
	}

	public boolean isValid() {
		// TODO Program config validity check.
		boolean isValid = true;

		return isValid;
	}
}
