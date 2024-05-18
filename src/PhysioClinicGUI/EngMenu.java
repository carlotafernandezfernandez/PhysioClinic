package PhysioClinicGUI;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import PhysioClinicPOJOs.Client;
import PhysioClinicPOJOs.Engineer;
import PhysioClinicPOJOs.Physio;
import UI.Menu;

public class EngMenu extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel label;
    private JButton loginButton, signUpButton, updateButton, exitButton;

    public EngMenu(int id)  {
        super("engMenu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        label = new JLabel("Choose an option:");
        label.setBounds(20, 20, 280, 30);
        label.setBackground(Color.GREEN); 
        label.setOpaque(true); 
        add(label);

        loginButton = new JButton("Change phone by ID");
        loginButton.setBounds(20, 60, 150, 30);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					Menu.changeEngPhoneID();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        add(loginButton);

        signUpButton = new JButton("Create a machine");
        signUpButton.setBounds(20, 100, 150, 30);
        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					Menu.createMachine();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        add(signUpButton);

        updateButton = new JButton("Show all machines");
        updateButton.setBounds(20, 140, 150, 30);
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					Menu.showMachines();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        add(updateButton);
        
        updateButton = new JButton("Change product availability");
        updateButton.setBounds(20, 180, 150, 30);
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					Menu.changeProdAvailability();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        add(updateButton);
        
        updateButton = new JButton("Show all prosthetics of same type");
        updateButton.setBounds(20, 220, 150, 30);
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					Menu.searchEngID();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        add(updateButton);

        exitButton = new JButton("Back to main menu");
        exitButton.setBounds(20, 260, 150, 30);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Exiting application.");
                // Perform any cleanup tasks here
                System.exit(0);
            }
        });
        add(exitButton);
    }
    
    public static Engineer CreateEngineerGUI() {
   	 JFrame frame;
   	 JTextField idField, nameField, phoneField, dobField, salaryField, emailField, physioIdField, specialityField;
   	 Engineer eng; 
   	
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

       frame.add(new JLabel("Email:"));
       emailField = new JTextField();
       frame.add(emailField);

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

                   String email = emailField.getText();
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

}
