package PhysioclinicXML;

import java.io.File;
import java.util.*;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import PhysioClinicIFaces.ClientManager;
import PhysioClinicIFaces.PhysioManager;
import PhysioClinicIFaces.XMLManager;
import PhysioClinicJDBC.JDBCClientManager;
import PhysioClinicJDBC.JDBCPhysioManager;
import PhysioClinicJDBC.JDBCManager;
import PhysioClinicPOJOs.Client;
import PhysioClinicPOJOs.Physio;

public class XMLManagerImpl implements XMLManager{
	JDBCManager manager;
	PhysioManager physiomanager;
	ClientManager clientmanager;
	
	@Override
	public Physio physio2xml(Integer id) {
		// TODO Auto-generated method stub
		Physio p = null;
		List<Client> clients = new ArrayList<Client>();
		manager = new JDBCManager();
		physiomanager = new JDBCPhysioManager(manager);
		clientmanager = new JDBCClientManager(manager);
		try {
			//Do a SQL query to get the physio by the id
			p = physiomanager.searchPhysioByID(id);
			//search for the clients of the physio
			clients = clientmanager.showAllClientsID(id);
			p.setClients(clients);
			
			//export the physio to an xml file
			JAXBContext jaxbContext = JAXBContext.newInstance(Physio.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			
			File file = new File("Physio.xml");
			marshaller.marshal(p, file);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return p; 
	}

	@Override
	public Client xml2Client(File xml) {
		// TODO Auto-generated method stub
		Client c = new Client();
		manager = new JDBCManager();
		clientmanager = new JDBCClientManager(manager);
		
		try{
		//read client form xml file
		JAXBContext jaxbContext = JAXBContext.newInstance(Client.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		c = (Client) unmarshaller.unmarshal(xml);
		clientmanager.createClient(c);
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return c;
	}

}
