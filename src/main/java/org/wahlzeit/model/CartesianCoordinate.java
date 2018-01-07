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


@DesignPattern(name = "Value Object", participants = { "CartesianCoordinate" })
public class CartesianCoordinate extends AbstractCoordinate {

	private final double x;
	private final double y;
	private final double z;

	private static final Map<String, CartesianCoordinate> sharedCartesianCoordinatesMap = new HashMap<String, CartesianCoordinate>();

	/**
	 * @methodtype constructor
	 */
	private CartesianCoordinate(double x, double y, double z) {

		assertDouble(x);
		assertDouble(y);
		assertDouble(z);

		this.x = x;
		this.y = y;
		this.z = z;

		assertClassInvariants();
	}

	/**
	 * @methodtype factory
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public static final CartesianCoordinate createCartesianCoordinate(double x, double y, double z) {
		String sharedKey = toString(x, y, z);
		boolean coordinateAlreadyExists = checkExistence(sharedKey);

		if (coordinateAlreadyExists) {
			return sharedCartesianCoordinatesMap.get(sharedKey);
		}
		CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(x, y, z);
		sharedCartesianCoordinatesMap.put(sharedKey, cartesianCoordinate);
		return cartesianCoordinate;
	}

	// Getters

	/**
	 * 
	 */
	public double getXCoordinate() {
		assertClassInvariants();
		double xCopy = this.x;
		assertClassInvariants();
		return xCopy;
	}

	/**
	 * 
	 */
	public double getYCoordinate() {
		assertClassInvariants();
		double yCopy = this.y;
		assertClassInvariants();
		return yCopy;
	}

	/**
	 * 
	 */
	public double getZCoordinate() {
		assertClassInvariants();
		double zCopy = this.z;
		assertClassInvariants();
		return zCopy;
	}

	/**
	 * 
	 */
	public CartesianCoordinate createCoordinateWithX(double x) {
		assertClassInvariants();
		assertDouble(x);
	
		CartesianCoordinate coordinateWithX = CartesianCoordinate.createCartesianCoordinate(x, this.y, this.z);
	
		assertClassInvariants();
		return coordinateWithX;
	}

	/**
	 * 
	 */
	public CartesianCoordinate createCoordinateWithY(double y) {
		assertClassInvariants();
		assertDouble(y);
	
		CartesianCoordinate coordinateWithY = CartesianCoordinate.createCartesianCoordinate(this.x, y, this.z);
	
		assertClassInvariants();
		return coordinateWithY;
	}

	/**
	 * 
	 */
	public CartesianCoordinate createCoordinateWithZ(double z) {
		assertClassInvariants();
		assertDouble(z);
	
		CartesianCoordinate coordinateWithZ = CartesianCoordinate.createCartesianCoordinate(this.x, this.y, z);
	
		assertClassInvariants();
		return coordinateWithZ;
	}

	/**
	 * returns this Object, as we already have a cartesian coordinate
	 * representation
	 */
	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		return this;
	}

	@Override
	public double calculateDistance(Coordinate cor) {
		assertClassInvariants();
		AssertionUtils.assertNotNull(cor, this.getClass().getSimpleName());

		CartesianCoordinate cartesianCor = cor.asCartesianCoordinate();
		double deltaX = x - cartesianCor.getXCoordinate();
		double deltaY = y - cartesianCor.getYCoordinate();
		double deltaZ = z - cartesianCor.getZCoordinate();
		double distance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2) + Math.pow(deltaZ, 2));

		assertDistance(distance);
		assertClassInvariants();
		return distance;
	}

	/**
	 * Converts this Cartesian coordinate in a spherical coordinate representation
	 * 
	 * @methodtype conversion
	 * @methodproperty primitive
	 */
	@Override
	public SphericCoordinate asSphericCoordinate() {
		SphericCoordinate spheric = computeSpheric();
		assertSphericRepresenatation(spheric);
		return spheric;
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

			return SphericCoordinate.createSphericCoordinate(radius, phi, theta);
		}
		return SphericCoordinate.createSphericCoordinate(ParamsUtil.EARTH_RADIUS, 0.0, 0.0);
	}

	/**
	 * 
	 */
	private double calculateRadius() {
		double radius = Math.sqrt(Math.pow(this.getXCoordinate(), 2) + Math.pow(this.getYCoordinate(), 2)
				+ Math.pow(this.getZCoordinate(), 2));
		assertRadius(radius);
		return radius;
	}

	/**
	 * 
	 */
	private double calculateTheta(double radius) {
		AssertionUtils.assertNotNull(radius, this.getClass().getSimpleName());
		double theta = Math.acos(this.getZCoordinate() / radius);
		assertLongitude(theta);
		return theta;
	}

	/**
	 * 
	 */
	private double calculatePhi() {
		double phi = Math.atan2(this.getYCoordinate(), this.getXCoordinate());
		assertLatitude(phi);
		return phi;
	}

	/**
	 * Checks if the CartesianCoordinate Object, that should be created, already
	 * exists.
	 * 
	 * @param key
	 * @return boolean
	 */
	private static boolean checkExistence(String key) {
		boolean coordinateExists = sharedCartesianCoordinatesMap.containsKey(key);
		return coordinateExists;
	}

	private static String toString(double x, double y, double z) {
		String asString = "x: " + x + ", " + "y: " + y + ", " + "z " + z;
		return asString;
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
		AssertionUtils.assertNotNull(cor, this.getClass().getSimpleName());
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

	/**
	 * @methodtype: assertion
	 */
	private void assertClassInvariants() {
		assertDouble(this.x);
		assertDouble(this.y);
		assertDouble(this.z);
	}

	/**
	 * @methodtype assertion
	 */
	public void assertSphericRepresenatation(Coordinate isSpheric) {
		AssertionUtils.assertNotNull(isSpheric, this.getClass().getSimpleName());
		if (!(isSpheric instanceof SphericCoordinate)) {
			throw new WrongCoordinateTypeException(isSpheric);
		}
	}

}
