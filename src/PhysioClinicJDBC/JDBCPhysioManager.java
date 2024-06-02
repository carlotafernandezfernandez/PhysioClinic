package PhysioClinicJDBC;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import PhysioClinicIFaces.PhysioManager;
import PhysioClinicPOJOs.Client;
import PhysioClinicPOJOs.Physio;

public class JDBCPhysioManager implements PhysioManager{
	
	private JDBCManager manager;
	
	public JDBCPhysioManager (JDBCManager m) {
		this.manager = m;
	}

	public Physio searchPhysioByID (int physio_id) {
		// TODO Auto-generated method stub
		Physio physio = new Physio();
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM Physiotherapist WHERE physio_id= " + physio_id;
		
			ResultSet rs = stmt.executeQuery(sql);
			
			//Integer id = rs.getInt("physio_id");
			String name = rs.getString("physio_name");
			String speciality = rs.getString("physio_speciality");
			String email = rs.getString("physio_email");
			String phone = rs.getString("physio_phone");
			//byte[] license = rs.getBytes("physio_license");
			Float salary = rs.getFloat("physio_salary");
			Date doB = rs.getDate("physio_doB");
			
		    physio = new Physio(physio_id, name, phone, doB, speciality, email, salary);

		    
		    rs.close();
		    stmt.close();
		    
		}catch(Exception e) {e.printStackTrace();}
		
		return physio;
	}
	
	

	public void createPhysio(Physio p) {
		try {
			String sql= "INSERT INTO Physiotherapist (physio_id, physio_name, physio_speciality, "
					+ "physio_phone, physio_email, physio_salary, physio_doB)"
					+ "VALUES (?,?,?,?,?,?,?)";
			
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, p.getId());
			prep.setString(2, p.getName());
			prep.setString(3, p.getSpeciality());
			prep.setString(4, p.getPhone());
			prep.setString(5, p.getEmail());
			prep.setFloat(6, p.getSalary());
			prep.setDate(7, (Date) p.getDoB());
			
			prep.executeUpdate();
			prep.close();
					
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Physio> showAllPhysios() {
		
		List<Physio> physios= new ArrayList<Physio>();
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM Physiotherapist";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				Integer id = rs.getInt("physio_id");
				String name = rs.getString("physio_name");
				String speciality = rs.getString("physio_speciality");
				String phone = rs.getString("physio_phone");
				String email = rs.getString("physio_email");
				float salary = rs.getFloat("physio_salary");
				Date dob = rs.getDate("physio_doB");
				
				
				Physio p = new Physio (id, name, phone, dob, speciality, email, salary);
				physios.add(p);
			}
			
			rs.close();
			stmt.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		
		}
		
		return physios;
	}
}
		
