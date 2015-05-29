package com.guevent.gibx.jim;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class Printer {
	
	public static void print(String file){
		Desktop desktop = Desktop.getDesktop();
		try{
			desktop.print(new File(file)); //"C:\\GED\\cis_ind.doc"
		}catch (IOException ie){
			ie.printStackTrace();
		}
	}

}
