package org.wahlzeit.model;

public class SphericCoordinate implements Coordinate {

	// TODO: Move EARTH_RADIUS to a common Utils class
	private final double EARTH_RADIUS = 6378.00; // in kilometers

	private double latitude = 0.0;
	private double longitude = 0.0;
	private double radius = EARTH_RADIUS;

	/**
	 * @methodtype constructor
	 */
	public SphericCoordinate() {

	}

	/**
	 * @methodtype constructor
	 */
	public SphericCoordinate(double radius, double latitude, double longitude) {
		if (radius < 0) {
			throw new IllegalArgumentException("Values smaller 0 for Radius are not allowed!");
		}
		if (latitude < -90.00 || latitude > 90.00) {
			throw new IllegalArgumentException("Value of latitude must be between -90.00 and 90.00");
		}
		if (longitude < -180.00 || longitude > 180.00) {
			throw new IllegalArgumentException("Value of longitude must be between -180.00 and 180.00");
		}

		this.radius = radius;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * Converts this Spherical coordinate in a Cartesian coordinate representation
	 */
	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		return computeCartesian();
	}

	/**
	 * converts this spherical coordinate to a Cartesian coordinate representation
	 * 
	 * @methodtype conversion
	 * @methodproperty composed
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
	 * Calculates the Cartesian distance between this coordinate and another
	 * coordinate by transforming this Spherical coordinate to a Cartesian
	 * coordinate and pass it to the getDistance method of CartesianCoordinate class
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
	 * returns the distance between this spherical Coordinate and another Coordinate
	 * (spherical or cartesian)
	 */
	@Override
	public double getSphericDistance(Coordinate cor) {
		return this.getDistance(cor);
	}

	/**
	 * Calculates the Distance between two spherical coordinates
	 */
	@Override
	public double getDistance(Coordinate cor) {
		SphericCoordinate spericCor = cor.asSphericCoordinate();

		double phi1 = Math.toRadians(this.getLatitude());
		double phi2 = Math.toRadians(spericCor.getLatitude());
		double deltaPhi = Math.toRadians(this.getLatitude() - spericCor.getLatitude());
		double deltaTheta = Math.toRadians(this.getLongitude() - spericCor.getLongitude());

		double a = Math.sin(deltaPhi / 2) * Math.sin(deltaPhi / 2)
				+ Math.cos(phi1) * Math.cos(phi2) * Math.sin(deltaTheta / 2) * Math.sin(deltaTheta / 2);

		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		double distance = EARTH_RADIUS * c;

		return distance;
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

	// Getter

	/**
	 * @methodtype get
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * @methodtype get
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @methodtype get
	 */
	public double getLatitude() {
		return latitude;
	}

	// SETTER:

	/**
	 * @methodtype set
	 */
	public void setRadius(double r) {
		if (r < 0) {
			throw new IllegalArgumentException("Values smaller 0 for Radius are not allowed!");
		}
		this.radius = r;
	}

	/**
	 * @methodtype set
	 */
	public void setLongitude(double longitude) {
		if (longitude < -180.00 || longitude > 180.00) {
			throw new IllegalArgumentException("Value of longitude must be between -180.00 and 180.00");
		}
		this.longitude = longitude;
	}

	/**
	 * @methodtype set
	 */
	public void setLatitude(double latitude) {
		if (latitude < -90.00 || latitude > 90.00) {
			throw new IllegalArgumentException("Value of latitude must be between -90.00 and 90.00");
		}
		this.latitude = latitude;
	}

}
