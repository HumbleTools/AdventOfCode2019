package com.lma.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Utils {

	public static Stream<String> getStringsInput(final String filePath) {
		Path path = null;
		try {
			path = Paths.get(Utils.class.getClassLoader()
					.getResource(filePath).toURI());
			return Files.lines(path).filter(str -> !str.startsWith("###"));
		} catch (final URISyntaxException | IOException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}
}
