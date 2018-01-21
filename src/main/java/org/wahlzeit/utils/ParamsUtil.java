package org.wahlzeit.utils;

import org.wahlzeit.model.BuildingsType;
import org.wahlzeit.model.Location;

public final class ParamsUtil {

	public static final double EARTH_RADIUS = 6378.00; // in kilometers

	public static final double DELTA = 0.0000001;

	public static final double MAX_LATITUDE = 90.00;

	public static final double MIN_LATITUDE = -90.00;

	public static final double MAX_LONGITUDE = 180.00;

	public static final double MIN_LONGITUDE = -180.00;

	private ParamsUtil() {
		// private because no instance needed
	}

	/**
	 * As I didn't know if something like a BuildingsId really exists, I create a
	 * unique String as a key here that I can use within the PhotoManager to store
	 * Buildings in a HashMap.
	 */
	public static String createBuildingsID(int year, String name, Location location, BuildingsType buildingsType) {
		return "" + year + "/" + name + "/" + buildingsType.getName() + "/" + location.asString();

	}

}
