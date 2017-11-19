package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class CartesianCoordinateTest {

	private CartesianCoordinate coordinatesA;
	private CartesianCoordinate coordinatesB;
	private double DELTA = 0.000001; // TODO: Move this to a common Utils class

	// Arrange before each test
	@Before
	public void setUp() {
		coordinatesA = new CartesianCoordinate(1.0, 1.0, 1.0);
		coordinatesB = new CartesianCoordinate(0.0, 0.0, 0.0);
	}

	@Test
	public void createCoordinateTest() {
		assertNotNull(coordinatesA);
	}

	@Test
	public void createCoordinateTestDefault() {
		CartesianCoordinate coordinate = new CartesianCoordinate();
		assertNotNull(coordinate);
		assertEquals(coordinate.getXCoordinate(), 1.0, DELTA);
		assertEquals(coordinate.getYCoordinate(), 1.0, DELTA);
		assertEquals(coordinate.getZCoordinate(), 1.0, DELTA);
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

	@Test
	public void asCartesianCoordinateTest() {
		assertEquals(coordinatesA, coordinatesA.asCartesianCoordinate());
	}

	@Test
	public void asSphericCoordinateTest() {
		CartesianCoordinate coordinate = new CartesianCoordinate(20.0, 0.0, 0.0);
		SphericCoordinate asSperic = coordinate.asSphericCoordinate();
		assertEquals(asSperic.getRadius(), 20.0, DELTA);
		assertEquals(asSperic.getLatitude(), 0.0, DELTA);
		assertEquals(asSperic.getLongitude(), 1.57079632, DELTA);
	}

	@Test
	public void asSphericCoordinateTest2() {
		CartesianCoordinate coordinate = new CartesianCoordinate(3.56, 4.56, 5.89);
		SphericCoordinate asSperic = coordinate.asSphericCoordinate();
		assertEquals(asSperic.getRadius(), 8.2558645822227, DELTA);
		assertEquals(asSperic.getLatitude(), 0.90793387553656, DELTA);
		assertEquals(asSperic.getLongitude(), 0.77641211202797, DELTA);
	}

	@Test
	public void asSphericEqualsTest() {
		CartesianCoordinate coordinate = new CartesianCoordinate(20.0, 0.0, 0.0);
		SphericCoordinate coordinate2 = new SphericCoordinate(20.0, 0.0, 1.57079632);
		assertEquals(coordinate.asSphericCoordinate(), coordinate2);
	}

	@Test
	public void getCartesianDistanceWithOneSpericTest() {
		CartesianCoordinate coordinate1 = new CartesianCoordinate(4.942, 1.087, -3.883);
		SphericCoordinate coordinate2 = new SphericCoordinate(6378.00, 23.7, 85.3);
		assertEquals(coordinate1.getDistance(coordinate2), 6373.374, 0.01);
	}

	@Test
	public void getSphericDistanceTest() {
		CartesianCoordinate coordinate1 = new CartesianCoordinate(20.0, 0.0, 0.0);
		SphericCoordinate coordinate2 = new SphericCoordinate(6378.0, 5.0, 0.0);
		assertEquals(coordinate1.getSphericDistance(coordinate2), 583.33914053, DELTA);
	}
}
