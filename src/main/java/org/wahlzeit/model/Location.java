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

/**
 * Class describing the Location of a {@Photo}
 * 
 */
public class Location {
	public Coordinate coordinate;

	/**
	 * @methodtype constructor
	 */
	public Location(Coordinate corrdiante) {
		this.coordinate = corrdiante;
	}

	/**
	 * @methodtype get
	 * @methodproperty primitive
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}

	/**
	 * @methodtype set
	 * @methodproperty primitive
	 */
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	/**
	 * Returns the Latitude and Longitue of the Location in a string
	 * 
	 * @methodtype conversion
	 * @methodproperty primitive
	 */
	public String asString() {
		return "Lat:" + this.coordinate.asSphericCoordinate().getLatitude() + "/Long:"
				+ this.coordinate.asSphericCoordinate().getLongitude();
	}
}