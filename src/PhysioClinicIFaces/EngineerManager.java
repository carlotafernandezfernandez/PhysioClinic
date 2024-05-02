package PhysioClinicIFaces;
import java.util.List;
import PhysioClinicPOJOs.Engineer;

public interface EngineerManager {

	//public void createEngineer(Engineer e);
	//public List<Engineer> showAllEngineers();
	//public void deleteEngineerByID(int eng_id);
	public void changeEngineerSalary(float new_eng_salary);
	
}