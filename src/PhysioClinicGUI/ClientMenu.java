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
	private static JDBCManager jdbcmanager = new JDBCManager();;
	private static PhysioManager physiomanager = new JDBCPhysioManager(jdbcmanager);
	private static final long serialVersionUID = 1L;
	private JLabel label;
    private JButton loginButton, signUpButton, exitButton;

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
    
    

    
    
 }
  

