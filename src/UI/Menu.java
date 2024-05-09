package UI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.persistence.*;

import PhysioClinicIFaces.*;
import PhysioClinicJDBC.*;
import PhysioClinicJPA.*;
import PhysioClinicPOJOs.*;

public class Menu {

	private static JDBCManager jdbcmanager;
	private static ClientManager clientmanager;
	private static EngineerManager engineermanager;
	private static PhysioManager physiomanager;
    private static UserManager usermanager;
    private static ExamsManager examsmanager;
    private static MachineManager machinemanager;
    private static ProductsManager productsmanager;
    private static ProstheticsManager prostheticsmanager;
    private static BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
	

    public static void main(String[] args) {
		
		jdbcmanager = new JDBCManager();
		clientmanager = new JDBCClientManager(jdbcmanager); 
		engineermanager = new JDBCEngineerManager(jdbcmanager);
		physiomanager = new JDBCPhysioManager(jdbcmanager);
        examsmanager = new JDBCExamsManager(jdbcmanager);
        machinemanager = new JDBCMachineManager(jdbcmanager);     
        productsmanager = new JDBCProductsManager(jdbcmanager);
        prostheticsmanager = new JDBCProstheticsManager(jdbcmanager);
        usermanager = new JPAUserManager();
		
		try {
			int choice;
			
			do {
				
				System.out.println("Choose an option");
				System.out.println("1. Login User");
				System.out.println("2. Sign-up new user");
				System.out.println("3. Update password");
				System.out.println("0. Exit.");
								
				choice = Integer.parseInt(reader.readLine());
										
				switch(choice)
				{
				case 1: 
					login();					
					
				case 2:
					System.out.println("Add info of new user.");
					signUpUser();
				
				case 3: 
					System.out.println("Udpate the password of an exissting user.");
					updatePassword();
				
				case 0:
					System.out.println("Exiting application.");
					jdbcmanager.disconnect();
				}
				
			}while(choice!=0);
			
			
		} catch(Exception e){
			e.printStackTrace();
			}
	} //límite main
    
    private static void login() throws Exception {
		System.out.println("Email: ");
		String email = reader.readLine();
		
		System.out.println("Password: ");
		String passwd = reader.readLine();
		
		User u = usermanager.checkPassword(email, passwd);
		
		if(u!=null & u.getRole().getName().equals("physiotherapist"))
		{
			System.out.println("Login of owner successful!");
			//call for physiotherapist submenu;
			physioMenu(email);
		} else if(u!=null & u.getRole().getName().equals("client")){
			System.out.println("Login of owner successful!");
			//call for client submenu;
			clientMenu(email);
		} else if (u!=null & u.getRole().getName().equals("engineer")){
			System.out.println("Login of owner successful!");
			//call for engineer submenu;
			engMenu(email);
		}
		
	}
    
