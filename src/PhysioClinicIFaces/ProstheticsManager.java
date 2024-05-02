package PhysioClinicIFaces;
import java.util.List;
import PhysioClinicPOJOs.Prosthetics;

public interface ProstheticsManager {

	//public void createProsthetic(Prosthetics p);
	//public List<Prosthetics> showAllProsthetics();
	//public void deleteprostheticByID(int prosthetic_id);
	public List<Prosthetics> showAllProstheticsOfType(String type);
}