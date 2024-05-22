package PhysioClinicJDBC;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import PhysioClinicIFaces.EngineerManager;
import PhysioClinicPOJOs.Engineer;
import PhysioClinicPOJOs.User;

public class JDBCEngineerManager implements EngineerManager{
	
	private JDBCManager manager;
	
	public JDBCEngineerManager (JDBCManager m) {
		this.manager = m;
	}

	@Override
	public void changeEngineerTelephoneByID (String phone, int eng_id) {
		// TODO Auto-generated method stub
		try {
			String sql = "UPDATE engineer SET eng_phone= ? WHERE eng_id= ?;";
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
			String sql = "SELECT * FROM Engineer WHERE eng_id= " + eng_id;
		
			ResultSet rs = stmt.executeQuery(sql);
			
			//Integer e_id = rs.getInt("eng_id");
			String speciality = rs.getString("eng_speciality");
			String email = rs.getString("eng_email");
			String phone = rs.getString("eng_phone");
			String name = rs.getString("eng_name");
			Date doB = rs.getDate("eng_doB");
			Float salary = rs.getFloat("eng_salary");
			
		    eng = new Engineer (eng_id, name, phone, doB, speciality, email, salary);
		    
		    rs.close();
		    stmt.close();
		    
		}catch(Exception e) {e.printStackTrace();}
		
		
		return eng;
	}
	
	public void createEngineer(Engineer e) {
		try {
			String sql= "INSERT INTO Engineer (eng_id, "
					+ "eng_speciality, eng_email, eng_phone, eng_name, eng_doB, "
					+ "eng_salary)"
					+ "VALUES (?,?,?,?,?,?,?)";
			
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, e.getId());
			prep.setString(2, e.getSpeciality());
			prep.setString(3, e.getEmail());
			prep.setString(4, e.getPhone());
			prep.setString(5, e.getName());
			prep.setDate(6, e.getDoB());
			prep.setFloat(7, e.getSalary());
			
			prep.executeUpdate();				
					
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
	
	
	
	
	
}