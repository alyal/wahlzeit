package org.wahlzeit.exceptions;

import java.util.logging.Logger;

public class PhotoFactoryCreationException extends IllegalStateException {

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
		log.warning("An error while creating a Photo with " + is.getClass().getName() + " occured: " + message);
	}
}
