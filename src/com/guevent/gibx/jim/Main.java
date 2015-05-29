package com.guevent.gibx.jim;

import java.lang.reflect.InvocationTargetException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
		// "\\\\192.168.1.44/Documents/JIM/GED.mdb"
		// "C:/GED/GED.mdb"
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		Object db = JOptionPane.showInputDialog(null, "GIBX Database: ", "J:\\GED.mdb");
		new Main((String)db);
	}

}
