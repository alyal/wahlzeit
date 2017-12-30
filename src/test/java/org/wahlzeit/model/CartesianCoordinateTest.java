package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.utils.ParamsUtil;

public class CartesianCoordinateTest {

	private CartesianCoordinate coordinatesA;
	private CartesianCoordinate coordinatesB;
	private double DELTA = ParamsUtil.DELTA;

	// Arrange before each test
	@Before
	public void setUp() {
		coordinatesA = CartesianCoordinate.createCartesianCoordinate(1.0, 1.0, 1.0);
	}

	@Test
	public void createCoordinateTest() {
		assertNotNull(coordinatesA);
	}

	@Test
	public void createCoordinateTestDefault() {
		CartesianCoordinate coordinate = coordinatesA;
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
		CartesianCoordinate coordinatesB = CartesianCoordinate.createCartesianCoordinate(1.0, 2.0, 3.0);
		assertEquals(coordinatesB.getXCoordinate(), 1.0, 0);
		assertEquals(coordinatesB.getYCoordinate(), 2.0, 0);
		assertEquals(coordinatesB.getZCoordinate(), 3.0, 0);
	}

	@Test
	public void isEqualTest() {
		CartesianCoordinate coordinatesB = CartesianCoordinate.createCartesianCoordinate(1.0, 1.0, 1.0);
		assertEquals(coordinatesA.isEqual(coordinatesB), true);
	}

	@Test
	public void isEqualHugeValuesTest() {
		CartesianCoordinate coordinatesB = CartesianCoordinate.createCartesianCoordinate(154683.23549, 164893.68479,
				136876.35467);
		CartesianCoordinate coordinatesA = CartesianCoordinate.createCartesianCoordinate(154683.23549, 164893.68479,
				136876.35467);
		assertEquals(coordinatesA.isEqual(coordinatesB), true);
	}

	@Test
	public void isEqualMinimumValuesTest() {
		CartesianCoordinate coordinatesB = CartesianCoordinate.createCartesianCoordinate(0.0023549, 0.0068479,
				0.0035467);
		CartesianCoordinate coordinatesA = CartesianCoordinate.createCartesianCoordinate(0.0023549, 0.0068479,
				0.0035467);
		assertEquals(coordinatesA.isEqual(coordinatesB), true);
	}

	@Test
	public void equalsTest() {
		CartesianCoordinate coordinatesB = CartesianCoordinate.createCartesianCoordinate(1.0, 1.0, 1.0);
		assertEquals(coordinatesA.equals(coordinatesB), true);
	}

	@Test
	public void equalsHugeValuesTest() {
		CartesianCoordinate coordinatesB = CartesianCoordinate.createCartesianCoordinate(154683.23549, 164893.68479,
				136876.35467);
		CartesianCoordinate coordinatesA = CartesianCoordinate.createCartesianCoordinate(154683.23549, 164893.68479,
				136876.35467);
		assertEquals(coordinatesA.equals(coordinatesB), true);
	}

	@Test
	public void equalsMinimumValuesTest() {
		CartesianCoordinate coordinatesB = CartesianCoordinate.createCartesianCoordinate(0.0023549, 0.0068479,
				0.0035467);
		CartesianCoordinate coordinatesA = CartesianCoordinate.createCartesianCoordinate(0.0023549, 0.0068479,
				0.0035467);
		assertEquals(coordinatesA.equals(coordinatesB), true);
	}

	@Test
	public void coordinatesAreEqualTest() {
		CartesianCoordinate coordinatesB = CartesianCoordinate.createCartesianCoordinate(1.0, 1.0, 1.0);
		assertEquals(coordinatesA.isEqual(coordinatesB), true);
		assertEquals(coordinatesA.equals(coordinatesB), true);
	}

	@Test
	public void coordinatesAreNotEqualTest() {
		CartesianCoordinate coordinatesB = CartesianCoordinate.createCartesianCoordinate(0.0023549, 0.0068479,
				0.0035467);
		assertEquals(coordinatesA.isEqual(coordinatesB), false);
		assertEquals(coordinatesA.equals(coordinatesB), false);
	}

	@Test
	public void coordinatesAreNotEqualDifferentValuesTest() {
		CartesianCoordinate coordinatesB = CartesianCoordinate.createCartesianCoordinate(1.264, 15.368, 68.349);
		CartesianCoordinate coordinatesA = CartesianCoordinate.createCartesianCoordinate(56.985, 79.256, 65.369);
		assertEquals(coordinatesA.isEqual(coordinatesB), false);
		assertEquals(coordinatesA.equals(coordinatesB), false);
	}

