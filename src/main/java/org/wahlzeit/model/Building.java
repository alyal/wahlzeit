package org.wahlzeit.model;

/**
 * Class representing a building and its properties
 *
 */
public class Building {

	private int constructionYear;
	private String name;
	private Location location; 

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
	 * @param year
	 * @param name
	 * @param location
	 */
	public Building(int year, String name, Location location) {
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
		this.location = location;
	}
}
