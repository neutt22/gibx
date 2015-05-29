package com.guevent.gibx.jim.view;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;

import com.guevent.gibx.jim.Util;
import com.guevent.gibx.jim.model.FormRenderer;

public class ViewNew extends JDialog{
	
	private static final long serialVersionUID = 1L;
	
	private JTextField txtAssured1 = new JTextField();
	private JTextField txtAssured2 = new JTextField();
	private DateField calInception1 = CalendarFactory.createDateField();
	private JTextField txtInception2 = new JTextField();
	private DateField calExpiry1 = CalendarFactory.createDateField();
	private JTextField txtExpiry2 = new JTextField();
	private JTextField txtCOI = new JTextField("PIP.SA 2015 00");
	private JTextArea txtBeneficiaries = new JTextArea("NAME OF BENEFICIARIES / RELATIONSHIP\n\n");
	private JTextArea txtDependents1 = new JTextArea("NAME OF FAMILY MEMBERS / RELATIONSHIP\n\n");
	private JTextArea txtDependents2 = new JTextArea("NAME OF FAMILY MEMBERS / RELATIONSHIP\n\n");
	private JTextField txtAddress = new JTextField();
	private JTextField txtReferral = new JTextField("0.00");
	private JTextField txtTelephone = new JTextField();
	private JTextField txtSumAssured = new JTextField("440,000.00");
	private JTextField txtBasicPrem = new JTextField("1,500.00");
	private JTextField txtDStamps = new JTextField("0.00");
	private JTextField txtVAT = new JTextField("0.00");
	private JTextField txtOthers = new JTextField("0.00");
	private JTextField txtTotal = new JTextField("1,500.00");
	private JTextField txtPrincipalName = new JTextField("Malayan Insurance Company");
	private JTextField txtInsuranceLine = new JTextField("PA Individual");
	private JTextArea txtNote = new JTextArea("Daily Hospital Income Benefit P1,000.00");
	private JTextField txtCompany = new JTextField();
	
	private JButton btnAdd = new JButton("Add");
	
	private FormRenderer formRenderer;
	private Font f = new Font("Arial", Font.PLAIN, 12);
	private JLabel lblError = new JLabel("<html><font color=red>*on err: Php0.00</font></html>");
	private Font fSmall = new Font("Arial Narrow", Font.ITALIC, 11);
	private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		
	private JPanel mainPane = new JPanel(new MigLayout("", "[grow]", "[grow]"));
	private JPanel pane = new JPanel(new MigLayout("", "[grow]", "[grow]"));
	private JPanel pane2 = new JPanel(new MigLayout("", "[grow]", "[grow]"));
	private JPanel pane3 = new JPanel(new MigLayout("", "[grow]", "[grow]"));

	public ViewNew(){
		setTitle("Family360 - New Member OVEJERA");
		calInception1.setDateFormat(dateFormat);
		calExpiry1.setDateFormat(dateFormat);
		pane.add(new JLabel("COI:"), "right");
		pane.add(txtCOI, "w 150, wrap");
		pane.add(new JLabel("ASSURED: "), "right");
		pane.add(txtAssured1, "w 250, wrap");
		pane.add(new JLabel("ASSURED2: "), "right");
		pane.add(txtAssured2, "w 250, wrap");
		pane.add(new JLabel("INCEPTION: "), "right");
		pane.add(calInception1, "w 120, split 2");
		pane.add(txtInception2, "w 120, wrap");
		pane.add(new JLabel("EXPIRATION: "), "right");
		pane.add(calExpiry1, "w 120, split 2");
		pane.add(txtExpiry2, "w 120, wrap");
		
		pane.add(new JSeparator(), "growx, wrap, span");
		
		txtBeneficiaries.setFont(f);
		txtBeneficiaries.setWrapStyleWord(true);
		txtDependents1.setFont(f);
		txtDependents1.setWrapStyleWord(true);
		txtDependents2.setFont(f);
		txtDependents2.setWrapStyleWord(true);
		txtNote.setFont(f);
		txtNote.setWrapStyleWord(true);
		pane2.add(new JLabel("BENEFICIARIES: "));
		pane2.add(new JLabel("FAMILY MEMBERS: "), ", wrap");
		pane2.add(new JScrollPane(txtBeneficiaries), "grow, h 190, w 260!");
		pane2.add(new JScrollPane(txtDependents1), "grow, h 190, w 440!");
		pane2.add(new JScrollPane(txtDependents2), "grow, h 190, w 440!, wrap");
		
		pane2.add(new JSeparator(), "growx, wrap, span");
		
		pane3.add(new JLabel("ADDRESS:"), "right");
		pane3.add(txtAddress, "growx, w 450, span 3");
		pane3.add(new JLabel("REFERRAL:"), "right");
		pane3.add(txtReferral, "growx, w 100, wrap");
		pane3.add(new JLabel("TELEPHONE:"), "right");
		pane3.add(txtTelephone, "w 150");
		pane3.add(new JLabel("PRINCIPAL:"), "right");
		pane3.add(txtPrincipalName, "w 150, wrap");
		pane3.add(new JLabel("SUM ASSURED:"), "right");
		pane3.add(txtSumAssured, "w 140");
		pane3.add(new JLabel("INSURANCE LINE:"), "right");
		pane3.add(txtInsuranceLine, "w 150, wrap");
		pane3.add(new JLabel("BASIC PREMIUM:"), "right");
		pane3.add(txtBasicPrem, "w 120");
		pane3.add(new JLabel("NOTE:"), "right");
		pane3.add(new JScrollPane(txtNote), "grow, h 50, span 2 4, wrap");
		pane3.add(new JLabel("DStamps:"), "right");
		pane3.add(txtDStamps, "w 50, wrap");
		pane3.add(new JLabel("VAT:"), "right");
		pane3.add(txtVAT, "w 50, wrap");
		pane3.add(new JLabel("OTHERS:"), "right");
		pane3.add(txtOthers, "w 120, split");
		pane3.add(lblError, "wrap");
		pane3.add(new JLabel("TOTAL:"), "right");
		pane3.add(txtTotal, "w 120");
		pane3.add(new JLabel("REFERENCE:"), "right");
		pane3.add(txtCompany, "w 150, wrap");
		pane3.add(btnAdd, "span 2, growx");
		
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		mainPane.add(pane, "wrap");
		mainPane.add(pane2, "wrap");
		mainPane.add(pane3);
		add(mainPane);
		
		pack();
		setModal(true);
		formRenderer = new FormRenderer(this, "new");
		addAmountFieldsListener(formRenderer);
		lblError.setFont(fSmall);
		txtTotal.setEditable(false);
	}
	
