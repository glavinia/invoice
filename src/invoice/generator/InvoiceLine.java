package invoice.generator;

import connection.DatabaseConnection;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;

public class InvoiceLine extends JFrame {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	DatabaseConnection db = new DatabaseConnection();

	private JPanel contentPane;
	private JTextField textProd_Name;
	private JTextField textCus_Name;
	private JTextField textPrice;
	private JTextField textField_Qty;
	private JTextField textVat_Rate;
	private JComboBox comboProd_name;
	private JComboBox comboCus_name;
	private JTextArea textDescription;
	private final ButtonGroup buttonGroupInvoiceLine = new ButtonGroup();
	private JRadioButton rdbtnAdd;
	private JRadioButton rdbtnEdit;
	private JRadioButton rdbtnRemove;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnRemove;
	private JButton btnReset;
	private JRadioButton rdbtnAdd_1;
	private JRadioButton rdbtnRemove_1;
	private JRadioButton rdbtnEdit_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InvoiceLine frame = new InvoiceLine();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InvoiceLine() {
		
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle("Add Product to Customer");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 600, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 35, 584, 407);
		contentPane.add(tabbedPane);
		
		JPanel panelInvoiceLine = new JPanel();
		tabbedPane.addTab("Products", null, panelInvoiceLine, "Add the product to client in package.");
		tabbedPane.setEnabledAt(0, true);
		panelInvoiceLine.setLayout(null);
		
		JLabel lblProductName = new JLabel("Product Name");
		lblProductName.setBounds(10, 38, 83, 25);
		panelInvoiceLine.add(lblProductName);
		
		textProd_Name = new JTextField();
		textProd_Name.setEditable(false);
		textProd_Name.setBounds(140, 40, 60, 20);
		panelInvoiceLine.add(textProd_Name);
		textProd_Name.setColumns(10);
		
		
		textCus_Name = new JTextField();
		textCus_Name.setEditable(false);
		textCus_Name.setBounds(350, 100, 140, 23);
		panelInvoiceLine.add(textCus_Name);
		textCus_Name.setColumns(10);
		
		JLabel lblForPrice = new JLabel("Price");
		lblForPrice.setBounds(10, 74, 83, 25);
		panelInvoiceLine.add(lblForPrice);
		
		textPrice = new JTextField();
		textPrice.setEditable(false);
		textPrice.setBounds(140, 76, 120, 20);
		panelInvoiceLine.add(textPrice);
		textPrice.setColumns(10);
		
		JLabel lblForQty = new JLabel("Qty");
		lblForQty.setBounds(10, 110, 83, 25);
		panelInvoiceLine.add(lblForQty);
		
		textField_Qty = new JTextField();
		textField_Qty.setEditable(false);
		textField_Qty.setBounds(140, 112, 120, 20);
		panelInvoiceLine.add(textField_Qty);
		textField_Qty.setColumns(10);
		
		JLabel lblForVat = new JLabel("Vat Rate");
		lblForVat.setBounds(10, 146, 83, 25);
		panelInvoiceLine.add(lblForVat);
		
		textVat_Rate = new JTextField();
		textVat_Rate.setEditable(false);
		textVat_Rate.setBounds(140, 148, 120, 20);
		panelInvoiceLine.add(textVat_Rate);
		textVat_Rate.setColumns(11);
		
		JLabel lblDescriptionName = new JLabel("Product Detail");
		lblDescriptionName.setBounds(10, 185, 120, 25);
		panelInvoiceLine.add(lblDescriptionName);
		
