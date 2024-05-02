package PhysioClinicIFaces;
import java.util.List;
import PhysioClinicPOJOs.Products;

public interface ProductsManager {

	public void createProduct(Products p);
	public List<Products> showAllProducts();
	public void deleteProductByID(int product_id);
}