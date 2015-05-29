package com.guevent.gibx.jim.model;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.swing.JTextField;

import com.guevent.gibx.jim.view.ViewEdit;
import com.guevent.gibx.jim.view.ViewNew;
import com.guevent.gibx.jim.view.ViewRenew;

public class FormRenderer implements FocusListener{
	
	private static DecimalFormat amountFormatter = new DecimalFormat("###,##0.00");
	private static NumberFormat nf = NumberFormat.getInstance(Locale.getDefault());
	
	private Object view;
	private String form;
	
	public FormRenderer(Object v, String form){
		if(form.equals("new")){
			this.form = "new";
		}else if(form.equals("edit")){
			this.form = "edit";
		}else{
			this.form = "renew";
		}
		view = v;
	}

	@Override
	public void focusGained(FocusEvent fe) {
		if(fe.getSource() instanceof JTextField){
			((JTextField)fe.getSource()).selectAll();
		}
		
	}

	@Override
	public void focusLost(FocusEvent fe) {
		String sum = null;
		String bprem = null;
		String others = null;
		
		if(form.equals("new")){
			sum = ((ViewNew) view).getSum();
			bprem = ((ViewNew) view).getBprem();
			others = ((ViewNew)view).getOthers();
		}else if(form.equals("renew")){
			sum = ((ViewRenew) view).getSum();
			bprem = ((ViewRenew) view).getBprem();
			others = ((ViewRenew)view).getOthers();
		}else if(form.equals("edit")){
			sum = ((ViewEdit) view).getSum();
			bprem = ((ViewEdit) view).getBprem();
			others = ((ViewEdit)view).getOthers();
		}

		double dbSum = 0;
		double dbBprem = 0;
		double dbOthers = 0;
		double dbTotal = 0;
		
		try{
			dbSum = nf.parse(sum).doubleValue();
		}catch(ParseException pe){
			dbSum = 220000.00;
		}
		
		try{
			dbBprem = nf.parse(bprem).doubleValue();
		}catch(ParseException pe){
			dbBprem = 1500.00;
		}
		
		try{
			dbOthers = nf.parse(others).doubleValue();
		}catch(ParseException pe){
			dbTotal = 0.00;
		}
		
		try{
			dbTotal = dbBprem + dbOthers;
		}catch(Exception pe){
			dbTotal = 0.00;
		}
		
		String formattedSum = amountFormatter.format(dbSum);
		String formattedBprem = amountFormatter.format(dbBprem);
		String formattedOthers = amountFormatter.format(dbOthers);
		String formattedTotal = amountFormatter.format(dbTotal);
		
		if(form.equals("new")){
			((ViewNew) view).setSum(formattedSum);
			((ViewNew) view).setBprem(formattedBprem);
			((ViewNew) view).setOthers(formattedOthers);
			((ViewNew) view).setTotal(formattedTotal);
		}else if(form.equals("renew")){
			((ViewRenew) view).setSum(formattedSum);
			((ViewRenew) view).setBprem(formattedBprem);
			((ViewRenew) view).setOthers(formattedOthers);
			((ViewRenew) view).setTotal(formattedTotal);
		}else if(form.equals("edit")){
			((ViewEdit) view).setSum(formattedSum);
			((ViewEdit) view).setBprem(formattedBprem);
			((ViewEdit) view).setOthers(formattedOthers);
			((ViewEdit) view).setTotal(formattedTotal);
		}
		
	}
	
	public static void main(String args[]){
		String amount1 = "2,200.00";
		String amount2 ="1,222.90";
		
		double dbAmt1 = 0;double dbAmt2 = 0;
		double sum = 0;
		try {
			dbAmt1 = nf.parse(amount1).doubleValue();
			dbAmt2 = nf.parse(amount2).doubleValue();
			sum = dbAmt1 + dbAmt2;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String formattedAmount = amountFormatter.format(sum);
		System.out.println(formattedAmount);
	}

}
