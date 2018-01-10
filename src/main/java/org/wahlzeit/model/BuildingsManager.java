package org.wahlzeit.model;

import java.util.HashMap;
import java.util.Map;

import org.wahlzeit.utils.AssertionUtils;

/**
 * A BuildingsManager to manage Buildings & BuildingsType
 *
 */
public class BuildingsManager {

	/**
	 * 
	 */
	private static final BuildingsManager buildingsManagerInstance = new BuildingsManager();

	/**
	 * 
	 */
	private static final Map<String, BuildingsType> buildingsTypeChache = new HashMap<String, BuildingsType>();

	/**
	 * 
	 */
	private BuildingsManager() {

	}

	/**
	 * 
	 * @return
	 */
	public BuildingsManager getBuildingsManagerInstance() {
		return buildingsManagerInstance;
	}

	/**
	 * 
	 * @param buildingsType
	 * @return
	 */
	public Building createBuilding(String buildingsType) {
		AssertionUtils.assertNotNull(buildingsType);
		BuildingsType type = getBuildingsType(buildingsType);
		Building building = type.createInstance();
		return building;
	}

	/**
	 * 
	 * @param year
	 * @param name
	 * @param location
	 * @param buildingsType
	 * @return
	 */
	public Building createBuilding(int year, String name, Location location, String buildingsType) {
		AssertionUtils.assertNotNull(location);
		AssertionUtils.assertNotNull(name);
		AssertionUtils.assertNotNull(buildingsType);
		AssertionUtils.assertNotNull(year);
		BuildingsType type = getBuildingsType(buildingsType);
		Building building = type.createInstance(year, name, location);
		return building;
	}

	/**
	 * 
	 * @param buildingsType
	 * @return
	 */
	public static BuildingsType getBuildingsType(String buildingsType) {
		boolean typeAlreadyExists = checkExistence(buildingsType);

		if (typeAlreadyExists) {
			return buildingsTypeChache.get(buildingsType);
		}
		BuildingsType newBuildingsType = new BuildingsType(buildingsType);
		buildingsTypeChache.put(buildingsType, newBuildingsType);
		return newBuildingsType;
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	private static boolean checkExistence(String key) {
		boolean coordinateExists = buildingsTypeChache.containsKey(key);
		return coordinateExists;
	}

}
