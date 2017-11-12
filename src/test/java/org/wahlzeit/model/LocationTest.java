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

import org.junit.Test;

public class LocationTest {

	@Test
	public void testCreateLocation() {
		Coordinate coordinates = new Coordinate(1.0, 1.0, 1.0);
		Location newLocation = new Location(coordinates);
		assertNotNull(newLocation);
	}
	
	@Test
	public void testCreateLocationDefault() {
		Location newLocation = new Location();
		Coordinate cor = newLocation.getCoordinate();
		assertNotNull(newLocation);
		assertEquals(cor.getXCoordinate(), 0.0, 0.0000001);
		assertEquals(cor.getYCoordinate(), 0.0, 0.0000001);
		assertEquals(cor.getZCoordinate(), 0.0, 0.0000001);
	}

}
