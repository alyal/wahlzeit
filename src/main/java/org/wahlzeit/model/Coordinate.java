package org.wahlzeit.model;

public interface Coordinate {

	/**
	 * transforms a coordinate into Cartesian coordinate representation
	 */
	public CartesianCoordinate asCartesianCoordinate();

	/**
	 * Returns the distance between a Cartesian coordinate and another coordinate
	 * (spherical or cartesian)
	 */
	public double getCartesianDistance(Coordinate cor);

	/**
	 *  transforms a coordinate into spherical Coordinate representation
	 */
	public SphericCoordinate asSphericCoordinate();

	/**
	 * Returns the distance between this spherical coordinate and another coordinate
	 * (spherical or cartesian)
	 */
	public double getSphericDistance(Coordinate cor);

	/**
	 * Returns distance between this and another Coordinate
	 */
	public double getDistance(Coordinate cor);

	/**
	 * Checks if a Coordinate is equal to another one
	 */
	public boolean isEqual(Coordinate cor);

}
