package PhysioClinicPOJOs; 
import java.util.*;
import java.sql.Date; 
import java.io.Serializable;

public class Exams implements Serializable {
    
	private static final long serialVersionUID = -5928180842122961230L;
	
	private int id; 
    private String type; 
    private Machine machine; 
    private Date doB; 
    private byte[] exam; 
    private Client client; 
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
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	
	public List<Physio> getPhysios() {
		return physios;
	}
	public void setPhysios(List<Physio> physios) {
		this.physios = physios;
	}
	public byte[] getExam() {
		return exam;
	}
	public void setExam(byte[] exam) {
		this.exam = exam;
	}	
    
	public Exams(String type, Machine machine, Date doB, Client client, int physio_id) {
		super();
		this.type = type;
		this.machine = machine;
		this.doB = doB;
		this.client = client;
		this.physios = new ArrayList<Physio>();
		
	} 
	public Exams(int id, String type, Machine machine, Date doB, Client client, int physio_id) {
		super();
		this.id= id;
		this.type = type;
		this.machine = machine;
		this.doB = doB;
		this.client = client;
		this.physios = new ArrayList<Physio>();
		
	} 
	public Exams() {
		super();
		this.physios = new ArrayList<Physio>();
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(exam);
		result = prime * result + Objects.hash(client, doB, id, machine, physios, type);
		return result;
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
		return Objects.equals(client, other.client) && Objects.equals(doB, other.doB) && Arrays.equals(exam, other.exam)
				&& id == other.id && Objects.equals(machine, other.machine) && Objects.equals(physios, other.physios)
				&& Objects.equals(type, other.type);
	}
	@Override
	public String toString() {
		return "Exams [id=" + id + ", type=" + type + ", machine=" + machine + ", doB=" + doB + ", client=" + client
				+ "]";
	}
	
    
}