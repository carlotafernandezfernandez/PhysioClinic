package PhysioClinicGUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class getDataGUI extends JFrame{
	
	public static int getInt (String what) {
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
