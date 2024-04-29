package PhysioClinicPOJOs; 
import java.util.*; 
import java.io.Serializable;

public class Prosthetics implements Serializable {
    
    private static final long serialVersionUID = 7826851246375282594L;
	private int id; 
    private String type; 
    private int client_id;
    private String observations;
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
	public int getClient_id() {
		return client_id;
	}
	public void setClient_id(int client_id) {
		this.client_id = client_id;
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
	public Prosthetics(int id, String type, int client_id, String observations) {
		super();
		this.id = id;
		this.type = type;
		this.client_id = client_id;
		this.observations = observations;
	} 
	public Prosthetics(String type, int client_id, String observations) {
		super();
		this.type = type;
		this.client_id = client_id;
		this.observations = observations;
	} 
	public Prosthetics() {
		super();
	}
	@Override
	public int hashCode() {
		return Objects.hash(client_id, id, observations, type);
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
		return client_id == other.client_id && id == other.id && Objects.equals(observations, other.observations)
				&& Objects.equals(type, other.type);
	}
	@Override
	public String toString() {
		return "Prosthetics [id=" + id + ", type=" + type + ", client_id=" + client_id + ", observations="
				+ observations + "]";
	} 
	
	
    
    

    
}