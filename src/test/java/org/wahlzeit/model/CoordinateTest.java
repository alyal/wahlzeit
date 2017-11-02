package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

/**
 * Test case for the {@Coordinate} class
 *
 */
public class CoordinateTest {

	private Coordinate coordinatesA;
	private Coordinate coordinatesB;

	// Arrange before each test
	@Before
	public void setUp() {
		coordinatesA = new Coordinate(1.0, 1.0, 1.0);
		coordinatesB = new Coordinate(0, 0, 0);
	}

	@Test
	public void createCoordinateTest() {
		assertNotNull(coordinatesA);
	}

	@Test
	public void getCoordinatesTest() {
		assertEquals(coordinatesA.getXCoordinate(), 1.0, 0);
		assertEquals(coordinatesA.getYCoordinate(), 1.0, 0);
		assertEquals(coordinatesA.getZCoordinate(), 1.0, 0);
	}

	@Test
	public void setCoordinatesTest() {
		coordinatesB.setXCoordinate(1.0);
		coordinatesB.setYCoordinate(2.0);
		coordinatesB.setZCoordinate(3.0);
		assertEquals(coordinatesB.getXCoordinate(), 1.0, 0);
		assertEquals(coordinatesB.getYCoordinate(), 2.0, 0);
		assertEquals(coordinatesB.getZCoordinate(), 3.0, 0);
	}

	@Test
	public void isEqualTest() {
		coordinatesB.setXCoordinate(1.0);
		coordinatesB.setYCoordinate(1.0);
		coordinatesB.setZCoordinate(1.0);
		assertEquals(coordinatesA.isEqual(coordinatesB), true);
	}

	@Test
	public void equalsTest() {
		coordinatesB.setXCoordinate(1.0);
		coordinatesB.setYCoordinate(1.0);
		coordinatesB.setZCoordinate(1.0);
		assertEquals(coordinatesA.equals(coordinatesB), true);
	}

	@Test
	public void coordinatesAreEqualTest() {
		coordinatesB.setXCoordinate(1.0);
		coordinatesB.setYCoordinate(1.0);
		coordinatesB.setZCoordinate(1.0);
		assertEquals(coordinatesA.isEqual(coordinatesB), true);
		assertEquals(coordinatesA.equals(coordinatesB), true);
	}

	@Test
	public void coordinatesAreNotEqualTest() {
		assertEquals(coordinatesA.isEqual(coordinatesB), false);
		assertEquals(coordinatesA.equals(coordinatesB), false);
	}

	@Test(expected = IllegalArgumentException.class)
	public void coordinatesAreEqualWithNullArgumentTest() {
		assertEquals(coordinatesA.equals(null), true);
	}

	@Test
	public void getDistanceTest() {
		coordinatesB.setXCoordinate(2.0);
		coordinatesB.setYCoordinate(2.0);
		coordinatesB.setZCoordinate(2.0);
		assertEquals(coordinatesA.getDistance(coordinatesB), 1.732050808, 0.001);
	}

	@Test
	public void getDistanceWithNegativeCoordinatesTest() {
		coordinatesB.setXCoordinate(-1.5);
		coordinatesB.setYCoordinate(-2.3);
		coordinatesB.setZCoordinate(-4.7);
		assertEquals(coordinatesA.getDistance(coordinatesB), 7.04485628, 0.001);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getDistanceWithNullArgumentTest() {
		coordinatesB = null;
		assertEquals(coordinatesA.getDistance(coordinatesB), 1.732050808, 0.001);
	}
}
