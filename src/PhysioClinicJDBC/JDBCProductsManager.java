package PhysioClinicJDBC;

import java.sql.PreparedStatement;
import PhysioClinicIFaces.ProductsManager;

public class JDBCProductsManager implements ProductsManager{
	
	private JDBCManager manager;
	
	public JDBCProductsManager (JDBCManager m) {
		this.manager = m;
	}

	@Override
	public void changeProductNAvailable(int product_id, int n_available) {
		// TODO Auto-generated method stub
		try {
			String sql = "UPDATE Products SET n_available= ? WHERE prod_id= ?;";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			
			prep.setFloat(1, n_available);
			prep.setInt(2, product_id);
			
			prep.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		
	}
		
}