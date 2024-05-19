package PhysioClinicGUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import PhysioClinicIFaces.*;
import PhysioClinicJDBC.JDBCEngineerManager;
import PhysioClinicJDBC.JDBCManager;
import PhysioClinicJDBC.JDBCPhysioManager;
import PhysioClinicPOJOs.Engineer;
import PhysioClinicPOJOs.Machine;
import PhysioClinicPOJOs.Physio;

public class MachineMenu extends JFrame {
	static JDBCManager jdbcmanager;
	static EngineerManager engineermanager = new JDBCEngineerManager(jdbcmanager);
	
	private static final long serialVersionUID = 1L;
	private JLabel label;
    private JButton loginButton, signUpButton, updateButton, exitButton;
    
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

         // Create the submit button
         JButton submitButton = new JButton("Create machine");
         frame.add(submitButton);
         

         // Add empty label for alignment
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

}
