package br.ufc.tabd.algorithms;

import java.io.IOException;
import java.util.List;

import br.ufc.tabd.model.Tdrives;

public class Main {

	public static void main(String[] args) throws IOException {
		
				MapMatching m = new MapMatching();
				DbScan d = new DbScan();
				
				System.out.println("Consultando os dados...");
				List<Tdrives> clusters = m.readCSV();
				System.out.println("Mapeando...");
				m.map(clusters);
				System.out.println("Reclusterizando...");
				d.dbSCAN(10, 0.01, clusters);// dbscan com distancia de rede
				System.out.println("finalizando");
				d.writeCSV(clusters);
		
	}
}

	


