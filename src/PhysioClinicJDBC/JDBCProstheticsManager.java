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
			String sql = "SELECT * FROM Prosthetics WHERE prost_type LIKE ? ";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, type);
			ResultSet rs = prep.executeQuery();
			
			while(rs.next())
			{
				Integer id = rs.getInt("prost_id");
				String p_typeORIGInAL = rs.getString("prost_type");
				String p_typeNOSPACES = p_typeORIGInAL.replaceAll("\\s","");
				String p_type = p_typeNOSPACES.toLowerCase();
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
			prep.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return prosthetics;
	}
}