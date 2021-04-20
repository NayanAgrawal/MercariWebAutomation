package com.onlineStore.mercari.testBase;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Read config properties file to access constant data.
 *
 * @author Nayan Agrawal
 * @version 1.0
 * @since 2021-04-21
 */
public class ReadPropertiesFile {

	private static File file = new File("Config.properties");

	private static Properties prop = new Properties();
	static {
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String propertyKey) {
		return prop.getProperty(propertyKey);
	}

}
