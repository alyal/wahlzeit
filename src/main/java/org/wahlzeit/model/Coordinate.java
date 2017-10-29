package org.wahlzeit.model;

/**
 * Class for Coordinates. Coordinates are part of {@Location}. This class
 * contains methods that can be executed on a coordinate Object
 *
 */
public class Coordinate {

	private double x;
	private double y;
	private double z;

	public Coordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	// GETTERS and SETTERS

	public double getXCoordinate() {
		return x;
	}

	public double getYCoordinate() {
		return y;
	}

	public double getZCoordinate() {
		return z;
	}

	public void setXCoordinate(double newXCoordinate) {
		this.x = newXCoordinate;
	}

	public void setYCoordinate(double newYCoordinate) {
		this.y = newYCoordinate;
	}

	public void setZCoordinate(double newZCoordinate) {
		this.z = newZCoordinate;
	}

	/**
	 * Calculates the distance between two coordinates
	 * 
	 * @param coordinate
	 *            the coordinate we want to calculate the distance to
	 * @return distance between both coordinates
	 */
	public double getDistance(Coordinate coordinate) {
		if (coordinate != null) {
			double deltaX = x - coordinate.x;
			double deltaY = y - coordinate.y;
			double deltaZ = z - coordinate.z;
			return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2) + Math.pow(deltaZ, 2));
		} else {
			throw new IllegalArgumentException("null as an argument is not allowed!");
		}

	}

	/**
	 * Forwards coordinates to isEqual()-method after checking if argument is not
	 * null
	 * 
	 * @param coordinate
	 * @return boolean
	 */
	public boolean equals(Coordinate coordinate) {
		if (coordinate != null) {
			return this.isEqual(coordinate);
		} else {
			throw new IllegalArgumentException("null as an argument is not allowed!");
		}
	}

	/**
	 * Checks if coordinates are equal
	 * 
	 * @param coordinate
	 * @return boolean
	 */
	public boolean isEqual(Coordinate coordinate) {
		if (x == coordinate.x && y == coordinate.y && z == coordinate.z) {
			return true;
		}
		return false;
	}

}
