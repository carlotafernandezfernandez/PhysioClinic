package PhysioClinicIFaces;
import java.util.List;
import PhysioClinicPOJOs.Physio;

public interface PhysioManager {

	//public void createMachine(Physio p);
	public List<Physio> showAllPhysios();
	//public void deletePhysioByID(int physio_id);
	public Physio searchPhysioByID(int physio_id);
	public void createPhysio(Physio p);
}