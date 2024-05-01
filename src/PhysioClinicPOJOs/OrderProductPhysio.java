package PhysioClinicPOJOs;

import java.io.Serializable;
import java.util.Objects;

public class OrderProductPhysio implements Serializable{
	
	private static final long serialVersionUID = -603180946856190919L;
	
	private int id; 
	private int physio_id; 
	private int product_id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPhysio_id() {
		return physio_id;
	}
	public void setPhysio_id(int physio_id) {
		this.physio_id = physio_id;
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
	
	public OrderProductPhysio(int id, int physio_id, int product_id) {
		super();
		this.id = id;
		this.physio_id = physio_id;
		this.product_id = product_id;
	}
	public OrderProductPhysio(int physio_id, int product_id) {
		super();
		this.physio_id = physio_id;
		this.product_id = product_id;
	}
	public OrderProductPhysio() {
		super();
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(id, physio_id, product_id);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderProductPhysio other = (OrderProductPhysio) obj;
		return id == other.id && physio_id == other.physio_id && product_id == other.product_id;
	}
	
	@Override
	public String toString() {
		return "OrderProductPhysio [id=" + id + ", physio_id=" + physio_id + ", product_id=" + product_id + "]";
	}

}
