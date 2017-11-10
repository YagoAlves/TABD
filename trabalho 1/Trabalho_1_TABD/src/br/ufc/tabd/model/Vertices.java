package br.ufc.tabd.model;

public class Vertices implements Comparable<Vertices>{
	
	 private int id;
	 private float longitude;
	 private float latitude; 
	 private double estimate;
	 private Vertices ancestor;
	 
	 public double getEstimate() {
		return estimate;
	}
	public void setEstimate(double estimate) {
		this.estimate = estimate;
	}
	public Vertices getAncestor() {
		return ancestor;
	}
	public void setAncestor(Vertices ancestor) {
		this.ancestor = ancestor;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public boolean open = true;
	 																																																																																																												
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	
	//compara os vertices (comparable)
	public int compareTo(Vertices o) {
		double result = this.estimate - o.estimate;
		if(result > 0)
			return 1;
		else if(result < 0)
			return -1;
		else
			return 0;
	}
	
	
}
