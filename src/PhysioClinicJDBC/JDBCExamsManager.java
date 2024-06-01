package PhysioClinicJDBC;

import java.sql.PreparedStatement;
import java.sql.Date; 
import PhysioClinicIFaces.ExamsManager;

public class JDBCExamsManager implements ExamsManager{
	
	private JDBCManager manager;
	
	public JDBCExamsManager (JDBCManager m) {
		this.manager = m;
	}

	@Override
	public void deleteExamByID(int exam_id) {
		// TODO Auto-generated method stub
		
		try {
			String sql = "DELETE FROM Exam WHERE ex_id=?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, exam_id);
			
			prep.executeUpdate();	
			prep.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
		
}

	