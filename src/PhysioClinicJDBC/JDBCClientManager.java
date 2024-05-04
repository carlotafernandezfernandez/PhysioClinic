package PhysioClinicJDBC;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import PhysioClinicIFaces.ClientManager;
import PhysioClinicPOJOs.Client;

public class JDBCClientManager implements ClientManager{
	
	private JDBCManager manager;
	
	public JDBCClientManager (JDBCManager m) {
		this.manager = m;
	}
	
	@Override
	public void createClient(Client c) {
		// TODO Auto-generated method stub
		try {
			String sql= "INSERT INTO client (phone, name, doB, card_number, allergies, treatment, "
					+ "family_number, email)"
					+ "VALUES (?,?,?,?,?,?,?,?)";
			
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, c.getPhone());
			prep.setString(2, c.getName());
			prep.setDate(3, (Date) c.getDoB());
			prep.setInt(4, c.getCard_n());
			prep.setBoolean(7, c.isLarge_family());
			prep.setString(8, c.getEmail());
			
			prep.executeUpdate();				
					
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Client> showAllClients() {
		
		List<Client> clients= new ArrayList<Client>();
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM client";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				//phone, name, doB, card_number, allergies, treatment, family_number, email
				Integer id = rs.getInt("client_id");
				Integer phone = rs.getInt("phone");
				String name = rs.getString("name");
				Date doB = rs.getDate("doB");
				Integer card_number = rs.getInt("card_number");
				//WHAT DO I DO WITH ARRAYS
				List<String> allergies = rs.getString("allergies");
				Integer teatment = rs.getInt("treatment");
				Boolean family_number = rs.getBoolean("family_number");
				String email = rs.getString("email");
				Integer physio_id = rs.getInt("physiotherapist_id");
				
				//TENGO QUE BUSCAR AL PHYSIO POR ID Y METERLE EN EL CONSTRUCTOR?? O ES PHYSIO SIN ID
				Client c = new Client (id, physio, name, phone, doB, card_number, family_number, email);
				clients.add(c);
			}
			
			rs.close();
			stmt.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		
		}
		
		return clients;
	}

	@Override
	public void deleteClientByID(int client_id) {
		// TODO Auto-generated method stub
		try {
			String sql = "DELETE FROM clients WHERE id=?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			
			prep.setInt(1, client_id);
			
			prep.executeUpdate();			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	

}