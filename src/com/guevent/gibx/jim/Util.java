package com.guevent.gibx.jim;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import com.guevent.gibx.jim.model.AmountCellRenderer;
import com.guevent.gibx.jim.model.DMCellRenderer;
import com.guevent.gibx.jim.model.DateCellRenderer;

public class Util {

	public static SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
	public static DecimalFormat amountFormatter = new DecimalFormat("###,##0.00");
	public static DecimalFormat dmFormatter = new DecimalFormat("###0");
	
	public static int getMaxRow(String max){
		int m = 0;
		try{
			m = Integer.parseInt(max.trim());
		}catch(Exception e){
			return 50; //input not parsable, set 50 as default
		}
		return m;
	}
	
	public static String getSearchField(String field){
		String f = field;
		if(field.equals("COI")){
			f = "Policy_Number";
		}else if (field.equals("ASSURED")){
			f = "Name_of_Assured";
		}else if (field.equals("INCEPTION")){
			f = "Inception_Date";
		}else if (field.equals("EXPIRATION")){
			f = "Expiry_Date";
		}else if (field.equals("BPREM")){
			f = "Basic_Premium";
		}else if (field.equals("OTHERS")){
			f = "Others";
		}else if (field.equals("DM")){
			f = "Debit_memo";
		}else if (field.equals("POLICY")){
			f = "MICO_Policy";
		}else if (field.equals("COMPANY")){
			f = "Reference";
		}
		return f;
	}
	
	public static void backup(String url, String dest){
		FileChannel inChannel = null;
		FileChannel outChannel = null;
		try{
			inChannel = new FileInputStream(new File(url)).getChannel();
			outChannel = new FileOutputStream(new File(dest)).getChannel();
			outChannel.transferFrom(inChannel, 0, inChannel.size());
			inChannel.close();
			outChannel.close();
			JOptionPane.showMessageDialog(null, "Backup has been successfully created!\n\n" +
					"SOURCE: " + url + "\n"+
					"DESTINATION: " + dest, 
					"GIBX Backup", JOptionPane.INFORMATION_MESSAGE);
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Backup Error:\n" + e.getLocalizedMessage(), "GIBX Error", JOptionPane.ERROR_MESSAGE);
		}finally{
			try {
				inChannel.close();
				outChannel.close();
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Backup Resource Error:\n" + e.getLocalizedMessage(), "GIBX Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}
	
	public static String[] getSearchCat(){
		return new String[]{"CODE", "COI", "ASSURED", "INCEPTION", "EXPIRATION", "BPREM", "OTHERS", "DM", "POLICY", "COMPANY"};
	}
	
	public static void alignCell(TableColumnModel column){
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(JLabel.CENTER);
		for(int i = 0; i <= 15; i++){
			if(i != 5 && i != 6 ) column.getColumn(i).setCellRenderer(render);
		}
	}
	
	public static void formatDateCell(TableColumnModel column){
		DateCellRenderer render = new DateCellRenderer();
		render.setHorizontalAlignment(JLabel.CENTER);
		column.getColumn(5).setCellRenderer(render);
		column.getColumn(6).setCellRenderer(render);
	}
	
	public static void formatAmtCell(TableColumnModel column){
		AmountCellRenderer render = new AmountCellRenderer();
		render.setHorizontalAlignment(JLabel.CENTER);
		column.getColumn(9).setCellRenderer(render);
		column.getColumn(10).setCellRenderer(render);
		column.getColumn(11).setCellRenderer(render);
		column.getColumn(13).setCellRenderer(render);
	}
	
	public static void formatDMCell(TableColumnModel column){
		DMCellRenderer render = new DMCellRenderer();
		render.setHorizontalAlignment(JLabel.CENTER);
		column.getColumn(14).setCellRenderer(render);
	}

}
