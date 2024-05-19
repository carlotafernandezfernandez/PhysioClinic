package PhysioClinicGUI;
import UI.*; 
import javax.swing.*;

import PhysioClinicIFaces.ClientManager;
import PhysioClinicPOJOs.Client;
import PhysioClinicPOJOs.Engineer;
import PhysioClinicPOJOs.Physio;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import UI.Menu;

public class PhysioMenu extends JFrame{
	private JLabel label;
    private JButton loginButton, signUpButton, updateButton, exitButton;
    private static ClientManager clientmanager; 

    public PhysioMenu(int id)  {
        super("physioMenu");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        label = new JLabel("Choose an option:");
        label.setBounds(20, 20, 200, 30);
        label.setBackground(Color.BLUE); 
        label.setOpaque(true); 
        add(label);

        loginButton = new JButton("Create client");
        loginButton.setBounds(20, 60, 150, 30);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	Client c = ClientMenu.CreateClientGUI();
					clientmanager.createClient(c);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        add(loginButton);

        signUpButton = new JButton("Show all clients");
        signUpButton.setBounds(20, 100, 150, 30);
        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Menu.showClients();
            }
        });
        add(signUpButton);

        updateButton = new JButton("Delete Client");
        updateButton.setBounds(20, 140, 150, 30);
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					Menu.deleteClient();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        add(updateButton);
        
        updateButton = new JButton("Search client by ID");
        updateButton.setBounds(20, 180, 150, 30);
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					Menu.searchClientID();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        add(updateButton);
        
        updateButton = new JButton("Search engineer by ID");
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
        
        updateButton = new JButton("Print me to XML");
        updateButton.setBounds(20, 260, 150, 30);
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					Menu.loadClients(id);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        add(updateButton);
        
        updateButton = new JButton("Load clients from XML file");
        updateButton.setBounds(20, 300, 150, 30);
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					Menu.printMe(id);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        add(updateButton);

        exitButton = new JButton("Back to main menu");
        exitButton.setBounds(20, 340, 150, 30);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Exiting application.");
                // Perform any cleanup tasks here
                System.exit(0);
            }
        });
        add(exitButton);
    }
    
    public static Physio CreatePhysioGUI() {
      	 JFrame frame;
      	 JTextField idField, nameField, phoneField, dobField, salaryField, emailField, physioIdField, specialityField;
      	 Physio p; 
      	
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

          frame.add(new JLabel("Email:"));
          emailField = new JTextField();
          frame.add(emailField);

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

                      String email = emailField.getText();
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
    

}
