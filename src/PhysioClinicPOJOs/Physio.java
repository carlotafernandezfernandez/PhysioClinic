package PhysioClinicPOJOs; 
import java.util.*;

import javax.persistence.Basic;
import javax.persistence.FetchType;
import javax.persistence.Lob;

import java.sql.Date; 
import java.io.Serializable;

public class Physio implements Serializable {
    
    private static final long serialVersionUID = 1004619484145801436L;
	
    private int id; 
    private String name; 
    private String phone; 
    private Date doB; 
    private String specialty; 
    private String email; 
    private float salary; 
    @Basic(fetch=FetchType.LAZY)
    @Lob
    private byte[] license; 
    private List<Products> products; 
    private List<Exams> exams;
    private List<Client> clients;
	
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
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
	public List<Exams> getExams() {
		return exams;
	}
	public void setExams(List<Exams> exams) {
		this.exams = exams;
	}
	public List<Client> getClients() {
		return clients;
	}
	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Physio(int id, String name, String phone, Date doB, String specialty, String email, float salary) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.doB = doB;
		this.specialty = specialty;
		this.email = email;
		this.salary = salary;
		this.products = new ArrayList<Products>();
		this.exams = new ArrayList<Exams>();;
		this.clients = new ArrayList<Client>();;
	} 
	public Physio(String name, String phone, Date doB, String specialty, String email, float salary) {
		super();
		this.name = name;
		this.phone = phone;
		this.doB = doB;
		this.specialty = specialty;
		this.email = email;
		this.salary = salary;
		this.products = new ArrayList<Products>();
		this.exams = new ArrayList<Exams>();;
		this.clients = new ArrayList<Client>();;
	} 
	public Physio() {
		super();
		this.products = new ArrayList<Products>();
		this.exams = new ArrayList<Exams>();;
		this.clients = new ArrayList<Client>();;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(license);
		result = prime * result
				+ Objects.hash(clients, doB, email, exams, id, name, phone, products, salary, specialty);
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
		Physio other = (Physio) obj;
		return Objects.equals(clients, other.clients) && Objects.equals(doB, other.doB)
				&& Objects.equals(email, other.email) && Objects.equals(exams, other.exams) && id == other.id
				&& Arrays.equals(license, other.license) && Objects.equals(name, other.name) && phone == other.phone
				&& Objects.equals(products, other.products)
				&& Float.floatToIntBits(salary) == Float.floatToIntBits(other.salary)
				&& Objects.equals(specialty, other.specialty);
	}
	
	@Override
	public String toString() {
		return "Physio [id=" + id + ", name=" + name + ", phone=" + phone + ", doB=" + doB + ", specialty=" + specialty
				+ ", email=" + email + ", salary=" + salary + ", license=" + Arrays.toString(license) + ", products="
				+ products + ", exams=" + exams + ", clients=" + clients + "]";
	}
	
	
    
}