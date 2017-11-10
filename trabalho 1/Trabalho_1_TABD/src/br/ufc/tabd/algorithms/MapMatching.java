package br.ufc.tabd.algorithms;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.ufc.tabd.connection.VerticesDAO;
import br.ufc.tabd.model.Tdrives;
import br.ufc.tabd.model.Vertices;

public class MapMatching {
	
	
	List<Vertices> v;
	
	//Pega a lista de vertices(Tdrives)
	public void map(List<Tdrives> tList) {
		
		VerticesDAO vdao = new VerticesDAO();
		v = vdao.findAll();
		for (Tdrives td : tList) {
			td.setVertice(findVertex(td));
		}
	}
	
	//pega o vertice mais proximo do ponto
	private int findVertex (Tdrives t) {
		
		Vertices v1 = v.get(0); 
		for (Vertices v2 : v ) {
			Tdrives t1 = new Tdrives(); t1.setLatitude(v2.getLatitude()); t1.setLongitude(v2.getLongitude());
			Tdrives t2 = new Tdrives(); t2.setLatitude(v1.getLatitude()); t2.setLongitude(v1.getLongitude());
			if (DbScan.getDistance(t, t1) < DbScan.getDistance(t, t2)) {
				v1 = v2;
			}
		}
		return v1.getId();
	}
	//ler o arquivo de resultados
	public List<Tdrives> readCSV () throws IOException {
		List<Tdrives> tList = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader("Dados-2008-02-03.csv"));
		String line = "";
		br.readLine();
		
		while ((line = br.readLine()) != null) {
		    
			Tdrives t = new Tdrives();
			String[] row = line.split(";");
			
			t.setId(Integer.parseInt(row[1]));
		    t.setLatitude(Float.parseFloat(row[3]));
		    t.setLongitude(Float.parseFloat(row[4]));
		    t.setCluster(Integer.parseInt(row[5]));
		    t.setClassification(Integer.parseInt(row[6]));
		    
		    tList.add(t);
		}
		return tList;
	}
	
	public static void main(String[] args) throws IOException {
		
		MapMatching mapping = new MapMatching();
		DbScan dScan = new DbScan();
		
		List<Tdrives> tList = mapping.readCSV();
		System.out.println("mapeando");
		mapping.map(tList);
		dScan.writeCSV(tList);
		System.out.println("Terminado");
		
	} 
	

}
 