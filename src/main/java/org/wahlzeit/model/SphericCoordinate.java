package org.wahlzeit.model;

import org.wahlzeit.utils.ParamsUtil;

public class SphericCoordinate extends AbstractCoordinate {

	private double latitude = 0.0;
	private double longitude = 0.0;
	private double radius = ParamsUtil.EARTH_RADIUS;

	/**
	 * @methodtype constructor
	 */
	public SphericCoordinate() {

	}

	/**
	 * @methodtype constructor
	 */
	public SphericCoordinate(double radius, double latitude, double longitude) {
		assertClassInvariants();

		assertRadius(radius);
		assertLatitude(latitude);
		assertLongitude(longitude);

		this.radius = radius;
		this.latitude = latitude;
		this.longitude = longitude;

		assertClassInvariants();
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
		assertRadius(r);

		this.radius = r;

		assertCorrectRadiusSet(r);
		assertClassInvariants();

	}

	/**
	 * @methodtype set
	 */
	public void setLongitude(double longitude) {
		assertLongitude(longitude);

		this.longitude = longitude;

		assertCorrectLongitudeSet(longitude);
		assertClassInvariants();
	}

	/**
	 * @methodtype set
	 */
	public void setLatitude(double latitude) {
		assertLatitude(latitude);

		this.latitude = latitude;

		assertCorrectLatitudeSet(latitude);
		assertClassInvariants();
	}

	/**
	 * Converts this Spherical coordinate in a Cartesian coordinate representation
	 */
	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		CartesianCoordinate cartesian = computeCartesian();
		assertCartesianRepresenatation(cartesian);
		return cartesian;
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

	@Override
	public SphericCoordinate asSphericCoordinate() {
		return this;
	}

	/**
	 * returns the distance between this spherical Coordinate and another Coordinate
	 * (spherical or cartesian)
	 */
	@Override
	public double calculateDistance(Coordinate cor) {
		assertNotNull(cor);

		SphericCoordinate sphericCor = cor.asSphericCoordinate();

		double phi1 = Math.toRadians(this.getLatitude());
		double phi2 = Math.toRadians(sphericCor.getLatitude());
		double deltaPhi = Math.toRadians(this.getLatitude() - sphericCor.getLatitude());
		double deltaTheta = Math.toRadians(this.getLongitude() - sphericCor.getLongitude());

		double a = Math.sin(deltaPhi / 2) * Math.sin(deltaPhi / 2)
				+ Math.cos(phi1) * Math.cos(phi2) * Math.sin(deltaTheta / 2) * Math.sin(deltaTheta / 2);

		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		double distance = ParamsUtil.EARTH_RADIUS * c;

		assertDistance(distance);

		return distance;
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
		assertNotNull(coordinate);
		SphericCoordinate asSpheric = coordinate.asSphericCoordinate();

		if (Math.abs(this.latitude - asSpheric.getLatitude()) <= ParamsUtil.DELTA) {

			if (Math.abs(this.longitude - asSpheric.getLongitude()) <= ParamsUtil.DELTA) {

				if (Math.abs(this.radius - asSpheric.getRadius()) <= ParamsUtil.DELTA) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * @methodtype: assertion
	 */
	private void assertClassInvariants() {
		// for now in my opinion there are no real invariants.

	}

	/**
	 * @methodtype: assertion
	 */
	private void assertCorrectLongitudeSet(double setValue) {
		assert this.getLongitude() == setValue;
	}

	/**
	 * @methodtype: assertion
	 */
	private void assertCorrectLatitudeSet(double setValue) {
		assert this.getLatitude() == setValue;
	}

	/**
	 * @methodtype: assertion
	 */
	private void assertCorrectRadiusSet(double setValue) {
		assert this.getRadius() == setValue;
	}
}
