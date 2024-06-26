package UI;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

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
		
	   	 JTextField emailField;
	   	 JPasswordField passwdField; 
	   		   	
	       JFrame frame = new JFrame("");
	       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       frame.setSize(300, 300);
	       frame.setLayout(new GridLayout(4, 5));

	       frame.add(new JLabel("Email:"));
	       emailField = new JTextField();
	       frame.add(emailField);
	       JLabel emailLabel = new JLabel();
	       frame.add(emailLabel);

	       
	       frame.add(new JLabel("Password:"));
	       passwdField = new JPasswordField();
	       passwdField.setEchoChar('*');
	       frame.add(passwdField);
	       JLabel passwordLabel = new JLabel();
	       frame.add(passwordLabel);
	       
	       
	       JButton exitButton = new JButton("Main menu");
	       frame.add(exitButton);
	       
	       exitButton.addActionListener(new ActionListener() {
	              @Override
	              public void actionPerformed(ActionEvent e) {
	                  frame.dispose();
	              }
	              
	          });
	       
	       JButton showButton = new JButton("👁");
	       showButton.setFont(new Font("", Font.PLAIN, 23));
	       frame.add(showButton);
	       
	       JButton submitButton = new JButton("Log in");
	       frame.add(submitButton);
	  
	       
	       
	       showButton.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mousePressed(MouseEvent e) {
	                
	                char[] password = passwdField.getPassword();
	                
	                String passwordString = new String(password);
	                
	                passwordLabel.setText("⮀ " + passwordString);
	            }
	            
	            @Override
	            public void mouseReleased(MouseEvent e) {
	                passwordLabel.setText("");
	            }
	        });

	       submitButton.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {
	               try {

	            	   char[] passwd = passwdField.getPassword();
	            	   String passwordString = new String(passwd);
	                   String email = emailField.getText();
	                   byte [] passwordE = PhysioClinicEncription.Encription.encrypt(passwordString);

	                   User u = usermanager.checkPassword(email, passwordE);
	                   
	                   if (u != null && u.getRole().getName().equals("Physiotherapist")) {
	                       System.out.println("Login of physiotherapist successful!");
	                       PhysioMenu(u);
	                       frame.dispose(); // Close login window
	                   } else if (u != null && u.getRole().getName().equals("Client")) {
	                       System.out.println("Login of client successful!");
	                       ClientMenu(u);
	                       frame.dispose(); // Close login window
	                   } else if (u != null && u.getRole().getName().equals("Engineer")) {
	                       System.out.println("Login of engineer successful!");
	                       EngMenu(u);
	                      frame.dispose(); // Close login window
	                   } else {
	                    JOptionPane.showMessageDialog(frame, "Error in email or password");
	                   
	                   }
	                   
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
	                      String email = emailField.getText();
	                      String passwd = passwdField.getText();
	                      byte[] encrypted_passwd = PhysioClinicEncription.Encription.encrypt(passwd);
	                      Role r = (Role) roleCB.getSelectedItem();
	                      
	                      User u = new User(email, encrypted_passwd, r);
	                      usermanager.newUser(u);
	                      
	                      if(u!=null & u.getRole().getName().equals("Physiotherapist")){
	                    	  
	          				CreatePhysioGUI(u);
	          				
	          			} else if(u!=null & u.getRole().getName().equals("Client")){
	          				//System.out.println("Insert new client´s information -> ");
	          				showPhysiosGUI();
	          				CreateClientGUI(u);
	          				
	          			} else if (u!=null & u.getRole().getName().equals("Engineer")){
	          				CreateEngineerGUI(u);
	          				
	          			}
	                      
	                  } catch (Exception ex) {
	                      JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	                  }
	              }
	              
	          });

	       
	       
	       frame.setVisible(true);
	       
	}
    
    public static void CreateClientGUI(User u) {
      	 JFrame frame;
      	 JTextField nameField, phoneField, dobField, cardNumberField, physioIdField;
      	 Checkbox largeFamilyCheckbox;
      	
          frame = new JFrame("Create Client");
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.setSize(400, 400);
          frame.setLayout(new GridLayout(10, 2));

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

          frame.add(new JLabel("Physiotherapist ID (from the list provided):"));
          physioIdField = new JTextField();
          frame.add(physioIdField);
          

          JButton submitButton = new JButton("Create Client");
          frame.add(submitButton);
          

          frame.add(new JLabel(""));

          submitButton.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  try {
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
                      
                      Client c = new Client(u.getId(), p, name, phone, dob, cardNumber, largeFamily, u.getEmail());
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
    
    
    public static void CreatePhysioGUI(User u) {
    	 JFrame frame;
    	 JTextField nameField, phoneField, dobField, salaryField, specialityField;
    	     	
        frame = new JFrame("Create Physiotherapist");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(10, 2));
        
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
                    String name = nameField.getText();
                    String phone = phoneField.getText();
                    
                    String dobStr = dobField.getText();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    LocalDate dobLC = LocalDate.parse(dobStr, formatter);
                    Date dob = Date.valueOf(dobLC);
                    
                    float salary = Float.parseFloat(salaryField.getText());
                    String speciality = specialityField.getText();
                    
                    Physio p = new Physio(u.getId(), name, phone, dob, speciality, u.getEmail(), salary); 

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

    
    public static void CreateEngineerGUI (User u) {
	   	 JFrame frame;
	   	 JTextField nameField, phoneField, dobField, salaryField, specialityField;
	   		   	
	       frame = new JFrame("Create Engineer");
	       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       frame.setSize(400, 400);
	       frame.setLayout(new GridLayout(10, 2));

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
	                   String name = nameField.getText();
	                   String phone = phoneField.getText();
	                   
	                   String dobStr = dobField.getText();
	                   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	                   LocalDate dobLC = LocalDate.parse(dobStr, formatter);
	                   Date dob = Date.valueOf(dobLC);

	                   float salary = Float.parseFloat(salaryField.getText());
	                   String speciality = specialityField.getText();
	                   
	                   Engineer eng = new Engineer(u.getId(), name, phone, dob, speciality, u.getEmail(), salary); 

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
     	JTextField emailField, CpasswdField; 
     	JPasswordField newPField;
     	
         frame = new JFrame("Change password");
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setSize(400, 400);
         frame.setLayout(new GridLayout(6, 2));

         frame.add(new JLabel("Email:"));
         emailField = new JTextField();
         frame.add(emailField);

         frame.add(new JLabel("Current password:"));
         CpasswdField = new JTextField();
         frame.add(CpasswdField);
         
         frame.add(new JLabel("New Password:"));
         
         newPField = new JPasswordField();
         newPField.setEchoChar('*');
         frame.add(newPField);
	     JButton showButton = new JButton("👁");
	     showButton.setFont(new Font("", Font.PLAIN, 23));
	     frame.add(showButton);
	     JLabel passwordLabel = new JLabel();
	     passwordLabel.setFont(new Font("", Font.PLAIN, 13));
	     frame.add(passwordLabel);
	     
	     
	       showButton.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mousePressed(MouseEvent e) {
	                
	                char[] password = newPField.getPassword();
	                
	                String passwordString = new String(password);
	                
	                passwordLabel.setText("   ↳"+passwordString + "");
	            }
	            
	            @Override
	            public void mouseReleased(MouseEvent e) {
	                passwordLabel.setText("");
	            }
	        });
         
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
	                   char[] newpass = newPField.getPassword();
	            	   String passwordString = new String(newpass);
	                   byte [] passwordE = PhysioClinicEncription.Encription.encrypt(password);
	                   byte [] newPasswordE = PhysioClinicEncription.Encription.encrypt(passwordString);

	                   User u = usermanager.checkPassword(email, passwordE);
	                   
	                   if(u!=null) {
	                	   usermanager.changePassword(email, newPasswordE);
	                	   JOptionPane.showMessageDialog(frame, "Password changed correclty");
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
        
    
    public static void PhysioMenu(User u)  {
    	JLabel label;
        JButton CClientButton, clientsButton, DClientButton, clientButton, engButton, XMLButton, clientsXMLButton, exitButton;
        JFrame frame = new JFrame("Choose an option: ");
        frame.setSize(400, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        
        label = new JLabel("Choose an option:");
        label.setBounds(35, 20, 300, 30);
        label.setBackground(Color.CYAN); 
        label.setOpaque(true); 
        frame.add(label);

        clientsButton = new JButton("Show all clients");
        clientsButton.setBounds(35, 60, 250, 30);
        clientsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showClients();
            }
        });
        frame.add(clientsButton);

        DClientButton = new JButton("Delete Client");
        DClientButton.setBounds(35, 100, 250, 30);
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
        clientButton.setBounds(35, 140, 250, 30);
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
        engButton.setBounds(35, 180, 250, 30);
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
        XMLButton.setBounds(35, 220, 250, 30);
        XMLButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					printMe(u);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        frame.add(XMLButton);
        
        clientsXMLButton = new JButton("Load clients from XML file");
        clientsXMLButton.setBounds(35, 260, 250, 30);
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
        exitButton.setBounds(35, 300, 250, 30);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        frame.add(exitButton);
        frame.setVisible(true);
    }
    
    
    public static void printMe(User u) {
		// TODO Auto-generated method stub
    	JFrame frame = new JFrame();
		Physio p  = xmlmanager.physio2xml(u.getId());
		JOptionPane.showMessageDialog(frame, p);
	}
    
    public static void loadClients() {
    	Client c = new Client();
    	JFrame frame = new JFrame();
    	File file = new File("./xmls/External-Client.xml");
    	try {
    		c = xmlmanager.xml2Client(file);
        	System.out.print(c);
    	}catch(Exception ex) {
    		
    	}
    	
    }
    
    public static void showPhysiosGUI() {
    	JFrame frame = new JFrame("Physios");
    	frame.setSize(new Dimension(500, 500));
    	JPanel contentPanel = new JPanel();
    	List<Physio> physios = physiomanager.showAllPhysios();
    	String[] columnNames = {"ID", "Name", "Email", "Phone", "DoB", "Speciality"};
    	
    	DefaultTableModel model = new DefaultTableModel(columnNames, 0); 
    	
    	if (physios != null) {
    		for (Physio p : physios) {
		        Object[] rowData = {
		           p.getId(),
		           p.getName(),
		           p.getEmail(),
		           p.getPhone(),
		           p.getDoB(),
		           p.getSpeciality(),
		           
		            //c.getPhysio().getName(),
		           
		        };
		        model.addRow(rowData);
		    }
    		JTable table = new JTable(model);
    	    JScrollPane scrollPane = new JScrollPane(table);
    	    
    	    table.setPreferredSize(new Dimension(500, 500));
    	    table.getColumnModel().getColumn(0).setPreferredWidth(5);
    	    scrollPane.setPreferredSize(new Dimension(750,200));
    	    contentPanel.add(scrollPane, BorderLayout.CENTER);
    	    contentPanel.revalidate();
    	    contentPanel.repaint();
    	    contentPanel.setPreferredSize(new Dimension(775, 500));

    	    JButton exitButton = new JButton("Exit");
    	    contentPanel.add(exitButton);
            exitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                
            }});
    	    frame.add(contentPanel);
    	    frame.pack();
    	    frame.add(contentPanel);
    	    frame.setVisible(true);
    	

    	} else {
    		JOptionPane.showMessageDialog(frame, "No physios in the system");
    		
    		
    	}
    	
    }

    public static void showClients() {
    	JFrame frame = new JFrame("Clients");
    	frame.setSize(new Dimension(500, 500));
    	JPanel contentPanel = new JPanel();
    	List<Client> clients = clientmanager.showAllClients();
    	String[] columnNames = {"ID", "Name", "Email", "Phone", "DoB", "Card number", "Large Family"};
    	
    	DefaultTableModel model = new DefaultTableModel(columnNames, 0); 
    	
    	if (clients != null) {
    		for (Client c : clients) {
		        Object[] rowData = {
		            c.getId(),
		            c.getName(),
		            c.getEmail(),
		            c.getPhone(), 
		            c.getDoB(), 
		            c.getCard_n(), 
		            c.isLarge_family(), 
		            //c.getPhysio().getName(),
		           
		        };
		        model.addRow(rowData);
		    }
    		JTable table = new JTable(model);
    	    JScrollPane scrollPane = new JScrollPane(table);
    	    
    	    table.setPreferredSize(new Dimension(500, 500));
    	    table.getColumnModel().getColumn(0).setPreferredWidth(5);
    	    scrollPane.setPreferredSize(new Dimension(750,200));
    	    contentPanel.add(scrollPane, BorderLayout.CENTER);
    	    contentPanel.revalidate();
    	    contentPanel.repaint();
    	    contentPanel.setPreferredSize(new Dimension(775, 500));

    	    JButton exitButton = new JButton("Exit");
    	    contentPanel.add(exitButton);
            exitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                
            }});
    	    frame.add(contentPanel);
    	    frame.pack();
    	    frame.add(contentPanel);
    	    frame.setVisible(true);
    	

    	} else {
    		JOptionPane.showMessageDialog(frame, "No clients in the system");
    		
    		
    	}
    	
    }

    
    public static void deleteClient() throws NumberFormatException, Exception {

    	JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(3, 3));

        JTextField intField = new JTextField();
        frame.add(new JLabel("ID of client to delete:"));
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
    	JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(3, 3));

        JTextField intField = new JTextField();
        frame.add(new JLabel("Insert client ID: "));
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
    	JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(3, 3));

        JTextField intField = new JTextField();
        frame.add(new JLabel("Insert engineer ID"));
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
    
    public static void ClientMenu(User u)  {
    	JLabel label;
        JButton physioButton, examButton, exitButton;
        JFrame frame = new JFrame("Choose an option: ");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        label = new JLabel("Choose an option:");
        label.setBounds(20, 20, 300, 30);
        label.setBackground(Color.ORANGE); 
        label.setOpaque(true); 
        frame.add(label);

        physioButton = new JButton("Search physiotherapist by ID");
        physioButton.setBounds(35, 60, 250, 30);
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
        examButton.setBounds(35, 100, 250, 30);
        examButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					examGUI(examsmanager.showAllExamsINFO(u.getId()));
					deleteExamID();
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        frame.add(examButton);

        exitButton = new JButton("Back to main menu");
        exitButton.setBounds(35, 140, 250, 30);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        frame.add(exitButton);
        frame.setVisible(true);
    }
    
    public static void searchPhysioID() throws NumberFormatException, Exception {
    	
    	JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(3, 3));

        JTextField intField = new JTextField();
        frame.add(new JLabel("Insert physiotherapist ID: "));
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

    	JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(3, 3));

        JTextField intField = new JTextField();
        frame.add(new JLabel("Insert exam ID to delete"));
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
            	
                	JOptionPane.showMessageDialog(frame, "Exam deleted correctly");
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "No exam with this ID.");
                }
            }
        });

        frame.setVisible(true);
    }
    	
    
    public static void EngMenu(User u)  {
    	JLabel label;
        JButton phoneButton, machineButton, machinesButton, PAButton, PTButton, exitButton, cpButton;
        JFrame frame = new JFrame("Choose an option: ");
        frame.setSize(400, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        
        label = new JLabel("Choose an option:");
        label.setBounds(20, 20, 300, 30);
        label.setBackground(Color.GREEN); 
        label.setOpaque(true); 
        frame.add(label);

        phoneButton = new JButton("Change phone");
        phoneButton.setBounds(35, 60, 250, 30);
        phoneButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					changeEngPhoneID(u.getId());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        frame.add(phoneButton);

        machineButton = new JButton("Create my machine");
        machineButton.setBounds(35, 100, 250, 30);
        machineButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					CreateMachineGUI(u.getId());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        frame.add(machineButton);

        machinesButton = new JButton("Show all machines");
        machinesButton.setBounds(35, 140, 250, 30);
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
        PAButton.setBounds(35, 180, 250, 30);
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
        PTButton.setBounds(35, 260, 250, 30);
        PTButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	prostheticsType();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        frame.add(PTButton);
        
        cpButton = new JButton("Create my prosthetic");
        cpButton.setBounds(35, 220, 250, 30);
        cpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	showClients();
                	createProsthetic(u.getId());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        frame.add(cpButton);

        exitButton = new JButton("Back to main menu");
        exitButton.setBounds(35, 300, 250, 30);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        frame.add(exitButton);
        
        frame.setVisible(true);
    }
    
    public static void changeEngPhoneID(int id) throws NumberFormatException, Exception {
    	JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(3, 3));

        JTextField intField = new JTextField();
        frame.add(new JLabel("Insert new phone: "));
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
    
    public static void CreateMachineGUI(int eng_id) {
    	 JFrame frame;
    	 JTextField idField, typeField, dobField, doBoughtField, inspectionsField;
    	
        frame = new JFrame("Create Machine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(10, 2));

        /*frame.add(new JLabel("Machine ID:"));
        idField = new JTextField();
        frame.add(idField);*/
        
        frame.add(new JLabel("Type:"));
        typeField = new JTextField();
        frame.add(typeField);

        frame.add(new JLabel("Release date (yyyy/MM/dd):"));
        dobField = new JTextField();
        frame.add(dobField);
        
        frame.add(new JLabel("Date in which was bought (yyyy/MM/dd):"));
        doBoughtField = new JTextField();
        frame.add(doBoughtField);
        
        frame.add(new JLabel("Frequency of inspections: "));
        inspectionsField = new JTextField();
        frame.add(inspectionsField);
        
        frame.add(new JLabel("Assigned engineer (your id):" + eng_id));
        
        frame.add(new JLabel());
      
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
                    //int machine_id = Integer.parseInt(idField.getText());
                    String type = typeField.getText();
                    
                    String dobStr = dobField.getText();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    LocalDate dobLC = LocalDate.parse(dobStr, formatter);
                    Date dob = Date.valueOf(dobLC);
                    
                    String dbStr = doBoughtField.getText();
                    LocalDate dbLC = LocalDate.parse(dbStr, formatter);
                    Date db = Date.valueOf(dbLC);
                    String inspections = inspectionsField.getText();
                    
                    Engineer eng = engineermanager.searchEngineerByID(eng_id);
                    
                    Machine m = new Machine(type, dob, db, eng, inspections);
                	machinemanager.createMachine(m);
                    
                    JOptionPane.showMessageDialog(frame, "Machine created successfully!");
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });



        frame.setVisible(true);

    }
    
    public static void createProsthetic(int eng_id) {
   	 JFrame frame;
   	 JTextField idField, typeField, dobField, doBoughtField, inspectionsField, clientField;
   	
       frame = new JFrame("Create Prosthetic");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(500, 300);
       frame.setLayout(new GridLayout(10, 2));
       
       frame.add(new JLabel("Type:"));
       typeField = new JTextField();
       frame.add(typeField);

       frame.add(new JLabel("Release date (yyyy/MM/dd):"));
       dobField = new JTextField();
       frame.add(dobField);
       
       frame.add(new JLabel("Date in which was bought (yyyy/MM/dd):"));
       doBoughtField = new JTextField();
       frame.add(doBoughtField);
       
       frame.add(new JLabel("Frequency of inspections: "));
       inspectionsField = new JTextField();
       frame.add(inspectionsField);
       
       frame.add(new JLabel("ID of client (from the list provided): "));
       clientField = new JTextField();
       frame.add(clientField);
       
       frame.add(new JLabel("Assigned engineer (your id):" + eng_id));
       
       frame.add(new JLabel());
     
       JButton submitButton = new JButton("Create prosthetics");
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
            	   //int ID = Integer.parseInt(idField.getText());
            	   
                   String type = typeField.getText();
                   
                   String dobStr = dobField.getText();
                   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                   LocalDate dobLC = LocalDate.parse(dobStr, formatter);
                   Date dob = Date.valueOf(dobLC);
                   
                   String dbStr = doBoughtField.getText();
                   LocalDate dbLC = LocalDate.parse(dbStr, formatter);
                   Date db = Date.valueOf(dbLC);
                   String inspections = inspectionsField.getText();
                   int clientID = Integer.parseInt(clientField.getText());
                   
                   Client c = clientmanager.searchClientByID(clientID);
                   
                   Engineer eng = engineermanager.searchEngineerByID(eng_id);
                   
                   Prosthetics p = new Prosthetics(type, c, eng, inspections, dob, db);
                   prostheticsmanager.createProsthetic(p);
                   
                   JOptionPane.showMessageDialog(frame, "Prosthetics created successfully!");
                   frame.dispose();
                   
               } catch (Exception ex) {
                   JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
               }
           }
           
       });



       frame.setVisible(true);

   }

    
    public static void showMachines() {
    	JFrame frame = new JFrame("Machines");
    	frame.setSize(new Dimension(500, 500));
    	JPanel contentPanel = new JPanel();
    	List<Machine> machines = machinemanager.showAllMachines();
    	String[] columnNames = {"ID", "Type", "Date made", "Date first used", "Inspections", "Assigned engineer ID"};
    	
    	DefaultTableModel model = new DefaultTableModel(columnNames, 0); 
    	
    	if (machines != null) {
    		for (Machine m : machines) {
		        Object[] rowData = {
		            m.getId(),
		            m.getType(),
		            m.getDoB(),
		            m.getdBought(),
		            m.getInspections(),
		            m.getEngineer().getId(),
		            //c.getPhysio().getName(),
		           
		        };
		        model.addRow(rowData);
		    }
    		JTable table = new JTable(model);
    	    JScrollPane scrollPane = new JScrollPane(table);
    	    
    	    table.setPreferredSize(new Dimension(500, 500));
    	    table.getColumnModel().getColumn(0).setPreferredWidth(5);
    	    scrollPane.setPreferredSize(new Dimension(750,200));
    	    contentPanel.add(scrollPane, BorderLayout.CENTER);
    	    contentPanel.revalidate();
    	    contentPanel.repaint();
    	    contentPanel.setPreferredSize(new Dimension(775, 500));

    	    JButton exitButton = new JButton("Exit");
    	    contentPanel.add(exitButton);
            exitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                
            }});
    	    frame.add(contentPanel);
    	    frame.pack();
    	    frame.add(contentPanel);
    	    frame.setVisible(true);
    	

    	} else {
    		JOptionPane.showMessageDialog(frame, "No machines in the system");
    		
    		
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
	            	   
	                   int id = Integer.parseInt(idField.getText());
	                   int n = Integer.parseInt(nField.getText());
	                   productsmanager.changeProductNAvailable(id, n);
	                   JOptionPane.showMessageDialog(frame, "Number of available proucts changed corectly");
	                   
	               } catch (Exception ex) {
	                   //JOptionPane.showMessageDialog(frame, "No product with id: " + Integer.getInteger(idField.getText()));
	            	   System.out.println(ex);
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

	       
	       JButton exitButton = new JButton("Go back to main menu");
	       frame.add(exitButton);
	       
	       JButton submitButton = new JButton("Show");
	       frame.add(submitButton);
	       
	       
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
	            	   String p_typeORIGInAL = typeField.getText();
	            	   String p_typeNOSPACES = p_typeORIGInAL.replaceAll("\\s","");
	            	   String type = p_typeNOSPACES.toLowerCase();
	            	   List<Prosthetics> p = prostheticsmanager.showAllProstheticsOfType(type);
	            	   if(p != null) {
	            		   prstGUI(p, type);
	            	   }else {
	            		   JOptionPane.showMessageDialog(frame, "No " +typeField.getText()+" prosthetics in the database");
	            	   }
	               } catch (Exception ex) {
	                   System.out.println(ex); 
	               }
	           }
	           
	       });
	       frame.setVisible(true);
		
    }
    
    public static void prstGUI (List<Prosthetics> prosthetics, String type) {
    	JFrame frame = new JFrame("All " + type +" prosthetics");
    	frame.setSize(new Dimension(500, 500));
    	JPanel contentPanel = new JPanel();
    	String[] columnNames = {"ID", "Date built", "First used", "Inspections", "Assigned engineer", "Assigned client"};
    	
    	DefaultTableModel model = new DefaultTableModel(columnNames, 0); 
    	
    	if (prosthetics != null) {
    		for (Prosthetics p : prosthetics) {
		        Object[] rowData = {
		            p.getId(), 
		            p.getDoB(),
		            p.getDate_bought(), 
		            p.getInspections(),
		            p.getEngineer().getId(),
		            p.getClient().getId(),
		           
		        };
		        model.addRow(rowData);
		    }
    		JTable table = new JTable(model);
    	    JScrollPane scrollPane = new JScrollPane(table);
    	    
    	    table.setPreferredSize(new Dimension(500, 500));
    	    table.getColumnModel().getColumn(0).setPreferredWidth(5);
    	    scrollPane.setPreferredSize(new Dimension(750,200));
    	    contentPanel.add(scrollPane, BorderLayout.CENTER);
    	    contentPanel.revalidate();
    	    contentPanel.repaint();
    	    contentPanel.setPreferredSize(new Dimension(775, 500));

    	    JButton exitButton = new JButton("Exit");
    	    contentPanel.add(exitButton);
            exitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                
            }});
    	    frame.add(contentPanel);
    	    frame.pack();
    	    frame.add(contentPanel);
    	    frame.setVisible(true);
    	

    	} else {
    		JOptionPane.showMessageDialog(frame, "No prosthetics in the system");
    		
    		
    	}
    	
    }
    
    public static void examGUI (List<Exams> exams) {
    	JFrame frame = new JFrame("All exams in the DB");
    	frame.setSize(new Dimension(500, 500));
    	JPanel contentPanel = new JPanel();
    	String[] columnNames = {"ID", "Type", "Date created", "Assigned Machine", "Assigned client"};
    	
    	DefaultTableModel model = new DefaultTableModel(columnNames, 0); 
    	
    	if (exams != null) {
    		for (Exams e : exams) {
		        Object[] rowData = {
		            e.getId(), 
		            e.getType(), 
		            e.getDoB(), 
		            e.getMachine().getId(), 
		            e.getClient().getId(),
		           
		        };
		        model.addRow(rowData);
		    }
    		JTable table = new JTable(model);
    	    JScrollPane scrollPane = new JScrollPane(table);
    	    
    	    table.setPreferredSize(new Dimension(500, 500));
    	    table.getColumnModel().getColumn(0).setPreferredWidth(5);
    	    scrollPane.setPreferredSize(new Dimension(750,200));
    	    contentPanel.add(scrollPane, BorderLayout.CENTER);
    	    contentPanel.revalidate();
    	    contentPanel.repaint();
    	    contentPanel.setPreferredSize(new Dimension(775, 500));

    	    JButton exitButton = new JButton("Exit");
    	    contentPanel.add(exitButton);
            exitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                
            }});
    	    frame.add(contentPanel);
    	    frame.pack();
    	    frame.add(contentPanel);
    	    frame.setVisible(true);
    	

    	} else {
    		JOptionPane.showMessageDialog(frame, "No exams in the system");
    		
    		
    	}
    	
    }

    

} //final 
