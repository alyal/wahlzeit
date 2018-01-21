/*
 * Copyright (c) 2017 by alyal, https://github.com/alyal
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

import org.joda.time.LocalDate;
import org.wahlzeit.utils.AssertionUtils;
import org.wahlzeit.utils.ParamsUtil;

import com.googlecode.objectify.annotation.Entity;

/**
 * Class representing a building and its properties
 *
 */
@Entity
public class Building {

	private int constructionYear;
	private String name;
	private Location location;
	private final String className = this.getClass().getSimpleName();
	protected BuildingsType buildingsType;

	/**
	 * As I am not sure if buildings really have an ID, I generate an own String to
	 * have a key for storing it in the HashMap of BuildingsManager
	 */
	private String buildingsID;

	/**
	 * constructor
	 * 
	 * @param year
	 * @param name
	 * @param location
	 * @param buildingsType
	 */
	public Building(int year, String name, Location location, BuildingsType buildingsType) {
		AssertionUtils.assertNotNull(year, className);
		AssertionUtils.assertNotNull(name, className);
		AssertionUtils.assertNotNull(location, className);
		AssertionUtils.assertNotNull(buildingsType, className);
		this.constructionYear = year;
		this.name = name;
		this.location = location;
		this.buildingsType = buildingsType;
		this.buildingsID = ParamsUtil.createBuildingsID(year, name, location, buildingsType);
	}

	/**
	 * @methodtype get
	 * @methodproperty primitive
	 */
	public int getConstructionYear() {
		return constructionYear;
	}

	/**
	 * @methodtype set
	 * @methodproperty primitive
	 */
	public void setConstructionYear(int year) {
		AssertionUtils.assertNotNull(year, className);
		assertValidYear(year);
		this.constructionYear = year;
	}

	/**
	 * @methodtype get
	 * @methodproperty primitive
	 */
	public String getName() {
		return name;
	}

	/**
	 * @methodtype set
	 * @methodproperty primitive
	 */
	public void setName(String name) {
		AssertionUtils.assertNotNull(name, className);
		this.name = name;
	}

	/**
	 * @methodtype get
	 * @methodproperty primitive
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @methodtype set
	 * @methodproperty primitive
	 */
	public void setLocation(Location location) {
		AssertionUtils.assertNotNull(location, className);
		this.location = location;
	}

	/**
	 * @methodtype get
	 * @methodproperty primitive
	 */
	public String getID() {
		return buildingsID;
	}

	/**
	 * @methodtype set
	 * @methodproperty primitive
	 */
	public void setID(String iD) {
		AssertionUtils.assertNotNull(iD, className);
		this.buildingsID = iD;
	}

	/**
	 * @methodtype get
	 * @methodproperty primitive
	 */
	public BuildingsType getTyp() {
		return this.buildingsType;
	}

	/**
	 * @methodtype get
	 * @methodproperty primitive
	 */
	public boolean isSubtype(Building obj) {
		return obj.getTyp() instanceof BuildingsType;
	}

	/**
	 * @methodtype assertion
	 * @param year
	 */
	private void assertValidYear(int year) {
		LocalDate today = new LocalDate();
		if (year > today.getYear()) {
			throw new IllegalArgumentException("The passed construction year is in the future!");
		}
	}
}
