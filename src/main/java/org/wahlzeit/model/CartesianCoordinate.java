package org.wahlzeit.model;

import org.wahlzeit.utils.ParamsUtil;

public class CartesianCoordinate extends AbstractCoordinate {

	private double x = 1.0;
	private double y = 1.0;
	private double z = 1.0;

	/**
	 * @methodtype constructor
	 */
	public CartesianCoordinate() {

	}

	/**
	 * @methodtype constructor
	 */
	public CartesianCoordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	// Getters

	/**
	 * 
	 */
	public double getXCoordinate() {
		return x;
	}

	/**
	 * 
	 */
	public double getYCoordinate() {
		return y;
	}

	/**
	 * 
	 */
	public double getZCoordinate() {
		return z;
	}

	// SETTERS:

	/**
	 * 
	 */
	public void setXCoordinate(double x) {
		this.x = x;
	}

	/**
	 * 
	 */
	public void setYCoordinate(double y) {
		this.y = y;
	}

	/**
	 * 
	 */
	public void setZCoordinate(double z) {
		this.z = z;
	}

	/**
	 * 
	 */
	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		return this;
	}

	/**
	 * 
	 */
	@Override
	public double getDistance(Coordinate cor) {
		assertNotNull(cor);
		return getCartesianDistance(cor);
	}

	@Override
	public double calculateDistance(Coordinate cor) {
		assertNotNull(cor);
		CartesianCoordinate cartesianCor = cor.asCartesianCoordinate();
		double deltaX = x - cartesianCor.getXCoordinate();
		double deltaY = y - cartesianCor.getYCoordinate();
		double deltaZ = z - cartesianCor.getZCoordinate();
		return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2) + Math.pow(deltaZ, 2));
	}

	/**
	 * Converts this Cartesian coordinate in a spherical coordinate representation
	 * 
	 * @methodtype conversion
	 * @methodproperty primitive
	 */
	@Override
	public SphericCoordinate asSphericCoordinate() {
		return computeSpheric();
	}

	/**
	 * @methodtype conversion
	 * @methodproperty composed
	 */
	private SphericCoordinate computeSpheric() {
		double radius = calculateRadius();
		if (radius != 0.0) {
			// latitude
			double phi = calculatePhi();
			// longitude
			double theta = calculateTheta(radius);

			return new SphericCoordinate(radius, phi, theta);
		}
		return new SphericCoordinate(ParamsUtil.EARTH_RADIUS, 0.0, 0.0);
	}

	/**
	 * 
	 */
	private double calculateRadius() {
		return Math.sqrt(Math.pow(this.getXCoordinate(), 2) + Math.pow(this.getYCoordinate(), 2)
				+ Math.pow(this.getZCoordinate(), 2));
	}

	/**
	 * 
	 */
	private double calculateTheta(double radius) {
		return Math.acos(this.getZCoordinate() / radius);
	}

	/**
	 * 
	 */
	private double calculatePhi() {
		return Math.atan2(this.getYCoordinate(), this.getXCoordinate());
	}

	/**
	 * When override equals-method, it is necessary to override hashCode. (E.g. see
	 * Item 9 in EffectiveJava by Joshua Bloch (3. Edition, 2005))
	 */
	@Override
	public int hashCode() {
		int result = 17;
		long toLongBits;
		int c; // int hash code for field

		toLongBits = Double.doubleToLongBits(x);
		c = (int) (toLongBits ^ (toLongBits >>> 32));
		result = 31 * result + c;

		toLongBits = Double.doubleToLongBits(y);
		c = (int) (toLongBits ^ (toLongBits >>> 32));
		result = 31 * result + c;

		toLongBits = Double.doubleToLongBits(z);
		c = (int) (toLongBits ^ (toLongBits >>> 32));
		result = 31 * result + c;

		return result;
	}

	/**
	 * 
	 */
	@Override
	public boolean isEqual(Coordinate cor) {
		assertNotNull(cor);
		CartesianCoordinate asCartesian = cor.asCartesianCoordinate();

		if (Math.abs(x - asCartesian.getXCoordinate()) <= ParamsUtil.DELTA) {

			if (Math.abs(y - asCartesian.getYCoordinate()) <= ParamsUtil.DELTA) {

				if (Math.abs(z - asCartesian.getZCoordinate()) <= ParamsUtil.DELTA) {
					return true;
				}
			}
		}
		return false;
	}

}
