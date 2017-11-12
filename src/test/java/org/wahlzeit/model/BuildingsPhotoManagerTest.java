package org.wahlzeit.model;

import static org.junit.Assert.assertNotNull;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;

public class BuildingsPhotoManagerTest {
	
	
	/**
	 * This rule is needed to run the test. It provides an environment for the GAE.
	 * See {@LocalDatastoreServiceTestConfigProvider}.
	 * If this rule is missing, the test will fail with an NullpointerException,
	 * because no API for an environment is registered. 
	 * I used this ruleChain as presented in lecture slides B02 on page 27
	 */
	@ClassRule
	public static RuleChain ruleChain = RuleChain.outerRule(new LocalDatastoreServiceTestConfigProvider());

	
	@Test
	public void initializeTest() {
		assertNotNull(new BuildingsPhotoManager());
	}
}
