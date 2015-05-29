package com.guevent.gibx.jim.model;

import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.guevent.gibx.jim.GIBXConnection;

public class ModelMemberRenewer {
	
	public boolean renew(DefaultTableModel tableModel, GIBXConnection con, String infos[]){
		
		try{
			Connection conn = con.connect();
			if(conn == null) System.exit(0);
			String SQL = "INSERT INTO GED (Policy_Number, Name_Of_Assured, Name_Of_Assured2, Inception_Date, Inception_Date2, "
				+ "Expiry_Date,"
				+ "Expiry_Date2,"
				+ "Debit_memo,"
				+ "Name_of_Beneficiaries,"
				+ "Name_of_Family_Members,"
				+ "Name_of_Family_Members2,"
				+ "Address,"
				+ "Referral_Fee,"
				+ "Telephone,"
				+ "MICO_Policy,"
				+ "Sum_Assured,"
				+ "Principal_Name,"
				+ "Basic_Premium,"
				+ "Insurance_Line,"
				+ "Doc_Stamps,"
				+ "Note1,"
				+ "Vat,"
				+ "Others,"
				+ "Reference"
				+ ") "
				+ "VALUES ("
				+ "'" + infos[0] + "',"
				+ "'" + infos[1] + "',"
				+ "'" + infos[2] + "',"
				+ "'" + infos[3] + "',"
				+ "'" + infos[4] + "',"
				+ "'" + infos[5] + "',"
				+ "'" + infos[6] + "',"
				+ "'" + infos[7] + "',"
				+ "'" + infos[8] + "',"
				+ "'" + infos[9] + "',"
				+ "'" + infos[10] + "',"
				+ "'" + infos[11] + "',"
				+ "'" + infos[12] + "',"
				+ "'" + infos[13] + "',"
				+ "'" + infos[14] + "',"
				+ "'" + infos[15] + "',"
				+ "'" + infos[16] + "',"
				+ "'" + infos[17] + "',"
				+ "'" + infos[18] + "',"
				+ "'" + infos[19] + "',"
				+ "'" + infos[20] + "',"
				+ "'" + infos[21] + "',"
				+ "'" + infos[22] + "',"
				+ "'" + infos[23] + "'"
				+ ")";
			Statement st = conn.createStatement();
			st.executeUpdate(SQL);
		
			st.close();
			conn.close();
			return true;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error in renewing member:\n" + e.getLocalizedMessage(), "GIBX Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return false;
		
	}

	

}