    private static void signUpUser() {
		try {
			System.out.println("Introduce email: ");
			String email = reader.readLine();
			System.out.println("Introduce the password");
			String password = reader.readLine();
			
			MessageDigest md= MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] pass = md.digest();
			
			System.out.println("Introduce the role of the user. 1: physiotherapist, 2: client, 3:engineer");
			Integer rol = Integer.parseInt(reader.readLine());
			Role r = usermanager.getRole(rol);
			
			User u = new User(email, pass, r);
			
			usermanager.newUser(u);
		}catch(Exception e){e.printStackTrace();}
	}
      
    private static void updatePassword() throws Exception {
		System.out.println("Email: ");
		String email = reader.readLine();
				
		System.out.println("Enter current Password: ");
		String passwd = reader.readLine();
		
		User u = usermanager.checkPassword(email, passwd);
		
		if(u!=null)
		{
			System.out.println("Enter new Password: ");
			String new_passwd = reader.readLine();
			System.out.println("Login of owner successful!");
			usermanager.changePassword(email, new_passwd);
		}
				
	}
    
    private static void physioMenu(String email) {
		// TODO Auto-generated method stub
		try {
			int choice;
			do {
				System.out.println("Choose an option");
				System.out.println("1. Create client");
				System.out.println("2. Show all clients");
				System.out.println("3. Delete client");
				System.out.println("4. Search client by ID");
				System.out.println("5. Search engineer by ID");
				System.out.println("0. Return.");
				
				choice = Integer.parseInt(reader.readLine());
								
				switch(choice)
				{
				case 1: 
					createClient();
					break;
				case 2:
					showClients();
					break;
				case 3:
					deleteClient();
					break;
				case 4:
					searchClientID();
					break;
	
				case 5:
					searchEngID();
					break;
				case 0:
					System.out.println("Back to main menu");
					
				}
				
			}while(choice!=0);
			
			
		}catch(Exception e)
		{e.printStackTrace();}
	}
    
    private static void createClient() throws Exception {
    	Client c = null; 
    	
    	System.out.println("Type the id of the client");
		Integer id = Integer.parseInt(reader.readLine());
    	System.out.println("Type the name of the client");
		String name = reader.readLine();
		System.out.println("Type the phone of the client");
		Integer phone = Integer.parseInt(reader.readLine());
		System.out.println("Type the dob of the client, format=yyyy/mm/dd");
		String dob_str = reader.readLine();
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		Date dob = (Date) df.parse(dob_str);
		System.out.println("Type the cardnumber of the client");
		Integer cardnumber = Integer.parseInt(reader.readLine());
		System.out.println("Client has large family (YES:1, NO:0)");
		Boolean largeFam = Boolean.valueOf(reader.readLine());
		System.out.println("Type the email of the client");
		String email = reader.readLine();
		System.out.println("Type the ID of the assigned physiotherapist");
		int physioID = Integer.parseInt(reader.readLine());
		Physio p = physiomanager.searchPhysioByID(physioID);
    	
		c = new Client(id, p, name, phone, dob, cardnumber, largeFam, email);
		
    	clientmanager.createClient(c);
    }
    
    private static void showClients() {
    	List<Client> clients = null;
		
		clients = clientmanager.showAllClients();
		
		System.out.println(clients);
    }
    
    private static void deleteClient() throws NumberFormatException, Exception {
    	System.out.println("Type the id of the client");
		Integer id = Integer.parseInt(reader.readLine());
		
		clientmanager.deleteClientByID(id);
    }
    
    private static void searchClientID() throws NumberFormatException, Exception {
    	Client c = null; 
    	System.out.println("Type the id of the client");
		Integer id = Integer.parseInt(reader.readLine());
		
		c = clientmanager.searchClientByID(id);
		System.out.println(c);
    }
    
    private static void searchEngID() throws NumberFormatException, Exception {
    	Engineer e = null; 
    	System.out.println("Type the id of the engineer");
		Integer id = Integer.parseInt(reader.readLine());
		
		e = engineermanager.searchEngineerByID(id);
		System.out.println(e);
    }
    
    private static void clientMenu(String email) {
		// TODO Auto-generated method stub
		try {
			int choice;
			do {
				System.out.println("Choose an option");
				System.out.println("1. Search physiotherapist by ID");
				System.out.println("2. Delete exam by (exam)ID");
				System.out.println("0. Return.");
				
				choice = Integer.parseInt(reader.readLine());
								
				switch(choice)
				{
				case 1: 
					searchPhysioID();
					break;
				case 2:
					deleteExamID();
					break;
				case 0:
					System.out.println("Back to main menu");
					
				}
				
			}while(choice!=0);
			
			
		}catch(Exception e)
		{e.printStackTrace();}
	}
    
    public static void searchPhysioID() throws NumberFormatException, Exception {
    	Physio p = null; 
    	System.out.println("Type the id of the physio");
		Integer id = Integer.parseInt(reader.readLine());
		
		p = physiomanager.searchPhysioByID(id);
		System.out.println(p);
    }
    
    public static void deleteExamID() throws NumberFormatException, Exception {
    	System.out.println("Type the id of the exam");
		Integer id = Integer.parseInt(reader.readLine());
		
		examsmanager.deleteExamByID(id);
    }
    
    private static void engMenu(String email) {
		// TODO Auto-generated method stub
		try {
			int choice;
			do {
				System.out.println("Choose an option");
				System.out.println("1. Change phone by ID");
				System.out.println("2. Create a machine"); 
				System.out.println("3. Show all machines");
				System.out.println("4. Change product availability");
				System.out.println("5. Show all prosthetics of same type");
				System.out.println("0. Return.");
				
				choice = Integer.parseInt(reader.readLine());
								
				switch(choice) {
				case 1: 
					changeEngPhoneID();
					break;
				case 2:
					createMachine();
					break;
				case 3:
					showMachines();
					break;
				case 4:
					changeProdAvailability();
					break;
				case 5:
					prostheticsType();
					break;
				case 0:
					System.out.println("Back to main menu");
					
				}
				
			}while(choice!=0);
			
			
		}catch(Exception e)
		{e.printStackTrace();}
	}
    
    public static void changeEngPhoneID() throws NumberFormatException, Exception {
    	System.out.println("Type the id of the engineer");
		Integer id = Integer.parseInt(reader.readLine());
		System.out.println("Type the new phone number of the engineer");
		Integer new_ph = Integer.parseInt(reader.readLine());
		
		engineermanager.changeEngineerTelephoneByID(new_ph, id);
    }
    
    public static void createMachine() throws NumberFormatException, Exception {
    	Machine m = null; 
    	
    	System.out.println("Type the id of the machine");
		Integer id = Integer.parseInt(reader.readLine());
    	System.out.println("Type in the type of the machine");
		String type = reader.readLine();
		System.out.println("Type the dob of the machine, format=yyyy/mm/dd");
		String dob_str = reader.readLine();
		DateFormat df1 = new SimpleDateFormat("yyyy/MM/dd");
		Date dob = (Date) df1.parse(dob_str);
		System.out.println("Type in the date in which the machine was bought, format=yyyy/mm/dd");
		String db_str = reader.readLine();
		DateFormat df2 = new SimpleDateFormat("yyyy/MM/dd");
		Date db = (Date) df2.parse(db_str);
		System.out.println("Type the ID of the assigned engineer");
		int engID = Integer.parseInt(reader.readLine());
		Engineer e = engineermanager.searchEngineerByID(engID);
		
		m = new Machine(id, type, dob, db, e);
		
    	machinemanager.createMachine(m);
    }
    
    public static void showMachines() {
    	List<Machine> machines = null;
		
		machines = machinemanager.showAllMachines();
		
		System.out.println(machines);
    }
    
    public static void changeProdAvailability() throws NumberFormatException, Exception {
    	System.out.println("Type the id of the product");
		Integer id = Integer.parseInt(reader.readLine());
		System.out.println("Type new number of available product");
		Integer newN_available = Integer.parseInt(reader.readLine());
		
		productsmanager.changeProductNAvailable(id, newN_available);
    }
    
    public static void prostheticsType() throws Exception {
    	System.out.println("Type in the type of the prosthetic");
		String type = reader.readLine();
		
		prostheticsmanager.showAllProstheticsOfType(type);
    }

}//limite menu