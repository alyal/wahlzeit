package org.wahlzeit.exceptions;

import java.util.logging.Logger;

public class NegativeDistanceException extends IllegalStateException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(NegativeDistanceException.class.getName());

	public NegativeDistanceException() {
		super();
	}

	public NegativeDistanceException(double distance) {
		log.info("The distance was negative. Calculated value was: " + distance);
	}

}
