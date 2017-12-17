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

import org.wahlzeit.exceptions.NegativeDistanceException;
import org.wahlzeit.utils.AssertionUtils;
import org.wahlzeit.utils.ParamsUtil;

public abstract class AbstractCoordinate implements Coordinate {

	/**
	 * 
	 */
	@Override
	public abstract CartesianCoordinate asCartesianCoordinate();

	/**
	 * 
	 */
	@Override
	public abstract SphericCoordinate asSphericCoordinate();

	/**
	 * 
	 */
	public abstract double calculateDistance(Coordinate cor);

	/**
	 * 
	 */
	@Override
	public double getDistance(Coordinate cor) {
		AssertionUtils.assertNotNull(cor);
		double distance = getCartesianDistance(cor);
		assertDistance(distance);
		return distance;
	};

	/**
	 * 
	 */
	@Override
	public double getCartesianDistance(Coordinate cor) {
		AssertionUtils.assertNotNull(cor);

		CartesianCoordinate asCartesian = this.asCartesianCoordinate();

		return asCartesian.calculateDistance(cor);
	}

	/**
	 * 
	 */
	@Override
	public double getSphericDistance(Coordinate cor) {
		AssertionUtils.assertNotNull(cor);

		SphericCoordinate asSphericCoordinate = this.asSphericCoordinate();

		return asSphericCoordinate.calculateDistance(cor);
	};

	/**
	 * 
	 */
	@Override
	public abstract boolean isEqual(Coordinate cor);

	/**
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		AssertionUtils.assertNotNull(obj);
		assertInstanceOfCoordinate(obj);

		return isEqual((Coordinate) obj);
	}

	/**
	 * Defined abstract hashCode here, so it is necessary to override/ define
	 * hashCode method in extended subclass.
	 */
	@Override
	public abstract int hashCode();

	/**
	 * @methodtype assertion
	 */
	public void assertInstanceOfCoordinate(Object o) {
		if (!(o instanceof Coordinate)) {
			throw new IllegalArgumentException("Passed argument is not an instance of Coordinate!");
		}
	}

	/**
	 * @methodtype assertion
	 */
	public void assertRadius(double radius) {
		assertDouble(radius);
		if (radius < 0) {
			throw new IllegalArgumentException("Values smaller 0 for radius are not allowed!");
		}
	}

	/**
	 * @methodtype assertion
	 */
	public void assertLatitude(double latitude) {
		assertDouble(latitude);
		if (latitude < ParamsUtil.MIN_LATITUDE || latitude > ParamsUtil.MAX_LATITUDE) {
			throw new IllegalArgumentException("Value of latitude must be between -90.00 and 90.00");
		}

	}

	/**
	 * @methodtype assertion
	 */
	public void assertLongitude(double longitude) {
		assertDouble(longitude);
		if (longitude < ParamsUtil.MIN_LONGITUDE || longitude > ParamsUtil.MAX_LONGITUDE) {
			throw new IllegalArgumentException("Value of longitude must be between -180.00 and 180.00");
		}
	}

	/**
	 * @throws NegativeDistanceException
	 * @methodtype assertion
	 */
	public void assertDistance(double distance) throws NegativeDistanceException {
		if (distance <= 0) {
			throw new NegativeDistanceException(distance);
		}
	}

	/**
	 * @throws IllegalArgumetnexception
	 * @methodtype assertion
	 */
	public void assertDouble(double value) throws IllegalArgumentException {
		if (Double.isNaN(value) || value == Double.POSITIVE_INFINITY) {
			throw new IllegalArgumentException();
		}
	}

}
