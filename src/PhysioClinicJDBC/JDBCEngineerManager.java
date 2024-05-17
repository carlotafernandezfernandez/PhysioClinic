package PhysioClinicJDBC;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import PhysioClinicIFaces.EngineerManager;
import PhysioClinicPOJOs.Engineer;

public class JDBCEngineerManager implements EngineerManager{
	
	private JDBCManager manager;
	
	public JDBCEngineerManager (JDBCManager m) {
		this.manager = m;
	}

	@Override
	public void changeEngineerTelephoneByID (String phone, int eng_id) {
		// TODO Auto-generated method stub
		try {
			String sql = "UPDATE engineer SET eng_phone= ? WHERE id= ?;";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			
			prep.setString(1, phone);
			prep.setInt(2, eng_id);
			
			prep.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
			
		}	
	}

	@Override
	public Engineer searchEngineerByID(int eng_id) {
		// TODO Auto-generated method stub
		Engineer eng = null;
		
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM Engineer WHERE id= " + eng_id;
		
			ResultSet rs = stmt.executeQuery(sql);
			
			Integer e_id = rs.getInt("eng_id");
			//byte[] license = rs.getBytes("eng_license");
			String specialty = rs.getString("eng_specialty");
			String email = rs.getString("eng_email");
			String phone = rs.getString("eng_phone");
			String name = rs.getString("eng_name");
			Date doB = rs.getDate("eng_doB");
			Float salary = rs.getFloat("eng_salary");
			
		    eng = new Engineer (e_id, name, phone, doB, specialty, email, salary);
		    
		    rs.close();
		    stmt.close();
		    
		}catch(Exception e) {e.printStackTrace();}
		
		
		return eng;
	}
	
	public void createEngineer(Engineer e) {
		try {
			String sql= "INSERT INTO Engineer (eng_id, eng_address, eng_license, "
					+ "eng_speciality, eng_email, eng_phone, eng_name, eng_doB, "
					+ "eng_salary)"
					+ "VALUES (?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, e.getId());
			prep.setString(4, e.getSpecialty());
			prep.setString(5, e.getEmail());
			prep.setString(6, e.getPhone());
			prep.setString(7, e.getName());
			prep.setDate(8, (Date) e.getDoB());
			prep.setFloat(9, e.getSalary());
			
			
			prep.executeUpdate();				
					
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
	
	
	
	
}