package com.guevent.gibx.jim.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.guevent.gibx.jim.Member;
import com.guevent.gibx.jim.Printer;
import com.guevent.gibx.jim.Util;
import com.guevent.gibx.jim.model.ModelHome;
import com.guevent.gibx.jim.view.ViewEdit;
import com.guevent.gibx.jim.view.ViewHome;
import com.guevent.gibx.jim.view.ViewNew;
import com.guevent.gibx.jim.view.ViewRenew;


public class ControllerHome implements ActionListener, MouseListener, FocusListener, KeyListener, ListSelectionListener{
	
	private ViewHome viewHome;
	private ViewNew viewNew = new ViewNew();
	private ViewRenew viewRenew = new ViewRenew();
	private ViewEdit viewEdit = new ViewEdit();
	private ModelHome modelHome;
	private String url;
	
	public static SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	public static DecimalFormat amountFormatter = new DecimalFormat("###,##0.00");
	public static DecimalFormat dmFormatter = new DecimalFormat("###0");
	
	private Member member = new Member();
	
	
	public ControllerHome(ViewHome v, ModelHome m, String url){
		viewHome = v;
		modelHome = m;
		this.url = url;
		
		modelHome.fetchData(viewHome.getTableModel(), Util.getMaxRow(viewHome.getMaxRow()));
		viewHome.setTitle("Family360 - Home | " + url);
		viewHome.addListener(this, this, this, this, this);
		viewNew.addListener(this, this);
		viewRenew.addListener(this, this);
		viewEdit.addListener(this, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("menu_add")){
			viewNew.setLocationRelativeTo(viewHome);
			viewNew.clear();
			viewNew.setVisible(true);
		}
		if(e.getActionCommand().equals("menu_renew")){
			viewRenew.setLocationRelativeTo(viewHome);
			viewRenew.clear();
			
			viewRenew.setMember( 
					modelHome.getInfos(
							Integer.parseInt(
									member.getCODE()
							), member
					) 
			);
			
			//System.out.println("RENEWING CODE: " + member.getCODE());
			viewRenew.setVisible(true);
		}
		if(e.getActionCommand().equals("print_cis_exist")){
			Printer.print("C:\\GED\\cis_ind.doc");
		}
		if(e.getActionCommand().equals("print_cis_ind")){
			Printer.print("C:\\GED\\cis_ind.doc");
		}
		if(e.getActionCommand().equals("print_cis_grp")){
			Printer.print("C:\\GED\\cis_grp.doc");
		}
		if(e.getActionCommand().equals("menu_edit")){
			viewEdit.setLocationRelativeTo(viewHome);
			viewEdit.clear();
			viewEdit.setMember(
					modelHome.getInfos(
							Integer.parseInt(
									member.getCODE()
							), member));
			viewEdit.setVisible(true);
		}
		if(e.getActionCommand().equals("menu_backup")){
			Util.backup(url, "D:/ged_backup/GED.mdb");
			Util.backup(url, "\\\\ged01\\Documents\\JIM\\GED.mdb");
		}
		if(e.getActionCommand().equals("add")){
			modelHome.add(viewNew, viewHome.getTableModel(), viewNew.getInfos());
		}
		if(e.getActionCommand().equals("renew")){
			modelHome.renew(viewRenew, viewHome.getTableModel(), viewRenew.getInfos());
		}
		if(e.getActionCommand().equals("edit")){
			modelHome.update(viewEdit, member.getCODE(), viewEdit.getInfos());
		}
		if(e.getActionCommand().equals("maxRow") || e.getActionCommand().equals("btn_maxRow")){ //Maximum row update request
			viewHome.setTitle("Family360 - Please wait while populating for data...");
			viewHome.getTableModel().setRowCount(0); //Reset table model
			if(Util.getMaxRow(viewHome.getMaxRow()) < 0){ //entered a -negative value, reset to 50
				modelHome.fetchData(viewHome.getTableModel(), 50);
			}else{
				modelHome.fetchData(viewHome.getTableModel(), 
					Util.getMaxRow(viewHome.getMaxRow())); //update the table model w/ max row
			}
			viewHome.setTitle("Family360 - Home | " + url);
			viewHome.setLblReturnedRow("");
		}
		if(e.getActionCommand().equals("search") || e.getActionCommand().equals("btn_search")){
			viewHome.setTitle("Family360 - Please wait while searching for data...");
			viewHome.getTableModel().setRowCount(0); //Reset table model
			viewHome.getTxtSearch().selectAll();
			modelHome.search(viewHome.getTableModel(),
					Util.getSearchField(viewHome.getSearchField()), viewHome.getTxtSearch().getText().trim(), viewHome);
			viewHome.setTitle("Family360 - Home | " + url);
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent me) {
		modelHome.handlePopMenu(me, viewHome);
		modelHome.rowSelected(me, viewHome, member);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusGained(FocusEvent e) {
		if(e.getSource() instanceof JTextField){
			((JTextField)e.getSource()).selectAll();
		}
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		modelHome.rowSelected(ke, viewHome, member);
		
		if(ke.getKeyCode() == KeyEvent.VK_F5){
			viewHome.setTitle("Family360 - Please wait while populating for data...");
			viewHome.getTableModel().setRowCount(0); //Reset table model
			if(Util.getMaxRow(viewHome.getMaxRow()) < 0){ //entered a -negative value, reset to 50
				modelHome.fetchData(viewHome.getTableModel(), 50);
			}else{
				modelHome.fetchData(viewHome.getTableModel(), 
					Util.getMaxRow(viewHome.getMaxRow())); //update the table model w/ max row
			}
			viewHome.setTitle("Family360 - Home | " + url);
			viewHome.setLblReturnedRow("");
		}
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		
	}

	@Override
	public void keyTyped(KeyEvent ke) {
		
		
	}

	@Override
	public void valueChanged(ListSelectionEvent lse) {
		
	}
	
	

}
