package org.wahlzeit.model;

import org.joda.time.LocalDate;
import org.wahlzeit.utils.AssertionUtils;

/**
 * Class representing a building and its properties
 *
 */
public class Building {

	private int constructionYear;
	private String name;
	private Location location;
	private final String className = this.getClass().getSimpleName();

	/**
	 * default constructor
	 */
	public Building() {
		this.constructionYear = 1900;
		this.name = "Unknown";
		this.location = new Location(new CartesianCoordinate(1.0, 1.0, 1.0));
	}

	/**
	 * constructor
	 * 
	 * @param year
	 * @param name
	 * @param location
	 */
	public Building(int year, String name, Location location) {
		AssertionUtils.assertNotNull(year, className);
		AssertionUtils.assertNotNull(name, className);
		AssertionUtils.assertNotNull(location, className);
		this.constructionYear = year;
		this.name = name;
		this.location = location;
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
