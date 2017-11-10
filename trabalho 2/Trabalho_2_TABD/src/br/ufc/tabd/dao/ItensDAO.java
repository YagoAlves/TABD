package br.ufc.tabd.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.ufc.tabd.connection.ConectionFactory;
import br.ufc.tabd.model.Itens;

public class ItensDAO {
	
	public List<Itens> getItens () {
		
		List<Itens> ilist = new ArrayList<Itens>();
		Connection con;    
        PreparedStatement ps;
        String sql = "select r.userid,r.rating,m.title,m.genres "
        		+ "from movies m,rating r where rating >= 4 limit 50000";
        ResultSet rs;
        
		try {	
			
			con = new ConectionFactory().getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();	
			while(rs.next()) {
				
				Itens i = new Itens();
				i.setGenres(rs.getString("genres"));
				i.setRating(rs.getDouble("rating"));
				i.setTitle(rs.getString("title"));
				i.setUserid(rs.getInt("userid"));
				ilist.add(i);
				
			}
			
			ps.close();
			rs.close();
			con.close();
			
		} catch (Exception e) {
			System.out.println("erro dao " + e.toString());
		}
		
		return ilist;
	}

}
