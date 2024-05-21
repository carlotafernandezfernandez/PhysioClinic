package PhysioClinicJPA;

import java.security.MessageDigest;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import PhysioClinicIFaces.UserManager;
import PhysioClinicPOJOs.Role;
import PhysioClinicPOJOs.User;

public class JPAUserManager implements UserManager{
	private EntityManager em;
	
	public JPAUserManager() {
		super();
		this.connect();
	}
	
	@Override
	public void connect() {
		// TODO Auto-generated method stub
		em = Persistence.createEntityManagerFactory("physioclinic-provider").createEntityManager();
		
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys = ON").executeUpdate();
		em.getTransaction().commit();
		
		if(this.getRoles().isEmpty())
		{
			Role physio = new Role("Physiotherapist");
			Role client = new Role("Client");
			Role engineer = new Role("Engineer");
			this.newRole(physio);
			this.newRole(client);
			this.newRole(engineer);
		}
	}
	
	@Override
	public void disconnect() {
		// TODO Auto-generated method stub
		em.close();
	}
	
	@Override
	public void newRole(Role r) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		em.persist(r);
		em.getTransaction().commit();
		
	}
	
	@Override
	public void newUser(User u) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();
	}
	
	@Override
	public List<Role> getRoles() {
		// TODO Auto-generated method stub
		Query q = em.createNativeQuery("SELECT * FROM roles", Role.class);
		List<Role> roles = (List<Role>) q.getResultList();
		
		return roles;
	}
	
	@Override
	public Role getRole(Integer id) {
		// TODO Auto-generated method stub
		Query q = em.createNativeQuery("SELECT * FROM roles where id="+id, Role.class);
		Role r = (Role) q.getSingleResult();
		
		return r;
	}
	
	@Override
	public User getUser(String email) {
		// TODO Auto-generated method stub
		Query q = em.createNativeQuery("SELECT * FROM users where email="+email, User.class);
		User u = (User) q.getSingleResult();
		
		return u;
	}
	
	@Override
	public User checkPassword(String email, byte[] pass) {
		// TODO Auto-generated method stub
		User u = null;
		
		Query q = em.createNativeQuery("SELECT * from users where email =? and password=?", User.class);
		q.setParameter(1, email);
		
		try {
			q.setParameter(2, pass);
			
		}catch(Exception e)
		{e.printStackTrace();}
			
		
		try {
			u = (User) q.getSingleResult();
			
		}catch(NoResultException e) {}
		
		return u;
	}
	
	@Override
	public void changePassword(String email, byte[] new_passwd) {
		try {
			Query q2 = em.createNativeQuery("SELECT * FROM users WHERE email = ?", User.class);
			q2.setParameter(1, email);
			User user = (User) q2.getSingleResult();
			
			em.getTransaction().begin();
			user.setPassword(new_passwd);
			em.getTransaction().commit();
			
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		
	}

}
