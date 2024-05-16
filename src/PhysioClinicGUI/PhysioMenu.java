package PhysioClinicGUI;
import UI.*; 
import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;

import UI.Menu;

public class PhysioMenu extends JFrame{
	private JLabel label;
    private JButton loginButton, signUpButton, updateButton, exitButton;

    public PhysioMenu()  {
        super("physioMenu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        label = new JLabel("Choose an option:");
        label.setBounds(20, 20, 280, 30);
        label.setBackground(Color.BLUE); 
        label.setOpaque(true); 
        add(label);

        loginButton = new JButton("Create client");
        loginButton.setBounds(20, 60, 150, 30);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					Menu.createClient();
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
}
