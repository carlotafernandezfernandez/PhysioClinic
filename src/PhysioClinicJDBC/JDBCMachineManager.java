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
			//NO SE NECESITARÍA EL ID????
			String sql= "INSERT INTO Machine (machine_id, machine_type, machine_doB, machine_datebought, "
					+ "engineer_id)"
					+ "VALUES (?,?,?,?,?)";
			
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, m.getId());
			prep.setString(2, m.getType());
			prep.setDate(3, (Date) m.getDoB());
			prep.setDate(4, (Date) m.getdBought());
			prep.setInt(5,  m.getEngineer().getId());
			
			prep.executeUpdate();				
					
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
		machines = null; 
		
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
				Integer engineer_id = rs.getInt("engineer_id");
				Engineer eng = null;
				eng = engineermanager.searchEngineerByID(engineer_id);
		
				Machine m = new Machine (id, type, doB, d_bought, eng);
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

	
		
}