		textDescription = new JTextArea();
		textDescription.setLineWrap(true);
		textDescription.setWrapStyleWord(true);
		textDescription.setEditable(false);
		textDescription.setBounds(140, 187, 400, 80);
		panelInvoiceLine.add(textDescription);
		
		
		rdbtnEdit = new JRadioButton("Customer");
		rdbtnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboProd_name.setEnabled(true);
				comboCus_name.setEnabled(true);
				textProd_Name.setEditable(false);
				textField_Qty.setEditable(true);
				textPrice.setEditable(false);
				textDescription.setEditable(false);
				textVat_Rate.setEditable(false);
				btnReset.setEnabled(true);
				btnAdd.setEnabled(true);
			}
		});
		buttonGroupInvoiceLine.add(rdbtnEdit);
		rdbtnEdit.setBounds(251, 7, 109, 23);
		panelInvoiceLine.add(rdbtnEdit);
		
		JLabel lblChoosePackageId = new JLabel("Choose Product Name");
		lblChoosePackageId.setBounds(225, 43, 135, 14);
		panelInvoiceLine.add(lblChoosePackageId);
		
		comboProd_name = new JComboBox();
		comboProd_name.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent arg0) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				String selectedPID = comboProd_name.getSelectedItem().toString();
				try{
					Connection con = db.createConnection();
					PreparedStatement statement = con.prepareStatement("select * from product where productName=?");
					statement.setString(1,selectedPID);
					ResultSet rs = statement.executeQuery();
					while(rs.next()){
						textProd_Name.setText(rs.getString(1));
						textPrice.setText(Double.toString(rs.getDouble(3)));
						textVat_Rate.setText(Double.toString(rs.getDouble(4)));
						textDescription.setText(rs.getString(5));
					}
					con.close();
				}catch(Exception e){
					JOptionPane.showMessageDialog(comboProd_name, e.getMessage());
				}
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
			}
		});
		comboProd_name.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				try{
					Connection con = db.createConnection();
					Statement statement = con.createStatement();
					ResultSet rs = statement.executeQuery("select productName from product");
					comboProd_name.removeAllItems();			//remove previous list of items before updating the list with new items.
					while(rs.next()){
						comboProd_name.addItem(rs.getString("productName"));
					}
					con.close();
				}catch(Exception e){
					JOptionPane.showMessageDialog(comboProd_name,e.getMessage());
				}
			}
		});
		comboProd_name.setEnabled(false);
		comboProd_name.setBounds(362, 40, 60, 20);//225, 43, 135, 14
		panelInvoiceLine.add(comboProd_name);
		
		/////////////////////////////////////
		
		
		JLabel lblCustomerName = new JLabel("Choose Customer Name");
		lblCustomerName.setBounds(270, 70, 140, 23);
		panelInvoiceLine.add(lblCustomerName);
		
		comboCus_name = new JComboBox();
		comboCus_name.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent arg0) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				String selectedPID = comboCus_name.getSelectedItem().toString();
				try{
					Connection con = db.createConnection();
					PreparedStatement statement = con.prepareStatement("select customerName from invoice where customerName=?");
					statement.setString(1,selectedPID);
					ResultSet rs = statement.executeQuery();
					while(rs.next()){
						textCus_Name.setText(rs.getString(1));
					}
					con.close();
				}catch(Exception e){
					JOptionPane.showMessageDialog(comboCus_name, e.getMessage());
				}
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
			}
		});
		comboCus_name.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				try{
					Connection con = db.createConnection();
					Statement statement = con.createStatement();
					ResultSet rs = statement.executeQuery("select customerName from invoice");
					comboCus_name.removeAllItems();			//remove previous list of items before updating the list with new items.
					while(rs.next()){
						comboCus_name.addItem(rs.getString("customerName"));
					}
					con.close();
				}catch(Exception e){
					JOptionPane.showMessageDialog(comboCus_name,e.getMessage());
				}
			}
		});
		comboCus_name.setEnabled(false);
		comboCus_name.setBounds(420, 70, 120, 20);
		panelInvoiceLine.add(comboCus_name);
		

		/////////////////		
		
		btnAdd = new JButton("ADD");
		btnAdd.setEnabled(false);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					Connection con  = db.createConnection();
					PreparedStatement st = con.prepareStatement("insert into pack values(?,?,?,?,?,?,?,?)");
					st.setString(1,textCus_Name.getText());
					st.setString(2,textProd_Name.getText());
					st.setDouble(3,Double.parseDouble(textPrice.getText()));
					st.setString(4,textField_Qty.getText());
					st.setDouble(5,Double.parseDouble(textVat_Rate.getText()));
					double lineNet,lineVat,lineTotal;
					lineNet = Double.parseDouble(textField_Qty.getText())*Double.parseDouble(textPrice.getText());
					st.setDouble(6, lineNet);
					lineVat = lineNet * Double.parseDouble(textVat_Rate.getText());
					st.setDouble(7, lineVat);
					lineTotal = lineNet + lineVat;
					st.setDouble(8, lineTotal);
					
					
					int status = st.executeUpdate();
					if(status>0)
						JOptionPane.showMessageDialog(btnAdd, "Product Added Successfully");
					else
						JOptionPane.showMessageDialog(btnAdd, "Product Not Added! Try Again");
					con.close();
				}catch(Exception e){
					JOptionPane.showMessageDialog(btnAdd,e.getMessage());
				}
			}
		});
		btnAdd.setBounds(150, 270, 89, 23);
		panelInvoiceLine.add(btnAdd);
		
		btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_Qty.setText("");
			}
		});
		btnReset.setEnabled(false);
		btnReset.setBounds(251, 270, 89, 23);
		panelInvoiceLine.add(btnReset);

}}