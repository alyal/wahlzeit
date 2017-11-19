package org.wahlzeit.model;

public class CartesianCoordinate implements Coordinate {

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

	/**
	 * 
	 */
	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		return this;
	}

	/**
	 * returns the distance between this Cartesian Coordinate and another Coordinate
	 * (spherical or cartesian)
	 */
	@Override
	public double getCartesianDistance(Coordinate coordinate) {
		if (coordinate == null) {
			throw new IllegalArgumentException("null as an argument is not allowed!");
		}
		return this.getDistance(coordinate);

	}

	/**
	 * Calculates the Distance between two Cartesian coordinates
	 */
	@Override
	public double getDistance(Coordinate cor) {
		if (cor == null) {
			throw new IllegalArgumentException("null as an argument is not allowed!");
		}
		CartesianCoordinate cartesianCor = cor.asCartesianCoordinate();
		double deltaX = x - cartesianCor.getXCoordinate();
		double deltaY = y - cartesianCor.getYCoordinate();
		double deltaZ = z - cartesianCor.getZCoordinate();
		return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2) + Math.pow(deltaZ, 2));
	}

	/**
	 * Calculates the spherical distance between this coordinate and another
	 * coordinate by transforming this Cartesian coordinate to a Spherical
	 * coordinate and pass it to the getDistance method of SphericCoordinate class
	 */
	@Override
	public double getSphericDistance(Coordinate cor) {
		SphericCoordinate asSpheric = this.asSphericCoordinate();
		return asSpheric.getDistance(cor);
	}

	/**
	 * Converts this Cartesian coordinate in a spherical coordinate representation
	 * 
	 * @methodtype conversion
	 */
	@Override
	public SphericCoordinate asSphericCoordinate() {
		return computeSpehric();
	}

	/**
	 * @methodtype conversion
	 * @methodproperty composed
	 */
	private SphericCoordinate computeSpehric() {
		double radius = calculateRadius();
		if (radius != 0.0) {
			// latitude
			double phi = calculatePhi();
			// longitude
			double theta = calculateTheta(radius);

			return new SphericCoordinate(radius, phi, theta);
		}
		return new SphericCoordinate(6378.00, 0.0, 0.0);
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
	 * Forwards coordinates to isEqual()-method after checking if argument is an
	 * instance of coordinate
	 * 
	 * @param obj
	 *            a Coordinate class Object
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Coordinate)) {
			throw new IllegalArgumentException("null as an argument is not allowed!");
		}
		return this.isEqual((Coordinate) obj);
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
		final double delta = 0.0000001;

		CartesianCoordinate asCartesian = cor.asCartesianCoordinate();

		if (Math.abs(x - asCartesian.getXCoordinate()) <= delta) {

			if (Math.abs(y - asCartesian.getYCoordinate()) <= delta) {

				if (Math.abs(z - asCartesian.getZCoordinate()) <= delta) {
					return true;
				}
			}
		}
		return false;
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

}
