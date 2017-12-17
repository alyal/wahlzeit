/*
 * Copyright (c) 2017 by alyal, https://github.com/alyal
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
