package PhysioClinicIFaces;
import PhysioClinicPOJOs.Engineer;

public interface EngineerManager {

	public void createEngineer(Engineer e);
	//public List<Engineer> showAllEngineers();
	//public void deleteEngineerByID(int eng_id);
	public Engineer searchEngineerByID(int eng_id);
	public void changeEngineerTelephoneByID(String phone, int eng_id);
	
}