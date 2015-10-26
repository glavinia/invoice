package invoice.generator;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import connection.DatabaseConnection;

public class Customer extends JFrame {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	DatabaseConnection db = new DatabaseConnection();
	private JPanel contentPane;
	private JTextField textCus_Name;
	private JButton btnAdd;
	private JButton btnReset;


	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customer frame = new Customer();
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
	public Customer() {
		
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
		
		JPanel panelPackage = new JPanel();
		tabbedPane.addTab("Customer", null, panelPackage, "Add the customer.");
		tabbedPane.setEnabledAt(0, true);
		panelPackage.setLayout(null);
		
		JLabel lblCus_Name = new JLabel("Customer Name");
		lblCus_Name.setBounds(117, 100, 120, 25);
		panelPackage.add(lblCus_Name);
	
	
	textCus_Name = new JTextField();
	textCus_Name.setEditable(true);
	textCus_Name.setBounds(250, 100, 140, 23);
	panelPackage.add(textCus_Name);
	textCus_Name.setColumns(10);
	
	
	btnAdd = new JButton("ADD");
	btnAdd.setEnabled(true);
	btnAdd.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			try{
				Connection con  = db.createConnection();
				PreparedStatement st = con.prepareStatement("insert into invoice (documentNo,customerName) values(?,?)");
				Random generator = new Random();
				int randomIndex = generator.nextInt(10);
				st.setString(1,Integer.toString(randomIndex));
				st.setString(2,textCus_Name.getText());
				int status = st.executeUpdate();
				if(status>0)
					JOptionPane.showMessageDialog(btnAdd, "Customer Added Successfully");
				else
					JOptionPane.showMessageDialog(btnAdd, "Customer Not Added! Try Again");
				con.close();
			}catch(Exception e){
				JOptionPane.showMessageDialog(btnAdd,e.getMessage());
			}
		}
	});
	btnAdd.setBounds(150, 200, 89, 23);
	panelPackage.add(btnAdd);
	
	btnReset = new JButton("RESET");
	btnReset.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			textCus_Name.setText("");
		}
	});
	btnReset.setEnabled(true);
	btnReset.setBounds(250, 200, 89, 23);
	panelPackage.add(btnReset);
}
	}