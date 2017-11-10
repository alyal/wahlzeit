package org.wahlzeit.model;

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

	
	
	public BuildingPhoto() {
		super();
	}

	public BuildingPhoto(PhotoId photoId) {
		super(photoId);
	}

	public BuildingPhoto(Building building) {
		super();
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
		this.building = building;
	}
}
