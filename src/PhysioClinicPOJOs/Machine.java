package PhysioClinicPOJOs; 
import java.util.*; 
import java.io.Serializable;

public class Machine implements Serializable {
	
    private static final long serialVersionUID = 1981067232205947164L;
	
    private int id; 
	private String type; 
    private Date doB; 
    private Date dBought; 
    private List<Exams> exams;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getDoB() {
		return doB;
	}
	public void setDoB(Date doB) {
		this.doB = doB;
	}
	public Date getdBought() {
		return dBought;
	}
	public void setdBought(Date dBought) {
		this.dBought = dBought;
	}
	public List<Exams> getExams() {
		return exams;
	}
	public void setExams(List<Exams> exams) {
		this.exams = exams;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Machine(int id, String type, Date doB, Date dBought) {
		super();
		this.id = id;
		this.type = type;
		this.doB = doB;
		this.dBought = dBought;
		this.exams = new ArrayList<Exams>();
	}
	public Machine(String type, Date doB, Date dBought) {
		super();
		this.type = type;
		this.doB = doB;
		this.dBought = dBought;
		this.exams = new ArrayList<Exams>();
	}
	public Machine() {
		super();
		this.exams = new ArrayList<Exams>();
	}
	@Override
	public int hashCode() {
		return Objects.hash(dBought, doB, exams, id, type);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Machine other = (Machine) obj;
		return Objects.equals(dBought, other.dBought) && Objects.equals(doB, other.doB)
				&& Objects.equals(exams, other.exams) && id == other.id && Objects.equals(type, other.type);
	}
	@Override
	public String toString() {
		return "Machine [id=" + id + ", type=" + type + ", doB=" + doB + ", dBought=" + dBought + "]";
	}
    
	
    
    
    
}