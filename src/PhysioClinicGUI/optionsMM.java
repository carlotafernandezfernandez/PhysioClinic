package PhysioClinicGUI;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import PhysioClinicIFaces.*;
import PhysioClinicJPA.JPAUserManager;
import PhysioClinicPOJOs.User;

public class optionsMM extends JFrame{

    private static UserManager userManager = new JPAUserManager();
    
	public static void LoginGUI() {
		
	   	 	JTextField emailField, passwdField;
	   		   	
	       JFrame frame = new JFrame("");
	       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       frame.setSize(300, 300);
	       frame.setLayout(new GridLayout(3, 2));

	       frame.add(new JLabel("Email:"));
	       emailField = new JTextField();
	       frame.add(emailField);

	       frame.add(new JLabel("Password:"));
	       passwdField = new JTextField();
	       frame.add(passwdField);

	       JButton submitButton = new JButton("log in");
	       frame.add(submitButton);
	       
	       frame.add(new JLabel(""));

	       submitButton.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {
	               try {

	                   String password = passwdField.getText();
	                   String email = emailField.getText();
	                   byte [] passwordE = PhysioClinicEncription.Encription.encrypt(password);

	                   User u = userManager.checkPassword(email, passwordE);
	                   
	                   if (u != null && u.getRole().getName().equals("Physiotherapist")) {
	                       System.out.println("Login of physiotherapist successful!");
	                       new PhysioMenu(u.getId()).setVisible(true);
	                       frame.dispose(); // Close login window
	                   } else if (u != null && u.getRole().getName().equals("Client")) {
	                       System.out.println("Login of client successful!");
	                       new ClientMenu(u.getId()).setVisible(true);
	                       frame.dispose(); // Close login window
	                   } else if (u != null && u.getRole().getName().equals("Engineer")) {
	                       System.out.println("Login of engineer successful!");
	                       new EngMenu(u.getId()).setVisible(true);
	                      frame.dispose(); // Close login window
	                   } else {
	                       JFrame frame = null;
	   					JOptionPane.showMessageDialog(frame, "Please enter a valid integer for ID.");
	                   }
	                   
	                   frame.dispose();
	               } catch (Exception ex) {
	                   JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	               }
	           }
	           
	       });
	}
	
	public static void main (String []args ) {
		optionsMM.LoginGUI();
	}
	
}
