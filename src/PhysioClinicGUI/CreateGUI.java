package PhysioClinicGUI;

import java.awt.Checkbox;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import PhysioClinicIFaces.ClientManager;
import PhysioClinicIFaces.EngineerManager;
import PhysioClinicIFaces.ExamsManager;
import PhysioClinicIFaces.MachineManager;
import PhysioClinicIFaces.PhysioManager;
import PhysioClinicIFaces.ProductsManager;
import PhysioClinicIFaces.ProstheticsManager;
import PhysioClinicIFaces.UserManager;
import PhysioClinicIFaces.XMLManager;
import PhysioClinicJDBC.JDBCManager;
import PhysioClinicJDBC.JDBCPhysioManager;
import PhysioClinicPOJOs.Client;
import PhysioClinicPOJOs.Engineer;
import PhysioClinicPOJOs.Physio;

public class CreateGUI extends JFrame{
		private static JDBCManager jdbcmanager;
		private static PhysioManager physiomanager = new JDBCPhysioManager(jdbcmanager);
	
	public static Engineer CreateEngineerGUI(String email) {
	   	 JFrame frame;
	   	 JTextField idField, nameField, phoneField, dobField, salaryField, specialityField;
	   		   	
	       frame = new JFrame("Create Engineer");
	       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       frame.setSize(400, 400);
	       frame.setLayout(new GridLayout(10, 2));

	       frame.add(new JLabel("Engineer ID:"));
	       idField = new JTextField();
	       frame.add(idField);

	       frame.add(new JLabel("Name:"));
	       nameField = new JTextField();
	       frame.add(nameField);

	       frame.add(new JLabel("Phone:"));
	       phoneField = new JTextField();
	       frame.add(phoneField);

	       frame.add(new JLabel("Date of Birth (yyyy/MM/dd):"));
	       dobField = new JTextField();
	       frame.add(dobField);

	       frame.add(new JLabel("Salary:"));
	       salaryField = new JTextField();
	       frame.add(salaryField);

	       frame.add(new JLabel("Speciality:"));
	       specialityField = new JTextField();
	       frame.add(specialityField);

	       JButton submitButton = new JButton("Create Engineer");
	       frame.add(submitButton);
	       
	       frame.add(new JLabel(""));
	       final Engineer[] engineer = {};

	       submitButton.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {
	               try {
	                   int id = Integer.parseInt(idField.getText());
	                   String name = nameField.getText();
	                   String phone = phoneField.getText();
	                   
	                   String dobStr = dobField.getText();
	                   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	                   LocalDate dobLC = LocalDate.parse(dobStr, formatter);
	                   Date dob = Date.valueOf(dobLC);

	                   float salary = Float.parseFloat(salaryField.getText());
	                   String speciality = phoneField.getText();
	                   
	                   Engineer eng = new Engineer(id, name, phone, dob, speciality, email, salary); 

	                   engineer[0] = eng; 
	                   
	                   JOptionPane.showMessageDialog(frame, "Engineer created successfully!");
	                   frame.dispose();
	               } catch (Exception ex) {
	                   JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	               }
	           }
	           
	       });



	       frame.setVisible(true);
	       while (frame.isVisible()) {
	           try {
	               Thread.sleep(100); 
	           } catch (InterruptedException ex) {
	               ex.printStackTrace();
	           }
	       }
	       return engineer[0]; 
	   }
	
	public static Physio CreatePhysioGUI(String email) {
     	 JFrame frame;
     	 JTextField idField, nameField, phoneField, dobField, salaryField, specialityField;
     	     	
         frame = new JFrame("Create Physiotherapist");
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setSize(400, 400);
         frame.setLayout(new GridLayout(10, 2));

         frame.add(new JLabel("Physiotherapist ID:"));
         idField = new JTextField();
         frame.add(idField);
         
         frame.add(new JLabel("Name:"));
         nameField = new JTextField();
         frame.add(nameField);

         frame.add(new JLabel("Phone:"));
         phoneField = new JTextField();
         frame.add(phoneField);

         frame.add(new JLabel("Date of Birth (yyyy/MM/dd):"));
         dobField = new JTextField();
         frame.add(dobField);

         frame.add(new JLabel("Salary:"));
         salaryField = new JTextField();
         frame.add(salaryField);

         frame.add(new JLabel("Speciality:"));
         specialityField = new JTextField();
         frame.add(specialityField);

         // Create the submit button
         JButton submitButton = new JButton("Create Physiotherapist");
         frame.add(submitButton);
         

         // Add empty label for alignment
         frame.add(new JLabel(""));
         final Physio[] physio = {};

         submitButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 try {
                     int id = Integer.parseInt(idField.getText());
                     String name = nameField.getText();
                     String phone = phoneField.getText();
                     
                     String dobStr = dobField.getText();
                     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                     LocalDate dobLC = LocalDate.parse(dobStr, formatter);
                     Date dob = Date.valueOf(dobLC);
                     
                     float salary = Float.parseFloat(salaryField.getText());
                     String speciality = phoneField.getText();
                     
                     Physio p = new Physio(id, name, phone, dob, speciality, email, salary); 

                     physio[0] = p; 
                     
                     JOptionPane.showMessageDialog(frame, "Physiotherapist created successfully!");
                     frame.dispose();
                 } catch (Exception ex) {
                     JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                 }
             }
             
         });



         frame.setVisible(true);
         while (frame.isVisible()) {
             try {
                 Thread.sleep(100); 
             } catch (InterruptedException ex) {
                 ex.printStackTrace();
             }
         }
         return physio[0]; 
     }
	
	public static Client CreateClientGUI() {
   	 JFrame frame;
   	 JTextField idField, nameField, phoneField, dobField, cardNumberField, emailField, physioIdField;
   	 Checkbox largeFamilyCheckbox;
   	
       frame = new JFrame("Create Client");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(400, 400);
       frame.setLayout(new GridLayout(10, 2));

       frame.add(new JLabel("Client ID:"));
       idField = new JTextField();
       frame.add(idField);

       frame.add(new JLabel("Name:"));
       nameField = new JTextField();
       frame.add(nameField);

       frame.add(new JLabel("Phone:"));
       phoneField = new JTextField();
       frame.add(phoneField);

       frame.add(new JLabel("Date of Birth (yyyy/MM/dd):"));
       dobField = new JTextField();
       frame.add(dobField);

       frame.add(new JLabel("Card Number:"));
       cardNumberField = new JTextField();
       frame.add(cardNumberField);

       frame.add(new JLabel("Large Family:"));
       largeFamilyCheckbox = new Checkbox();
       frame.add(largeFamilyCheckbox);

       frame.add(new JLabel("Email:"));
       emailField = new JTextField();
       frame.add(emailField);

       frame.add(new JLabel("Physiotherapist ID:"));
       physioIdField = new JTextField();
       frame.add(physioIdField);
       

       JButton submitButton = new JButton("Create Client");
       frame.add(submitButton);
       

       frame.add(new JLabel(""));
       final Client[] client = {};

       submitButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               try {
                   int id = Integer.parseInt(idField.getText());
                   String name = nameField.getText();
                   String phone = phoneField.getText();

                   String dobStr = dobField.getText();
                   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                   LocalDate dobLC = LocalDate.parse(dobStr, formatter);
                   Date dob = Date.valueOf(dobLC);

                   int cardNumber = Integer.parseInt(cardNumberField.getText());
                   boolean largeFamily = largeFamilyCheckbox.getState();
                   String email = emailField.getText();
                   int physioID = Integer.parseInt(physioIdField.getText());
                   Physio p = physiomanager.searchPhysioByID(physioID);
                   //System.out.println(p);
                   
                   Client c = new Client(id, p, name, phone, dob, cardNumber, largeFamily, email);

                   client[0] = c; 
                   
                   JOptionPane.showMessageDialog(frame, "Client created successfully!");
                   frame.dispose();
               } catch (Exception ex) {
                   JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
               }
           }
           
       });



       frame.setVisible(true);
       while (frame.isVisible()) {
           try {
               Thread.sleep(100); 
           } catch (InterruptedException ex) {
               ex.printStackTrace();
           }
       }
       
       return client[0]; 
   }
}
