package br.ufc.tabd.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

import br.ufc.tabd.connection.RoadsDAO;
import br.ufc.tabd.connection.VerticesDAO;
import br.ufc.tabd.model.Roads;
import br.ufc.tabd.model.Vertices;

public class Dijkstra {

	VerticesDAO vdao = new VerticesDAO();
	RoadsDAO rdao =new RoadsDAO();
	double INFINITY = Double.MAX_VALUE;
	List <Vertices> v = vdao.findAll();
	List <Roads> r = rdao.findAll();
	HashMap<Integer, Double> distances; 
	
	public double dijkstra(int idA, int idB ) {
		
		distances = new HashMap<>();
		for(Vertices v1 : v) {
			distances.put(v1.getId(), INFINITY);
		}
		
		PriorityQueue<Vertices> q = new PriorityQueue<>();
		Vertices v3 = findVertex(idA);
		v3.setId(idA);
		v3.setEstimate(0.0);
		distances.put(v3.getId(), 0.0);
		q.add(v3);
		
		while (!q.isEmpty()) {
			
			Vertices v4 = q.poll();
			if(v4.isOpen()) {
				v4.setOpen(false);
				double uest = distances.get(v4.getId());
				for (Roads r: findRoads((v4.getId()))) {
					Vertices v5 = findVertex((r.getId_target()));
					double vest = distances.get(v5.getId());
					if(vest > (uest + r.getCost_id())) {
						v5.setEstimate(r.getCost_id() + uest);
						distances.put(v5.getId(), (uest + r.getCost_id()));
						q.add(v5);
					}
				}	
			}
			
		}
		
		return distances.get(idB);
		
	}
	
	//checa o id do vertice
	public Vertices findVertex(int id){
		for (Vertices ver : v) {
			if(ver.getId() == id)
				return ver;
		}
		return null;
	}
	//acha a roads correspondente ao vertice 
	private List<Roads> findRoads(int idVertice){
		List<Roads> egs = new ArrayList<Roads>();
		for (Roads ro : r) {
			if(ro.getId_source() == idVertice)
				egs.add(ro);
		}
		return egs;
	}
	
	
}
