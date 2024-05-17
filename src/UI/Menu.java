package UI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.persistence.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import PhysioClinicGUI.*; 
import PhysioClinicIFaces.*;
import PhysioClinicJDBC.*;
import PhysioClinicJPA.*;
import PhysioClinicPOJOs.*;
import PhysioClinicEncription.Encription;

public class Menu {
	
	private JLabel label;
    private JButton loginButton, signUpButton, updateButton, exitButton;

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
	private static XMLManager xmlmanager;

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
		
        new MainMenu().setVisible(true);}
        
        /*try {
			
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
					login(); break; 			
					
				case 2:
					System.out.println("Add info of new user:");
					signUpUser(); break; 
				
				case 3: 
					System.out.println("Udpate the password of an exissting user:");
					updatePassword(); break; 
				
				case 0:
					System.out.println("Exiting application.");
					jdbcmanager.disconnect(); break; 
				}
				
			}while(choice!=0);
			
			
		} catch(Exception e){
			e.printStackTrace();
			}
	} //límite main*/
	
       
    public static void login() {
    	String email = null; 
    	String passwd = null; 
    	byte [] encrypted_passwd = null; 
    	
		try {
			System.out.println("Email: ");
			email = reader.readLine();
			
			System.out.println("Password: ");
			passwd = reader.readLine();
			encrypted_passwd = PhysioClinicEncription.Encription.encrypt(passwd);
		}catch(Exception e){e.printStackTrace();}
		
		User u = usermanager.checkPassword(email, encrypted_passwd);
		
		if(u!=null & u.getRole().getName().equals("Physiotherapist"))
		{
			System.out.println("Login of owner successful!");
			//call for physiotherapist submenu
			new PhysioMenu().setVisible(true);
			//physioMenu(email);
		} else if(u!=null & u.getRole().getName().equals("Client")){
			System.out.println("Login of owner successful!");
			//call for client submenu;
			new ClientMenu().setVisible(true);
			//clientMenu(email);
		} else if (u!=null & u.getRole().getName().equals("Engineer")){
			System.out.println("Login of owner successful!");
			//call for engineer submenu;
			//engMenu(email);
			new EngMenu().setVisible(true);
		}
		
	}
    
    public static void signUpUser() {
		try {
			System.out.println("Introduce email: ");
			String email = reader.readLine();
			
			System.out.println("Introduce the password");
			String passwd = reader.readLine();
			byte[] encrypted_passwd = PhysioClinicEncription.Encription.encrypt(passwd);
			
			//MessageDigest md= MessageDigest.getInstance("MD5");
			//md.update(encrypted_passwd.getBytes());
			
			System.out.println("Introduce the role of the user. 1: physiotherapist, 2: client, 3:engineer");
			Integer rol = Integer.parseInt(reader.readLine());
			Role r = usermanager.getRole(rol);
			
			User u = new User(email, encrypted_passwd, r);
			
			usermanager.newUser(u);
			
			if(u!=null & u.getRole().getName().equals("Physiotherapist")){
				System.out.println("Insert new physiotherpist´s information ->");
				createPhys(); 
				
			} else if(u!=null & u.getRole().getName().equals("Client")){
				System.out.println("Insert new client´s information -> ");
				createClient();
				
			} else if (u!=null & u.getRole().getName().equals("Engineer")){
				System.out.println("Insert new engineer´s information -> ");
				createEng();
				
			}
			
			
		}catch(Exception e){e.printStackTrace();}
	}
    
    public static void createClient() throws Exception {
    	Client c = null; 

    	System.out.println("Type the id of the client");
		Integer id = Integer.parseInt(reader.readLine());
    	System.out.println("Type the name of the client");
		String name = reader.readLine();
		System.out.println("Type the phone of the client");
		String phone = reader.readLine();
		
		System.out.println("Type the dob of the client, format=yyyy/mm/dd");
		String dob_str = reader.readLine();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate dob_LC = LocalDate.parse(dob_str, formatter);
		Date dob = Date.valueOf(dob_LC);
		
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
    
    private static void createPhys() throws IllegalArgumentException, Exception {
    	Physio p = null; 
    	
    	System.out.println("Type the id of the physio");
		Integer id = Integer.parseInt(reader.readLine());
    	System.out.println("Type the name of the physio");
		String name = reader.readLine();
		System.out.println("Type the phone of the physio");
		String phone = reader.readLine();

		System.out.println("Type the dob of the engineer, format=yyyy/mm/dd");
		String dob_str = reader.readLine();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate dob_LC = LocalDate.parse(dob_str, formatter);
		Date dob = Date.valueOf(dob_LC);
		
		System.out.println("Type the email of the physio");
		String email = reader.readLine();
		System.out.println("Type the salary of the physio");
		Float salary = Float.parseFloat(reader.readLine());
		System.out.println("Type the speciality of the physio");
		String specialty = reader.readLine();
    	
		p = new Physio(id, name, phone, dob, specialty, email, salary);
		
    	physiomanager.createPhysio(p);
    }
    
    private static void createEng() throws NumberFormatException, Exception {
    	Engineer eng = null; 
    	
    	System.out.println("Type the id of the engineer");
		Integer id = Integer.parseInt(reader.readLine());
    	System.out.println("Type the name of the engineer");
		String name = reader.readLine();
		System.out.println("Type the phone of the engineer");
		String phone = reader.readLine();
		

		System.out.println("Type the dob of the engineer, format=yyyy/mm/dd");
		String dob_str = reader.readLine();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate dob_LC = LocalDate.parse(dob_str, formatter);
		Date dob = Date.valueOf(dob_LC);
		
		System.out.println("Type the email of the engineer");
		String email = reader.readLine();
		System.out.println("Type the salary of the engineer");
		Float salary = Float.parseFloat(reader.readLine());
		System.out.println("Type the speciality of the engineer");
		String specialty = reader.readLine();
		
		
		eng = new Engineer(id, name, phone, dob, specialty, email, salary);
		
		engineermanager.createEngineer(eng);
    }
      
    public static void updatePassword() {
    	String email = null; 
    	String passwd = null; 
    	byte[] encrypted_passwd = null; 
    	
    	try {
    		System.out.println("Email: ");
    		email = reader.readLine();
    				
    		System.out.println("Enter current password:");
    		passwd = reader.readLine();
    		encrypted_passwd = PhysioClinicEncription.Encription.encrypt(passwd);
    	}catch(Exception e){e.printStackTrace();}
		
		
		User u = usermanager.checkPassword(email, encrypted_passwd);
		
		if(u!=null) {
			try {
				System.out.println("Login of owner successful!");
				
				System.out.println("Enter new password: ");
				String new_passwd = reader.readLine();
				byte[] newEncrypted_passwd = PhysioClinicEncription.Encription.encrypt(new_passwd);
				
				usermanager.changePassword(email, newEncrypted_passwd);
			}catch (Exception e){e.printStackTrace();}
		}
				
	}
    

    public static void physioMenu(String email) {
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
				System.out.println("6. Print me to xml.");
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
				case 6: 
					printMe(id);
				case 0:
					System.out.println("Back to main menu");
					
				}
				
			}while(choice!=0);
			
			
		}catch(Exception e)
		{e.printStackTrace();}
	}
    
    private static void printMe(Integer id) {
		// TODO Auto-generated method stub
		xmlmanager.physio2xml(id);
	
	}
    

    public static void showClients() {
    	List<Client> clients = null;
		
		clients = clientmanager.showAllClients();
		
		System.out.println(clients);
    }
    
    public static void deleteClient() throws NumberFormatException, Exception {
    	System.out.println("Type the id of the client");
		Integer id = Integer.parseInt(reader.readLine());
		
		clientmanager.deleteClientByID(id);
    }
    
    public static void searchClientID() throws NumberFormatException, Exception {
    	Client c = null; 
    	System.out.println("Type the id of the client");
		Integer id = Integer.parseInt(reader.readLine());
		
		c = clientmanager.searchClientByID(id);
		System.out.println(c);
    }
    
    public static void searchEngID() throws NumberFormatException, Exception {
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
		String new_ph = reader.readLine();
		
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
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		Date dob = (Date) df.parse(dob_str);
		
		System.out.println("Type in the date in which the machine was bought, format=yyyy/mm/dd");
		String db_str = reader.readLine();
		Date db = (Date) df.parse(db_str);
		
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