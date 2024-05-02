package PhysioClinicJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDCBManager {
private Connection c = null;
	
	public JDCBManager() {
		
		try {
			
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./db/proyecto.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			
			System.out.print("Database Connection opened.");
			this.createTables();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			System.out.print("Libraries not loaded");
		}
	}
	
	private void createTables() {
		try {
			
			Statement stmt = c.createStatement();
			
			String sql = "CREATE TABLE Client ("
					+ "client_id	INTEGER,"
					+ "phone INTEGER NOT NULL,"
					+ "name	TEXT NOT NULL,"
					+ "doB	DATE NOT NULL,"
					+ "card_number	INTEGER,"
					+ "allergies	TEXT NOT NULL,"
					+ "treatment	INTEGER CHECK(treatment IN (0, 1)),"
					+ "family_number	INTEGER,"
					+ "email	TEXT,"
					+ "Physiotherapist_id	INTEGER,"
					+ "PRIMARY KEY(client_id),"
					+ "FOREIGN KEY(Physiotherapist_id) REFERENCES Physiotherapist(physio_id)";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE Engineer ("
					+ "eng_id	INTEGER,"
					+ "eng_licence	BLOB NOT NULL,"
					+ "eng_speciality	TEXT,"
					+ "eng_email	TEXT UNIQUE,"
					+ "eng_phone	INTEGER UNIQUE,"
					+ "eng_name	TEXT NOT NULL UNIQUE,"
					+ "eng_doB	DATE,"
					+ "eng_salary	NUMERIC(11, 2),"
					+ "PRIMARY KEY(eng_id)"
					+ ")";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE Exams ("
					+ "ex_id	INTEGER,"
					+ "ex_type	TEXT NOT NULL,"
					+ "ex_clientFrom	TEXT,"
					+ "ex_doB\"	DATE,"
					+ "Machine_id INTEGER,"
					+ "PRIMARY KEY(ex_id),"
					+ "FOREIGN KEY(Machine_id) REFERENCES Machine(Machine_id)"
					+ ")";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE Machine("
					+ "Machine_id INTEGER PRIMARY KEY,"
					+ "Machine_type TEXT,"
					+ "Machine_doB DATE,"
					+ "Machine_dateBought DATE DEFAULT CURRENT_DATE,"
					+ "Machine_inspections TEXT NOT NULL,"
					+ "Engineer_id INTEGER,"
					+ "FOREIGN KEY(Engineer_id) REFERENCES Engineer (eng_id)"
					+ ")";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE Physiotherapist ("
					+ "physio_id	INTEGER,"
					+ "physio_name	TEXT NOT NULL,"
					+ "physio_speciality	TEXT CHECK(physio_speciality IN (superior limbs, inferior limbs, spinal column)),"
					+ "physio_phone	INTEGER NOT NULL UNIQUE,"
					+ "physio_email	TEXT NOT NULL UNIQUE,"
					+ "physio_licence	BLOB NOT NULL,"
					+ "physio_salary NUMERIC(11, 2),"
					+ "physio_doB	DATE NOT NULL,"
					+ "PRIMARY KEY(physio_id)"
					+ ")";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE Products ("
					+ "	prod_id	INTEGER,"
					+ "	availability	INTEGER DEFAULT 1 CHECK(availability IN (0, 1)),"
					+ "	n_available	INTEGER NOT NULL,"
					+ "	PRIMARY KEY(\"prod_id\")"
					+ ")";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE Prosthetics("
					+ "	prost_id INTEGER PRIMARY KEY,"
					+ "	prost_type TEXT NOT NULL,"
					+ "	prost_clientUsing TEXT,"
					+ "	prost_doB DATE,"
					+ "	prost_dateBought DATE DEFAULT CURRENT_DATE,"
					+ "	prost_inspections TEXT NOT NULL,"
					+ "	eng_id INTEGER,"
					+ "	client_id INTEGER,"
					+ "	FOREIGN KEY(eng_id) REFERENCES Engineer (eng_id),"
					+ "	FOREIGN KEY(client_id) REFERENCES Client (client_id)"
					+ ")";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE OrderEngProd ("
					+ "	orderEP_id	INTEGER,"
					+ "	id_engineer	INTEGER NOT NULL,"
					+ "	id_product INTEGER NOT NULL,"
					+ "	PRIMARY KEY(orderEP_id),"
					+ "	FOREIGN KEY(id_engineer) REFERENCES Engineer(eng_id),"
					+ "	FOREIGN KEY(id_product) REFERENCES Products(prod_id)"
					+ ")";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE OrderPhysioExam ("
					+ "orderExamP_id	INTEGER,"
					+ "id_physio	INTEGER NOT NULL,"
					+ "id_exams	INTEGER NOT NULL,"
					+ "FOREIGN KEY(id_physio) REFERENCES Physiotherapist(Physiotherapist_id),"
					+ "FOREIGN KEY(id_exams) REFERENCES Exams(ex_id),"
					+ "PRIMARY KEY(orderExamP_id)"
					+ ")";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE OrderPhysioProd ("
					+ "orderPP_id	INTEGER,"
					+ "id_physio	INTEGER NOT NULL,"
					+ "id_product	INTEGER NOT NULL,"
					+ "FOREIGN KEY(id_product) REFERENCES Products(prod_id),"
					+ "FOREIGN KEY(id_physio) REFERENCES Physiotherapist(Physiotherapist_id),"
					+ "PRIMARY KEY(orderPP_id)"
					+ ")";
			stmt.executeUpdate(sql);
			
			
		}catch(SQLException e) {
			if(!e.getMessage().contains("already exists")) 
			{
				e.printStackTrace();
			}			
		}
		
	}
	
	public Connection getConnection() {
		return c;
	}
	
	public void disconnect()
	{
		try {
			c.close();
		}
		catch(SQLException e){ 
			e.printStackTrace();
		}		
	}
}
