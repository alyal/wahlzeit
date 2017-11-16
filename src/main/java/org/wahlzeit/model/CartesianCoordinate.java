package org.wahlzeit.model;

public class CartesianCoordinate implements ICoordinate {

	private double x = 1.0;
	private double y = 1.0;
	private double z = 1.0;

	/**
	 * @methodtype constructor
	 */
	public CartesianCoordinate() {

	}

	/**
	 * @methodtype constructor
	 */
	public CartesianCoordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		return this;
	}

	@Override
	public double getCartesianDistance(Coordinate coordinate) {
		if (coordinate == null) {
			throw new IllegalArgumentException("null as an argument is not allowed!");
		}
		double deltaX = x - coordinate.getXCoordinate();
		double deltaY = y - coordinate.getYCoordinate();
		double deltaZ = z - coordinate.getZCoordinate();
		return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2) + Math.pow(deltaZ, 2));
	}

	@Override
	public SphericCoordinate asSphericCoordinate() {
		return computeSpehric();
	}

	@Override
	public double getSphericDistance(Coordinate cor) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getDistance(Coordinate cor) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEqual(Coordinate cor) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @methodtype conversion
	 * @methodproperty composed
	 */
	private SphericCoordinate computeSpehric() {
		double radius = calculateRadius();
		if (radius != 0.0) {
			// latitude
			double phi = calculatePhi(radius);
			// longitude
			double theta = calculateTheta();

			return new SphericCoordinate(radius, phi, theta);
		}
		return new SphericCoordinate(0.0, 0.0, 0.0);
	}

	/**
	 * 
	 */
	private double calculateRadius() {
		return Math.sqrt(Math.pow(this.getXCoordinate(), 2) + Math.pow(this.getYCoordinate(), 2)
				+ Math.pow(this.getZCoordinate(), 2));
	}

	/**
	 * 
	 */
	private double calculatePhi(double radius) {
		return Math.acos(this.getZCoordinate() / radius);
	}

	/**
	 * 
	 */
	private double calculateTheta() {
		return Math.atan2(this.getYCoordinate(), this.getXCoordinate());
	}

	/**
	 * 
	 */
	public double getXCoordinate() {
		return x;
	}

	/**
	 * 
	 */
	public double getYCoordinate() {
		return y;
	}

	/**
	 * 
	 */
	public double getZCoordinate() {
		return z;
	}

	/**
	 * 
	 */
	public void setXCoordinate(double x) {
		this.x = x;
	}

	/**
	 * 
	 */
	public void setYCoordinate(double y) {
		this.y = y;
	}

	/**
	 * 
	 */
	public void setZCoordinate(double z) {
		this.z = z;
	}

}
