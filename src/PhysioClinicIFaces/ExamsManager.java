package PhysioClinicIFaces;

import java.util.List;

import PhysioClinicPOJOs.Exams;

public interface ExamsManager {

	//public void createExam(Exams e);
	public void deleteExamByID(int exam_id);
	public List<Exams> showAllExamsINFO(int client_id);
}