package br.ufc.tabd.apriori;

import br.ufc.tabd.dao.ItensDAO;
import br.ufc.tabd.model.Itens;
import br.ufc.tabd.model.StrongRule;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


public class Apriori {
	
	ItensDAO idao = new ItensDAO();
	List<Itens> ilist = idao.getItens();
	HashMap<String,Integer> generos = new HashMap<>();
	List<String> itensfreq = new ArrayList<String>();
	HashMap<String,Integer> regras = new HashMap<>();
	List<String> permute = new ArrayList<String>();
	List<StrongRule> sr = new ArrayList<>();
	
	public static List<String> split(String s, char caractere){
		List<String> aux = new ArrayList<>();
		int ini = 0;
		for(int k=0,t=s.length();k<t;k++){
			if(s.charAt(k)==caractere){
				aux.add(s.substring(ini, k));
				ini = k+1;			}
		}
		aux.add(s.substring(ini, s.length()));
		return aux;
	}
	
	public void getCommonItens() {
		
		System.out.println("Criando itens frequentes....");
		for(Itens itens : ilist) {
			
			List<String> s = split(itens.getGenres(), '|');	
			addGenero(s);
		}
		
		Set<String> chaves = generos.keySet();
		for (String chave : chaves) {
			
			if (generos.get(chave) >= 1000) {
				itensfreq.add(chave);	
			}
		}
		
		System.out.println("Permutando....");
		permuta();
		System.out.println("Criando regras ....");
		createRoles();
		System.out.println("Salvando no csv....");
		printcsv();
		System.out.println("Fim!");
		
	}
	
	public void addGenero(List<String> s) {
		
		for (String s1 : s) {
			Integer i = generos.get(s1);
			i = (i==null)?1:i+1;
			generos.put(s1, i);
		}
	}
	
	public void permuta () {
		
		String [] vetor = new String[itensfreq.size()];
		for (int i = 0; i < itensfreq.size(); i++) {
			vetor[i] = itensfreq.get(i) + ",";
		}
		
		int[] indices;
		CombinationGenerator x = new CombinationGenerator (vetor.length, 2);
		StringBuffer combination;
		while (x.hasMore ()) {
		  combination = new StringBuffer ();
		  indices = x.getNext ();
		  for (int j = 0; j < indices.length; j++) {
		    combination.append (vetor[indices[j]]);
		  }
		permute.add(combination.toString());
		 //regras.put(combination.toString (), null);
		 // System.out.println (combination.toString ());
			}
	}
		
	
	public void createRoles () {
		
		for (String s : permute) {
			//System.out.println(s);
			for (Itens i : ilist) {
				String [] d =  s.split(",");
				if (i.getGenres().contains(d[0]) && 
						i.getGenres().contains(d[1]) /*&&
						i.getGenres().contains(d[2])*/) {
						addRegra(s);
				}	
			}
		}
		
		Set<String> chaves = regras.keySet();
		for (String chave : chaves) {
			if (regras.get(chave) >= 1000) {
				StrongRule rule = new StrongRule();
				rule.setGenero(chave);
				rule.setQuantidade(regras.get(chave));
				String [] a = chave.split(",");
				//System.out.println(regras.get(chave) +  a[0]);
				float c = createconfiability(regras.get(chave), a[0]);
				rule.setConfiabilidade(c);
				sr.add(rule);
			}
		}
	}
	
	public void addRegra(String s) {
		
		Integer i = regras.get(s);
		//System.out.println(s + i);
		i = (i==null)?1:i+1;
		regras.put(s, i);
		
		
	}
	
	public void printcsv () {
		
		try{
			PrintWriter writer = new PrintWriter("Regras6.csv", "UTF-8");
			writer.println("Generos;Quantidade;Confiabilidade");	
				for (StrongRule r : sr) {
					
					writer.println(r.getGenero()+";"+ 
					r.getQuantidade() +";"+ r.getConfiabilidade());
				
				}
			
			writer.close();
		}catch (Exception e) {
			System.out.println("erro csv "+e.toString());
		}
		
	}
	
	public float createconfiability(int quantidadeA, String A) {
		
		float c = 0;
		int j = 0;
		for (Itens i : ilist) {
			if (i.getGenres().contains(A)) {
				j++;
			}
		}
		
		c = ((quantidadeA*100)/j);
		return c;
		
	}
	
	public static void main(String[] args) {
		
		Apriori a = new Apriori();
		a.getCommonItens();
		
		Set<String> chaves = a.generos.keySet();
		for (String chave : chaves) {
			System.out.println( chave + " " + a.generos.get(chave) + chaves.size());
		}
		
		
		
/*		
 Set<String> chaves = a.regras.keySet();
		for (String chave : chaves) {
			System.out.println(chave + " " + a.regras.get(chave));
		}
		
		
		for (Itens i : a.ilist) {
			System.out.println(i.getGenres());
		}

		Set<String> chaves = a.generos.keySet();
		for (String chave : chaves) {
			System.out.println( chave + " " + a.generos.get(chave));
		}
		
		for (String s : a.itensfreq) {
			System.out.println(s + a.itensfreq.size());
		}
		
		*/	
	}

}
