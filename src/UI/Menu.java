package UI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

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
			
			
		}catch(Exception e)
		{e.printStackTrace();}
	}
	

}