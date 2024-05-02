package PhysioClinicIFaces;
import java.util.List;
import PhysioClinicPOJOs.Exams;

public interface ExamsManager {

	public void createExam(Exams e);
	public List<Exams> showAllExams();
	public void deleteExamByID(int exam_id);
}