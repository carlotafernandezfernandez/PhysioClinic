package PhysioClinicJDBC;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import PhysioClinicIFaces.MachineManager;
import PhysioClinicIFaces.EngineerManager;
import PhysioClinicPOJOs.Machine;
import PhysioClinicPOJOs.Physio;
import PhysioClinicPOJOs.Client;
import PhysioClinicPOJOs.Engineer;

public class JDBCMachineManager implements MachineManager{
	
	private JDBCManager manager;
	private EngineerManager engineermanager; 
	
	public JDBCMachineManager (JDBCManager m) {
		this.manager = m;
		this.engineermanager = new JDBCEngineerManager(m); 
	}

	@Override
	public void createMachine(Machine m) {
		// TODO Auto-generated method stub
		try {
			
			String sql= "INSERT INTO Machine (machine_id, machine_type, machine_doB, machine_datebought, "
					+ "machine_inspections, engineer_id)"
					+ "VALUES (?,?,?,?,?,?)";
			
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			
			prep.setString(2, m.getType());
			prep.setDate(3, m.getDoB());
			prep.setDate(4, m.getdBought());
			prep.setString(5, m.getInspections());
			prep.setInt(6,  m.getEngineer().getId());
			
			prep.executeUpdate();	
			prep.close();
					
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public List<Machine> showAllMachines() {
		// TODO Auto-generated method stub
		List<Machine> machines= new ArrayList<Machine>(); 
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM machine";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				Integer id = rs.getInt("machine_id");
				String type = rs.getString("machine_type");
				Date doB = rs.getDate("machine_doB");
				Date d_bought = rs.getDate("machine_dateBought");
				String inspections = rs.getString("Machine_inspections");
				Integer engineer_id = rs.getInt("engineer_id");
				Engineer eng =  engineermanager.searchEngineerByID(engineer_id);
		
				Machine m = new Machine (id, type, doB, d_bought, eng, inspections);
				machines.add(m);
			}
			
			rs.close();
			stmt.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		
		}
		
		return machines;
	}
	
	@Override
	public Machine searchMachineID (int machine_id) {
		Machine m = new Machine();
		
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM Machine WHERE Machine_id=" + machine_id;
		
			ResultSet rs = stmt.executeQuery(sql);
			
			Integer id = rs.getInt("Machine_id");
			String type = rs.getString("Machine_type");
			Date doB = rs.getDate("Machine_doB");
			Date dB = rs.getDate("Machine_dateBought");
			String inspections = rs.getString("Machine_inspections");
			int eID = rs.getInt("Engineer_id");
			Engineer eng = engineermanager.searchEngineerByID(eID);
			
			m = new Machine (id, type, doB, dB, eng, inspections);
		    
		    rs.close();
		    stmt.close();
		    
		}catch(Exception e) {e.printStackTrace();}
		
		
		return m;
	}

	
		
}