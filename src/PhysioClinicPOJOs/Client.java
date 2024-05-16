package PhysioClinicPOJOs; 
import java.util.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Client")
@XmlType(propOrder = {"typeofClient", "physio", "phone_number","card_n", "treatments", "prosthetics", "exams"})


public class Client implements Serializable {
    
    private static final long serialVersionUID = -6746575861395147349L;
	@XmlTransient
    private int id; 
    @XmlAttribute
    private String name; 
    @XmlElement(name = "phone_number" ) //, Required = "false")
    private int phone; 
    @XmlJavaTypeAdapter(SQLDateAdapter.class)
    private Date doB; 
    @XmlElement
    private int card_n; 
    @XmlTransient
    private List<String> allergies; 
    @XmlElement
    private List<String> treatments_inprogress; 
    @XmlTransient
    private boolean large_family; 
    @XmlTransient
    private String email; 
    @XmlElement
    private List<Prosthetics> prosthetics;
    @XmlElement
    private List<Exams> exams; 
    @XmlElement
    private Physio physio; 
    	
    
	
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

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}
	public Physio getPhysio() {
		return physio;
	}

	public void setPhysio(Physio physio) {
		this.physio = physio;
	}

	public Date getDoB() {
		return doB;
	}

	public void setDoB(Date doB) {
		this.doB = doB;
	}

	public int getCard_n() {
		return card_n;
	}

	public void setCard_n(int card_n) {
		this.card_n = card_n;
	}

	public List<String> getAllergies() {
		return allergies;
	}

	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;
	}

	public List<String> getTreatments_inprogress() {
		return treatments_inprogress;
	}

	public void setTreatments_inprogress(List<String> treatments_inprogress) {
		this.treatments_inprogress = treatments_inprogress;
	}

	public boolean isLarge_family() {
		return large_family;
	}

	public void setLarge_family(boolean large_family) {
		this.large_family = large_family;
	}
	public List<Exams> getExams() {
		return exams;
	}

	public void setExams(List<Exams> exams) {
		this.exams = exams;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Prosthetics> getProsthetics() {
		return prosthetics;
	}

	public void setProsthetics(List<Prosthetics> prosthetics) {
		this.prosthetics = prosthetics;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Client() {
		super();
		this.allergies = new ArrayList<String>();
		this.treatments_inprogress = new ArrayList<String>();
		this.prosthetics = new ArrayList<Prosthetics>();
		this.exams = new ArrayList<Exams>();
		
	}

	public Client(int id, Physio physio, String name, int phone, Date doB, int card_n, 
			boolean large_family, String email) {
		super();
		this.id = id;
		this.physio = physio; 
		this.name = name;
		this.phone = phone;
		this.doB = doB;
		this.card_n = card_n;
		this.large_family = large_family;
		this.email = email;
		this.allergies = new ArrayList<String>();
		this.treatments_inprogress = new ArrayList<String>();
		this.prosthetics = new ArrayList<Prosthetics>();
		this.exams = new ArrayList<Exams>();
	}

	public Client(String name, Physio physio, int phone, Date doB, int card_n, boolean large_family, String email) {
		super();
		this.name = name;
		this.phone = phone;
		this.doB = doB;
		this.physio = physio; 
		this.card_n = card_n;
		this.large_family = large_family;
		this.email = email;
		this.allergies = new ArrayList<String>();
		this.treatments_inprogress = new ArrayList<String>();
		this.prosthetics = new ArrayList<Prosthetics>();
		this.exams = new ArrayList<Exams>();
	}

	@Override
	public int hashCode() {
		return Objects.hash(allergies, card_n, doB, email, id, large_family, name, phone, prosthetics,
				treatments_inprogress, exams, physio);
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
				&& Objects.equals(name, other.name) && phone == other.phone && Objects.equals(physio, other.physio)
				&& Objects.equals(prosthetics, other.prosthetics) && Objects.equals(exams, other.exams)
				&& Objects.equals(treatments_inprogress, other.treatments_inprogress);
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", phone=" + phone + ", doB=" + doB + ", card_n=" + card_n
				+ ", allergies=" + allergies + ", treatments_inprogress=" + treatments_inprogress + ", large_family="
				+ large_family + ", email=" + email + ", prosthetics=" + prosthetics + ", physio=" + physio + "]";
	}

}