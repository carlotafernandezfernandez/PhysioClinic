package PhysioClinicGUI;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import PhysioClinicIFaces.PhysioManager;
import PhysioClinicJDBC.JDBCManager;
import PhysioClinicJDBC.JDBCPhysioManager;
import PhysioClinicPOJOs.Client;
import PhysioClinicPOJOs.Physio;
import UI.*;

public class ClientMenu extends JFrame{
	/**
	 * 
	 */
	private static JDBCManager jdbcmanager;
	private static PhysioManager physiomanager = new JDBCPhysioManager(jdbcmanager);
	private static final long serialVersionUID = 1L;
	private JLabel label;
    private JButton loginButton, signUpButton, updateButton, exitButton;

    public ClientMenu(int id)  {
        super("clientMenu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        label = new JLabel("Choose an option:");
        label.setBounds(20, 20, 160, 30);
        label.setBackground(Color.ORANGE); 
        label.setOpaque(true); 
        add(label);

        loginButton = new JButton("Search physiotherapist by ID");
        loginButton.setBounds(20, 60, 150, 30);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					Menu.searchPhysioID();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        add(loginButton);

        signUpButton = new JButton("Delete exam by its ID");
        signUpButton.setBounds(20, 100, 150, 30);
        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					Menu.deleteExamID();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        add(signUpButton);

        exitButton = new JButton("Back to main menu");
        exitButton.setBounds(20, 140, 150, 30);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Exiting application.");
                // Perform any cleanup tasks here
                System.exit(0);
            }
        });
        add(exitButton);
    }
    
    public static Client CreateClientGUI() {
    	 JFrame frame;
    	 JTextField idField, nameField, phoneField, dobField, cardNumberField, emailField, physioIdField;
    	 Checkbox largeFamilyCheckbox;
    	 Client c = null; 
    	
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
        String dobStr = dobField.getText();

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
                    System.out.println(p);
                    
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

    public static int getID() {
        JFrame frame = new JFrame("Insert ID");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(3, 2));

        JTextField idField = new JTextField();
        frame.add(new JLabel("ID:"));
        frame.add(idField);

        JButton submitButton = new JButton("Submit");
        frame.add(submitButton);

        final int[] id = {0};

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    id[0] = Integer.parseInt(idField.getText());
                    
                    frame.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid integer for ID.");
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
        
        return id[0]; 
    }
    
    public static void main (String [] args) {
    	int id = getID();
        System.out.println("ID entered by the user: " + id);

    }
    
 }
  