	public void addAmountFieldsListener(FocusListener fl){
		txtSumAssured.addFocusListener(fl);
		txtBasicPrem.addFocusListener(fl);
		txtOthers.addFocusListener(fl);
		txtTotal.addFocusListener(fl);
	}
	
	public void addListener(ActionListener al, FocusListener fl){
		btnAdd.setActionCommand("add");
		btnAdd.addActionListener(al);
		
		txtCOI.addFocusListener(fl);
		txtAssured1.addFocusListener(fl);
		txtAssured2.addFocusListener(fl);
		txtInception2.addFocusListener(fl);
		txtExpiry2.addFocusListener(fl);
		txtAddress.addFocusListener(fl);
		txtReferral.addFocusListener(fl);
		txtTelephone.addFocusListener(fl);
		txtCompany.addFocusListener(fl);
	}
	
	public String[] getInfos(){
		String infos[] = new String[24];
		infos[0] = txtCOI.getText();
		infos[1] = txtAssured1.getText();
		infos[2] = txtAssured2.getText();
		infos[3] = Util.dateFormatter.format((Date)calInception1.getValue());
		infos[4] = txtInception2.getText();
		infos[5] = Util.dateFormatter.format((Date)calExpiry1.getValue());
		infos[6] = txtExpiry2.getText();
		infos[7] = "0"; //Debit_memo
		infos[8] = txtBeneficiaries.getText();
		infos[9] = txtDependents1.getText();
		infos[10] = txtDependents2.getText();
		infos[11] = txtAddress.getText();
		infos[12] = txtReferral.getText();
		infos[13] = txtTelephone.getText();
		infos[14] = ""; //MICO_Policy
		infos[15] = txtSumAssured.getText();
		infos[16] = txtPrincipalName.getText();
		infos[17] = txtBasicPrem.getText();
		infos[18] = txtInsuranceLine.getText();
		infos[19] = txtDStamps.getText();
		infos[20] = txtNote.getText();
		infos[21] = txtVAT.getText();
		infos[22] = txtOthers.getText();
		infos[23] = txtCompany.getText();
		
		return infos;
	}
	
	
	//Form is set to default
	public void clear(){
		txtCOI.setText("PIP.SA 2015 00");
		txtAssured1.setText("GED ");
		txtAssured2.setText("");
		txtBeneficiaries.setText("NAME OF BENEFICIARIES / RELATIONSHIP\n\n");
		txtDependents1.setText("NAME OF FAMILY MEMBERS / RELATIONSHIP\n\n");
		txtDependents2.setText("NAME OF FAMILY MEMBERS / RELATIONSHIP\n\n");
		txtAddress.setText("");
		txtTelephone.setText("");
	}
	
	public String getSum() { return txtSumAssured.getText(); }
	public String getBprem() { return txtBasicPrem.getText(); }
	public String getOthers() { return txtOthers.getText(); }
	
	public void setSum(String s){
		txtSumAssured.setText(s);
	}
	
	public void setBprem(String b){
		txtBasicPrem.setText(b);
	}
	
	public void setOthers(String t){
		txtOthers.setText(t);
	}
	
	public void setTotal(String t){
		txtTotal.setText(t);
	}

}
