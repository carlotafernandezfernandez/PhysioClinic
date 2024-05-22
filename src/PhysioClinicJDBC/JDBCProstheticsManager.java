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
	private EngineerManager engineermanager; 
	private ClientManager clientmanager; 
	
	public JDBCProstheticsManager (JDBCManager m) {
		this.manager = m;
		this.engineermanager = new JDBCEngineerManager(m);
		this.clientmanager = new JDBCClientManager(m);
	}

	@Override
	public List<Prosthetics> showAllProstheticsOfType(String type) {
		// TODO Auto-generated method stub
		List<Prosthetics> prosthetics = new ArrayList<Prosthetics>();
		prosthetics = null; 
		Engineer eng = null; 
		Client client = null;
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM Prosthetics WHERE prost_type = \""+ type + "\";";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				Integer id = rs.getInt("prost_id");
				String p_type = rs.getString("prost_type");
				Date doB = rs.getDate("prost_doB");
				Date d_bought = rs.getDate("prost_dateBought");
				String inspections = rs.getString("prost_inspections");
				Integer eng_id = rs.getInt("eng_id");
				Integer client_id = rs.getInt("client_id");
				eng = engineermanager.searchEngineerByID(eng_id);
				client = clientmanager.searchClientByID(client_id);
				
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