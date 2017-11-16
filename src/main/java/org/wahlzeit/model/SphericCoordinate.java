package org.wahlzeit.model;

public class SphericCoordinate implements ICoordinate{
	
	private double latitude = 0.0;
	private double longitude = 0.0;
	private double radius = 0.0;
	
	public SphericCoordinate() {
		
	}
	
	public SphericCoordinate(double radius, double latitude, double longitude) {
		this.radius = radius;
		this.latitude = latitude;
		this.longitude = longitude;
	} 

	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getCartesianDistance(Coordinate cor) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SphericCoordinate asSphericCoordinate() {
		// TODO Auto-generated method stub
		return null;
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
	// TODO
}
