package br.ufc.tabd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.ufc.tabd.connection.ConectionFactory;
import br.ufc.tabd.model.Ratings;

public class RatingsDAO {
	
public List<Ratings> getRatings() {
		
		List<Ratings> av = new ArrayList<Ratings>();
		Connection con;    
        PreparedStatement ps;
        String sql = "select * from rating";
        ResultSet rs;
        
		try {	
			
			con = new ConectionFactory().getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();	
			while(rs.next()) {
				
				Ratings r = new Ratings();
				r.setMovieid(rs.getInt("movieid"));
				r.setRating(rs.getDouble("rating"));
				r.setUserid(rs.getInt("userid"));
				av.add(r);
				
			}
			
			ps.close();
			rs.close();
			con.close();
			
		} catch (Exception e) {
			System.out.println("erro dao " + e.toString());
		}
		
		return av;
	}

	public List<Ratings> getIds() {
		
		List<Ratings> ids = new ArrayList<Ratings>();
		Connection con;    
        PreparedStatement ps;
        String sql = "select distinct userid from rating limit 30000";
        ResultSet rs;
        
		try {	
			
			con = new ConectionFactory().getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();	
			while(rs.next()) {
				
				Ratings r = new Ratings();
				//r.setMovieid(rs.getInt("movieid"));
				//r.setRating(rs.getDouble("rating"));
				r.setUserid(rs.getInt("userid"));
				ids.add(r);
				
			}
			
			ps.close();
			rs.close();
			con.close();
			
		} catch (Exception e) {
			System.out.println("erro dao " + e.toString());
		}
		
		return ids;
	}
	
	public List<Ratings> getRatingById( int id) {
		
		List<Ratings> userRating = new ArrayList<Ratings>();
		Connection con;    
        PreparedStatement ps;
        String sql = "select * from rating where userid = " + id + " and rating >= 4";
        ResultSet rs;
        
		try {	
			
			con = new ConectionFactory().getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();	
			while(rs.next()) {
				
				Ratings r = new Ratings();
				r.setMovieid(rs.getInt("movieid"));
				r.setRating(rs.getDouble("rating"));
				r.setUserid(rs.getInt("userid"));
				userRating.add(r);
				
			}
			
			ps.close();
			rs.close();
			con.close();
			
		} catch (Exception e) {
			System.out.println("erro dao " + e.toString());
		}
		
		return userRating;
	}
	
	
	


}
