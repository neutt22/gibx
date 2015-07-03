package com.guevent.gibx.jim;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.guevent.gibx.jim.controller.ControllerHome;
import com.guevent.gibx.jim.model.ModelHome;
import com.guevent.gibx.jim.view.ViewHome;

public class Main {
	
	private ViewHome v;
	private ModelHome m;
	
	public Main(String url){
		
		GIBXConnection gibxConn = new GIBXConnection(url);
		
		try {
			SwingUtilities.invokeAndWait(new Runnable(){
				public void run(){ 
					try {
						JFrame.setDefaultLookAndFeelDecorated(true);
						JDialog.setDefaultLookAndFeelDecorated(true);
						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					} catch (Exception e) {  
						e.printStackTrace();
					}
					v = new ViewHome();
				}
			});
		} catch (InvocationTargetException e) { 
			e.printStackTrace();
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}
		m = new ModelHome(gibxConn);
		new ControllerHome(v, m, url);
		
	}
	
	public static void main(String args[]) throws Exception{
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		JTextField txtUrl = new JTextField();
		String DEFAULT = getDefault(txtUrl);
		txtUrl.setText(DEFAULT);
		JCheckBox checkbox = new JCheckBox("Set as default path", true);
		
		String message = "Enter Admin Database:";
		Object[] params = {message, txtUrl, checkbox};
		String[] options = new String[2];
		options[0] = new String("Connect");
		options[1] = new String("Cancel");
		int res = JOptionPane.showOptionDialog(null, params ,"GIBX Connection v.2.0", 0,JOptionPane.INFORMATION_MESSAGE,null,options,null);	
		
		System.out.println(res);
		if(res == 1 || res == -1) System.exit(0);
		
		if(checkbox.isSelected()){
			writeNewDefault(txtUrl.getText());
		}
		
		new Main(txtUrl.getText());
	}
	
	public static void writeNewDefault(String path){
		try{
			File ini = new File(System.getenv("APPDATA") + "\\GIBX\\gibx.ini");
			PrintWriter writer = new PrintWriter(ini);
			writer.write("path=" + path);
			writer.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static String getDefault(JTextField txtUrl){
		//GIBX ini directory from AppData
		File mainDir = new File(System.getenv("APPDATA") + "\\GIBX");
		
		//GIBX ini file
		File ini = new File(mainDir.getAbsolutePath() + "\\gibx.ini");
		
		//Create dir if dir not exist
		if(!mainDir.exists()) mainDir.mkdir();
		
		//Create file if gibx.ini not exist
		if(!ini.exists()){
			try {
				PrintWriter writer = new PrintWriter(ini);
				writer.write("path=" + "\\\\Oman-PC\\Users\\Public\\GED.mdb");
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//read gibx.ini and return whatever string except null
		try {
			BufferedReader reader = new BufferedReader(new FileReader(ini));
			String path = reader.readLine();
			if(path != null) return path.split("=")[1];
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

}
