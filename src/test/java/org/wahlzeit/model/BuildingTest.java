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
		this.testType = new BuildingsType("test");
	}

	@Test
	public void createBuildingTestDefault() {
		Building building = new Building(testType);
		assertNotNull(building);
		assertEquals(building.getConstructionYear(), 1900);
		assertEquals(building.getName(), "Unknown");
		assertNotNull(building.getLocation());
		assertEquals(building.getTyp().getName(), "test");
	}

	@Test
	public void createBuildingTest() {
		Building building = new Building(2017, "Building", location, testType);
		assertNotNull(building);
		assertEquals(building.getConstructionYear(), 2017);
		assertEquals(building.getName(), "Building");
		assertEquals(building.getLocation(), location);
		assertEquals(building.getTyp().getName(), "test");
	}

	@Test
	public void setBuildingTest() {
		Building building = new Building(testType);
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
