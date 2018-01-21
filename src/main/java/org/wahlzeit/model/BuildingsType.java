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

import java.util.HashSet;
import java.util.Set;

import org.wahlzeit.utils.AssertionUtils;

import com.googlecode.objectify.annotation.Entity;

@Entity
public class BuildingsType {

	/**
	 * The name of the buildingsType (e.g. skyscraper, church, etc.)
	 */
	private String typeName;

	/**
	 * Describes the architecture of the building (e.g. renaissance, postmodernism,
	 * baroque, etc )
	 */
	private String architecture;

	/**
	 * The supertype of this BuidlingsType and its potential subtypes
	 */
	protected BuildingsType superType;

	/**
	 * Set of buildingsTypes representing the Type hierarchy
	 */
	private Set<BuildingsType> buildingsSubTypes = new HashSet<BuildingsType>();

	/**
	 * Constructor
	 * 
	 * @param name
	 */
	public BuildingsType(String name, String architecture) {
		this.typeName = name;
		this.architecture = architecture;
	}

	/**
	 * Creates a Buildings instance of this BuildingsType and the given parameters
	 * 
	 * @param year
	 * @param name
	 * @param location
	 * @return
	 */
	public Building createInstance(int year, String name, Location location) {
		return new Building(year, name, location, this);
	}

	/**
	 * Returns the Buildingstypes name
	 * 
	 * @return
	 */
	public String getName() {
		return typeName;
	}

	/**
	 * Sets the BuildingsType name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		AssertionUtils.assertNotNull(name);
		AssertionUtils.assertStringNotEmpty(name);
		this.typeName = name;
	}

	/**
	 * Returns the BuildingsTypes architecture
	 * 
	 * @return architecture
	 */
	public String getArchitecture() {
		return architecture;
	}

	/**
	 * Sets the BuildingsTypes architecture
	 * 
	 * @param architectureName
	 */
	public void setArchitecture(String architectureName) {
		AssertionUtils.assertNotNull(architectureName);
		AssertionUtils.assertStringNotEmpty(architectureName);
		this.architecture = architectureName;
	}

	/**
	 * Sets the BuildingsTypes supertype
	 * 
	 * @param type
	 */
	private void setSuperType(BuildingsType type) {
		AssertionUtils.assertNotNull(type);
		this.superType = type;
	}

	/**
	 * Returns the BuildingsTypes supertype
	 * 
	 * @return
	 */
	public BuildingsType getSuperType() {
		return superType;
	}

	/**
	 * Adds a new BuildingsType to the buildingsTypSet tree as a new subtype. The
	 * new added subtype is the than the supertype of all its underlying subtypes.
	 * 
	 * @param buidlingsSubType
	 */
	public void addBuildingsSubType(BuildingsType buidlingsSubType) {
		AssertionUtils.assertNotNull(buidlingsSubType);
		boolean alreadyExists = checkExistence(buidlingsSubType);
		if (!alreadyExists) {
			buidlingsSubType.setSuperType(this);
			buildingsSubTypes.add(buidlingsSubType);
		}
	}

	/**
	 * Checks if the type already exists.
	 * 
	 * @param type
	 * @return
	 */
	public boolean checkExistence(BuildingsType type) {
		AssertionUtils.assertNotNull(type);
		boolean isSubtype = buildingsSubTypes.contains(type);
		return isSubtype;
	}

	// Should maybe be renamed to hasSubtype
	/**
	 * Checks recursively if a given BuildingsType is of the same type or if it does
	 * exist within the subtree. If the type exists true is returned, otherwise
	 * false is returned
	 * 
	 * @param building
	 * @return boolean
	 */
	public boolean isSubtype(BuildingsType type) {
		AssertionUtils.assertNotNull(type);
		if (type.equals(this)) {
			return true;
		}
		for (BuildingsType nextType : buildingsSubTypes) {
			if (nextType.isSubtype(type)) {
				return true;
			}
		}
		return false;

	}

}
