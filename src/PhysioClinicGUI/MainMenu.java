package PhysioClinicGUI;
import UI.*; 
import javax.swing.*;
import java.awt.event.*;

public class MainMenu extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JLabel label;
    private JButton loginButton, signUpButton, updateButton, exitButton;

    public MainMenu()  {
        super("Menu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        label = new JLabel("Choose an option:");
        label.setBounds(20, 20, 200, 30);
        add(label);

        loginButton = new JButton("Login User");
        loginButton.setBounds(20, 60, 150, 30);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Menu.login();
            }
        });
        add(loginButton);

        signUpButton = new JButton("Sign-up new user");
        signUpButton.setBounds(20, 100, 150, 30);
        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Menu.signUpUser();
            }
        });
        add(signUpButton);

        updateButton = new JButton("Update password");
        updateButton.setBounds(20, 140, 150, 30);
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Menu.updatePassword();
            }
        });
        add(updateButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(20, 180, 150, 30);
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
