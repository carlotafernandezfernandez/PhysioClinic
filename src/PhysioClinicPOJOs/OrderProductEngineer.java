package PhysioClinicPOJOs;

import java.io.Serializable;
import java.util.Objects;

public class OrderProductEngineer implements Serializable{

	private static final long serialVersionUID = 1927142912894485245L;
	
	private int id; 
	private int engineer_id; 
	private int product_id;
	
	public int getId() {
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getEngineer_id() {
		return engineer_id;
	}
	public void setEngineer_id(int engineer_id) {
		this.engineer_id = engineer_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public OrderProductEngineer(int id, int engineer_id, int product_id) {
		super();
		this.id = id;
		this.engineer_id = engineer_id;
		this.product_id = product_id;
	}
	public OrderProductEngineer(int engineer_id, int product_id) {
		super();
		this.engineer_id = engineer_id;
		this.product_id = product_id;
	}
	public OrderProductEngineer() {
		super();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(engineer_id, id, product_id);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderProductEngineer other = (OrderProductEngineer) obj;
		return engineer_id == other.engineer_id && id == other.id && product_id == other.product_id;
	}
	
	@Override
	public String toString() {
		return "OrderProductEngineer [id=" + id + ", engineer_id=" + engineer_id + ", product_id=" + product_id + "]";
	}
	
	
	
	

}
