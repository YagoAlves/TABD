package br.ufc.tabd.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.ufc.tabd.model.Roads;

public class RoadsDAO {
	
public List<Roads> findAll () {
		
		List<Roads> r = new ArrayList<Roads>();
		Connection con;    
        PreparedStatement ps;
        String sql = "select id_edge, id_source,id_target,coast from roads";
        ResultSet rs;
		try {
			
			con = new ConectionFactory().getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();	
			while(rs.next()) {
				
				Roads r1 = new Roads();
				r1.setId_edge(Integer.parseInt(rs.getString("id_edge")));
				r1.setCost_id(rs.getDouble("coast"));
				r1.setId_target(Integer.parseInt(rs.getString("id_target")));
				r1.setId_source(Integer.parseInt(rs.getString("id_source")));
				r.add(r1);
			}
			
		} catch (Exception e) {
			System.out.println("erro");
		}
		return r;
	}

}
