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

import org.wahlzeit.exceptions.WrongCoordinateTypeException;
import org.wahlzeit.utils.AssertionUtils;
import org.wahlzeit.utils.ParamsUtil;

public class CartesianCoordinate extends AbstractCoordinate {

	private final double x;
	private final double y;
	private final double z;

	/**
	 * @methodtype constructor
	 */
	public CartesianCoordinate(double x, double y, double z) throws IllegalArgumentException {

		assertDouble(x);
		assertDouble(y);
		assertDouble(z);

		this.x = x;
		this.y = y;
		this.z = z;

		assertClassInvariants();
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
	private void setXCoordinate(double x) {
		// do nothing because Object should be immutable, so nothing should be changed
		// (see lecture C07 p.10: "no mutation methods of return type void").
		// For now I keep it as private method in case they are needed again later for
		// other homework. If not it can be removed.
	}

	/**
	 * 
	 */
	private void setYCoordinate(double y) {
		// do nothing because Object should be immutable, so nothing should be changed
		// (see lecture C07 p.10: "no mutation methods of return type void").
		// For now I keep it as private method in case they are needed again later for
		// other homework. If not it can be removed.
	}

	/**
	 * 
	 */
	private void setZCoordinate(double z) {
		// do nothing because Object should be immutable, so nothing should be changed
		// (see lecture C07 p.10: "no mutation methods of return type void").
		// For now I keep it as private method in case they are needed again later for
		// other homework. If not it can be removed.
	}

	/**
	 * 
	 */
	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		return new CartesianCoordinate(this.getXCoordinate(), this.getYCoordinate(), this.getZCoordinate());
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

			return new SphericCoordinate(radius, phi, theta);
		}
		return new SphericCoordinate(ParamsUtil.EARTH_RADIUS, 0.0, 0.0);
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

	/**
	 * @methodtype: assertion
	 */
	private void assertCorrectXValueSet(double setValue) {
		assert this.getXCoordinate() == setValue;
	}

	/**
	 * @methodtype: assertion
	 */
	private void assertCorrectYValueSet(double setValue) {
		assert this.getYCoordinate() == setValue;
	}

	/**
	 * @methodtype: assertion
	 */
	private void assertCorrectZValueSet(double setValue) {
		assert this.getZCoordinate() == setValue;
	}

}
