package org.wahlzeit.model;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class LocationTest {

	@Test
	public void testCreateLocation() {
		Coordinate coordinates = new Coordinate(1.0, 1.0, 1.0);
		Location newLocation = new Location(coordinates);
		assertNotNull(newLocation);
	}

}
