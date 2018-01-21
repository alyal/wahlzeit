package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class BuildingTest {

	Location location;
	BuildingsType testType;

	@Before
	public void setUp() {
		this.location = new Location(CartesianCoordinate.createCartesianCoordinate(1.0, 1.0, 1.0));
		this.testType = new BuildingsType("test", "testArchitecture");
	}

	@Test
	public void createBuildingTest() {
		Building building = new Building(2017, "Building", location, testType);
		assertNotNull(building);
		assertEquals(building.getConstructionYear(), 2017);
		assertEquals(building.getName(), "Building");
		assertEquals(building.getLocation(), location);
		assertEquals(building.getTyp().getName(), "test");
		assertEquals(building.getID(), "2017/Building/test/Lat:0.7853981633974483/Long:0.9553166181245092");
	}

	@Test
	public void setBuildingTest() {
		CartesianCoordinate testCoordinate = CartesianCoordinate.createCartesianCoordinate(1.0, 1.0, 1.0);
		Location testLocation = new Location(testCoordinate);
		Building building = new Building(2000, "Bla", testLocation, testType);
		assertNotNull(building);
		building.setConstructionYear(1234);
		building.setLocation(location);
		building.setName("Amazing building");
		assertEquals(building.getConstructionYear(), 1234);
		assertEquals(building.getName(), "Amazing building");
		assertEquals(building.getLocation(), location);
		assertEquals(building.getTyp().getName(), "test");
	}

	@Test
	public void getBuildingTest() {
		Building building = new Building(2017, "Building", location, testType);
		assertEquals(building.getConstructionYear(), 2017);
		assertEquals(building.getName(), "Building");
		assertEquals(building.getLocation(), location);
		assertEquals(building.getTyp().getName(), "test");
	}

}
