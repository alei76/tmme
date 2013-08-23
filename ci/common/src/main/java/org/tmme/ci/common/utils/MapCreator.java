package org.tmme.ci.common.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang3.Validate;

public class MapCreator {

	private MapCreator() {
		// avoid instantiation
	}

	public static Map<String, String> createMap(final String args) {
		Validate.notBlank(args);
		final Map<String, String> map = new HashMap<String, String>();
		Scanner scanner = null;
		try {
			scanner = new Scanner(args);
			scanner.useDelimiter(",");
			while (scanner.hasNext()) {
				final String nameValue = scanner.next();
				final String[] nameValuePair = nameValue.split(":");
				map.put(nameValuePair[0].trim(), nameValuePair[1].trim());
			}
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
		return map;
	}
}
