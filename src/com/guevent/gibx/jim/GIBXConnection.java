package com.guevent.gibx.jim;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class GIBXConnection {
	
	String url;
	
	public GIBXConnection(String u){
		url = u;
	}
	
	public Connection connect(){
		String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
		try {
			Class.forName(driver);
			return DriverManager.getConnection( "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};Dbq=" + url );
//			return DriverManager.getConnection( "jdbc:ucanaccess://" + url );
		} catch(SQLException sqle){
			JOptionPane.showMessageDialog(null, "GIBX Connection:\n" + sqle.getMessage()
				+ "\n\nPress OK to exit.", "SQL Error", JOptionPane.ERROR_MESSAGE);
			sqle.printStackTrace();
			
		} catch (ClassNotFoundException cne){
			cne.printStackTrace();
		}
		return null;
	}
	
	
}
