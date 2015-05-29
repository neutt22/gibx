package com.guevent.gibx.jim.model;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.guevent.gibx.jim.GIBXConnection;
import com.guevent.gibx.jim.Member;
import com.guevent.gibx.jim.RowSelector;
import com.guevent.gibx.jim.Util;
import com.guevent.gibx.jim.view.ViewEdit;
import com.guevent.gibx.jim.view.ViewHome;
import com.guevent.gibx.jim.view.ViewNew;
import com.guevent.gibx.jim.view.ViewRenew;

public class ModelHome {
	
	private GIBXConnection connection;
	private ModelMemberAdder adder = new ModelMemberAdder();
	private ModelMemberRenewer renewer = new ModelMemberRenewer();
	private ModelMemberUpdater updater = new ModelMemberUpdater();
	
	public ModelHome(GIBXConnection gibxCon){
		connection = gibxCon;
	}
	
	// Add a fucking member
	public void add(ViewNew v, DefaultTableModel tableModel, String infos[]){
		if(adder.add(tableModel, connection, infos)){
			v.setVisible(false);
		}else{
			v.setVisible(true);
		}
	}
	
	// Renew a fucking member
	public void renew(ViewRenew v, DefaultTableModel tableModel, String infos[]){
		if(renewer.renew(tableModel, connection, infos)){
			v.setVisible(false);
		}else{
			v.setVisible(true);
		}
	}
	
	// Update a fucking member
	public void update(ViewEdit v, String code, String infos[]){
		if(updater.update(code, connection, infos)){
			v.setVisible(false);
		}else{
			v.setVisible(true);
		}
	}
	
	public double getTotal(BigDecimal bprem, BigDecimal others){
		return bprem.doubleValue() + others.doubleValue();
	}
	
	public void handlePopMenu(MouseEvent me, ViewHome v){
		if(me.isPopupTrigger()){
			JTable source = (JTable)me.getSource();
			int row = source.rowAtPoint(me.getPoint());
			int column = source.columnAtPoint(me.getPoint());
			
			if(!source.isRowSelected(row)){
				source.changeSelection(row, column, false, false);
			}
			v.getPopupMenu().show(me.getComponent(), me.getX(), me.getY());
		}
	}
	
	public void rowSelected(MouseEvent me, ViewHome v, Member m){
		RowSelector.rowSelected(me, v, this, m);
	}
	
	public void rowSelected(KeyEvent me, ViewHome v, Member m){
		RowSelector.rowSelected(me, v, this, m);
	}
		
	public void fetchData(DefaultTableModel tableModel, int maxRow){
		try{
			String select = "CODE, Policy_Number," +
					"Name_of_Assured, Address, Telephone," +
					"Inception_Date, Expiry_Date," +
					"Name_of_Beneficiaries, Name_of_Family_Members," +
					"Sum_Assured, Basic_Premium, Others, Note1, " +
					"Referral_Fee, Debit_memo, MICO_Policy, Reference";
	
			Connection con = connection.connect();
			if(con == null) System.exit(0);
			String SQL = "SELECT " + select+ " FROM GED ORDER BY CODE DESC";
			Statement st = con.createStatement();
			st.setMaxRows(maxRow);
			ResultSet rs = st.executeQuery(SQL);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			
			//GET ALL COLUMN NAMES
//			for(int i = 1; i <= columns; i++){
//				columnNames.addElement( rsmd.getColumnName(i) );
//			}
			
			//GET ALL DATA
			while(rs.next()){
				Vector<Object> row = new Vector<Object>(columns);
				for(int i = 1; i <= columns; i++){
					row.addElement(rs.getObject(i));
				}
				tableModel.addRow(row);
				//data.addElement(row);
			}
		
			rs.close();
			st.close();
			con.close();
		}catch( Exception e){
			e.printStackTrace();
		}
	}
	
