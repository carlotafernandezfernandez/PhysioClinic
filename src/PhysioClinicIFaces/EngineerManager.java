package PhysioClinicIFaces;
import java.util.List;
import PhysioClinicPOJOs.Engineer;

public interface EngineerManager {

	//public void createEngineer(Engineer e);
	//public List<Engineer> showAllEngineers();
	//public void deleteEngineerByID(int eng_id);
	public static Engineer searchEngineerByID(int eng_id) {
		// TODO Auto-generated method stub
		return null;
	}
	public void changeEngineerSalaryByID(float new_eng_salary, int eng_id);
	
}