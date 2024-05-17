package PhysioClinicGUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
}
