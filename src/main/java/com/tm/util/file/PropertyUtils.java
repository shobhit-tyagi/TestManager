package com.tm.util.file;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.tm.util.exceptions.FileLoadException;

public class PropertyUtils {

	public static Properties loadProperties(final String fileName) throws FileLoadException {
		final ClassLoader loader = Thread.currentThread().getContextClassLoader();
		final Properties properties = new Properties();
			final InputStream resourceStream = loader.getResourceAsStream(fileName);
			try {
				properties.load(resourceStream);
			} catch (final IOException e) {
				throw new FileLoadException("File '"+fileName+"' could not be loaded", e);
			}
		
		return properties;
	}
}
