package org.wahlzeit.utils;

import java.util.logging.Logger;

import org.wahlzeit.model.PhotoId;

public class AssertionUtils {

	private static final Logger log = Logger.getLogger(AssertionUtils.class.getName());

	private AssertionUtils() {

	}

	/**
	 * @methodtype assertion
	 */
	public static void assertNotNull(Object o) {
		if (o == null) {
			log.warning("null as an argument is not allowed!");
			throw new IllegalArgumentException("null as an argument is not allowed!");
		}
	}

	/**
	 * @methodtype assertion
	 */
	public static void assertNotNull(Object o, String className) {
		if (o == null) {
			log.warning("Error in: " + className + "! Null as an argument is not allowed!");
			throw new IllegalArgumentException("null as an argument is not allowed!");
		}
	}
	
	/**
	 * @throws IllegalArgumentException
	 * @mthodtype: assertion
	 */
	public static void assertInstanceOfPhotoId(Object o) throws IllegalArgumentException {
		if (!(o instanceof PhotoId)) {
			log.warning("Passed argument is not an instance of PhotoId, but was: " + o.getClass().getName());
			throw new IllegalArgumentException("Passed argument is not an instance of PhotoId!");
		}

	}

	/**
	 * @throws IllegalArgumentException
	 * @mthodtype: assertion
	 */
	public static void assertInstanceOfPhotoId(Object o, String className) throws IllegalArgumentException {
		if (!(o instanceof PhotoId)) {
			log.warning("Error in: " + className + "! Passed argument is not an instance of PhotoId, but was: "
					+ o.getClass().getName());
			throw new IllegalArgumentException("Passed argument is not an instance of PhotoId!");
		}
	}

	public static void assertStringNotEmpty(String s) {
		if (s.isEmpty()) {
			throw new IllegalArgumentException("Passed String is empty. Empty Stings are not allowed!");
		}
	}

}
