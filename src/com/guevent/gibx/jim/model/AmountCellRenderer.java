package com.guevent.gibx.jim.model;

import java.text.DecimalFormat;

import javax.swing.table.DefaultTableCellRenderer;

public class AmountCellRenderer extends DefaultTableCellRenderer{

	private static final long serialVersionUID = 1L;
	
	 @Override
	    public void setValue(Object value) {
	        DecimalFormat amountFormatter = new DecimalFormat("###,##0.00");
	        
	        setText((value == null) ? "" : amountFormatter.format(value));
	    }

}
