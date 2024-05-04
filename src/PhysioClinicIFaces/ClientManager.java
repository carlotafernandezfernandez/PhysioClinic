package PhysioClinicIFaces;
import java.util.List;
import PhysioClinicPOJOs.Client;
import PhysioClinicPOJOs.Engineer;

public interface ClientManager {

	public void createClient(Client c);
	public List<Client> showAllClients();
	public void deleteClientByID(int client_id);
	public static Client searchClientByID(int client_id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}