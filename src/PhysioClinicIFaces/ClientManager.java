package PhysioClinicIFaces;
import java.util.List;
import PhysioClinicPOJOs.Client;

public interface ClientManager {

	public void createClient(Client c);
	public List<Client> showAllClients();
	public void deleteClientByID(int client_id);
	public Client searchClientByID(int client_id);
	
}