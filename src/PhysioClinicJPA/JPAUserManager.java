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
		//PHYSIOCLINIC-PROVIDER QUE ES
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
	public User checkPassword(String email, String pass) {
		// TODO Auto-generated method stub
		User u = null;
		
		Query q = em.createNativeQuery("SELECT * from users where email =? and password=?", User.class);
		q.setParameter(1, email);
		
		try {
			
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(pass.getBytes());
			byte[] pw = md.digest();
			
			q.setParameter(2, pw);
			
		}catch(Exception e)
		{e.printStackTrace();}
			
		
		try {
			u = (User) q.getSingleResult();
			
		}catch(NoResultException e) {}
		
		return u;
	}

}
