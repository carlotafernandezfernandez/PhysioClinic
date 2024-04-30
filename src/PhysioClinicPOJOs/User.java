package PhysioClinicPOJOs; 
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;


@Entity
@Table(name= "users")
public class User implements Serializable {
	
	private static final long serialVersionUID = 4922087932630492957L;
	
	@Id
	@GeneratedValue (generator = "users")
	@TableGenerator(name = "users", table = "sqite_sequence",  pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "users")
	private Integer id;
	private String email;
	@Lob
	private byte[] password;
	@ManyToOne
	@JoinColumn (name = "role_id")
	private Role role;
	
	public User() {
		super();
	}
	
	public User(String email, byte[] password, Role role) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public byte[] getPassword() {
		return password;
	}
	public void setPassword(byte[] password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(password);
		result = prime * result + Objects.hash(email, id, role);
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
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Arrays.equals(password, other.password) && Objects.equals(role, other.role);
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + Arrays.toString(password) + ", role=" + role
				+ "]";
	}
	
	
	
}