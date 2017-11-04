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

	public void setXCoordinate(double xCoordinate) {
		this.x = xCoordinate;
	}

	public void setYCoordinate(double yCoordinate) {
		this.y = yCoordinate;
	}

	public void setZCoordinate(double zCoordinate) {
		this.z = zCoordinate;
	}

	/**
	 * Calculates the distance between two coordinates
	 * 
	 * @param coordinate
	 *            the coordinate we want to calculate the distance to
	 * @return distance between both coordinates
	 */
	public double getDistance(Coordinate coordinate) {
		if (coordinate == null) {
			throw new IllegalArgumentException("null as an argument is not allowed!");
		}
		double deltaX = x - coordinate.getXCoordinate();
		double deltaY = y - coordinate.getYCoordinate();
		double deltaZ = z - coordinate.getZCoordinate();
		return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2) + Math.pow(deltaZ, 2));
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
	 * Checks if coordinates are equal
	 * 
	 * @param coordinate
	 * @return boolean
	 */
	public boolean isEqual(Coordinate coordinate) {
		final double delta = 0.0000001;

		if (Math.abs(x - coordinate.getXCoordinate()) <= delta) {

			if (Math.abs(y - coordinate.getYCoordinate()) <= delta) {

				if (Math.abs(z - coordinate.getZCoordinate()) <= delta) {
					return true;
				}
			}
		}
		return false;
	}

}
