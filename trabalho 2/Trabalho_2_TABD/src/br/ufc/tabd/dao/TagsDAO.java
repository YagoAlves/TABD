package br.ufc.tabd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import br.ufc.tabd.connection.ConectionFactory;
import br.ufc.tabd.model.Tags;

public class TagsDAO{
	
public List<Tags> getTags() {
		
		List<Tags> tags = new ArrayList<Tags>();
		Connection con;    
        PreparedStatement ps;
        String sql = "select * from tags";
        ResultSet rs;
        
		try {	
			
			con = new ConectionFactory().getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();	
			
			while(rs.next()) {
				
				Tags t = new Tags();
				//t.setMovieid(rs.getInt(rs.getString("movieid")));
				t.setTag(rs.getString("tag"));
				t.setUserid(rs.getInt("userid"));
				tags.add(t);
				
			}
			
			ps.close();
			rs.close();
			con.close();
			
		} catch (Exception e) {
			System.out.println("erro dao " + e.toString());
		}
		
		
		return tags;
	}


}
