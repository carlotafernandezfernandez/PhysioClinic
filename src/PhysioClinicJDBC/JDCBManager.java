package PhysioClinicJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDCBManager {
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
					+ "FOREIGN KEY(Physiotherapist_id) REFERENCES \"Physiotherapist\"(physio_id)";
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
					+ "FOREIGN KEY(Machine_id) REFERENCES \"Machine\"(Machine_id)"
					+ ")";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE Machine(\r\n"
					+ "	Machine_id INTEGER PRIMARY KEY,\r\n"
					+ "	Machine_type TEXT,\r\n"
					+ "	Machine_doB DATE,\r\n"
					+ "	Machine_dateBought DATE DEFAULT CURRENT_DATE,\r\n"
					+ "	Machine_inspections TEXT NOT NULL,\r\n"
					+ "	Engineer_id INTEGER, \r\n"
					+ "	FOREIGN KEY(\"Engineer_id\") REFERENCES \"Engineer\" (eng_id)\r\n"
					+ ")";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE \"Physiotherapist\" (\r\n"
					+ "	\"physio_id\"	,\r\n"
					+ "	\"physio_name\"	TEXT NOT NULL,\r\n"
					+ "	\"physio_speciality\"	TEXT CHECK(\"physio_speciality\" IN (\"superior limbs\", \"inferior limbs\", \"spinal column\")),\r\n"
					+ "	\"physio_phone\"	INTEGER NOT NULL UNIQUE,\r\n"
					+ "	\"physio_email\"	TEXT NOT NULL UNIQUE,\r\n"
					+ "	\"physio_licence\"	BLOB NOT NULL,\r\n"
					+ "	\"physio_salary\"	NUMERIC(11, 2),\r\n"
					+ "	\"physio_doB\"	DATE NOT NULL,\r\n"
					+ "	PRIMARY KEY(\"physio_id\")\r\n"
					+ ")";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE \"Products\" (\r\n"
					+ "	\"prod_id\"	INTEGER,\r\n"
					+ "	\"availability\"	INTEGER DEFAULT 1 CHECK(\"availability\" IN (0, 1)),\r\n"
					+ "	\"n_available\"	INTEGER NOT NULL,\r\n"
					+ "	PRIMARY KEY(\"prod_id\")\r\n"
					+ ")";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE Prosthetics(\r\n"
					+ "	prost_id INTEGER PRIMARY KEY,\r\n"
					+ "	prost_type TEXT NOT NULL,\r\n"
					+ "	prost_clientUsing TEXT,\r\n"
					+ "	prost_doB DATE,\r\n"
					+ "	prost_dateBought DATE DEFAULT CURRENT_DATE,\r\n"
					+ "	prost_inspections TEXT NOT NULL,\r\n"
					+ "	eng_id INTEGER,\r\n"
					+ "	client_id INTEGER,\r\n"
					+ "	FOREIGN KEY(\"eng_id\") REFERENCES \"Engineer\" (eng_id),\r\n"
					+ "	FOREIGN KEY(\"client_id\") REFERENCES \"Client\" (client_id)\r\n"
					+ ")";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE \"OrderEngProd\" (\r\n"
					+ "	\"orderEP_id\"	INTEGER,\r\n"
					+ "	\"id_engineer\"	INTEGER NOT NULL,\r\n"
					+ "	\"id_product\"	INTEGER NOT NULL,\r\n"
					+ "	PRIMARY KEY(\"orderEP_id\"),\r\n"
					+ "	FOREIGN KEY(\"id_engineer\") REFERENCES \"Engineer\"(\"eng_id\"),\r\n"
					+ "	FOREIGN KEY(\"id_product\") REFERENCES \"Products\"(\"prod_id\")\r\n"
					+ ")";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE \"OrderPhysioExam\" (\r\n"
					+ "	\"orderExamP_id\"	INTEGER,\r\n"
					+ "	\"id_physio\"	INTEGER NOT NULL,\r\n"
					+ "	\"id_exams\"	INTEGER NOT NULL,\r\n"
					+ "	FOREIGN KEY(\"id_physio\") REFERENCES \"Physiotherapist\"(\"Physiotherapist_id\"),\r\n"
					+ "	FOREIGN KEY(\"id_exams\") REFERENCES \"Exams\"(\"ex_id\"),\r\n"
					+ "	PRIMARY KEY(\"orderExamP_id\")\r\n"
					+ ")";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE \"OrderPhysioProd\" (\r\n"
					+ "	\"orderPP_id\"	INTEGER,\r\n"
					+ "	\"id_physio\"	INTEGER NOT NULL,\r\n"
					+ "	\"id_product\"	INTEGER NOT NULL,\r\n"
					+ "	FOREIGN KEY(\"id_product\") REFERENCES \"Products\"(\"prod_id\"),\r\n"
					+ "	FOREIGN KEY(\"id_physio\") REFERENCES \"Physiotherapist\"(\"Physiotherapist_id\"),\r\n"
					+ "	PRIMARY KEY(\"orderPP_id\")\r\n"
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
