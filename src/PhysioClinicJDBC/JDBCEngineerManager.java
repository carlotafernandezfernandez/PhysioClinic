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
	public void changeEngineerSalaryByID(float new_eng_salary, int eng_id) {
		// TODO Auto-generated method stub
		try {
			String sql = "UPDATE engineer SET eng_salary= ? WHERE id= ?;";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			
			prep.setFloat(1, new_eng_salary);
			prep.setInt(2, eng_id);
			
			prep.executeQuery();
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
			String sql = "SELECT * FROM engineer WHERE id=" + eng_id;
		
			ResultSet rs = stmt.executeQuery(sql);
			
			Integer e_id = rs.getInt("eng_id");
			//COMO HACER BYTE[]
			Byte[] license = rs.get("eng_license");
			String specialty = rs.getString("eng_specialty");
			String email = rs.getString("eng_email");
			Integer phone = rs.getInt("eng_phone");
			String name = rs.getString("eng_name");
			Date doB = rs.getDate("eng_doB");
			Float salary = rs.getFloat("eng_salary");
			
		    eng = new Engineer (e_id, name, phone, doB, specialty, email, salary, license);
		    
		    rs.close();
		    stmt.close();
		    
		}catch(Exception e) {e.printStackTrace();}
		
		
		return eng;
	}
	}

}