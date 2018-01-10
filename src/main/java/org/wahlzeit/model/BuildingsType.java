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

public class BuildingsType {

	private String typeName;

	/**
	 * 
	 * @param name
	 */
	public BuildingsType(String name) {
		this.typeName = name;
	}

	/**
	 * 
	 * @return
	 */
	public Building createInstance() {
		return new Building(this);
	}

	/**
	 * 
	 * @param year
	 * @param name
	 * @param location
	 * @return
	 */
	public Building createInstance(int year, String name, Location location) {
		return new Building(year, name, location, this);
	}

	/**
	 * 
	 * @return
	 */
	public String getName() {
		return typeName;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.typeName = name;
	}

}
