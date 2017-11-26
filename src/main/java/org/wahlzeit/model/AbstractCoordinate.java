package org.wahlzeit.model;

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
	public double getCartesianDistance(Coordinate cor) {
		assertNotNull(cor);
		CartesianCoordinate asCartesian = this.asCartesianCoordinate();
		return asCartesian.getDistance(cor);
	}

	/**
	 * 
	 */
	@Override
	public double getSphericDistance(Coordinate cor) {
		assertNotNull(cor);
		SphericCoordinate asSpheric = this.asSphericCoordinate();
		return asSpheric.getDistance(cor);
	};

	/**
	 * 
	 */
	@Override
	public double getDistance(Coordinate cor) {
		assertNotNull(cor);
		return calculateDistance(cor);
	}

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

}
