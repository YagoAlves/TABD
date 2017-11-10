package br.ufc.tabd.algorithms;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import br.ufc.tabd.connection.TdriversDAO;
import br.ufc.tabd.model.Tdrives;

public class DbScan {
	
	List<Tdrives> tList = null;
	Dijkstra d = new Dijkstra();
	
	public void dbSCAN(int minPoints, double eps, List<Tdrives> t){
		//TdriversDAO tDao = new TdriversDAO();
		//tList = tDao.findAll("02:00:00", "03:00:00", "2008-02-03");
		tList = t;	
		int index = 0;
		
		for (Tdrives td : tList) {
			if(td.isVisited() == false){
				td.setVisited(true);
				
				ArrayList<Tdrives> neighbord = new ArrayList<Tdrives>(); 
				int index2 = neighborsPoint(td, eps, neighbord);
				
				if(index2 < minPoints){
					td.setCluster(Tdrives.OUTLIER);
				}else{
					index++;
					td.setCluster(index);
					td.setClassification(Tdrives.CORE_POINT);
					expandCluster(neighbord, index, minPoints, eps);
				}
			}
		}
			
		System.out.println("Quantidade de clusters: "+index);
		
	}
	
	private void expandCluster(ArrayList<Tdrives> neighbors, int index, int minPoint, double eps){
		System.out.println("Expandindo cluster "+index );
		
		for (Tdrives td : neighbors) {
			td.setCluster(index);
		}
		
		while(neighbors.size() > 0){
			Tdrives t = neighbors.get(0);
			neighbors.remove(0);
			
			if(t.isVisited() == false){
				t.setVisited(true);
				ArrayList<Tdrives> neighborsOfPoint = new ArrayList<Tdrives>(); 
				int index2 = neighborsPoint(t, eps, neighborsOfPoint);
				
				for (Tdrives td : neighborsOfPoint) {
					td.setCluster(index);
				}
				
				if(index2 >= minPoint){
					t.setClassification(Tdrives.CORE_POINT);
					neighbors.addAll(neighborsOfPoint);
				}else{
					t.setClassification(Tdrives.BORDER_POINT);
				}	
			}	
		}
	}
	
	
	private int neighborsPoint(Tdrives t, double eps, ArrayList<Tdrives> neighbors){
		for (Tdrives td : tList) {
			//if(getDistance(t, td) < eps){
				double distance = d.dijkstra(t.getVertice(),td.getVertice());
			if (distance < eps) {
				neighbors.add(td);
			}
		}
		
		ArrayList<Tdrives> distincts = new ArrayList<Tdrives>();
		for (Tdrives td : neighbors) {
			if(!contains(distincts, td)){
				distincts.add(td);
			}
		}
		return distincts.size();
	}
	
	public static double getDistance (Tdrives p, Tdrives q)
	{

		double dx = p.getLatitude()-q.getLatitude();

		double dy = p.getLongitude()-q.getLongitude();

		double distance = Math.sqrt (dx * dx + dy * dy);

		return distance;

	}
	
	private boolean contains(ArrayList<Tdrives> tList, Tdrives t){
		for (Tdrives td : tList) {
			if(td.getId() == t.getId())
				return true;
		}
		return false;
	}
	
	public void writeCSV(List<Tdrives> list){
		try{
			PrintWriter writer = new PrintWriter("Dijkstra -2008-02-03.csv", "UTF-8");
			writer.println("student_id;id_taxista;weekday;latitude;longitude;cluster;iscore;vertex");
			for (Tdrives td : list) {
				writer.println("369609;"+td.getId()+"; 2008-02-03;"
						+td.getLatitude()+";"+td.getLongitude()+";"+
						td.getCluster()+";"+td.getClassification() +";"+ td.getVertice());
			}
			writer.close();
		}catch (Exception e) {
			System.out.println("erro csv "+e.toString());
		}
	}
	
	/*public static void main(String[] args) {
		DbScan d = new DbScan();
		long start = System.currentTimeMillis();
		d.dbSCAN(30,0.003);
		long elapsed = System.currentTimeMillis() - start;
		System.out.println(elapsed/1000+" segundos");
		System.out.println("Gravando csv");
		//d.writeCSV(tList);
		System.out.println("CSV gravado");
	}*/
	
}
	
