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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;

public class BuildingsTypeTest {

	private BuildingsType testType;

	private BuildingsType testSubType;

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
	public void setUp() {
		testType = new BuildingsType("testName", "testArchitecture");
		testSubType = new BuildingsType("subType", "subTypeArchitecture");
	}

	@Test
	public void initalizationTest() {
		assertNotNull(testType);
	}

	@Test
	public void createInstanceWithParameters() {
		CartesianCoordinate testCoordinate = CartesianCoordinate.createCartesianCoordinate(1.0, 1.0, 1.0);
		Location testLocation = new Location(testCoordinate);
		Building testBuilding = testType.createInstance(2000, "Bla", testLocation);
		assertNotNull(testBuilding);
		assertEquals(testBuilding.getTyp().getName(), "testName");
		assertEquals(testBuilding.getLocation(), testLocation);
		assertEquals(testBuilding.getConstructionYear(), 2000);
		assertEquals(testBuilding.getTyp().getArchitecture(), "testArchitecture");
	}

	@Test
	public void getterAndSetterTest() {
		testType.setArchitecture("modern");
		testType.setName("skyscaper");
		assertEquals(testType.getArchitecture(), "modern");
		assertEquals(testType.getName(), "skyscaper");
	}

	@Test
	public void addSubTypeTest() {
		testType.addBuildingsSubType(testSubType);
		assertTrue(testType.isSubtype(testSubType));
		assertEquals(testSubType.getSuperType().getName(), "testName");
	}

	@Test
	public void isSubTypeTest() {
		testType.addBuildingsSubType(testSubType);
		assertTrue(testType.isSubtype(testSubType));
	}

	@Test
	public void isNoSubTypeTest() {
		BuildingsType notASubType = new BuildingsType("noSubtype", "noArchitecture");
		assertFalse(testType.isSubtype(notASubType));
	}

}
