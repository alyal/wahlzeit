package org.wahlzeit.model;

public class SphericCoordinate implements Coordinate {

	private double latitude = 0.0;
	private double longitude = 0.0;
	private double radius = 0.0;

	/**
	 * 
	 */
	public SphericCoordinate() {

	}

	/**
	 * 
	 */
	public SphericCoordinate(double radius, double latitude, double longitude) {
		this.radius = radius;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * 
	 */
	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		return computeCartesian();
	}

	/**
	 * 
	 */
	@Override
	public double getCartesianDistance(Coordinate cor) {
		CartesianCoordinate asCartesian = this.asCartesianCoordinate();
		return asCartesian.getDistance(cor);
	}

	/**
	 * 
	 */
	@Override
	public SphericCoordinate asSphericCoordinate() {
		return this;
	}

	/**
	 * 
	 */
	@Override
	public double getSphericDistance(Coordinate cor) {
		return this.getDistance(cor);
	}

	/**
	 * 
	 */
	@Override
	public double getDistance(Coordinate cor) {
		SphericCoordinate spericCor = cor.asSphericCoordinate();

		double a = Math.pow(this.getRadius(), 2);
		double b = Math.pow(spericCor.getRadius(), 2);
		
		// TODO: Make this nicer, maybe move to a own method: 
		double c = 2 * a * b * (Math.cos(Math.toRadians(this.getLatitude())) * Math.cos(Math.toRadians(
				spericCor.getLatitude() * Math.cos(Math.toRadians(this.longitude - spericCor.getLongitude())))));
		double d = Math.sin(this.latitude) * Math.sin(spericCor.getLatitude());

		return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2) - c * (c + d));
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

		toLongBits = Double.doubleToLongBits(latitude);
		c = (int) (toLongBits ^ (toLongBits >>> 32));
		result = 31 * result + c;

		toLongBits = Double.doubleToLongBits(longitude);
		c = (int) (toLongBits ^ (toLongBits >>> 32));
		result = 31 * result + c;

		toLongBits = Double.doubleToLongBits(radius);
		c = (int) (toLongBits ^ (toLongBits >>> 32));
		result = 31 * result + c;

		return result;
	}

	/**
	 * 
	 */
	@Override
	public boolean isEqual(Coordinate coordinate) {
		final double delta = 0.0000001;

		SphericCoordinate asSpheric = coordinate.asSphericCoordinate();

		if (Math.abs(this.latitude - asSpheric.getLatitude()) <= delta) {

			if (Math.abs(this.longitude - asSpheric.getLongitude()) <= delta) {

				if (Math.abs(this.radius - asSpheric.getRadius()) <= delta) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 
	 */
	private CartesianCoordinate computeCartesian() {
		final double sinPhi = Math.sin(Math.toRadians(this.latitude));
		final double sinTheta = Math.sin(Math.toRadians(this.longitude));
		final double cosPhi = Math.cos(Math.toRadians(this.latitude));
		final double cosTheta = Math.cos(Math.toRadians(this.longitude));
		
		double x = calculateX(radius, sinTheta, cosPhi);
		double y = calculateY(radius, sinTheta, sinPhi);
		double z = calculateZ(radius, cosTheta);

		return new CartesianCoordinate(x, y, z);
	}

	/**
	 * 
	 */
	private double calculateX(double radius, double sinTheta, double cosPhi) {
		return radius * sinTheta * cosPhi;
	};

	/**
	 * 
	 */
	private double calculateY(double radius, double sinTheta, double sinPhi) {
		return radius * sinTheta * sinPhi;
	};

	/**
	 * 
	 */
	private double calculateZ(double radius, double cosTheta) {
		return radius * cosTheta;
	};

	/**
	 * 
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * 
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * 
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * 
	 */
	public void setRadius(double r) {
		this.radius = r;
	}

	/**
	 * 
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * 
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

}
