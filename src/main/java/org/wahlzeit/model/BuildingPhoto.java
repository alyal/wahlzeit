package org.wahlzeit.model;

import org.wahlzeit.utils.AssertionUtils;

import com.googlecode.objectify.annotation.Subclass;

/**
 * 
 * BuildingsPhoto represents a photo of a building uploaded by a user. It is a
 * subclass of {@Photo}.
 */

@Subclass
public class BuildingPhoto extends Photo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Building building;
	private final String className = this.getClass().getSimpleName();

	public BuildingPhoto() {
		super();
	}

	public BuildingPhoto(PhotoId photoId) {
		super(photoId);
	}

	/**
	 * 
	 * @param building
	 * @throws IllegalArgumentException
	 */
	public BuildingPhoto(Building building) throws IllegalArgumentException {
		super();
		AssertionUtils.assertNotNull(building, this.getClass().getName());
		this.building = building;
	}

	/**
	 * @methodtype get
	 * @methodproperty primitive
	 */
	public Building getBuilding() {
		return building;
	}

	/**
	 * @methodtype set
	 * @methodproperty primitive
	 */
	public void setBuilding(Building building) {
		AssertionUtils.assertNotNull(building, className);
		this.building = building;
	}
}
