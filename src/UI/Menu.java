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
	   					JOptionPane.showMessageDialog(frame, "Please enter a valid integer for ID.");
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
	       frame.setLayout(new GridLayout(4, 2));

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
         frame.setLayout(new GridLayout(4, 1));

         frame.add(new JLabel("Email:"));
         emailField = new JTextField();
         frame.add(emailField);

         frame.add(new JLabel("Current password:"));
         CpasswdField = new JTextField();
         frame.add(CpasswdField);
         
         frame.add(new JLabel("New Password:"));
         newPField = new JTextField();
         frame.add(newPField);
         
         JButton submitButton = new JButton("change");
	       frame.add(submitButton);
	       
	       frame.add(new JLabel(""));
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
	                   }
	                   
	               } catch (Exception ex) {
	                   JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	               }
	               
	           }});
	       frame.setVisible(true);
	  }
    	

   
         
    	/*String email = null; 
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
				
	}*/
        
    
    public static void PhysioMenu(int id)  {
    	JLabel label;
        JButton CClientButton, clientsButton, DClientButton, clientButton, engButton, XMLButton, clientsXMLButton, exitButton;
        JFrame frame = new JFrame("Choose an option: ");
        frame.setSize(400, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        
        label = new JLabel("Choose an option:");
        label.setBounds(20, 20, 200, 30);
        label.setBackground(Color.BLUE); 
        label.setOpaque(true); 
        frame.add(label);

        CClientButton = new JButton("Create client");
        CClientButton.setBounds(20, 60, 150, 30);
        CClientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	Client c = CreateClientGUIforPHYSIO();
					clientmanager.createClient(c);
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
					loadClients();
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
					printMe(id);
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
    
    public static Client CreateClientGUIforPHYSIO() {
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
         final Client[] client = {};

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
    	List<Client> clients = null;
		
		clients = clientmanager.showAllClients();
		
		System.out.println(clients);
    }
    
    public static void deleteClient() throws NumberFormatException, Exception {
    	//int id = MainMenu.getIntGUI("ID");
    	System.out.println("Type the id of the client");
		Integer id = Integer.parseInt(reader.readLine());
		
		clientmanager.deleteClientByID(id);
    }
    
    public static void searchClientID() throws NumberFormatException, Exception {
    	Client c = null; 
    	System.out.println("Type the id of the client");
		Integer id = Integer.parseInt(reader.readLine());
    	//int id = MainMenu.getIntGUI("ID");
		
		c = clientmanager.searchClientByID(id);
		System.out.println(c);
    }
    
    public static void searchEngID() throws NumberFormatException, Exception {
    	Engineer e = null; 
    	System.out.println("Type the id of the engineer");
		Integer id = Integer.parseInt(reader.readLine());
    	//int id = new getIdGUI("ID");
    	
		e = engineermanager.searchEngineerByID(id);
		System.out.println(e);
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
    	Physio p = null; 
    	//System.out.println("Type the id of the physio");
		//Integer id = Integer.parseInt(reader.readLine());
    	int id = getIntGUI("physiotherapist ID");
    	System.out.println(id); 
    	p = physiomanager.searchPhysioByID(id);
		System.out.println(p); 
	
    }
    
    public static void deleteExamID() throws NumberFormatException, Exception {
    	System.out.println("Type the id of the exam");
		Integer id = Integer.parseInt(reader.readLine());
    	//int id = MainMenu.getIntGUI("ID");
		examsmanager.deleteExamByID(id);
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
					changeEngPhoneID();
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
					createMachine();
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
    
    public static void changeEngPhoneID() throws NumberFormatException, Exception {
    	System.out.println("Type the id of the engineer");
		Integer id = Integer.parseInt(reader.readLine());
    	//int id = MainMenu.getIntGUI("ID");
    	
		System.out.println("Type the new phone number of the engineer");
		String new_ph = reader.readLine();
    	//String new_ph = MainMenu.getStringGUI("new phone number");
    	
		engineermanager.changeEngineerTelephoneByID(new_ph, id);
    }
    
    public static Machine CreateMachineGUI() {
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
        
        frame.add(new JLabel(""));
        final Machine[] m1 = {};

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

                    m1[0] = m; 
                    
                    JOptionPane.showMessageDialog(frame, "Machine created successfully!");
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
        return m1[0]; 
    }
    public static void createMachine() throws NumberFormatException, Exception {
    	Machine m = null; 
    	
    	/*System.out.println("Type the id of the machine");
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
		
		m = new Machine(id, type, dob, db, e);*/
		
    	m = CreateMachineGUI();
    	machinemanager.createMachine(m);
    }
    
    public static void showMachines() {
    	List<Machine> machines = null;
		
		machines = machinemanager.showAllMachines();
		
		System.out.println(machines);
    }
    
    public static void changeProdAvailability() throws NumberFormatException, Exception {
    	//int id = MainMenu.getIntGUI("ID");
    	System.out.println("Type the id of the product");
		Integer id = Integer.parseInt(reader.readLine());
    	//int newN_available = MainMenu.getIntGUI("new number of available products");
		System.out.println("Type new number of available product");
		Integer newN_available = Integer.parseInt(reader.readLine());
		
		productsmanager.changeProductNAvailable(id, newN_available);
    }
    
    public static void prostheticsType() throws Exception {
    	//String type = MainMenu.getStringGUI("type of prosthetics to be shown");
    	System.out.println("Type in the type of the prosthetic");
		String type = reader.readLine();
		
		prostheticsmanager.showAllProstheticsOfType(type);
    }

    
    public static int getIntGUI (String what) {
    	
        JFrame frame = new JFrame("Insert " + what);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(3, 2));

        JTextField intField = new JTextField();
        frame.add(new JLabel(what +":"));
        frame.add(intField);

        JButton submitButton = new JButton("Submit");
        frame.add(submitButton);

        final int[] i = {0};

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    i[0] = Integer.parseInt(intField.getText());
          
                    frame.dispose();
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid integer for ID.");
                }
            }
        });

        frame.setVisible(true);
        
        return i[0];
    }
	
	public String getStringGUI(String what) {
        JFrame frame = new JFrame("Insert " + what);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(3, 2));

        JTextField stringField = new JTextField();
        frame.add(new JLabel("-> "));
        frame.add(stringField);

        JButton submitButton = new JButton("Submit");
        frame.add(submitButton);

        final String[] s = {};

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    s[0] = stringField.getText();
                    
                    frame.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid string.");
                }
            }
        });

       
        frame.setVisible(true);
        return s[0]; 
    }


} //final 
