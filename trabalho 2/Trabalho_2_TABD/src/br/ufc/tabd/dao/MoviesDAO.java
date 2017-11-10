package br.ufc.tabd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.ufc.tabd.connection.ConectionFactory;
import br.ufc.tabd.model.Movies;

public class MoviesDAO {
	
	public List<Movies> getMovies() {
		
		List<Movies> filmes = new ArrayList<Movies>();
		Connection con;    
        PreparedStatement ps;
        String sql = "select * from movies";
        ResultSet rs;
        
		try {	
			
			con = new ConectionFactory().getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();	
			while(rs.next()) {
				
				Movies m = new Movies();
				m.setGenres(rs.getString("genres"));
				m.setMovieid(rs.getInt("movieid"));
				m.setTitle(rs.getString("title"));
				filmes.add(m);
				
			}
			
			ps.close();
			rs.close();
			con.close();
			
		} catch (Exception e) {
			System.out.println("erro dao " + e.toString());
		}
		
		
		return filmes;
	}

}
