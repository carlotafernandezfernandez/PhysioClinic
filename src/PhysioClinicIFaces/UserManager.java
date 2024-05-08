package PhysioClinicIFaces;

import java.util.List;
import PhysioClinicPOJOs.Role;
import PhysioClinicPOJOs.User;

public interface UserManager {
	
	public void connect();
	public void disconnect();
	public void newRole(Role r);
	public void newUser(User u);
	public List<Role> getRoles();
	public Role getRole(Integer id);
	public User getUser(String email);
	public User checkPassword(String email, String pass);
	public void changePassword(String email, String new_passwd);

}