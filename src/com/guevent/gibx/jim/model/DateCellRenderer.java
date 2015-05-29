package com.guevent.gibx.jim.model;

import java.text.SimpleDateFormat;

import javax.swing.table.DefaultTableCellRenderer;

public class DateCellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;

	public DateCellRenderer() { super(); }

    @Override
    public void setValue(Object value) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        
        setText((value == null) ? "" : sdf.format(value));
    }
    
}