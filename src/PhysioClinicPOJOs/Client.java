package PhysioClinicPOJOs; 
import java.util.*; 
import java.io.Serializable;

public class Client implements Serializable {
    
    private static final long serialVersionUID = -6746575861395147349L;
	
    private int id; 
    private String name; 
    private int phone; 
    private Date doB; 
    private int card_n; 
    private List<String> allergies; 
    private List<String> treatments_inprogress; 
    private boolean large_family; 
    private String email; 
    private List<Prosthetics> prosthetics;
	
    public Client() {
		super();
		this.allergies = new ArrayList<String>();
		this.treatments_inprogress = new ArrayList<String>();
		this.prosthetics = new ArrayList<Prosthetics>();
		
	}

	public Client(int id, String name, int phone, Date doB, int card_n, boolean large_family, String email) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.doB = doB;
		this.card_n = card_n;
		this.large_family = large_family;
		this.email = email;
		this.allergies = new ArrayList<String>();
		this.treatments_inprogress = new ArrayList<String>();
		this.prosthetics = new ArrayList<Prosthetics>();
	}

	public Client(String name, int phone, Date doB, int card_n, boolean large_family, String email) {
		super();
		this.name = name;
		this.phone = phone;
		this.doB = doB;
		this.card_n = card_n;
		this.large_family = large_family;
		this.email = email;
		this.allergies = new ArrayList<String>();
		this.treatments_inprogress = new ArrayList<String>();
		this.prosthetics = new ArrayList<Prosthetics>();
	}

	@Override
	public int hashCode() {
		return Objects.hash(allergies, card_n, doB, email, id, large_family, name, phone, prosthetics,
				treatments_inprogress);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(allergies, other.allergies) && card_n == other.card_n && Objects.equals(doB, other.doB)
				&& Objects.equals(email, other.email) && id == other.id && large_family == other.large_family
				&& Objects.equals(name, other.name) && phone == other.phone
				&& Objects.equals(prosthetics, other.prosthetics)
				&& Objects.equals(treatments_inprogress, other.treatments_inprogress);
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", phone=" + phone + ", doB=" + doB + ", card_n=" + card_n
				+ ", large_family=" + large_family + ", email=" + email + "]";
	}
    
    
    
    
    
    
}