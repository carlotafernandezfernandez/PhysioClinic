package PhysioClinicJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import PhysioClinicIFaces.ExamsManager;
import PhysioClinicIFaces.ProstheticsManager;
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
				String typeOfAnimal = rs.getString("typeOfAnimal");
				Date dob = rs.getDate("dob");
				String coat = rs.getString("coat");
				
				Owner o = ownermanager.searchOwnerById(owner_id);
				
				Pet p = new Pet(id, coat,  name,cured, typeOfAnimal, dob, o);
				pets.add(p);
			}
			
			rs.close();
			stmt.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return pets;
	}
		
}