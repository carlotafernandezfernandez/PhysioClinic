package PhysioClinicIFaces;

import java.io.File;

import PhysioClinicPOJOs.Client;

public interface XMLManager {
	public void physio2xml (Integer id);
	public Client xml2Client (File xml);
	
}
