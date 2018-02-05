/*
 * Copyright (c) 2018 by alyal, https://github.com/alyal
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package org.wahlzeit.model;

import java.util.HashMap;
import java.util.Map;

import org.wahlzeit.exceptions.BuildingsTypeDoesNotExistException;
import org.wahlzeit.utils.AssertionUtils;
import org.wahlzeit.utils.ParamsUtil;

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
	 * A Map for storing BuildingsTypes.
	 */
	private static final Map<String, BuildingsType> buildingsTypeChache = new HashMap<String, BuildingsType>();

	/**
	 * A Map for storing Buildings.
	 */
	private static final Map<String, Building> buildingsChache = new HashMap<String, Building>();

	/**
	 * 
	 */
	private BuildingsManager() {

	}

	/**
	 * Returns the singleton instance of the BuildingsManager
	 * 
	 */
	public static BuildingsManager getBuildingsManagerInstance() {
		return buildingsManagerInstance;
	}

	/**
	 * Creates a building with a buildingsType
	 * 
	 * @param year
	 * @param name
	 * @param location
	 * @param buildingsType
	 */
	public synchronized Building createBuilding(int year, String name, Location location, String buildingsType) {
		AssertionUtils.assertNotNull(location);
		AssertionUtils.assertNotNull(name);
		AssertionUtils.assertNotNull(buildingsType);
		AssertionUtils.assertNotNull(year);
		BuildingsType type = getBuildingsType(buildingsType);
		Building building = getBuilding(year, name, location, type);
		return building;
	}

	/**
	 * Creates a buildingsType if it does not exist already and adds it to the
	 * BuildingstypeCache
	 * 
	 * @param typeName
	 * @param architectureName
	 */
	public BuildingsType createBuildingsType(String typeName, String architectureName) {
		AssertionUtils.assertNotNull(architectureName);
		AssertionUtils.assertNotNull(typeName);
		AssertionUtils.assertStringNotEmpty(architectureName);
		AssertionUtils.assertStringNotEmpty(typeName);

		boolean typeAlreadyExists = checkExistence(typeName);
		if (typeAlreadyExists) {
			return buildingsTypeChache.get(typeName);
		} else {
			synchronized (buildingsTypeChache) {
				BuildingsType newBuildingsType = new BuildingsType(typeName, architectureName);
				buildingsTypeChache.put(typeName, newBuildingsType);
				return newBuildingsType;
			}
		}
	}

	/**
	 * Returns the buildingsType. If it does not exist, a
	 * BuildingsTypeDoesNotExistException is thrown
	 * 
	 * @param buildingsType
	 * @throws BuildingsTypeDoesNotExistException
	 */
	public static BuildingsType getBuildingsType(String buildingsType) throws BuildingsTypeDoesNotExistException {
		boolean typeExists = checkExistence(buildingsType);
		if (typeExists) {
			return buildingsTypeChache.get(buildingsType);
		} else {
			throw new BuildingsTypeDoesNotExistException();
		}
	}

	/**
	 * Returns the Building matching to the created BuildingsID. If it does not
	 * exist, a new building is stored in the buildingsChache under the generated
	 * BuildingsID.
	 * 
	 * @param year
	 * @param name
	 * @param location
	 * @param buildingsType
	 */
	public static Building getBuilding(int year, String name, Location location, BuildingsType buildingsType) {
		String key = ParamsUtil.createBuildingsID(year, name, location, buildingsType);
		boolean buildingExists = checkBuildingsExistence(key);
		if (buildingExists) {
			return buildingsChache.get(key);
		} else {
			synchronized (buildingsChache) {
				Building building = buildingsType.createInstance(year, name, location);
				buildingsChache.put(key, building);
				return building;
			}
		}
	}

	/**
	 * Checks if the type already exists.
	 * 
	 * @param key
	 */
	private static boolean checkExistence(String key) {
		boolean typeExists = buildingsTypeChache.containsKey(key);
		return typeExists;
	}

	/**
	 * Checks if the Building already exists
	 * 
	 * @param key
	 * 
	 */
	private static boolean checkBuildingsExistence(String key) {
		boolean buildingExisits = buildingsChache.containsKey(key);
		return buildingExisits;
	}

}
