package PhysioClinicJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date; 
import PhysioClinicIFaces.ExamsManager;
import PhysioClinicIFaces.MachineManager;
import PhysioClinicPOJOs.Client;
import PhysioClinicPOJOs.Engineer;
import PhysioClinicPOJOs.Exams;
import PhysioClinicPOJOs.Machine;

public class JDBCExamsManager implements ExamsManager{
	
	private JDBCManager manager;
	private JDBCMachineManager machinemanager; 
	private JDBCClientManager clientmanager; 
	
	public JDBCExamsManager (JDBCManager m) {
		this.manager = m;
		this.machinemanager = new JDBCMachineManager(m);
		this.clientmanager = new JDBCClientManager(m);
	}

	@Override
	public void deleteExamByID(int exam_id) {
		// TODO Auto-generated method stub
		
		try {
			String sql = "DELETE FROM Exam WHERE ex_id=?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, exam_id);
			
			prep.executeUpdate();	
			prep.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	@Override
	public List<Exams> showAllExamsINFO(int client_id){
		List<Exams> exams= new ArrayList<Exams>(); 
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM Exam WHERE client_id =" + client_id;
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				Integer id = rs.getInt("ex_id");
				String type = rs.getString("ex_type");
				int clientid = rs.getInt("client_id");
				Client c = clientmanager.searchClientByID(clientid);
				Date doB = rs.getDate("ex_doB");
				int machineID = rs.getInt("Machine_id");
				Machine machine = machinemanager.searchMachineID(machineID);
		
				Exams e = new Exams (id, type, machine, doB, c);

				exams.add(e);
			}
			
			rs.close();
			stmt.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		
		}
		
		return exams; 
		
	}
	

	
		
}

	