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
package org.wahlzeit.exceptions;

import java.util.logging.Logger;

public class SettingErrorException extends IllegalStateException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(SettingErrorException.class.getName());

	public SettingErrorException() {
		super();
	}

	public SettingErrorException(double should, double is) {
		log.warning("An error occured: the set value should be " + should + " but was " + is);
	}
}
