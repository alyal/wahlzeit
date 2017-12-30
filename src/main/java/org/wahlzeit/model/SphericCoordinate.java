/*
 * Copyright (c) 2017 by alyal, https://github.com/alyal
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package org.wahlzeit.model;

import java.util.HashMap;
import java.util.Map;

import org.wahlzeit.exceptions.WrongCoordinateTypeException;
import org.wahlzeit.utils.AssertionUtils;
import org.wahlzeit.utils.ParamsUtil;

public class SphericCoordinate extends AbstractCoordinate {

	private final double latitude;
	private final double longitude;
	private final double radius;

	private static Map<String, SphericCoordinate> sharedSphericCoordinatesMap = new HashMap<String, SphericCoordinate>();

	/**
	 * @methodtype constructor
	 */
	private SphericCoordinate(double radius, double latitude, double longitude) {

		assertRadius(radius);
		assertLatitude(latitude);
		assertLongitude(longitude);

		this.radius = radius;
		this.latitude = latitude;
		this.longitude = longitude;

		assertClassInvariants();
	}

	/**
	 * Creates a new SphericCoordinate Object if it does not exist already. If an
	 * Instance already exists, it returns the existing one.
	 * 
	 * @param radius
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	public static final SphericCoordinate createSphericCoordinate(double radius, double latitude, double longitude) {
		String sharedKey = toString(radius, latitude, longitude);
		boolean coordinateAlreadyExists = checkExistence(sharedKey);

		if (coordinateAlreadyExists) {
			return sharedSphericCoordinatesMap.get(sharedKey);
		}
		SphericCoordinate newSphericCoordinate = new SphericCoordinate(radius, latitude, longitude);
		sharedSphericCoordinatesMap.put(sharedKey, newSphericCoordinate);
		return newSphericCoordinate;
	}

	// Getter

	/**
	 * @methodtype get
	 */
	public double getRadius() {
		assertClassInvariants();
		double radiusCopy = this.radius;
		assertClassInvariants();
		return radiusCopy;
	}

	/**
	 * @methodtype get
	 */
	public double getLongitude() {
		assertClassInvariants();
		double longitudeCopy = this.longitude;
		assertClassInvariants();
		return longitudeCopy;
	}

	/**
	 * @methodtype get
	 */
	public double getLatitude() {
		assertClassInvariants();
		double latitudeCopy = this.latitude;
		assertClassInvariants();
		return latitudeCopy;
	}

	/**
	 * 
	 */
	public SphericCoordinate createCoordinateWithRadius(double radius) {
		assertClassInvariants();
		assertDouble(radius);

		SphericCoordinate coordinateWithRadius =  SphericCoordinate.createSphericCoordinate(radius, this.latitude, this.longitude);

		assertClassInvariants();
		return coordinateWithRadius;
	}

	/**
	 * 
	 */
	public SphericCoordinate createCoordinateWithLatitude(double latitude) {
		assertClassInvariants();
		assertDouble(latitude);

		SphericCoordinate coordinateWithLatitude = SphericCoordinate.createSphericCoordinate(this.radius, latitude, this.longitude);

		assertClassInvariants();
		return coordinateWithLatitude;
	}

	/**
	 * 
	 */
	public SphericCoordinate createCoordinateWithLongitude(double longitude) {
		assertClassInvariants();
		assertDouble(longitude);

		SphericCoordinate coordinateWithLongitude = SphericCoordinate.createSphericCoordinate(this.radius, this.latitude, longitude);

		assertClassInvariants();
		return coordinateWithLongitude;
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
		assertClassInvariants();
		final double sinPhi = Math.sin(Math.toRadians(this.getLatitude()));
		final double sinTheta = Math.sin(Math.toRadians(this.getLongitude()));
		final double cosPhi = Math.cos(Math.toRadians(this.getLatitude()));
		final double cosTheta = Math.cos(Math.toRadians(this.getLongitude()));

		double x = calculateX(this.getRadius(), sinTheta, cosPhi);
		double y = calculateY(this.getRadius(), sinTheta, sinPhi);
		double z = calculateZ(this.getRadius(), cosTheta);

		assertClassInvariants();

		return CartesianCoordinate.createCartesianCoordinate(x, y, z);
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
	 * returns this Object, as we already have a spheric coordinate
	 * representation
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
	public double calculateDistance(Coordinate cor) {
		assertClassInvariants();
		AssertionUtils.assertNotNull(cor, this.getClass().getSimpleName());

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
		assertClassInvariants();

		return distance;
	}

	/**
	 * Checks if the SphericCoordinate Object, that should be created, already
	 * exists.
	 * 
	 * @param key
	 * @return boolean
	 */

	private static boolean checkExistence(String key) {
		boolean coordinateExists = sharedSphericCoordinatesMap.containsKey(key);
		return coordinateExists;
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
		AssertionUtils.assertNotNull(coordinate, this.getClass().getSimpleName());
		SphericCoordinate asSpheric = coordinate.asSphericCoordinate();

		if (Math.abs(this.getLatitude() - asSpheric.getLatitude()) <= ParamsUtil.DELTA) {

			if (Math.abs(this.getLongitude() - asSpheric.getLongitude()) <= ParamsUtil.DELTA) {

				if (Math.abs(this.getRadius() - asSpheric.getRadius()) <= ParamsUtil.DELTA) {
					return true;
				}
			}
		}

		return false;
	}

	private static final String toString(double radius, double latitude, double longitude) {
		String asString = "radius: " + radius + ", " + "latidude: " + latitude + ", " + "longitude: " + longitude;
		return asString;
	}

	/**
	 * @methodtype: assertion
	 */
	private void assertClassInvariants() {
		assertRadius(this.radius);
		assertLatitude(this.latitude);
		assertLongitude(this.longitude);
	}

	/**
	 * @methodtype assertion
	 */
	public void assertCartesianRepresenatation(Coordinate isCartesian) throws WrongCoordinateTypeException {
		AssertionUtils.assertNotNull(isCartesian, this.getClass().getSimpleName());
		if (!(isCartesian instanceof CartesianCoordinate)) {
			throw new WrongCoordinateTypeException(isCartesian);
		}
	}

}
