package PhysioClinicPOJOs; 
import java.util.*; 
import java.io.Serializable;

public class Prosthetics implements Serializable {
    
    private static final long serialVersionUID = 7826851246375282594L;
	private int id; 
    private String type; 
    private Client client;
    private Engineer engineer; 
    private String observations;
	
    public Engineer getEngineer() {
		return engineer;
	}
	public void setEngineer(Engineer engineer) {
		this.engineer = engineer;
	}
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
	public Client getClient() {
		return client;
	}
	public void setClient_id(Client client_id) {
		this.client = client_id;
	}
	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Prosthetics(int id, String type, Engineer engineer, Client client, String observations) {
		super();
		this.id = id;
		this.type = type;
		this.client = client;
		this.engineer = engineer; 
		this.observations = observations;
	} 
	public Prosthetics(String type, Client client, Engineer engineer, String observations) {
		super();
		this.type = type;
		this.engineer = engineer; 
		this.client = client;
		this.observations = observations;
	} 
	public Prosthetics() {
		super();
	}
	@Override
	public int hashCode() {
		return Objects.hash(client, engineer, id, observations, type);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prosthetics other = (Prosthetics) obj;
		return Objects.equals(client, other.client) && Objects.equals(engineer, other.engineer) && id == other.id
				&& Objects.equals(observations, other.observations) && Objects.equals(type, other.type);
	}
	@Override
	public String toString() {
		return "Prosthetics [id=" + id + ", type=" + type + ", engineer=" + engineer + ", observations=" + observations
				+ "]";
	}
	
	
	
	

	
	
    
    

    
}