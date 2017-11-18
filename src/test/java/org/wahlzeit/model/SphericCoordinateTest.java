package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class SphericCoordinateTest {
	
	private SphericCoordinate spericCoordinateA;
	private SphericCoordinate spericCoordinateB;
	private double DELTA = 0.000001;
	
	@Before
	public void setUp() {
		spericCoordinateA = new SphericCoordinate();
		spericCoordinateB = new SphericCoordinate(5.56, 60.5, 35.4);
	}
	
	@Test
	public void createSpericCoordinateDefaultTest() {
		assertNotNull(spericCoordinateA);
		assertEquals(spericCoordinateA.getLatitude(), 0.0, DELTA);
		assertEquals(spericCoordinateA.getLongitude(), 0.0, DELTA);
		assertEquals(spericCoordinateA.getRadius(), 0.0, DELTA);
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
	public void spehericCoordinateSettersTest() {
		spericCoordinateB.setLatitude(23.56);
		spericCoordinateB.setLongitude(44.87);
		spericCoordinateB.setRadius(15.69);
		
		assertEquals(spericCoordinateB.getLatitude(), 23.56, 0.0);
		assertEquals(spericCoordinateB.getLongitude(), 44.87, 0.0);
		assertEquals(spericCoordinateB.getRadius(), 15.69, 0.0);
	}
	
	@Test 
	public void asCartesianCoodinateTest() {
		CartesianCoordinate asCartesian = spericCoordinateB.asCartesianCoordinate();
		assertEquals(asCartesian.getXCoordinate(), 1.585999436 , DELTA);
		assertEquals(asCartesian.getYCoordinate(), 2.803244514 , DELTA);
		assertEquals(asCartesian.getZCoordinate(), 4.532110544 , DELTA);
	}
	
	@Test 
	public void asSpericCoodinateTest() {
		SphericCoordinate compare = spericCoordinateB.asSphericCoordinate();
		assertEquals(spericCoordinateB, compare);
	
	}
	@Test
	public void getSphericDistanceTest() {
		// TODO
	}
	
	

}
