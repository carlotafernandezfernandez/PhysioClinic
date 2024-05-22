package PhysioClinicIFaces;

import java.io.File;

import PhysioClinicPOJOs.Client;
import PhysioClinicPOJOs.Physio;

public interface XMLManager {
	public Physio physio2xml (Integer id);
	public Client xml2Client (File xml);
	
}
