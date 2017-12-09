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
