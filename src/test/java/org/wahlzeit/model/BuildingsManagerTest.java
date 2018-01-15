/*
 * Copyright (c) 2018 by alyal, https://github.com/alyal
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.wahlzeit.exceptions.BuildingsTypeDoesNotExistException;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;

public class BuildingsManagerTest {

	private BuildingsManager manager;

	private BuildingsType testType;

	/**
	 * This rule is needed to run the test. It provides an environment for the GAE.
	 * See {@LocalDatastoreServiceTestConfigProvider}. If this rule is missing, the
	 * test will fail with an NullpointerException, because no API for an
	 * environment is registered. I used this ruleChain as presented in lecture
	 * slides B02 on page 27
	 */
	@ClassRule
	public static RuleChain ruleChain = RuleChain.outerRule(new LocalDatastoreServiceTestConfigProvider());

	@Before
	public void setup() {
		manager = BuildingsManager.getBuildingsManagerInstance();
		testType = manager.createBuildingsType("testType", "testArchitecture");
	}

	@Test
	public void getInstanceTest() {
		assertNotNull(manager);
	}

	@Test
	public void createBuildingTest() {
		Building building = manager.createBuilding("testType");
		assertNotNull(building);
		assertEquals(building.getTyp().getName(), "testType");
		assertEquals(building.getTyp().getArchitecture(), "testArchitecture");
		assertEquals(building.getConstructionYear(), 1900);
	}

	@Test
	public void createBuildingTestWithParameters() {
		CartesianCoordinate testCoordinate = CartesianCoordinate.createCartesianCoordinate(1.0, 1.0, 1.0);
		Location testLocation = new Location(testCoordinate);
		Building building = manager.createBuilding(2000, "TestName", testLocation, "testType");
		assertNotNull(building);
		assertEquals(building.getTyp().getName(), "testType");
		assertEquals(building.getTyp().getArchitecture(), "testArchitecture");
		assertEquals(building.getConstructionYear(), 2000);
		assertEquals(building.getLocation(), testLocation);
		assertEquals(building.getName(), "TestName");
	}

	@Test(expected = BuildingsTypeDoesNotExistException.class)
	public void createBuildingTestWithNotExitstingTxpe() {
		Building building = manager.createBuilding("testType2");
		assertNotNull(building);
	}

}
