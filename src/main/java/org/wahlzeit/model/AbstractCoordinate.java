package org.wahlzeit.model;

import org.wahlzeit.utils.ParamsUtil;

import com.google.appengine.api.taskqueue.InternalFailureException;

public abstract class AbstractCoordinate implements Coordinate {

	/**
	 * 
	 */
	@Override
	public abstract CartesianCoordinate asCartesianCoordinate();

	/**
	 * 
	 */
	@Override
	public abstract SphericCoordinate asSphericCoordinate();

	/**
	 * 
	 */
	public abstract double calculateDistance(Coordinate cor);

	/**
	 * 
	 */
	@Override
	public double getDistance(Coordinate cor) {
		assertNotNull(cor);
		return getCartesianDistance(cor);
	};

	/**
	 * 
	 */
	@Override
	public double getCartesianDistance(Coordinate cor) {
		assertNotNull(cor);

		CartesianCoordinate asCartesian = this.asCartesianCoordinate();

		assertCartesianRepresenatation(asCartesian);

		return asCartesian.calculateDistance(cor);
	}

	/**
	 * 
	 */
	@Override
	public double getSphericDistance(Coordinate cor) {
		assertNotNull(cor);

		SphericCoordinate asSphericCoordinate = this.asSphericCoordinate();

		assertSphericRepresenatation(asSphericCoordinate);

		return asSphericCoordinate.calculateDistance(cor);
	};

	/**
	 * 
	 */
	@Override
	public abstract boolean isEqual(Coordinate cor);

	/**
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		assertNotNull(obj);
		assertInstanceOfCoordinate(obj);

		return isEqual((Coordinate) obj);
	}

	/**
	 * Defined abstract hashCode here, so it is necessary to override/ define
	 * hashCode method in extended subclass.
	 */
	@Override
	public abstract int hashCode();

	/**
	 * @methodtype assertion
	 */
	public void assertInstanceOfCoordinate(Object o) {
		if (!(o instanceof Coordinate)) {
			throw new IllegalArgumentException("Passed argument is not an instance of Coordinate!");
		}
	}

	/**
	 * @methodtype assertion
	 */
	public void assertNotNull(Object o) {
		if (o == null) {
			throw new IllegalArgumentException("null as an argument is not allowed!");
		}
	}

	/**
	 * @methodtype assertion
	 */
	public void assertRadius(double radius) {
		if (radius < 0) {
			throw new IllegalArgumentException("Values smaller 0 for radius are not allowed!");
		}
	}

	/**
	 * @methodtype assertion
	 */
	public void assertLatitude(double latitude) {
		if (latitude < ParamsUtil.MIN_LATITUDE || latitude > ParamsUtil.MAX_LATITUDE) {
			throw new IllegalArgumentException("Value of latitude must be between -90.00 and 90.00");
		}

	}

	/**
	 * @methodtype assertion
	 */
	public void assertLongitude(double longitude) {
		if (longitude < ParamsUtil.MIN_LONGITUDE || longitude > ParamsUtil.MAX_LONGITUDE) {
			throw new IllegalArgumentException("Value of longitude must be between -180.00 and 180.00");
		}
	}
	/**
	 * @methodtype assertion
	 */
	public void assertSphericRepresenatation(Coordinate isSpheric) {
		assertNotNull(isSpheric);
		if (!(isSpheric instanceof SphericCoordinate)) {
			// TODO
			// Not sure if this is a good exception for that, will maybe define own
			// Exception after next lecture about exception handling
			throw new InternalFailureException("the result is of wrong type!");
		}
	}

	/**
	 * @methodtype assertion
	 */
	public void assertCartesianRepresenatation(Coordinate isCartesian) {
		assertNotNull(isCartesian);
		if (!(isCartesian instanceof CartesianCoordinate)) {
			// TODO
			// Not sure if this is a good exception for that, will maybe define own
			// Exception after next lecture about exception handling
			throw new InternalFailureException("the result is of wrong type!");
		}
	}

	/**
	 * @methodtype assertion
	 */
	public void assertDistance(double distance) {
		if (distance <= 0) {
			// TODO
			// Not sure if this is a good exception for that, will maybe define own
			// Exception after next lecture about exception handling
			throw new InternalFailureException("A distance smaller than 0 is not possible!");
		}
	}

}
