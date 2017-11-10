package br.ufc.tabd.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.ufc.tabd.model.Tdrives;

public class TdriversDAO {
	
	public List<Tdrives> findAll (String hourS, String hourF, String data) {
		
		List<Tdrives> td = new ArrayList<Tdrives>();
		Connection con;    
        PreparedStatement ps;
        String sql = "select * from tdrive where data_hora BETWEEN '"+data+" "
        		+hourS+"' AND '"+data+" " +hourF+"'";
        ResultSet rs;
		try {	
			
			con = new ConectionFactory().getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();	
			while(rs.next()) {
				
				Tdrives t = new Tdrives();
				t.setVisited(false);
				t.setId(Integer.parseInt(rs.getString("id_taxista")));
				t.setLatitude(Float.parseFloat(rs.getString("latitude")));
				t.setLongitude(rs.getFloat("longitude"));
				td.add(t);
				
			}
			
			ps.close();
			rs.close();
			
		} catch (Exception e) {
			System.out.println("erro dao " + e.toString());
		}
		return td;
	}
	
	
}
