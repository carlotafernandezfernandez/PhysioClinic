package PhysioclinicXML;

import java.io.File;
import java.util.*;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

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
	public void engineer2xml(Integer id) {
		// TODO Auto-generated method stub
		Physio p = null;
		List<Client> clients = new ArrayList<Client>();
		manager = new JDBCManager();
		physiomanager = new JDBCPhysioManager(manager);
		clientmanager = new JDBCClientManager(manager);
		try {
			//Do a SQL query to get the owner by the id
			p = physiomanager.searchPhysioByID(id);
			//search for the pets of the owner
			clients = clientmanager.showAllClients();
			p.setClients(clients);
			
			//export the owner to an xml file
			JAXBContext jaxbContext = JAXBContext.newInstance(Physio.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			
			File file = new File("Owner.xml");
			marshaller.marshal(p, file);
			System.out.print(p);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Client xml2Client(File xml) {
		// TODO Auto-generated method stub
		return null;
	}

}
