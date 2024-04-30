package PhysioClinicPOJOs;

import java.io.Serializable;
import java.util.Objects;

public class OrderExamPhysio implements Serializable{
	private static final long serialVersionUID = -2406629001412151279L;
	
	private int id; 
	private int physio_id; 
	private int exam_id;
	
	public OrderExamPhysio(int id, int physio_id, int exam_id) {
		super();
		this.id = id;
		this.physio_id = physio_id;
		this.exam_id = exam_id;
	}
	public OrderExamPhysio(int physio_id, int exam_id) {
		super();
		this.physio_id = physio_id;
		this.exam_id = exam_id;
	}
	public OrderExamPhysio() {
		super();
	}
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
	public int getExam_id() {
		return exam_id;
	}
	public void setExam_id(int exam_id) {
		this.exam_id = exam_id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		return Objects.hash(exam_id, id, physio_id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderExamPhysio other = (OrderExamPhysio) obj;
		return exam_id == other.exam_id && id == other.id && physio_id == other.physio_id;
	}
	
	@Override
	public String toString() {
		return "OrderExamPhysio [id=" + id + ", physio_id=" + physio_id + ", exam_id=" + exam_id + "]";
	}
	
	

}
