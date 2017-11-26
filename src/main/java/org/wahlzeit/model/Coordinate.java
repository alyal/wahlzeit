package org.wahlzeit.model;

public interface Coordinate {

	/**
	 * transforms a coordinate into Cartesian coordinate representation
	 */
	CartesianCoordinate asCartesianCoordinate();

	/**
	 * Returns the distance between a Cartesian coordinate and another coordinate
	 * (spherical or cartesian)
	 */
	double getCartesianDistance(Coordinate cor);

	/**
	 * transforms a coordinate into spherical Coordinate representation
	 */
	SphericCoordinate asSphericCoordinate();

	/**
	 * Returns the distance between this spherical coordinate and another coordinate
	 * (spherical or cartesian)
	 */
	double getSphericDistance(Coordinate cor);

	/**
	 * Returns distance between this and another Coordinate
	 */
	double getDistance(Coordinate cor);

	/**
	 * Checks if a Coordinate is equal to another one
	 */
	boolean isEqual(Coordinate cor);
}
