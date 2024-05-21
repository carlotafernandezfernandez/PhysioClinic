package UI;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import PhysioClinicIFaces.*;
import PhysioClinicJDBC.*;
import PhysioClinicJPA.*;
import PhysioClinicPOJOs.*;
import PhysioclinicXML.XMLManagerImpl;
import PhysioClinicEncription.Encription;

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
        xmlmanager = new XMLManagerImpl();
        
        //int n = getInt("what");
    	MainMenu();
        
    }

	public static void MainMenu()  {
        JButton loginButton, signUpButton, updateButton, exitButton;
        JFrame frame = new JFrame("Choose an option: ");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        

        JLabel label = new JLabel("Choose an option:");
        label.setBounds(20, 20, 200, 30);
        frame.add(label);

        loginButton = new JButton("Login User");
        loginButton.setBounds(20, 60, 150, 30);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoginGUI();
            }
        });
        frame.add(loginButton);

        signUpButton = new JButton("Sign-up new user");
        signUpButton.setBounds(20, 100, 150, 30);
        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                signUpUserGUI();
            }
        });
        frame.add(signUpButton);

        updateButton = new JButton("Update password");
        updateButton.setBounds(20, 140, 150, 30);
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updatePasswordGUI();
            }
        });
        frame.add(updateButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(20, 180, 150, 30);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Exiting application.");
                // Perform any cleanup tasks here
                System.exit(0);
            }
        });
        frame.add(exitButton);
        frame.setVisible(true);
       
    }

    public static void LoginGUI() {
		
	   	 JTextField emailField, passwdField;
	   		   	
	       JFrame frame = new JFrame("");
	       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       frame.setSize(300, 300);
	       frame.setLayout(new GridLayout(4, 2));

	       frame.add(new JLabel("Email:"));
	       emailField = new JTextField();
	       frame.add(emailField);

	       frame.add(new JLabel("Password:"));
	       passwdField = new JTextField();
	       frame.add(passwdField);

	       JButton submitButton = new JButton("log in");
	       frame.add(submitButton);
	       JButton exitButton = new JButton("Go back to main menu");
	       frame.add(exitButton);
	       
	       frame.add(new JLabel(""));
	       submitButton.addActionListener(new ActionListener() {
	              @Override
	              public void actionPerformed(ActionEvent e) {
	                  frame.dispose();
	              }
	              
	          });

	       
	       frame.add(new JLabel(""));

	       submitButton.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {
	               try {

	                   String password = passwdField.getText();
	                   String email = emailField.getText();
	                   byte [] passwordE = PhysioClinicEncription.Encription.encrypt(password);

	                   User u = usermanager.checkPassword(email, passwordE);
	                   
	                   if (u != null && u.getRole().getName().equals("Physiotherapist")) {
	                       System.out.println("Login of physiotherapist successful!");
	                       PhysioMenu(u.getId());
	                       frame.dispose(); // Close login window
	                   } else if (u != null && u.getRole().getName().equals("Client")) {
	                       System.out.println("Login of client successful!");
	                       ClientMenu(u.getId());
	                       frame.dispose(); // Close login window
	                   } else if (u != null && u.getRole().getName().equals("Engineer")) {
	                       System.out.println("Login of engineer successful!");
	                       EngMenu(u.getId());
	                      frame.dispose(); // Close login window
	                   } else {
	                    JFrame frame = null;
	                    JOptionPane.showMessageDialog(frame, "Error in email or password");
	                   }
	                   
	                   frame.dispose();
	               } catch (Exception ex) {
	                   JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	               }
	           }
	           
	       });
	       frame.setVisible(true);
	}
    
    public static void signUpUserGUI() {
			JTextField emailField, passwdField;
			JComboBox <Role> roleCB = new JComboBox<>(new DefaultComboBoxModel<> (usermanager.getRoles().toArray(new Role[0])));
		   	
	       JFrame frame = new JFrame("");
	       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       frame.setSize(300, 300);
	       frame.setLayout(new GridLayout(5, 3));

	       frame.add(new JLabel("Email:"));
	       emailField = new JTextField();
	       frame.add(emailField);

	       frame.add(new JLabel("Password:"));
	       passwdField = new JTextField();
	       frame.add(passwdField);
	       
	       frame.add(new JLabel("Role: "));
	       frame.add(roleCB);

	       JButton submitButton = new JButton("Sing up");
	       frame.add(submitButton);
	       JButton exitButton = new JButton("Go back to main menu");
	       frame.add(exitButton);
	       
	       frame.add(new JLabel(""));
	       submitButton.addActionListener(new ActionListener() {
	              @Override
	              public void actionPerformed(ActionEvent e) {
	                  frame.dispose();
	              }
	              
	          });
	       
	       frame.add(new JLabel(""));
	       submitButton.addActionListener(new ActionListener() {
	              @Override
	              public void actionPerformed(ActionEvent e) {
	                  try {
	                      String email = emailField.getText();
	                      String passwd = passwdField.getText();
	                      byte[] encrypted_passwd = PhysioClinicEncription.Encription.encrypt(passwd);
	                      Role r = (Role) roleCB.getSelectedItem();
	                      
	                      User u = new User(email, encrypted_passwd, r);
	                      usermanager.newUser(u);
	                      
	                      if(u!=null & u.getRole().getName().equals("Physiotherapist")){
	          				CreatePhysioGUI(u.getEmail());
	          				
	          			} else if(u!=null & u.getRole().getName().equals("Client")){
	          				//System.out.println("Insert new client´s information -> ");
	          				CreateClientGUI(u.getEmail());
	          				
	          			} else if (u!=null & u.getRole().getName().equals("Engineer")){
	          				CreateEngineerGUI(u.getEmail());
	          				
	          			}
	                      
	                  } catch (Exception ex) {
	                      JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	                  }
	              }
	              
	          });

	       
	       
	       frame.setVisible(true);
	       
	}
    
    public static void CreateClientGUI(String email) {
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

          //frame.add(new JLabel("Email:"));
          //emailField = new JTextField();
          //frame.add(emailField);

          frame.add(new JLabel("Physiotherapist ID:"));
          physioIdField = new JTextField();
          frame.add(physioIdField);
          

          JButton submitButton = new JButton("Create Client");
          frame.add(submitButton);
          

          frame.add(new JLabel(""));

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
                      //String email = emailField.getText();
                      int physioID = Integer.parseInt(physioIdField.getText());
                      Physio p = physiomanager.searchPhysioByID(physioID);
                      
                      Client c = new Client(id, p, name, phone, dob, cardNumber, largeFamily, email);
                      clientmanager.createClient(c);
                      
                      JOptionPane.showMessageDialog(frame, "Client created successfully!");
                      frame.dispose();
                  } catch (Exception ex) {
                      JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                  }
              }
              
          });

          frame.setVisible(true);
      }
    
    
    public static void CreatePhysioGUI(String email) {
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

        JButton submitButton = new JButton("Create Physiotherapist");
        frame.add(submitButton);
        
        frame.add(new JLabel(""));

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

                    physiomanager.createPhysio(p);
                    
                    JOptionPane.showMessageDialog(frame, "Physiotherapist created successfully!");
                    frame.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });



        frame.setVisible(true);
    }

    
    public static void CreateEngineerGUI (String email) {
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

	                   engineermanager.createEngineer(eng);
	                   
	                   JOptionPane.showMessageDialog(frame, "Engineer created successfully!");
	                   frame.dispose();
	               } catch (Exception ex) {
	                   JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	               }
	           }
	           
	       });

	       frame.setVisible(true);
	       
	   }
      
    public static void updatePasswordGUI() {
    	JFrame frame;
     	 JTextField emailField, CpasswdField, newPField;
     	
         frame = new JFrame("Change password");
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setSize(400, 400);
         frame.setLayout(new GridLayout(5, 1));

         frame.add(new JLabel("Email:"));
         emailField = new JTextField();
         frame.add(emailField);

         frame.add(new JLabel("Current password:"));
         CpasswdField = new JTextField();
         frame.add(CpasswdField);
         
         frame.add(new JLabel("New Password:"));
         newPField = new JTextField();
         frame.add(newPField);
         
         JButton submitButton = new JButton("Change");
	       frame.add(submitButton);
	       JButton exitButton = new JButton("Go back to main menu");
	       frame.add(exitButton);
	       
	       
	       frame.add(new JLabel(""));
	       
	       exitButton.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {
	        	   frame.dispose();
	           }});

	       submitButton.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {
	               try {
	            	   String password = CpasswdField.getText();
	                   String email = emailField.getText();
	                   String newpass = newPField.getText();
	                   byte [] passwordE = PhysioClinicEncription.Encription.encrypt(password);
	                   byte [] newPasswordE = PhysioClinicEncription.Encription.encrypt(newpass);

	                   User u = usermanager.checkPassword(email, passwordE);
	                   
	                   if(u!=null) {
	                	   usermanager.changePassword(email, newPasswordE);
	                	   JOptionPane.showMessageDialog(frame, "password changed correclty");
	                	   frame.dispose();
	                   } else {
		                   JOptionPane.showMessageDialog(frame, "No user with that email and password in the database");
		               }
	                   
	               }catch (Exception ex) {
	                   JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	               }
	               
	           }});
	       
	       	           
	       frame.setVisible(true);
	  }
        
    
    public static void PhysioMenu(int id)  {
    	JLabel label;
        JButton CClientButton, clientsButton, DClientButton, clientButton, engButton, XMLButton, clientsXMLButton, exitButton;
        JFrame frame = new JFrame("Choose an option: ");
        frame.setSize(400, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        
        label = new JLabel("Choose an option:");
        label.setBounds(20, 20, 200, 30);
        label.setBackground(Color.CYAN); 
        label.setOpaque(true); 
        frame.add(label);

        CClientButton = new JButton("Create client");
        CClientButton.setBounds(20, 60, 150, 30);
        CClientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	CreateClientGUIforPHYSIO();
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        frame.add(CClientButton);

        clientsButton = new JButton("Show all clients");
        clientsButton.setBounds(20, 100, 150, 30);
        clientsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showClients();
            }
        });
        frame.add(clientsButton);

        DClientButton = new JButton("Delete Client");
        DClientButton.setBounds(20, 140, 150, 30);
        DClientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					deleteClient();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        frame.add(DClientButton);
        
        clientButton = new JButton("Search client by ID");
        clientButton.setBounds(20, 180, 150, 30);
        clientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					searchClientID();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        frame.add(clientButton);
        
        engButton = new JButton("Search engineer by ID");
        engButton.setBounds(20, 220, 150, 30);
        engButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					searchEngID();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        frame.add(engButton);
        
        XMLButton = new JButton("Print me to XML");
        XMLButton.setBounds(20, 260, 150, 30);
        XMLButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					printMe(id);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        frame.add(XMLButton);
        
        clientsXMLButton = new JButton("Load clients from XML file");
        clientsXMLButton.setBounds(20, 300, 150, 30);
        clientsXMLButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					loadClients();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        frame.add(clientsXMLButton);

        exitButton = new JButton("Back to main menu");
        exitButton.setBounds(20, 340, 150, 30);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        frame.add(exitButton);
        frame.setVisible(true);
    }
    
    public static void CreateClientGUIforPHYSIO() {
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
         
         frame.add(new JLabel("Email:"));
         emailField = new JTextField();
         frame.add(emailField);

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

         frame.add(new JLabel("Physiotherapist ID:"));
         physioIdField = new JTextField();
         frame.add(physioIdField);
         

         JButton submitButton = new JButton("Create Client");
         frame.add(submitButton);
         

         frame.add(new JLabel(""));

         submitButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 try {
                     int id = Integer.parseInt(idField.getText());
                     String name = nameField.getText();
                     String phone = phoneField.getText();

                     String dobStr = dobField.getText();
                     String email = emailField.getText();
                     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                     LocalDate dobLC = LocalDate.parse(dobStr, formatter);
                     Date dob = Date.valueOf(dobLC);

                     int cardNumber = Integer.parseInt(cardNumberField.getText());
                     boolean largeFamily = largeFamilyCheckbox.getState();
                     //String email = emailField.getText();
                     int physioID = Integer.parseInt(physioIdField.getText());
                     Physio p = physiomanager.searchPhysioByID(physioID);
                     
                     Client c = new Client(id, p, name, phone, dob, cardNumber, largeFamily, email);
                     clientmanager.createClient(c);
                     
                     JOptionPane.showMessageDialog(frame, "Client created successfully!");
                     frame.dispose();
                 } catch (Exception ex) {
                     JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                 }
             }
             
         });



         frame.setVisible(true);
     }
    
    public static void printMe(Integer id) {
		// TODO Auto-generated method stub
		xmlmanager.physio2xml(id);
	
	}
    
    public static void loadClients() {
    	Client c = null;
    	File file = new File("./xmls/External-Client.xml");
    	c = xmlmanager.xml2Client(file);
    	System.out.print(c);
    }

    public static void showClients() {
    	JFrame frame = new JFrame("Clients");
    	List<Client> clients = clientmanager.showAllClients();
    	if (clients != null) {
    		JOptionPane.showMessageDialog(frame, clients);
    	} else {
    		JOptionPane.showMessageDialog(frame, "No clients in the system");
    	}
		JOptionPane.showMessageDialog(frame, clients);
        
		//System.out.println(machines);
    	
    }
    
    public static void deleteClient() throws NumberFormatException, Exception {

    	JFrame frame = new JFrame("Insert client ID to delete");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(3, 3));

        JTextField intField = new JTextField();
        frame.add(new JLabel());
        frame.add(intField);

        JButton submitButton = new JButton("Submit");
        frame.add(submitButton);
        
        JButton exitButton = new JButton("Back to main menu");
        exitButton.setBounds(20, 140, 150, 30);
        frame.add(exitButton);
        
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                	int id = Integer.parseInt(intField.getText());
                	clientmanager.deleteClientByID(id);

                	JOptionPane.showMessageDialog(frame, "Client deleted");
                	
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid integer for ID.");
                }
            }
        });

        frame.setVisible(true);
    	
    }
    
    public static void searchClientID() throws NumberFormatException, Exception {
    	JFrame frame = new JFrame("Insert client ID");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(3, 3));

        JTextField intField = new JTextField();
        frame.add(new JLabel("->"));
        frame.add(intField);

        JButton submitButton = new JButton("Submit");
        frame.add(submitButton);
        
        JButton exitButton = new JButton("Back to main menu");
        exitButton.setBounds(20, 140, 150, 30);
        frame.add(exitButton);
        
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                	int id = Integer.parseInt(intField.getText());
                	Client c = clientmanager.searchClientByID(id);

                	if (c != null) {
                		JOptionPane.showMessageDialog(frame, c);
                	} else {
                		JOptionPane.showMessageDialog(frame, "No client with this id in the system");
                	}
                	
                	
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid integer for ID.");
                }
            }
        });

        frame.setVisible(true);

    }
    
    public static void searchEngID() throws NumberFormatException, Exception {
    	JFrame frame = new JFrame("Insert engineer ID");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(3, 3));

        JTextField intField = new JTextField();
        frame.add(new JLabel("->"));
        frame.add(intField);

        JButton submitButton = new JButton("Submit");
        frame.add(submitButton);
        
        JButton exitButton = new JButton("Back to main menu");
        exitButton.setBounds(20, 140, 150, 30);
        frame.add(exitButton);
        
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                	int id = Integer.parseInt(intField.getText());
                	Engineer eng = engineermanager.searchEngineerByID(id); 

                	if (eng != null) {
                		JOptionPane.showMessageDialog(frame, eng);
                	} else {
                		JOptionPane.showMessageDialog(frame, "No engineer with this id in the system");
                	}
                	
                	
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid integer for ID.");
                }
            }
        });

        frame.setVisible(true);
    	
    }
    
    public static void ClientMenu(int id)  {
    	JLabel label;
        JButton physioButton, examButton, exitButton;
        JFrame frame = new JFrame("Choose an option: ");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        label = new JLabel("Choose an option:");
        label.setBounds(20, 20, 160, 30);
        label.setBackground(Color.ORANGE); 
        label.setOpaque(true); 
        frame.add(label);

        physioButton = new JButton("Search physiotherapist by ID");
        physioButton.setBounds(20, 60, 150, 30);
        physioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					searchPhysioID();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        frame.add(physioButton);

        examButton = new JButton("Delete exam by its ID");
        examButton.setBounds(20, 100, 150, 30);
        examButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					deleteExamID();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        frame.add(examButton);

        exitButton = new JButton("Back to main menu");
        exitButton.setBounds(20, 140, 150, 30);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        frame.add(exitButton);
        frame.setVisible(true);
    }
    
    public static void searchPhysioID() throws NumberFormatException, Exception {
    	
    	JFrame frame = new JFrame("Insert physiotherapist ID");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(3, 3));

        JTextField intField = new JTextField();
        frame.add(new JLabel());
        frame.add(intField);

        JButton submitButton = new JButton("Submit");
        frame.add(submitButton);
        
        JButton exitButton = new JButton("Back to main menu");
        exitButton.setBounds(20, 140, 150, 30);
        frame.add(exitButton);
        
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                	int id = Integer.parseInt(intField.getText());
                	Physio p = physiomanager.searchPhysioByID(id);
                	
                	if (p != null) {
                		JOptionPane.showMessageDialog(frame, p);
                	} else {
                		JOptionPane.showMessageDialog(frame, "No physio with this id in the system");
                	}

                	
                	
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid integer for ID.");
                }
            }
        });

        frame.setVisible(true);
    }
    
    public static void deleteExamID() throws NumberFormatException, Exception {

    	JFrame frame = new JFrame("Insert exam ID to delete");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(3, 3));

        JTextField intField = new JTextField();
        frame.add(new JLabel("->"));
        frame.add(intField);

        JButton submitButton = new JButton("Submit");
        frame.add(submitButton);
        
        JButton exitButton = new JButton("Back to main menu");
        exitButton.setBounds(20, 140, 150, 30);
        frame.add(exitButton);
        
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                	int id = Integer.parseInt(intField.getText());
            		examsmanager.deleteExamByID(id);
            	
                	JOptionPane.showMessageDialog(frame, "Machine deleted correctly");
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "No machines with this ID.");
                }
            }
        });

        frame.setVisible(true);
    }
    	
    
    public static void EngMenu(int id)  {
    	JLabel label;
        JButton phoneButton, machineButton, machinesButton, PAButton, PTButton, exitButton;
        JFrame frame = new JFrame("Choose an option: ");
        frame.setSize(400, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        
        label = new JLabel("Choose an option:");
        label.setBounds(20, 20, 280, 30);
        label.setBackground(Color.GREEN); 
        label.setOpaque(true); 
        frame.add(label);

        phoneButton = new JButton("Change phone by ID");
        phoneButton.setBounds(20, 60, 150, 30);
        phoneButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					changeEngPhoneID(id);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        frame.add(phoneButton);

        machineButton = new JButton("Create a machine");
        machineButton.setBounds(20, 100, 150, 30);
        machineButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					CreateMachineGUI();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        frame.add(machineButton);

        machinesButton = new JButton("Show all machines");
        machinesButton.setBounds(20, 140, 150, 30);
        machinesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					showMachines();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        frame.add(machinesButton);
        
        PAButton = new JButton("Change product availability");
        PAButton.setBounds(20, 180, 150, 30);
        PAButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					changeProdAvailability();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
       frame. add(PAButton);
        
        PTButton = new JButton("Show all prosthetics of same type");
        PTButton.setBounds(20, 220, 150, 30);
        PTButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					searchEngID();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        frame.add(PTButton);

        exitButton = new JButton("Back to main menu");
        exitButton.setBounds(20, 260, 150, 30);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        frame.add(exitButton);
        
        frame.setVisible(true);
    }
    
    public static void changeEngPhoneID(int id) throws NumberFormatException, Exception {
    	JFrame frame = new JFrame("Insert new phone for engineer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(3, 3));

        JTextField intField = new JTextField();
        frame.add(new JLabel("->"));
        frame.add(intField);

        JButton submitButton = new JButton("Submit");
        frame.add(submitButton);
        
        JButton exitButton = new JButton("Back to main menu");
        exitButton.setBounds(20, 140, 150, 30);
        frame.add(exitButton);
        
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                	String phone = intField.getText();
                	engineermanager.changeEngineerTelephoneByID(phone, id);
                	
                	JOptionPane.showMessageDialog(frame, "Changed to" + engineermanager.searchEngineerByID(id));
                	
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid integer for ID.");
                }
            }
        });

        frame.setVisible(true);
    }
    
    public static void CreateMachineGUI() {
    	 JFrame frame;
    	 JTextField idField, typeField, phoneField, dobField, engIdField, doBoughtField;
    	 Machine m; 
    	
        frame = new JFrame("Create Machine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(10, 2));

        frame.add(new JLabel("Machine ID:"));
        idField = new JTextField();
        frame.add(idField);
        
        frame.add(new JLabel("Type:"));
        typeField = new JTextField();
        frame.add(typeField);

        frame.add(new JLabel("Date of Birth (yyyy/MM/dd):"));
        dobField = new JTextField();
        frame.add(dobField);
        
        frame.add(new JLabel("Date in which was bought (yyyy/MM/dd):"));
        doBoughtField = new JTextField();
        frame.add(doBoughtField);

        frame.add(new JLabel("Assigned engineer ID:"));
        engIdField = new JTextField();
        frame.add(engIdField);

        JButton submitButton = new JButton("Create machine");
        frame.add(submitButton);
        
        JButton exitButton = new JButton("Exit");
        frame.add(exitButton);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
            
        });
        
        frame.add(new JLabel(""));

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    String type = typeField.getText();
                    
                    String dobStr = dobField.getText();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    LocalDate dobLC = LocalDate.parse(dobStr, formatter);
                    Date dob = Date.valueOf(dobLC);
                    
                    String dbStr = doBoughtField.getText();
                    LocalDate dbLC = LocalDate.parse(dbStr, formatter);
                    Date db = Date.valueOf(dbLC);
                    
                    int eng_id = Integer.parseInt(engIdField.getText());
                    Engineer eng = engineermanager.searchEngineerByID(eng_id);
                    
                    Machine m = new Machine(id, type, dob, db, eng);

                	machinemanager.createMachine(m);
                    
                    JOptionPane.showMessageDialog(frame, "Machine created successfully!");
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });



        frame.setVisible(true);

    }
    
    public static void showMachines() {
    	JFrame frame = new JFrame("Machines");
    	List<Machine> machines = null;
		
		machines = machinemanager.showAllMachines();
		
		if(machines != null) {
			 JOptionPane.showMessageDialog(frame, machines);
		}else {
			 JOptionPane.showMessageDialog(frame, "No machines in the database");
		}
		

       
		//System.out.println(machines);
    }
    
    public static void changeProdAvailability() throws NumberFormatException, Exception {

	   	 JTextField idField, nField;
	   		   	
	       JFrame frame = new JFrame("");
	       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       frame.setSize(300, 300);
	       frame.setLayout(new GridLayout(4, 2));

	       frame.add(new JLabel("Product ID:"));
	       idField = new JTextField();
	       frame.add(idField);

	       frame.add(new JLabel("New number of available products"));
	       nField = new JTextField();
	       frame.add(nField);

	       JButton submitButton = new JButton("Change");
	       frame.add(submitButton);
	       JButton exitButton = new JButton("Go back to main menu");
	       frame.add(exitButton);
	       
	       frame.add(new JLabel(""));
	       exitButton.addActionListener(new ActionListener() {
	              @Override
	              public void actionPerformed(ActionEvent e) {
	                  frame.dispose();
	              }
	              
	          });


	       submitButton.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {
	               try {

	                   int id = Integer.getInteger(idField.getText());
	                   int n = Integer.getInteger(nField.getText());
	                   productsmanager.changeProductNAvailable(id, n);
	                   JOptionPane.showMessageDialog(frame, "Number of available proucts changed corectly");
	                   
	               } catch (Exception ex) {
	                   JOptionPane.showMessageDialog(frame, "No product with id: " + Integer.getInteger(idField.getText()));
	               }
	           }
	           
	       });
	       frame.setVisible(true);
    }
    
    public static void prostheticsType() throws Exception {
    	JTextField idField, typeField;
		   	
	       JFrame frame = new JFrame("Show all prosthetics of");
	       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       frame.setSize(300, 300);
	       frame.setLayout(new GridLayout(3, 2));

	       frame.add(new JLabel("Type ->"));
	       typeField = new JTextField();
	       frame.add(typeField);

	       JButton submitButton = new JButton("Show");
	       frame.add(submitButton);
	       JButton exitButton = new JButton("Go back to main menu");
	       frame.add(exitButton);
	       
	       frame.add(new JLabel(""));
	       exitButton.addActionListener(new ActionListener() {
	              @Override
	              public void actionPerformed(ActionEvent e) {
	                  frame.dispose();
	              }
	              
	          });

	       
	       frame.add(new JLabel(""));

	       submitButton.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {
	               try {
	            	   String type = typeField.getText();
	            	   List<Prosthetics> p = prostheticsmanager.showAllProstheticsOfType(type);
	            	   if(p != null) {
	            		   JOptionPane.showMessageDialog(frame, p);
	            	   }else {
	            		   JOptionPane.showMessageDialog(frame, "No prosthetics in the database");
	            	   }
	               } catch (Exception ex) {
	                   JOptionPane.showMessageDialog(frame, "No prosthetics in the database");
	               }
	           }
	           
	       });
	       frame.setVisible(true);
		
    }


} //final 
