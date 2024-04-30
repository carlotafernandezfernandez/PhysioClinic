package PhysioClinicPOJOs; 
import java.util.*; 
import java.io.Serializable;

public class Products implements Serializable {
    
    private static final long serialVersionUID = -6100310099069726164L;
	
    private int id; 
    private String name; 
    private boolean availability; 
    private int n_available; 
    private List<Engineer> engineers; 
    private List<Physio> physios;
	
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isAvailability() {
		return availability;
	}
	public void setAvailability(boolean availability) {
		this.availability = availability;
	}
	public int getN_available() {
		return n_available;
	}
	public void setN_available(int n_available) {
		this.n_available = n_available;
	}
	public List<Engineer> getEngineers() {
		return engineers;
	}
	public void setEngineers(List<Engineer> engineers) {
		this.engineers = engineers;
	}
	public List<Physio> getPhysios() {
		return physios;
	}
	public void setPhysios(List<Physio> physios) {
		this.physios = physios;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Products(int id, String name, boolean availability, int n_available) {
		super();
		this.id = id;
		this.name = name;
		this.availability = availability;
		this.n_available = n_available;
		this.engineers = new ArrayList<Engineer>();
		this.physios = new ArrayList<Physio>();
	}
	public Products(String name, boolean availability, int n_available) {
		super();
		this.name = name;
		this.availability = availability;
		this.n_available = n_available;
		this.engineers = new ArrayList<Engineer>();
		this.physios = new ArrayList<Physio>();
	}
	public Products() {
		super();
		this.engineers = new ArrayList<Engineer>();
		this.physios = new ArrayList<Physio>();
	}
	@Override
	public int hashCode() {
		return Objects.hash(availability, engineers, id, n_available, name, physios);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Products other = (Products) obj;
		return availability == other.availability && Objects.equals(engineers, other.engineers) && id == other.id
				&& n_available == other.n_available && Objects.equals(name, other.name)
				&& Objects.equals(physios, other.physios);
	}
	@Override
	public String toString() {
		return "Products [id=" + id + ", name=" + name + ", availability=" + availability + ", available=" + n_available + ", past buyers = +]";
	}
	
	
	
    
    
}