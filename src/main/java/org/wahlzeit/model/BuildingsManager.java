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
	 * Creates a default building with a buildingsType
	 * 
	 * @param buildingsType
	 * @return
	 */
	public synchronized Building createBuilding(String buildingsType) {
		AssertionUtils.assertNotNull(buildingsType);
		BuildingsType type = getBuildingsType(buildingsType);
		Building building = type.createInstance();
		return building;
	}

	/**
	 * Creates a building with a buildingsType
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
	 * Creates a buildingsType if it does not exist already and adds it to the
	 * BuildingstypeCache
	 * 
	 * @param typeName
	 * @param architectureName
	 * @return
	 */
	public BuildingsType createBuildingsType(String typeName, String architectureName) {
		AssertionUtils.assertNotNull(architectureName);
		AssertionUtils.assertNotNull(typeName);
		AssertionUtils.assertStringNotEmpty(architectureName);
		AssertionUtils.assertStringNotEmpty(typeName);

		boolean typeAlreadyExists = checkExistence(typeName);
		if (!typeAlreadyExists) {
			BuildingsType newBuildingsType = new BuildingsType(typeName, architectureName);
			buildingsTypeChache.put(typeName, newBuildingsType);
			return newBuildingsType;
		} else {
			return buildingsTypeChache.get(typeName);
		}
	}

	/**
	 * Returns the buildingsType. If it does not exist, a
	 * BuildingsTypeDoesNotExistException is thrown
	 * 
	 * @param buildingsType
	 * @return
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
	 * Checks if the type already exists.
	 * 
	 * @param key
	 * @return
	 */
	private static boolean checkExistence(String key) {
		boolean typeExists = buildingsTypeChache.containsKey(key);
		return typeExists;
	}

}
