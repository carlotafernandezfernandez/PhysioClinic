package PhysioClinicPOJOs; 
import java.util.*;
import java.sql.Date;  
import java.io.Serializable;

public class Prosthetics implements Serializable {
    
    private static final long serialVersionUID = 7826851246375282594L;
	private int id; 
    private String type; 
    private Client client;
    private Engineer engineer; 
    private Date doB; 
    private Date date_bought; 
    private String inspections;
	
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
		return inspections;
	}
	public void setObservations(String observations) {
		this.inspections = observations;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Prosthetics(int id, String type, Engineer engineer, Client client, String inspections,
			Date doB, Date date_bought) {
		super();
		this.id = id;
		this.date_bought = date_bought; 
		this.doB = doB; 
		this.type = type;
		this.client = client;
		this.engineer = engineer; 
		this.inspections = inspections;
	} 
	public Prosthetics(String type, Client client, Engineer engineer, String inspections, 
			Date doB, Date date_bought) {
		super();
		this.type = type;
		this.date_bought = date_bought; 
		this.doB = doB; 
		this.engineer = engineer; 
		this.client = client;
		this.inspections = inspections;
	} 
	public Prosthetics() {
		super();
	}
	public Date getDoB() {
		return doB;
	}
	public void setDoB(Date doB) {
		this.doB = doB;
	}
	public Date getDate_bought() {
		return date_bought;
	}
	public void setDate_bought(Date date_bought) {
		this.date_bought = date_bought;
	}
	@Override
	public int hashCode() {
		return Objects.hash(client, date_bought, doB, engineer, id, inspections, type);
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
		return Objects.equals(client, other.client) && Objects.equals(date_bought, other.date_bought)
				&& Objects.equals(doB, other.doB) && Objects.equals(engineer, other.engineer) && id == other.id
				&& Objects.equals(inspections, other.inspections) && Objects.equals(type, other.type);
	}
	@Override
	public String toString() {
		return "Prosthetics [id=" + id + ", type=" + type + ", client=" + client + ", engineer=" + engineer + ", doB="
				+ doB + ", date_bought=" + date_bought + ", observations=" + inspections + "]";
	}
	
	
	
	
	
	

	
	
    
    

    
}