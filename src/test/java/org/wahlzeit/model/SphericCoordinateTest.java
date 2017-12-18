package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.utils.ParamsUtil;

public class SphericCoordinateTest {

	private SphericCoordinate spericCoordinateA;
	private SphericCoordinate spericCoordinateB;
	private double DELTA = ParamsUtil.DELTA;
	private final double EARTH_RADIUS = ParamsUtil.EARTH_RADIUS;

	@Before
	public void setUp() {
		spericCoordinateA = new SphericCoordinate(EARTH_RADIUS, 0.0, 0.0);
		spericCoordinateB = new SphericCoordinate(5.56, 60.5, 35.4);
	}

	@Test
	public void createSpericCoordinateDefaultTest() {
		assertNotNull(spericCoordinateA);
		assertEquals(spericCoordinateA.getLatitude(), 0.0, DELTA);
		assertEquals(spericCoordinateA.getLongitude(), 0.0, DELTA);
		assertEquals(spericCoordinateA.getRadius(), EARTH_RADIUS, DELTA);
	}

	@Test
	public void createSpericCoordinateTest() {
		assertNotNull(spericCoordinateB);
		assertEquals(spericCoordinateB.getLatitude(), 60.5, DELTA);
		assertEquals(spericCoordinateB.getLongitude(), 35.4, DELTA);
		assertEquals(spericCoordinateB.getRadius(), 5.56, DELTA);
	}

	@Test
	public void spehericCoordinateGettersTest() {
		assertEquals(spericCoordinateB.getLatitude(), 60.5, 0.0);
		assertEquals(spericCoordinateB.getLongitude(), 35.4, 0.0);
		assertEquals(spericCoordinateB.getRadius(), 5.56, 0.0);
	}

	@Test
	public void asSphericCoordinateTest() {
		assertEquals(spericCoordinateB, spericCoordinateB.asSphericCoordinate());
	}

	@Test
	public void asCartesianCoordinateTest() {
		CartesianCoordinate asCartesian = spericCoordinateB.asCartesianCoordinate();
		assertEquals(asCartesian.getXCoordinate(), 1.585999436, DELTA);
		assertEquals(asCartesian.getYCoordinate(), 2.803244514, DELTA);
		assertEquals(asCartesian.getZCoordinate(), 4.532110544, DELTA);
	}

	@Test
	public void getSphericDistanceTest() {
		SphericCoordinate coordinate1 = new SphericCoordinate(EARTH_RADIUS, 23.7, 85.3);
		SphericCoordinate coordinate2 = new SphericCoordinate(EARTH_RADIUS, 89.5, 12.4);
		assertEquals(coordinate1.getSphericDistance(coordinate2), 7364.0555, 0.01);
	}

	@Test
	public void getSphericDistanceWithOneCartesianTest() {
		SphericCoordinate coordinate1 = new SphericCoordinate(EARTH_RADIUS, 23.7, 85.3);
		CartesianCoordinate coordinate2 = new CartesianCoordinate(4.942, 1.087, -3.883);
		assertEquals(coordinate1.getSphericDistance(coordinate2), 9303.184, 0.01);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setNotAllowedRadiusTest() {
		new SphericCoordinate(-10, 123.00, 85.00);
	}

	@Test(expected = IllegalArgumentException.class)
	public void notAllowedLatitudeTest() {
		new SphericCoordinate(EARTH_RADIUS, 123.00, 85.00);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setNotAllowedLongitudeTest() {
		new SphericCoordinate(EARTH_RADIUS, 123.00, 234.00);

	}

}