	@Test
	public void coordinatesAreNotEqualOnlyOneDifferentValueTest() {
		CartesianCoordinate coordinatesB = CartesianCoordinate.createCartesianCoordinate(1.0, 46.1465, 1.0);
		assertEquals(coordinatesA.isEqual(coordinatesB), false);
		assertEquals(coordinatesA.equals(coordinatesB), false);
	}

	@Test(expected = IllegalArgumentException.class)
	public void coordinatesAreEqualWithNullArgumentTest() {
		assertEquals(coordinatesA.equals(null), true);
	}

	@Test
	public void getDistanceTest() {
		CartesianCoordinate coordinatesB = CartesianCoordinate.createCartesianCoordinate(2.0, 2.0, 2.0);
		assertEquals(coordinatesA.getDistance(coordinatesB), 1.732050808, 0.001);
	}

	@Test
	public void getDistanceTestHugeValues() {
		CartesianCoordinate coordinatesB = CartesianCoordinate.createCartesianCoordinate(1228.158, 6567.457, 8986.265);
		CartesianCoordinate coordinatesA = CartesianCoordinate.createCartesianCoordinate(1546.235, 1648.684, 1368.354);
		assertEquals(coordinatesA.getDistance(coordinatesB), 9073.481625, 0.0001);
	}

	@Test
	public void getDistanceTestSmallValues() {
		CartesianCoordinate coordinatesB = CartesianCoordinate.createCartesianCoordinate(0.0123, 0.0687, 0.0879);
		CartesianCoordinate coordinatesA = CartesianCoordinate.createCartesianCoordinate(0.0235, 0.0684, 0.0354);
		assertEquals(coordinatesA.getDistance(coordinatesB), 0.0536822, 0.0001);
	}

	@Test
	public void getDistanceWithNegativeCoordinatesTest() {
		CartesianCoordinate coordinatesB = CartesianCoordinate.createCartesianCoordinate(-1.5, -2.3, -4.7);
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
		CartesianCoordinate coordinate = CartesianCoordinate.createCartesianCoordinate(20.0, 0.0, 0.0);
		SphericCoordinate asSperic = coordinate.asSphericCoordinate();
		assertEquals(asSperic.getRadius(), 20.0, DELTA);
		assertEquals(asSperic.getLatitude(), 0.0, DELTA);
		assertEquals(asSperic.getLongitude(), 1.57079632, DELTA);
	}

	@Test
	public void asSphericCoordinateTest2() {
		CartesianCoordinate coordinate = CartesianCoordinate.createCartesianCoordinate(3.56, 4.56, 5.89);
		SphericCoordinate asSperic = coordinate.asSphericCoordinate();
		assertEquals(asSperic.getRadius(), 8.2558645822227, DELTA);
		assertEquals(asSperic.getLatitude(), 0.90793387553656, DELTA);
		assertEquals(asSperic.getLongitude(), 0.77641211202797, DELTA);
	}

	@Test
	public void asSphericEqualsTest() {
		CartesianCoordinate coordinate = CartesianCoordinate.createCartesianCoordinate(20.0, 0.0, 0.0);
		SphericCoordinate coordinate2 = SphericCoordinate.createSphericCoordinate(20.0, 0.0, 1.57079632);
		assertEquals(coordinate.asSphericCoordinate(), coordinate2);
	}

	@Test
	public void getCartesianDistanceWithOneSpericTest() {
		CartesianCoordinate coordinate1 = CartesianCoordinate.createCartesianCoordinate(4.942, 1.087, -3.883);
		SphericCoordinate coordinate2 = SphericCoordinate.createSphericCoordinate(6378.00, 23.7, 85.3);
		assertEquals(coordinate1.getDistance(coordinate2), 6373.374, 0.01);
	}

	@Test
	public void getSphericDistanceTest() {
		CartesianCoordinate coordinate1 = CartesianCoordinate.createCartesianCoordinate(20.0, 0.0, 0.0);
		SphericCoordinate coordinate2 = SphericCoordinate.createSphericCoordinate(6378.0, 5.0, 0.0);
		assertEquals(coordinate1.getSphericDistance(coordinate2), 583.33914053, DELTA);
	}
}
