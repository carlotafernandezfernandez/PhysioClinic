package PhysioClinicJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCManager {
private Connection c = null;
	
	public JDBCManager() {
		
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
					+ "client_id	INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "phone TEXT,"
					+ "name	TEXT NOT NULL,"
					+ "doB	DATE,"
					+ "card_number	INTEGER,"
					+ "allergies	TEXT,"
					+ "treatment	INTEGER CHECK(treatment IN (0, 1)),"
					+ "family_number	INTEGER,"
					+ "email	TEXT,"
					+ "Physiotherapist_id	INTEGER,"
					+ "FOREIGN KEY(Physiotherapist_id) REFERENCES Physiotherapist(physio_id))";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE Engineer ("
					+ "eng_id	INTEGER PRIMARY KEY,"
					+ "eng_licence	BLOB,"
					+ "eng_speciality	TEXT,"
					+ "eng_email	TEXT UNIQUE,"
					+ "eng_phone	TEXT UNIQUE,"
					+ "eng_name	TEXT NOT NULL UNIQUE,"
					+ "eng_doB	DATE,"
					+ "eng_salary	NUMERIC(11, 2)"
					+ ")";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE Machine("
					+ "Machine_id INTEGER PRIMARY KEY,"
					+ "Machine_type TEXT,"
					+ "Machine_doB DATE,"
					+ "Machine_dateBought DATE,"
					+ "Machine_inspections TEXT,"
					+ "Engineer_id INTEGER,"
					+ "FOREIGN KEY(Engineer_id) REFERENCES Engineer (eng_id)"
					+ ")";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE Physiotherapist ("
					+ "physio_id	INTEGER PRIMARY KEY,"
					+ "physio_name	TEXT NOT NULL,"
					+ "physio_speciality	TEXT,"
					+ "physio_phone	TEXT,"
					+ "physio_email	TEXT,"
					+ "physio_licence	BLOB,"
					+ "physio_salary NUMERIC(11, 2),"
					+ "physio_doB	DATE NOT NULL"
					+ ")";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE Products ("
					+ "	prod_id	INTEGER PRIMARY KEY,"
					+ "	availability	INTEGER DEFAULT 1 CHECK(availability IN (0, 1)),"
					+ "	n_available	INTEGER NOT NULL"
					+ ")";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE Prosthetics ("
					+ "prost_id	INTEGER PRIMARY KEY,"
					+ "prost_type	TEXT NOT NULL,"
					+ "prost_doB	DATE,"
					+ "prost_dateBought DATE DEFAULT CURRENT_DATE,"
					+ "prost_inspections	TEXT NOT NULL,"
					+ "eng_id	INTEGER,"
					+ "client_id	INTEGER,"
					+ "FOREIGN KEY(eng_id) REFERENCES Engineer(eng_id),"
					+ "FOREIGN KEY(client_id) REFERENCES Client(client_id)"
					+ ")";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE OrderEngProd ("
					+ "	orderEP_id	INTEGER PRIMARY KEY,"
					+ "	id_engineer	INTEGER NOT NULL,"
					+ "	id_product INTEGER NOT NULL,"
					+ "	FOREIGN KEY(id_engineer) REFERENCES Engineer(eng_id),"
					+ "	FOREIGN KEY(id_product) REFERENCES Products(prod_id)"
					+ ")";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE OrderPhysioExam ("
					+ "orderExamP_id	INTEGER PRIMARY KEY,"
					+ "id_physio	INTEGER NOT NULL,"
					+ "id_exams	INTEGER NOT NULL,"
					+ "FOREIGN KEY(id_physio) REFERENCES Physiotherapist(Physiotherapist_id),"
					+ "FOREIGN KEY(id_exams) REFERENCES Exams(ex_id)"
					+ ")";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE OrderPhysioProd ("
					+ "orderPP_id	INTEGER PRIMARY KEY,"
					+ "id_physio	INTEGER NOT NULL,"
					+ "id_product	INTEGER NOT NULL,"
					+ "FOREIGN KEY(id_product) REFERENCES Products(prod_id),"
					+ "FOREIGN KEY(id_physio) REFERENCES Physiotherapist(Physiotherapist_id)"
					+ ")";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE OrderPhysioProd ("
					+ "orderPP_id	INTEGER PRIMARY KEY,"
					+ "id_physio	INTEGER NOT NULL,"
					+ "id_product	INTEGER NOT NULL,"
					+ "FOREIGN KEY(id_product) REFERENCES Products(prod_id),"
					+ "FOREIGN KEY(id_physio) REFERENCES Physiotherapist(Physiotherapist_id)"
					+ ")";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE Exam ("
					+ "ex_id INTEGER PRIMARY KEY,"
					+ "ex_type TEXT,"
					+ "client_id INTEGER,"
					+ "ex_doB DATE,"
					+ "ex_image BLOB,"
					+ "Machine_id INTEGER,"
					+ "FOREIGN KEY (\"Machine_id\") REFERENCES Machine (\"Machine_id\"),"
					+ "FOREIGN KEY (client_id) REFERENCES Client (\"client_id\")"
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