	public void search(DefaultTableModel tableModel, String field, String query, ViewHome viewHome){
		try{
			String select = "CODE, Policy_Number," +
				"Name_of_Assured, Address, Telephone," +
				"Inception_Date, Expiry_Date," +
				"Name_of_Beneficiaries, Name_of_Family_Members," +
				"Sum_Assured, Basic_Premium, Others, Note1, " +
				"Referral_Fee, Debit_memo, MICO_Policy, Reference";
			Connection con = connection.connect();
			if(con == null) System.exit(0);
			String SQL = "SELECT " + select+ " FROM GED WHERE " + field + " like '%" + query + "%' ORDER BY CODE DESC";
			//String SQL = "SELECT " + select+ " FROM GED WHERE " + field + " like '%SHE%' ORDER BY CODE DESC";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(SQL);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			int rowCount = 0;
			while(rs.next()){
				Vector<Object> row = new Vector<Object>(columns);
				for(int i = 1; i <= columns; i++){
					row.addElement(rs.getObject(i));
				}
				tableModel.addRow(row);
				rowCount++;
			}
			
			rs.close();
			st.close();
			con.close();
			viewHome.setLblReturnedRow("AFFECTED ROWS: " + String.valueOf(rowCount) );
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public Member getInfos(int code, Member m){
		try{
			Connection conn = connection.connect();
			if(conn == null) System.exit(0);
			
			String SQL = "SELECT Policy_Number, Name_Of_Assured, Name_Of_Assured2, Inception_Date, Inception_Date2, "
				+ "Expiry_Date, Expiry_Date2, Debit_memo, Name_of_Beneficiaries, Name_of_Family_Members, Name_of_Family_Members2,"
				+ "Address,Referral_Fee,Telephone,MICO_Policy,Sum_Assured,Principal_Name,Basic_Premium,Insurance_Line,Doc_Stamps,"
				+ "Note1,Vat,Others,Reference FROM GED WHERE CODE=" + code;
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(SQL);
			rs.next();

			m.setPolicy_Number((String)rs.getObject(1));
			m.setName_Of_Assured((String)rs.getObject(2));
			m.setName_Of_Assured2((String)rs.getObject(3));
			try{
				m.setInception_Date(Util.dateFormatter.format((Date)rs.getObject(4)));
			}catch(NullPointerException npe){
				m.setInception_Date("01/01/2015");
			}
			m.setInception_Date2((String)rs.getObject(5));
			try{
				m.setExpiry_Date(Util.dateFormatter.format((Date)rs.getObject(6)));
			}catch(NullPointerException npe){
				m.setExpiry_Date("01/01/2016");
			}
			m.setExpiry_Date2((String)rs.getObject(7));
			m.setDebit_memo(Util.dmFormatter.format(rs.getObject(8)));
			m.setName_of_Beneficiaries(rs.getObject(9).toString());
			m.setName_of_Family_Members(rs.getObject(10).toString());
			m.setName_of_Family_Members2(rs.getObject(11).toString());
			m.setAddress(rs.getObject(12).toString());
			try{
				m.setReferral_Fee(Util.amountFormatter.format(rs.getObject(13)));
			}catch(NullPointerException npe){
				m.setReferral_Fee("0.00");
			}
			m.setTelephone(rs.getObject(14).toString());
			try{
				m.setMICO_Policy(rs.getObject(15).toString());
			}catch(NullPointerException npe){
				m.setMICO_Policy("");
			}
			m.setSum_Assured(Util.amountFormatter.format(rs.getObject(16)));
			m.setPrincipal_Name(rs.getObject(17).toString());
			m.setBasic_Premium(Util.amountFormatter.format(rs.getObject(18)));
			m.setInsurance_Line(rs.getObject(19).toString());
			m.setDoc_Stamps(rs.getObject(20).toString());
			m.setNote(rs.getObject(21).toString());
			m.setVat(rs.getObject(22).toString());
			m.setOthers(Util.amountFormatter.format(rs.getObject(23)));
			m.setReference(rs.getObject(24).toString());
			
			st.close();
			conn.close();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error in retrieving member:\n" + e.getLocalizedMessage(), "GIBX Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return m;
	}


}
