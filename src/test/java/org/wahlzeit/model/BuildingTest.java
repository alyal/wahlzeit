package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class BuildingTest {
	
	Location location;
	
	@Before
	public void setUp() {
		this.location = new Location(CartesianCoordinate.createCartesianCoordinate(1.0, 1.0, 1.0));
	}
	
	@Test
	public void createBuildingTestDefault() {
		Building building = new Building();
		assertNotNull(building);
		assertEquals(building.getConstructionYear(),1900);
		assertEquals(building.getName(), "Unknown");
		assertNotNull(building.getLocation());
	}
	
	@Test
	public void createBuildingTest() {		
		Building building = new Building(2017, "Building", location);
		assertNotNull(building);
		assertEquals(building.getConstructionYear(),2017);
		assertEquals(building.getName(), "Building");
		assertEquals(building.getLocation(), location);
	}
	
	@Test
	public void setBuildingTest() {
		Building building = new Building();
		assertNotNull(building);
		building.setConstructionYear(1234);
		building.setLocation(location);
		building.setName("Amazing building");
		assertEquals(building.getConstructionYear(),1234);
		assertEquals(building.getName(), "Amazing building");
		assertEquals(building.getLocation(), location);
	}
	
	@Test
	public void getBuildingTest() {
		Building building = new Building(2017, "Building", location);		
		assertEquals(building.getConstructionYear(),2017);
		assertEquals(building.getName(), "Building");
		assertEquals(building.getLocation(), location);
	}

}
