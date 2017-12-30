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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class LocationTest {
	
	private Coordinate coordinates;
	
	@Before
	public void setUp() {
		coordinates = CartesianCoordinate.createCartesianCoordinate(1.0, 1.0, 1.0);
	}

	@Test
	public void testCreateLocation() {
		Location newLocation = new Location(coordinates);
		assertNotNull(newLocation);
	}
	
	@Test
	public void testCreateLocationDefault() {
		Location newLocation = new Location(coordinates);
		Coordinate coordinate = newLocation.getCoordinate();
		CartesianCoordinate cor = coordinate.asCartesianCoordinate();
		assertNotNull(newLocation);		
		assertEquals(cor.getXCoordinate(), 1.0, 0.0000001);
		assertEquals(cor.getYCoordinate(), 1.0, 0.0000001);
		assertEquals(cor.getZCoordinate(), 1.0, 0.0000001);
	}

}
