package model;

public class Position {
	private double g_lat;
	private double g_lon;
	
	public Position() {
		this.g_lat = 0;
		this.g_lon = 0;
	}
	public Position(double lat, double lon) {
		this.g_lat = lat;
		this.g_lon = lon;
	}
	public double getG_lat() {
		return g_lat;
	}
	
	public void setG_lat(double g_lat) {
		this.g_lat = g_lat;
	}
	
	public double getG_lon() {
		return g_lon;
	}
	
	public void setG_lon(double g_lon) {
		this.g_lon = g_lon;
	}
	
	@Override
	public String toString() {
		return "Latitude :"+this.getG_lat()+" Long: "+this.getG_lon();
	}
	
}
