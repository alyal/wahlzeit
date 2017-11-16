package org.wahlzeit.model;

public interface ICoordinate {

	/**
	 * 
	 */
	public CartesianCoordinate asCartesianCoordinate();

	/**
	 * 
	 */
	public double getCartesianDistance(Coordinate cor);

	/**
	 * 
	 */
	public SphericCoordinate asSphericCoordinate();

	/**
	 * 
	 */
	public double getSphericDistance(Coordinate cor);

	/**
	 * 
	 */
	public double getDistance(Coordinate cor);

	/**
	 * 
	 */
	public boolean isEqual(Coordinate cor);

}
