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

public class PhotoFactoryCreationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(PhotoFactoryCreationException.class.getName());

	public PhotoFactoryCreationException() {
		super();
	}

	public PhotoFactoryCreationException(String message) {
		log.warning("An error while creating a Photo occured: " + message);
	}

	public PhotoFactoryCreationException(Object is, String message) {
		if (is != null) {
			log.warning("An error while creating a Photo with " + is.getClass().getName() + " occured: " + message);
		} else {
			log.warning("The passed argument was null!" + message);
		}
	}
}
