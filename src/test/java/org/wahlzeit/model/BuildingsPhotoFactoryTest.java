package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.wahlzeit.exceptions.PhotoFactoryCreationException;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;

public class BuildingsPhotoFactoryTest {

	BuildingsPhotoFactory instance;

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
		this.instance = new BuildingsPhotoFactory();
		this.instance.initialize();
	}

	@Test
	public void initializeTest() {
		BuildingsPhotoFactory instance = new BuildingsPhotoFactory();
		instance.initialize();
		assertNotNull(instance);
		assertEquals(instance instanceof BuildingsPhotoFactory, true);
	}

	@Test
	public void createPhotoTest() throws PhotoFactoryCreationException {
		Photo photo = instance.createPhoto();
		assertNotNull(photo);
		assertNotNull(photo.getId());

	}

	@Test
	public void createPhotoTestWithId() throws PhotoFactoryCreationException {
		Photo photo = instance.createPhoto(new PhotoId(15));
		assertNotNull(photo);

		PhotoId id = photo.getId();
		int idAsInt = id.asInt();
		assertEquals(idAsInt, 15);

	}

	@Test
	public void createPhotoTestWithBuilding() throws PhotoFactoryCreationException {
		instance.createBuildingsPhoto(new Building());
	}

	@Test(expected = PhotoFactoryCreationException.class)
	public void createBuildingsPhotoErrorTest() throws PhotoFactoryCreationException {
		Building b = null;
		instance.createBuildingsPhoto(b);
	}

	@Test(expected = PhotoFactoryCreationException.class)
	public void createPhotoWithIdErrorTest() throws PhotoFactoryCreationException {
		instance.createPhoto(null);
	}

}
