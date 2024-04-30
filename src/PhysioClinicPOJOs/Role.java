package PhysioClinicPOJOs; 
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="roles")
public class Role implements Serializable{
	
	private static final long serialVersionUID = -1277299929730135337L;
	
	@Id
	@GeneratedValue(generator = "roles")
	@TableGenerator(name = "roles", table = "sqlite_sequence", pkColumnName = "name", valueColumnName="seq", pkColumnValue = "roles")
	private Integer id;
	private String name;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
	private List<User> users;
	
	
	public Role() {
		super();
	}


	public Role(String name) {
		super();
		this.name = name;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<User> getUsers() {
		return users;
	}


	public void setUsers(List<User> users) {
		this.users = users;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id, name, users);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name) && Objects.equals(users, other.users);
	}


	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
		}
	
	}
