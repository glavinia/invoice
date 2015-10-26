package invoice.generator;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import javax.swing.table.*;
import com.mysql.jdbc.ResultSetMetaData;
import connection.DatabaseConnection;

public class InvoiceHeader extends JFrame{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	DatabaseConnection db = new DatabaseConnection();
	private JFrame frmInvoiceGenerator;
	private JTextField textCus_Name;
	private JTextField tfData;
	private JComboBox cusName;
	private JButton btnCheck;
	private double totalNetAmt=0.0,totalVatAmt = 0.0,totalAmt = 0.0;
	private double printLineNet,printLineVat,printLineTotal;
	private static InvoiceHeader window;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new InvoiceHeader();
					window.frmInvoiceGenerator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public InvoiceHeader() {
		initialize();
	}
	/**
	 * Initialise the contents of the frame.
	 */
	private void initialize() {
		
		frmInvoiceGenerator = new JFrame();
		//frmInvoiceGenerator.setVisible(true);
		frmInvoiceGenerator.setResizable(false);
		frmInvoiceGenerator.setTitle("Invoice Generator");
		frmInvoiceGenerator.setBounds(100, 100, 482, 453);
		frmInvoiceGenerator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmInvoiceGenerator.getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 0, 466, 54);
		frmInvoiceGenerator.getContentPane().add(panel_1);
		
		JLabel lblInvoiceGenerator = new JLabel("Invoice Generator");
		lblInvoiceGenerator.setFont(new Font("Tahoma", Font.BOLD, 28));
		panel_1.add(lblInvoiceGenerator);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(204, 204, 255));
		panel_2.setBounds(0, 65, 466, 318);
		frmInvoiceGenerator.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		textCus_Name = new JTextField();
		textCus_Name.setEditable(false);
		textCus_Name.setBounds(20, 100, 140, 23);
		panel_2.add(textCus_Name);
		textCus_Name.setColumns(10);
		
		tfData = new JTextField();
		tfData.setBounds(100, 150, 140, 23);
		panel_2.add(tfData);
		tfData.setColumns(10);
		
		JLabel lblData = new JLabel("Enter Data");
		lblData.setBounds(10, 150, 140, 23);
		panel_2.add(lblData);
		
		
		JLabel lblCustomerName = new JLabel("Choose Customer Name");
		lblCustomerName.setBounds(50, 70, 140, 23);
		panel_2.add(lblCustomerName);
		
		cusName = new JComboBox();
		cusName.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent arg0) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				String selectedPID = cusName.getSelectedItem().toString();
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
					JOptionPane.showMessageDialog(cusName, e.getMessage());
				}
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
			}
		});
		cusName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				try{
					Connection con = db.createConnection();
					Statement statement = con.createStatement();
					ResultSet rs = statement.executeQuery("select customerName from invoice");
					cusName.removeAllItems();			//remove previous list of items before updating the list with new items.
					while(rs.next()){
						cusName.addItem(rs.getString("customerName"));
					}
					con.close();
				}catch(Exception e){
					JOptionPane.showMessageDialog(cusName,e.getMessage());
				}
			}
		});
		cusName.setEnabled(true);
		cusName.setBounds(170, 100, 120, 20);
		panel_2.add(cusName);
		
	
	
	btnCheck = new JButton("Check");
	btnCheck.setEnabled(true);
	btnCheck.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(textCus_Name.getText().isEmpty()){
				JOptionPane.showMessageDialog(btnCheck, "Please select a customer!");
			} else if(tfData.getText().isEmpty()){
				JOptionPane.showMessageDialog(btnCheck, "Please select a data!");
			}	
			try{
				
				Connection con  = db.createConnection();
				String selectedPIP = cusName.getSelectedItem().toString();
				PreparedStatement statement = con.prepareStatement("select * from pack where customerName=?");
				statement.setString(1,selectedPIP);
				ResultSet rs = statement.executeQuery();
				System.out.println("\t\tLine Amount");
				while(rs.next()){
					totalNetAmt = totalNetAmt+rs.getDouble(6);
					totalVatAmt = totalVatAmt+rs.getDouble(7);
					printLineNet = rs.getDouble(6);
					printLineVat = rs.getDouble(7);
					printLineTotal = rs.getDouble(8);
					System.out.println(cusName.getSelectedItem().toString()+" "+  tfData.getText() +"\n"+ "Line net amt "+printLineNet +  "\nLine vat Amt : " + printLineVat+"\nLine total amt "+printLineTotal);
				}
				
				totalAmt = totalNetAmt+totalVatAmt;	
				Connection conn  = db.createConnection();
				String insertStatement = "update invoice set dateInvoiced=?,totalNetAmt=?,totalVatAmt=?,totalAmt=?,line=? where customerName=?";
				PreparedStatement st = conn.prepareStatement(insertStatement);	
				st.setString(1, tfData.getText());
				st.setDouble(2,totalNetAmt);
				st.setDouble(3,totalVatAmt);
				st.setDouble(4,totalAmt);
				st.setString(5,"LineNetAmt: "+String.valueOf(printLineNet)+ " LineVatAmt: " +String.valueOf(printLineVat)+ " LineTotalAmt" + printLineTotal);
				
				st.setString(6,cusName.getSelectedItem().toString());
				
				st.executeUpdate();
				int status = statement.executeUpdate();
				if(status>0)
					JOptionPane.showMessageDialog(btnCheck, "Product Added Successfully");
				else
					JOptionPane.showMessageDialog(btnCheck, "Product Not Added! Try Again");
				
				System.out.println(totalNetAmt +  "  " + totalVatAmt+"  "+totalAmt+ " " + rs.getString(6));
				con.close();
				conn.close();
			}catch(Exception e){
			}
			System.out.println("\t\tTotal Amount");
			System.out.println(cusName.getSelectedItem().toString()+" "+  tfData.getText() +"\n"+ "Total net amt "+totalNetAmt +  "\nTotal vat Amt : " + totalVatAmt+"\nTotal amt "+totalAmt);
		}
	});
	btnCheck.setBounds(10, 268, 89, 23);
	panel_2.add(btnCheck);
	
	
	JMenuBar menuBar = new JMenuBar();
	frmInvoiceGenerator.setJMenuBar(menuBar);
	
	JMenu mnFile = new JMenu("File");
	menuBar.add(mnFile);
	
	
	JMenuItem mntmProduct = new JMenuItem("Add Product");
	mntmProduct.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			Products.main(null);
		}
	});
	mnFile.add(mntmProduct);
	
	JMenuItem mntmCustomer = new JMenuItem("Add Customer");
	mntmCustomer.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			Customer.main(null);
		}
	});
	mnFile.add(mntmCustomer);
	
	JMenuItem mntmInvoiceLine = new JMenuItem("Add Products to Customer");
	mntmInvoiceLine.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			InvoiceLine.main(null);
		}
	});
	mnFile.add(mntmInvoiceLine);
	
	JMenuItem mntmExit = new JMenuItem("Exit");
	mntmExit.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			window.frmInvoiceGenerator.dispose();
		}
	});
	mnFile.add(mntmExit);
}
}