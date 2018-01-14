package org.wahlzeit.exceptions;

import java.util.logging.Logger;

public class BuildingsTypeDoesNotExistException extends IllegalArgumentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(BuildingsTypeDoesNotExistException.class.getName());

	public BuildingsTypeDoesNotExistException() {
		log.warning("The requested buildings type does not exist!");
	}

}
