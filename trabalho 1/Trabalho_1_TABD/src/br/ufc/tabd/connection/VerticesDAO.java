package br.ufc.tabd.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.ufc.tabd.model.Vertices;

public class VerticesDAO {
	
	public List<Vertices> findAll () {
		
		List<Vertices> v = new ArrayList<Vertices>();
		Connection con;    
        PreparedStatement ps;
        String sql = "select id,latitude,longitude from vertices";
        ResultSet rs;
	try {
			
			con = new ConectionFactory().getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();	
			while(rs.next()) {
				
				Vertices v1 = new Vertices();
				v1.setId(Integer.parseInt(rs.getString("id")));
				v1.setLatitude(Float.parseFloat(rs.getString("latitude")));
				v1.setLongitude(Float.parseFloat(rs.getString("longitude")));
				v.add(v1);
			}
			
		} catch (Exception e) {
			System.out.println("erro");
		}
		return v;
	}
	
	
}
