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
	public void isEqualHugeValuesTest() {
		coordinatesB.setXCoordinate(154683.23549);
		coordinatesB.setYCoordinate(164893.68479);
		coordinatesB.setZCoordinate(136876.35467);
		coordinatesA.setXCoordinate(154683.23549);
		coordinatesA.setYCoordinate(164893.68479);
		coordinatesA.setZCoordinate(136876.35467);
		assertEquals(coordinatesA.isEqual(coordinatesB), true);
	}
	
	@Test
	public void isEqualMinimumValuesTest() {
		coordinatesB.setXCoordinate(0.0023549);
		coordinatesB.setYCoordinate(0.0068479);
		coordinatesB.setZCoordinate(0.0035467);
		coordinatesA.setXCoordinate(0.0023549);
		coordinatesA.setYCoordinate(0.0068479);
		coordinatesA.setZCoordinate(0.0035467);
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
	public void equalsHugeValuesTest() {
		coordinatesB.setXCoordinate(154683.23549);
		coordinatesB.setYCoordinate(164893.68479);
		coordinatesB.setZCoordinate(136876.35467);
		coordinatesA.setXCoordinate(154683.23549);
		coordinatesA.setYCoordinate(164893.68479);
		coordinatesA.setZCoordinate(136876.35467);
		assertEquals(coordinatesA.equals(coordinatesB), true);
	}
	
	@Test
	public void equalsMinimumValuesTest() {
		coordinatesB.setXCoordinate(0.0023549);
		coordinatesB.setYCoordinate(0.0068479);
		coordinatesB.setZCoordinate(0.0035467);
		coordinatesA.setXCoordinate(0.0023549);
		coordinatesA.setYCoordinate(0.0068479);
		coordinatesA.setZCoordinate(0.0035467);
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
	
	@Test
	public void coordinatesAreNotEqualDifferentValuesTest() {
		coordinatesB.setXCoordinate(1.264);
		coordinatesB.setYCoordinate(15.368);
		coordinatesB.setZCoordinate(68.349);
		coordinatesA.setXCoordinate(56.985);
		coordinatesA.setYCoordinate(79.256);
		coordinatesA.setZCoordinate(65.369);
		assertEquals(coordinatesA.isEqual(coordinatesB), false);
		assertEquals(coordinatesA.equals(coordinatesB), false);
	}
	
	@Test
	public void coordinatesAreNotEqualOnlyOneDifferentValueTest() {
		coordinatesB.setXCoordinate(1.0);
		coordinatesB.setYCoordinate(46.1465);
		coordinatesB.setZCoordinate(1.0);
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
	public void getDistanceTestHugeValues() {
		coordinatesA.setXCoordinate(1546.235);
		coordinatesA.setYCoordinate(1648.684);
		coordinatesA.setZCoordinate(1368.354);
		coordinatesB.setXCoordinate(1228.158);
		coordinatesB.setYCoordinate(6567.457);
		coordinatesB.setZCoordinate(8986.265);
		assertEquals(coordinatesA.getDistance(coordinatesB), 9073.481625, 0.0001);
	}
	
	@Test
	public void getDistanceTestSmallValues() {
		coordinatesA.setXCoordinate(0.0235);
		coordinatesA.setYCoordinate(0.0684);
		coordinatesA.setZCoordinate(0.0354);
		coordinatesB.setXCoordinate(0.0123);
		coordinatesB.setYCoordinate(0.0687);
		coordinatesB.setZCoordinate(0.0879);
		assertEquals(coordinatesA.getDistance(coordinatesB), 0.0536822, 0.0001);
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
