package com.guevent.gibx.jim;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.Date;

import javax.swing.JTable;

import com.guevent.gibx.jim.controller.ControllerHome;
import com.guevent.gibx.jim.model.ModelHome;
import com.guevent.gibx.jim.view.ViewHome;

public class RowSelector {
	
	public static void rowSelected(MouseEvent me, ViewHome viewHome, ModelHome modelHome, Member m){
		JTable source = (JTable)me.getSource();
		int row = source.rowAtPoint(me.getPoint());
		rowSelected(source, row, viewHome, modelHome, m);
	}
	
	public static void rowSelected(KeyEvent ke, ViewHome viewHome, ModelHome modelHome, Member m){	
		JTable source = (JTable)ke.getSource();
		int row = source.getSelectedRow();
		rowSelected(source, row, viewHome, modelHome, m);
	}
	
	private static void rowSelected(JTable source, int row, ViewHome viewHome, ModelHome modelHome, Member m){
		String coi = null, assured = null, address = null, company = null, note = null,
		telephone = null, benef = null, dependents = null, policy = null;
		Date inception = null, expiry = null;
		BigDecimal bprem = null, others = null, referral = null, sum = null;
		Double dm = null;
		
		coi = (String)source.getValueAt(row, 1);
		assured = (String)source.getValueAt(row, 2);
		address = (String)source.getValueAt(row, 3);
		telephone = (String)source.getValueAt(row, 4);
		inception = (Date)source.getValueAt(row, 5);
		expiry = (Date)source.getValueAt(row, 6);
		benef = (String)source.getValueAt(row, 7);
		dependents = (String)source.getValueAt(row, 8);
		sum = (BigDecimal)source.getValueAt(row, 9);
		bprem = (BigDecimal)source.getValueAt(row, 10);
		others = (BigDecimal)source.getValueAt(row, 11);
		note = (String)source.getValueAt(row, 12);
		referral = (BigDecimal)source.getValueAt(row, 13);
		dm = (Double)source.getValueAt(row, 14);
		policy = (String)source.getValueAt(row, 15);
		company = (String)source.getValueAt(row, 16);
		
		int iCode = (Integer)source.getValueAt(row, 0);
		m.setCODE(String.valueOf(iCode));
		
		viewHome.setCOI(coi);
		viewHome.setAssured(assured);
		viewHome.setAddress(address);
		viewHome.setTelephone(telephone);
		
		if(inception == null) {
			viewHome.setInception("N/A");
		}else{
			viewHome.setInception(ControllerHome.formatter.format(inception));
		}
		
		if(expiry == null){
			viewHome.setExpiry("N/A");
		}else{
			viewHome.setExpiry(ControllerHome.formatter.format(expiry));
		}
	
		viewHome.setBeneficiaries(benef);
		viewHome.setDependents(dependents);
		
		if(sum == null){
			viewHome.setSumAssured("N/A");
		}else{
			viewHome.setSumAssured(String.valueOf(ControllerHome.amountFormatter.format(sum)));
		}
		
		if(bprem == null){
			viewHome.setBasicPremium("N/A");
		}else{
			viewHome.setBasicPremium(String.valueOf(ControllerHome.amountFormatter.format(bprem)));
		}
		
		if(others == null){
			viewHome.setOthers("N/A");
		}else{
			viewHome.setOthers(String.valueOf(ControllerHome.amountFormatter.format(others)));
		}
		
		if(note == null){
			viewHome.setNote("N/A");
		}else{
			viewHome.setNote(note);
		}
		
		if(referral == null){
			viewHome.setReferral("N/A");
		}else{
			viewHome.setReferral(String.valueOf(ControllerHome.amountFormatter.format(referral)));
		}
		
		if(bprem != null && others != null){
			viewHome.setTotal(String.valueOf(ControllerHome.amountFormatter.format(modelHome.getTotal(bprem, others))));	
		}else{
			viewHome.setTotal("N/A");	
		}
		
		if(dm == null){
			viewHome.setDM("N/A");
		}else{
			viewHome.setDM(String.valueOf(ControllerHome.dmFormatter.format(dm)));
		}
		
		viewHome.setPolicy(policy);
		viewHome.setCompany(company);
	}

}
