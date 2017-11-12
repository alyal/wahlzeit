package org.wahlzeit.model;

import static org.junit.Assert.assertNotNull;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;

public class BuildingPhotoTest {

	/**
	 * This rule is needed to run the test. It provides an environment for the GAE.
	 * See {@LocalDatastoreServiceTestConfigProvider}.
	 * If this rule is missing, the test will fail with an NullpointerException
	 * cause no API for an environment is registered. 
	 * I used this ruleChain as presented in lectures slides B02 on page 27
	 */
	@ClassRule
	public static RuleChain ruleChain = RuleChain.outerRule(new LocalDatastoreServiceTestConfigProvider());

	@Test
	public void createBuildingsPhotoTestDefault() {		
		BuildingPhoto photo = new BuildingPhoto();
		assertNotNull(photo);
	}
	
	@Test
	public void createBuildingsPhotoTest() {
		Building building = new Building();
		BuildingPhoto photo = new BuildingPhoto(building);
		assertNotNull(photo);
	}

}
