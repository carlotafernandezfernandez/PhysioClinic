package PhysioClinicJDBC;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import PhysioClinicIFaces.ClientManager;
import PhysioClinicIFaces.EngineerManager;
import PhysioClinicIFaces.ExamsManager;
import PhysioClinicIFaces.ProstheticsManager;
import PhysioClinicPOJOs.Client;
import PhysioClinicPOJOs.Engineer;
import PhysioClinicPOJOs.Prosthetics;

public class JDBCProstheticsManager implements ProstheticsManager{
	
	private JDBCManager manager;
	
	public JDBCProstheticsManager (JDBCManager m) {
		this.manager = m;
	}

	@Override
	public List<Prosthetics> showAllProstheticsOfType(String type) {
		// TODO Auto-generated method stub
		List<Prosthetics> prosthetics = new ArrayList<Prosthetics>();
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM prosthetics WHERE prost_type="+type;
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				Integer id = rs.getInt("prost_id");
				String p_type = rs.getString("prost_type");
				Boolean cured = rs.getBoolean("cured");
				Date doB = rs.getDate("prost_doB");
				Date d_bought = rs.getDate("prost_dateBought");
				String inspections = rs.getString("prost_inspections");
				Integer eng_id = rs.getInt("eng_id");
				Engineer eng = null; 
				Integer client_id = rs.getInt("client_id");
				Client client = null; 
				eng = EngineerManager.searchEngineerByID(eng_id);
				client = ClientManager.searchClientByID(client_id);
				
				Prosthetics p = new Prosthetics(id, p_type, eng, client, inspections, doB, d_bought);
				prosthetics.add(p);
			}
			
			rs.close();
			stmt.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return prosthetics;
	}
		
}