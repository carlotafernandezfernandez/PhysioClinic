package PhysioClinicPOJOs; 
import java.util.*;
import java.io.Serializable;

public class Exams implements Serializable {
    
	private static final long serialVersionUID = -5928180842122961230L;
	
	private int id; 
    private String type; 
    private Machine machine; 
    private Date doB; 
    private int client_id; 
    private List<Physio> physios;
	
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Machine getMachine() {
		return machine;
	}
	public void setMachine(Machine machine) {
		this.machine = machine;
	}
	public Date getDoB() {
		return doB;
	}
	public void setDoB(Date doB) {
		this.doB = doB;
	}
	public int getClient_id() {
		return client_id;
	}
	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}
	
	public List<Physio> getPhysios() {
		return physios;
	}
	public void setPhysios(List<Physio> physios) {
		this.physios = physios;
	}
	public Exams(String type, Machine machine, Date doB, int client_id, int physio_id) {
		super();
		this.type = type;
		this.machine = machine;
		this.doB = doB;
		this.client_id = client_id;
		this.physios = new ArrayList<Physio>();
		
	} 
	public Exams(int id, String type, Machine machine, Date doB, int client_id, int physio_id) {
		super();
		this.id= id;
		this.type = type;
		this.machine = machine;
		this.doB = doB;
		this.client_id = client_id;
		this.physios = new ArrayList<Physio>();
		
	} 
	public Exams() {
		super();
		this.physios = new ArrayList<Physio>();
		
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(client_id, doB, id, machine, physios, type);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Exams other = (Exams) obj;
		return client_id == other.client_id && Objects.equals(doB, other.doB) && id == other.id
				&& Objects.equals(machine, other.machine) && Objects.equals(physios, other.physios)
				&& Objects.equals(type, other.type);
	}
	@Override
	public String toString() {
		return "Exams [id=" + id + ", type=" + type + ", machine=" + machine + ", doB=" + doB + ", client_id="
				+ client_id + "]";
	} 
	
	
	
	
    
    
    
}