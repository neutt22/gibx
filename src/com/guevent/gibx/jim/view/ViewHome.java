package com.guevent.gibx.jim.view;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

import com.guevent.gibx.jim.Util;

public class ViewHome extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel mainPane;
	
	private JPopupMenu popMenu = new JPopupMenu();
	private JMenuItem mnuNew = new JMenuItem("New");
	private JMenuItem mnuRenew = new JMenuItem("Renew");
	private JMenuItem mnuEdit = new JMenuItem("Edit");
	private JMenuItem mnuDelete = new JMenuItem("Delete");
	private JMenu mnuPrint = new JMenu("Print");
	private JMenu mnuCIS = new JMenu("CIS");
	private JMenuItem mnuCIS_Exist = new JMenuItem("This");
	private JMenuItem mnuCIS_Ind = new JMenuItem("Individual");
	private JMenuItem mnuCIS_Grp = new JMenuItem("Group");
	private JMenuItem mnuExport = new JMenuItem("Export");
	private JMenuItem mnuBackup = new JMenuItem("Backup");
	
	private JButton btnSearch = new JButton("Search");
	private JLabel lblReturnedRow = new JLabel("");
	private JButton btnRefresh = new JButton("Refresh");
	
	private ImageIcon logo = new ImageIcon(getClass().getResource("/res/logo.png"));
	private Cursor textCursor = new Cursor(Cursor.TEXT_CURSOR);
	
	public ViewHome(){
		super();
		setIconImage(logo.getImage());
		setTitle("Family360 - Please wait while loading data...");
		init();
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}
	
	private JTextField txtAssured = new JTextField();
	private JTextField txtCOI = new JTextField();
	private JTextField txtPolicy = new JTextField();
	private JTextField txtDM = new JTextField();
	private JTextField txtCompany = new JTextField();
	private JTextField txtInception = new JTextField();
	private JTextField txtExpiry = new JTextField();
	private JTextField txtAddress = new JTextField();
	private JTextField txtTelephone = new JTextField();
	private JTextField txtReferral = new JTextField();
	private JTextField txtSumAssured = new JTextField();
	private JTextField txtBasicPremium = new JTextField();
	private JTextField txtOthers = new JTextField();
	private JTextField txtTotal = new JTextField();
	private JTextArea txtDependents = new JTextArea();
	private JTextArea txtBeneficiaries = new JTextArea();
	private JTextArea txtNote = new JTextArea();
	private JTextField txtSearch = new JTextField();
	
	private JTable table;
	private DefaultTableModel tableModel;
	private JComboBox<String> cboSearchCat = new JComboBox<String>(Util.getSearchCat());
	
	private Font f = new Font("Arial", Font.PLAIN, 12);
	
	
	private void init(){
		mainPane = new JPanel(new MigLayout("", "[grow]", "[grow]"));
		
		JPanel topPane = new JPanel(new MigLayout("", "[grow]", "[grow]"));
		topPane.add(new JLabel("ASSURED: "), "right");
		topPane.add(txtAssured, "w 200, growx"); txtAssured.setCursor(textCursor);
		topPane.add(new JLabel("COI: "), "right");
		topPane.add(txtCOI, "w 140, growx"); txtCOI.setCursor(textCursor);
		topPane.add(new JLabel("MICO POLICY: "), "right");
		topPane.add(txtPolicy, "w 120, growx"); txtPolicy.setCursor(textCursor);
		topPane.add(new JLabel("DM: "), "right");
		topPane.add(txtDM, "w 100, growx, wrap");txtDM.setCursor(textCursor);
		topPane.add(new JLabel("INCEPTION: "), "right");
		topPane.add(txtInception, "w 90"); txtInception.setCursor(textCursor);
		topPane.add(new JLabel("TELEPHONE: "), "right");
		topPane.add(txtTelephone, "growx"); txtTelephone.setCursor(textCursor);
		topPane.add(new JLabel("ADDRESS: "), "right");
		topPane.add(txtAddress, "growx, wrap, span 4"); txtAddress.setCursor(textCursor);
		topPane.add(new JLabel("EXPIRATION: "), "right");
		topPane.add(txtExpiry, "w 90"); txtExpiry.setCursor(textCursor);
		topPane.add(new JLabel("REFERRAL FEE: "), "right");
		topPane.add(txtReferral, "w 90"); txtReferral.setCursor(textCursor);
		topPane.add(new JLabel("COMPANY: "), "right");
		topPane.add(txtCompany, "w 120, growx, wrap"); txtCompany.setCursor(textCursor);
		topPane.add(new JSeparator(), "growx, wrap, span");
		topPane.add(new JLabel("SUM ASSURED:"), "right");
		topPane.add(txtSumAssured, "w 90"); txtSumAssured.setCursor(textCursor);
		topPane.add(new JLabel("BASIC PREMIUM:"), "right");
		topPane.add(txtBasicPremium, "w 90"); txtBasicPremium.setCursor(textCursor);
		topPane.add(new JLabel("OTHERS:"), "right");
		topPane.add(txtOthers, "w 90"); txtOthers.setCursor(textCursor);
		topPane.add(new JLabel("TOTAL:"), "right");
		topPane.add(txtTotal, "w 90, wrap"); txtTotal.setCursor(textCursor);
		topPane.add(new JSeparator(), "growx, wrap, span");
		
		JPanel midPane = new JPanel(new MigLayout("", "[grow]", "[grow]"));
		txtDependents.setFont(f); txtDependents.setCursor(textCursor);
		txtDependents.setWrapStyleWord(true);
		txtBeneficiaries.setFont(f); txtBeneficiaries.setCursor(textCursor);
		txtBeneficiaries.setWrapStyleWord(true);
		txtNote.setFont(f); txtNote.setCursor(textCursor);
		txtNote.setWrapStyleWord(true);
		midPane.add(new JLabel("BENEFICIARIES:"), "");
		midPane.add(new JLabel("DEPENDENTS:"), "");
		midPane.add(new JLabel("NOTE:"), "wrap");
		midPane.add(new JScrollPane(txtBeneficiaries), "grow, h 190, w 240");
		midPane.add(new JScrollPane(txtDependents), "grow, h 190, w 440");
		midPane.add(new JScrollPane(txtNote), "grow, h 60!, wrap, w 240, top");
		midPane.add(new JLabel("SEARCH BY:"), "split 3");
		midPane.add(cboSearchCat);
		midPane.add(txtSearch, "w 150");
		midPane.add(btnSearch, "split 2");
		midPane.add(lblReturnedRow, "wrap");
		
		//for F5 key reload
		this.setFocusable(true);
		
		tableModel = new DefaultTableModel(new Object[][]{}, new String[]{"CODE", "COI", "Assured", 
				"ADDR", "TEL", "Inception", "Expiry", "Beneficiaries", "Dependents", "SUM ASSURED", 
				"BPREM", "OTHERS", "NOTE", "REF. FEE", "DM", "POLICY", "COMPANY"} ){ 
			private static final long serialVersionUID = 1L; 
			public boolean isCellEditable(int row, int col){
				return false;
			}
		}; 
		table = new JTable(tableModel);
		table.setAutoCreateRowSorter(true);
		Util.formatDateCell(table.getColumnModel());
		Util.alignCell(table.getColumnModel());
		Util.formatAmtCell(table.getColumnModel());
		Util.formatDMCell(table.getColumnModel());
		
		mainPane.add(topPane, "wrap");
		mainPane.add(midPane, "wrap");
		mainPane.add(new JScrollPane(table), "span, grow, wrap");
		mainPane.add(getFooterPane());	
		/*****************************************************************/
		popMenu.add(mnuNew);
		popMenu.add(mnuRenew);
		popMenu.add(mnuEdit);
		popMenu.add(mnuDelete);
		popMenu.add(mnuBackup);
		mnuCIS.add(mnuCIS_Exist);
		mnuCIS.add(mnuCIS_Ind);
		mnuCIS.add(mnuCIS_Grp);
		mnuPrint.add(mnuCIS);
		popMenu.add(mnuPrint);
		popMenu.add(mnuExport);
		
		/*****************************************************************/
		add(mainPane);
		editbleFields(false);
	}
	
	private JTextField txtMaxRow = new JTextField("50");
	public JPanel getFooterPane(){
		JPanel footerPane = new JPanel(new MigLayout("", "[grow]", "[grow]"));
		footerPane.add(new JLabel("Max Row:"), "split");
		footerPane.add(txtMaxRow, "growx, w 50");
		footerPane.add(btnRefresh);
		footerPane.add(new JLabel("*Type 0 for all members"));
		return footerPane;
	}
	
	public DefaultTableModel getTableModel(){ return tableModel; }
	public JPopupMenu getPopupMenu() { return popMenu; }
	
	public void editbleFields(boolean bol){
		txtAssured.setEditable(bol);
		txtCOI.setEditable(bol);
		txtPolicy.setEditable(bol);
		txtDM.setEditable(bol);
		txtInception.setEditable(bol);
		txtExpiry.setEditable(bol);
		txtAddress.setEditable(bol);
		txtTelephone.setEditable(bol);
		txtReferral.setEditable(bol);
		txtSumAssured.setEditable(bol);
		txtBasicPremium.setEditable(bol);
		txtOthers.setEditable(bol);
		txtTotal.setEditable(bol);
//		txtNote.setEditable(bol);
//		txtDependents.setEditable(bol);
//		txtBeneficiaries.setEditable(bol);
		
		txtCompany.setEditable(bol);
	}
	
	public void setLblReturnedRow(String s){
		lblReturnedRow.setText(s);
	}
	
	public void addListener(MouseListener ml, 
			ActionListener al, 
			FocusListener fl, 
			KeyListener kl,
			ListSelectionListener ll){
		
		table.addMouseListener(ml);
		table.addKeyListener(kl);
		
		mnuNew.setActionCommand("menu_add");
		mnuNew.addActionListener(al);
		
		mnuRenew.setActionCommand("menu_renew");
		mnuRenew.addActionListener(al);
		
		mnuEdit.setActionCommand("menu_edit");
		mnuEdit.addActionListener(al);
		
		mnuDelete.setActionCommand("menu_delete");
		mnuDelete.addActionListener(al);
		
		mnuPrint.setActionCommand("menu_print");
		mnuPrint.addActionListener(al);
		
		mnuCIS_Exist.setActionCommand("print_cis_exist");
		mnuCIS_Exist.addActionListener(al);
		
		mnuCIS_Ind.setActionCommand("print_cis_ind");
		mnuCIS_Ind.addActionListener(al);
		
		mnuCIS_Grp.setActionCommand("print_cis_grp");
		mnuCIS_Grp.addActionListener(al);
		
		mnuExport.setActionCommand("menu_export");
		mnuPrint.addActionListener(al);
		
		mnuBackup.setActionCommand("menu_backup");
		mnuBackup.addActionListener(al);
		
		txtAssured.addFocusListener(fl);
		txtCOI.addFocusListener(fl);
		txtPolicy.addFocusListener(fl);
		txtDM.addFocusListener(fl);
		txtInception.addFocusListener(fl);
		txtExpiry.addFocusListener(fl);
		txtAddress.addFocusListener(fl);
		txtTelephone.addFocusListener(fl);
		txtReferral.addFocusListener(fl);
		txtSumAssured.addFocusListener(fl);
		txtBasicPremium.addFocusListener(fl);
		txtOthers.addFocusListener(fl);
		txtTotal.addFocusListener(fl);
		txtCompany.addFocusListener(fl);
		txtSearch.addFocusListener(fl);
		
		txtSearch.setActionCommand("search");
		txtSearch.addActionListener(al);
		
		btnSearch.setActionCommand("btn_search");
		btnSearch.addActionListener(al);
		
		btnRefresh.setActionCommand("btn_maxRow");
		btnRefresh.addActionListener(al);
		
		txtMaxRow.setActionCommand("maxRow");
		txtMaxRow.addActionListener(al);
		
		//for F5 key reload
		this.addKeyListener(kl);
		this.addMouseListener(ml);
	}
	
	/**============SETTERS==============================================================**/
	public void setAssured(String assured){ txtAssured.setText(assured); }
	public void setCOI(String coi){ txtCOI.setText(coi); }
	public void setPolicy(String policy){ txtPolicy.setText(policy); }
	public void setDM(String dm){ txtDM.setText(dm); }
	public void setInception(String inception){ txtInception.setText(inception); }
	public void setExpiry(String expiry){ txtExpiry.setText(expiry); }
	public void setAddress(String address){ txtAddress.setText(address); }
	public void setTelephone(String telephone){ txtTelephone.setText(telephone); }
	public void setReferral(String referral){ txtReferral.setText(referral); }
	public void setSumAssured(String sum){ txtSumAssured.setText(sum); }
	public void setBasicPremium(String bprem){ txtBasicPremium.setText(bprem); }
	public void setOthers(String others){ txtOthers.setText(others); }
	public void setTotal(String total){ txtTotal.setText(total); }
	public void setDependents(String dependents){ txtDependents.setText(dependents); }
	public void setBeneficiaries(String benef){ txtBeneficiaries.setText(benef); }
	public void setCompany(String co) { txtCompany.setText(co); }
	public void setNote(String note) { txtNote.setText(note); }
	
	/**============GETTERS==============================================================**/
	public String getMaxRow() { return txtMaxRow.getText(); }
	public JTextField getTxtSearch() { return txtSearch; }
	public String getSearchField() { return cboSearchCat.getSelectedItem().toString(); }
	
}
