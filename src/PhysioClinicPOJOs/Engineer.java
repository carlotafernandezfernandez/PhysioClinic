package PhysioClinicPOJOs; 
import java.util.*; 
import java.io.Serializable;

public class Engineer implements Serializable {
	
	private static final long serialVersionUID = -1861794938648642519L;
	
	private int id; 
    private String name; 
    private int phone; 
    private Date doB; 
    private String specialty; 
    private String email; 
    private float salary; 
    private byte[] license; 
    private List<Products> products; 
    private List<Machine> machines;
    private List<Prosthetics> prosthetics;
	
    public Engineer (String name, int phone, Date doB, String specialty, String email, float salary,
			byte[] license) {
		super();
		this.name = name;
		this.phone = phone;
		this.doB = doB;
		this.specialty = specialty;
		this.email = email;
		this.salary = salary;
		this.license = license;
		this.products = new ArrayList<Products>(); 
		this.machines = new ArrayList<Machine>(); 
		this.prosthetics = new ArrayList<Prosthetics>(); 
	}

	public Engineer(int id, String name, int phone, Date doB, String specialty, String email, float salary,
			byte[] license) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.doB = doB;
		this.specialty = specialty;
		this.email = email;
		this.salary = salary;
		this.license = license;
		this.products = new ArrayList<Products>(); 
		this.machines = new ArrayList<Machine>(); 
		this.prosthetics = new ArrayList<Prosthetics>(); 
	}

	public Engineer() {
		super();
		this.products = new ArrayList<Products>(); 
		this.machines = new ArrayList<Machine>(); 
		this.prosthetics = new ArrayList<Prosthetics>(); 
	}

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

	public Date getDoB() {
		return doB;
	}

	public void setDoB(Date doB) {
		this.doB = doB;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public byte[] getLicense() {
		return license;
	}

	public void setLicense(byte[] license) {
		this.license = license;
	}

	public List<Products> getProducts() {
		return products;
	}

	public void setProducts(List<Products> products) {
		this.products = products;
	}

	public List<Machine> getMachines() {
		return machines;
	}

	public void setMachines(List<Machine> machines) {
		this.machines = machines;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(license);
		result = prime * result
				+ Objects.hash(doB, email, id, machines, name, phone, products, prosthetics, salary, specialty);
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
		Engineer other = (Engineer) obj;
		return Objects.equals(doB, other.doB) && Objects.equals(email, other.email) && id == other.id
				&& Arrays.equals(license, other.license) && Objects.equals(machines, other.machines)
				&& Objects.equals(name, other.name) && phone == other.phone && Objects.equals(products, other.products)
				&& Objects.equals(prosthetics, other.prosthetics)
				&& Float.floatToIntBits(salary) == Float.floatToIntBits(other.salary)
				&& Objects.equals(specialty, other.specialty);
	}

	@Override
	public String toString() {
		return "Engineer [id=" + id + ", name=" + name + ", phone=" + phone + ", doB=" + doB + ", specialty="
				+ specialty + ", email=" + email + ", salary=" + salary + ", license=" + Arrays.toString(license)
				+ ", products=" + products + ", machines=" + machines + "]";
	}    
    
}