package br.ufc.tabd.model;

import java.sql.Date;

public class Tdrives {
	
	public static int CORE_POINT = 1;
	public static int BORDER_POINT = 0;
	public static int OUTLIER = -1;
	
	private int vertice;
	private int classification;
	private int cluster;
	private boolean visited;
	private Date data_hora;
	private int id;
	private float longitude;
	private float latitude;
	
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
	public Date getData_hora() {
		return data_hora;
	}
	public void setData_hora(Date data_hora) {
		this.data_hora = data_hora;
	}
	
	public int getCluster() {
		return cluster;
	}
	public void setCluster(int cluster) {
		this.cluster = cluster;
	}
	public boolean isVisited() {
		return visited;
	}
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	public int getClassification() {
		return classification;
	}
	public void setClassification(int classification) {
		this.classification = classification;
	}
	
	public boolean equals(Tdrives t1 , Tdrives t2) {
		if (t1.getId() == t2.getId())
			return true;
		return false;
	}
	public int getVertice() {
		return vertice;
	}
	public void setVertice(int vertice) {
		this.vertice = vertice;
	}
	
	
}
