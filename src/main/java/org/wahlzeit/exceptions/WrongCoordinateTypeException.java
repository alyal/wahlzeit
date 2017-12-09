package org.wahlzeit.exceptions;

import java.util.logging.Logger;

import org.wahlzeit.model.Coordinate;

public class WrongCoordinateTypeException extends IllegalStateException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(WrongCoordinateTypeException.class.getName());

	public WrongCoordinateTypeException() {
		// TODO Auto-generated constructor stub
	}

	public WrongCoordinateTypeException(Coordinate is) {
		log.warning("The Representation of coordinate is wrong. It was: " + is.getClass().getName());
	}

}
