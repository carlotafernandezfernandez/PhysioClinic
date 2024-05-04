package PhysioClinicJDBC;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import PhysioClinicIFaces.PhysioManager;
import PhysioClinicPOJOs.Physio;

public class JDBCPhysioManager implements PhysioManager{
	
	private JDBCManager manager;
	
	public JDBCPhysioManager (JDBCManager m) {
		this.manager = m;
	}

	@Override
	public Physio searchPhysioByID(int physio_id) {
		// TODO Auto-generated method stub
		Physio physio = null;
		
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM physiotherapist WHERE id=" + physio_id;
		
			ResultSet rs = stmt.executeQuery(sql);
			
			Integer id = rs.getInt("physio_id");
			String name = rs.getString("physio_name");
			String specialty = rs.getString("physio_specialty");
			String email = rs.getString("physio_email");
			Integer phone = rs.getInt("physio_phone");
			//COMO HACER BYTE[]
			Byte[] license = rs.get("physio_license");
			Float salary = rs.getFloat("physio_salary");
			Date doB = rs.getDate("physio_doB");
			
		    physio = new Physio(physio_id, name, phone, doB, specialty, email, salary, license);
		    
		    rs.close();
		    stmt.close();
		    
		}catch(Exception e) {e.printStackTrace();}
		
		
		return physio;
	}

}
		
