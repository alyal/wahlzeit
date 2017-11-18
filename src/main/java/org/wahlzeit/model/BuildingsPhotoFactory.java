package org.wahlzeit.model;

import java.util.logging.Logger;

import org.wahlzeit.services.LogBuilder;

public class BuildingsPhotoFactory extends PhotoFactory {

	private static final Logger log = Logger.getLogger(BuildingsPhotoFactory.class.getName());

	/**
	 * Hidden singleton instance; needs to be initialized from the outside.
	 */
	private static BuildingsPhotoFactory instance;

	/**
	 *
	 */
	protected BuildingsPhotoFactory() {
		
	}

	/**
	 * Hidden singleton instance; needs to be initialized from the outside.
	 */
	public static void initialize() {
		getInstance(); // drops result due to getInstance() side-effects
	}

	/**
	 * Public singleton access method.
	 * @methodtype get
	 */
	public static synchronized BuildingsPhotoFactory getInstance() {
		if (instance == null) {
			log.config(LogBuilder.createSystemMessage().addAction("setting generic BuildingsPhotoFactory").toString());
			setInstance(new BuildingsPhotoFactory());
		}

		return instance;
	}

	/**
	 * Method to set the singleton instance of PhotoFactory.
	 */
	protected static synchronized void setInstance(BuildingsPhotoFactory buildingsPhotoFactory) {
		if (instance != null) {
			throw new IllegalStateException("attempt to initalize BuildingsPhotoFactory twice");
		}

		instance = buildingsPhotoFactory;
	}

	// Instantiate Building Photo:

	/**
	 * @methodtype factory
	 */
	@Override
	public Photo createPhoto() {
		return new BuildingPhoto();
	}

	/**
	 * @methodtype factory
	 */
	@Override
	public Photo createPhoto(PhotoId id) {
		return new BuildingPhoto(id);
	}
	
	/**
	 * @methodtype factory
	 */
	
	public BuildingPhoto createBuildingsPhoto(Building building) {
		return new BuildingPhoto(building);
	}

}
