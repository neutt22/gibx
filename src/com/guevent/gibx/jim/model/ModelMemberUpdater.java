package com.guevent.gibx.jim.model;

import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.guevent.gibx.jim.GIBXConnection;

public class ModelMemberUpdater {
	
	public boolean update(String code, GIBXConnection conn, String infos[]){
		try{
			Connection con = conn.connect();
			if(con == null) System.exit(0);
			
			String SQL = "UPDATE GED SET " +
						"Policy_Number='" + infos[0] + "'," +
						"Name_Of_Assured='" + infos[1] + "'," +
						"Name_Of_Assured2='" + infos[2] + "'," +
						"Inception_Date='" + infos[3] + "'," +
						"Inception_Date2='" + infos[4] + "'," +
						"Expiry_Date='" + infos[5] + "'," +
						"Expiry_Date2='" + infos[6] + "'," +
						"Debit_memo='" + infos[7] + "'," +
						"Name_of_Beneficiaries='" + infos[8] + "'," +
						"Name_of_Family_Members='" + infos[9] + "'," +
						"Name_of_Family_Members2='" + infos[10] + "'," +
						"Address='" + infos[11] + "'," +
						"Referral_Fee='" + infos[12] + "'," +
						"Telephone='" + infos[13] + "'," +
						"MICO_Policy='" + infos[14] + "'," +
						"Sum_Assured='" + infos[15] + "'," +
						"Principal_Name='" + infos[16] + "'," +
						"Basic_Premium='" + infos[17] + "'," +
						"Insurance_Line='" + infos[18] + "'," +
						"Doc_Stamps='" + infos[19] + "'," +
						"Note1='" + infos[20] + "'," +
						"Vat='" + infos[21] + "'," +
						"Others='" + infos[22] + "'," +
						"Reference='" + infos[23] + "'" +
						
						" WHERE CODE=" + code
						;
			
			Statement st = con.createStatement();
			st.executeUpdate(SQL);
			st.close();
			con.close();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error in editing member:\n" + e.getLocalizedMessage(), "GIBX Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		return true;
	}

}
