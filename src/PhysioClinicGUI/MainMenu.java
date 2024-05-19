package PhysioClinicGUI;
import UI.*; 
import javax.swing.*;

import java.awt.GridLayout;
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
    
    public static int getIntGUI(String what) {
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
        
        while (frame.isVisible()) {
            try {
                Thread.sleep(100); 
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        
        return i[0]; 
    }
    
    public static String getStringGUI(String what) {
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
        
        while (frame.isVisible()) {
            try {
                Thread.sleep(100); 
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        
        return s[0]; 
    }
   
    
    
}
