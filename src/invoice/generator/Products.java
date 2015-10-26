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

	public class Products extends JFrame {
		
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		DatabaseConnection db = new DatabaseConnection();

		private JPanel contentPane;
		private JTextField textProd_Name;
		private JTextField textPrice;
		private JTextField textUm_Symbol;
		private JTextField textVat_Rate;
		private JComboBox comboProd_Name;
		private JTextArea textDescription;
		private final ButtonGroup buttonGroupProduct = new ButtonGroup();
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
						Products frame = new Products();
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
		public Products() {
			
			setAlwaysOnTop(true);
			setResizable(false);
			setTitle("Products");
			setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			setBounds(100, 100, 600, 480);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBounds(0, 35, 584, 407);
			contentPane.add(tabbedPane);
			
			JPanel panelProduct = new JPanel();
			tabbedPane.addTab("Products", null, panelProduct, "Add, Edit or Remove the products.");
			tabbedPane.setEnabledAt(0, true);
			panelProduct.setLayout(null);
			
			JLabel lblProductName = new JLabel("Product Name");
			lblProductName.setBounds(10, 38, 83, 25);
			panelProduct.add(lblProductName);
			
			textProd_Name = new JTextField();
			textProd_Name.setEditable(false);
			textProd_Name.setBounds(140, 40, 60, 20);
			panelProduct.add(textProd_Name);
			textProd_Name.setColumns(10);
			
			JLabel lblForPrice = new JLabel("Price");
			lblForPrice.setBounds(10, 74, 83, 25);
			panelProduct.add(lblForPrice);
			
			textPrice = new JTextField();
			textPrice.setEditable(false);
			textPrice.setBounds(140, 76, 120, 20);
			panelProduct.add(textPrice);
			textPrice.setColumns(10);
			
			JLabel lblForUmSymbol = new JLabel("umSymbol");
			lblForUmSymbol.setBounds(10, 110, 83, 25);
			panelProduct.add(lblForUmSymbol);
			
			textUm_Symbol = new JTextField();
			textUm_Symbol.setEditable(false);
			textUm_Symbol.setBounds(140, 112, 120, 20);
			panelProduct.add(textUm_Symbol);
			textUm_Symbol.setColumns(10);
			
			JLabel lblForVat = new JLabel("Vat");
			lblForVat.setBounds(10, 146, 83, 25);
			panelProduct.add(lblForVat);
			
			textVat_Rate = new JTextField();
			textVat_Rate.setEditable(false);
			textVat_Rate.setBounds(140, 148, 120, 20);
			panelProduct.add(textVat_Rate);
			textVat_Rate.setColumns(11);
			
			JLabel lblDescription = new JLabel("Product Detail");
			lblDescription.setBounds(10, 185, 120, 25);
			panelProduct.add(lblDescription);
			
			textDescription = new JTextArea();
			textDescription.setLineWrap(true);
			textDescription.setWrapStyleWord(true);
			textDescription.setEditable(false);
			textDescription.setBounds(140, 187, 400, 80);
			panelProduct.add(textDescription);
			
			rdbtnAdd = new JRadioButton("Add");
			buttonGroupProduct.add(rdbtnAdd);
			rdbtnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					comboProd_Name.setEnabled(false);
					textUm_Symbol.setEditable(true);
					textPrice.setEditable(true);
					textProd_Name.setEditable(true);
					textDescription.setEditable(true);
					textVat_Rate.setEditable(true);
					btnAdd.setEnabled(true);
					btnRemove.setEnabled(false);
					btnEdit.setEnabled(false);
					btnReset.setEnabled(true);
				}
			});
			rdbtnAdd.setBounds(140, 7, 109, 23);
			panelProduct.add(rdbtnAdd);
			
			rdbtnEdit = new JRadioButton("Edit");
			rdbtnEdit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					comboProd_Name.setEnabled(true);
					textProd_Name.setEditable(false);
					textUm_Symbol.setEditable(true);
					textPrice.setEditable(true);
					textDescription.setEditable(true);
					textVat_Rate.setEditable(true);
					btnAdd.setEnabled(false);
					btnRemove.setEnabled(false);
					btnReset.setEnabled(true);
					btnEdit.setEnabled(true);
				}
			});
			buttonGroupProduct.add(rdbtnEdit);
			rdbtnEdit.setBounds(251, 7, 109, 23);
			panelProduct.add(rdbtnEdit);
			
			JLabel lblChoosePackageId = new JLabel("Choose Product Name");
			lblChoosePackageId.setBounds(225, 43, 135, 14);
			panelProduct.add(lblChoosePackageId);
			
			comboProd_Name = new JComboBox();
			comboProd_Name.addPopupMenuListener(new PopupMenuListener() {
				public void popupMenuCanceled(PopupMenuEvent arg0) {
				}
				public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
					String selectedPID = comboProd_Name.getSelectedItem().toString();
					try{
						Connection con = db.createConnection();
						PreparedStatement statement = con.prepareStatement("select * from product where productName=?");
						statement.setString(1,selectedPID);
						ResultSet rs = statement.executeQuery();
						while(rs.next()){
							textProd_Name.setText(rs.getString(1));
							textUm_Symbol.setText(rs.getString(2));
							textPrice.setText(Double.toString(rs.getDouble(3)));
							textVat_Rate.setText(Double.toString(rs.getDouble(4)));
							textDescription.setText(rs.getString(5));
						}
						con.close();
					}catch(Exception e){
						JOptionPane.showMessageDialog(comboProd_Name, e.getMessage());
					}
				}
				public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
				}
			});
			comboProd_Name.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent arg0) {
					try{
						Connection con = db.createConnection();
						Statement statement = con.createStatement();
						ResultSet rs = statement.executeQuery("select productName from product");
						comboProd_Name.removeAllItems();			//remove previous list of items before updating the list with new items.
						while(rs.next()){
							comboProd_Name.addItem(rs.getString("productName"));
						}
						con.close();
					}catch(Exception e){
						JOptionPane.showMessageDialog(comboProd_Name,e.getMessage());
					}
				}
			});
			comboProd_Name.setEnabled(false);
			comboProd_Name.setBounds(362, 40, 60, 20);
			panelProduct.add(comboProd_Name);
			
			rdbtnRemove = new JRadioButton("Remove");
			rdbtnRemove.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					comboProd_Name.setEnabled(true);
					textProd_Name.setEditable(false);
					textUm_Symbol.setEditable(false);
					textPrice.setEditable(false);
					textDescription.setEditable(false);
					textVat_Rate.setEditable(false);
					btnAdd.setEnabled(false);
					btnEdit.setEnabled(false);
					btnReset.setEnabled(false);
					btnRemove.setEnabled(true);
				}
			});
			buttonGroupProduct.add(rdbtnRemove);
			rdbtnRemove.setBounds(364, 7, 109, 23);
			panelProduct.add(rdbtnRemove);
			
			btnAdd = new JButton("ADD");
			btnAdd.setEnabled(false);
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try{
						Connection con  = db.createConnection();
						PreparedStatement st = con.prepareStatement("insert into product values(?,?,?,?,?)");
						st.setString(1,textProd_Name.getText());
						st.setString(2,textUm_Symbol.getText());
						st.setDouble(3,Double.parseDouble(textPrice.getText()));
						st.setDouble(4,Double.parseDouble(textVat_Rate.getText()));
						st.setString(5,textDescription.getText());
						
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
			btnAdd.setBounds(10, 268, 89, 23);
			panelProduct.add(btnAdd);
			
			btnEdit = new JButton("SAVE");
			btnEdit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try{
						Connection con = db.createConnection();
						String insertStatement = "update product set umSymbol=?,price=?,vatrate=?,description=? where productname=?";
						PreparedStatement st = con.prepareStatement(insertStatement);
						st.setString(1,textUm_Symbol.getText());
						st.setDouble(2,Double.parseDouble(textPrice.getText()));
						st.setDouble(3,Double.parseDouble(textVat_Rate.getText()));
						st.setString(4,textDescription.getText());
						
						st.setString(5,comboProd_Name.getSelectedItem().toString());
						int status = st.executeUpdate();
						if(status>0)
							JOptionPane.showMessageDialog(btnEdit,comboProd_Name.getSelectedItem().toString()+" Product Updated Successfully");
						else
							JOptionPane.showMessageDialog(btnEdit,"Not Inserted, Try Again!");
						con.close();
					}catch(Exception e){
						JOptionPane.showMessageDialog(btnEdit, e.getMessage());
					}
				}
			});
			btnEdit.setEnabled(false);
			btnEdit.setBounds(140, 268, 89, 23);
			panelProduct.add(btnEdit);
			
			btnRemove = new JButton("REMOVE");
			btnRemove.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(comboProd_Name.getSelectedItem().toString().equals("")){
						JOptionPane.showConfirmDialog(btnRemove, "Choose a Product to delete");
					}
					else{
						try{
							Connection con  = db.createConnection();
							PreparedStatement st = con.prepareStatement("delete from product where productName=?");
							st.setString(1,comboProd_Name.getSelectedItem().toString());
							int c = st.executeUpdate();
							if(c>0)
								JOptionPane.showMessageDialog(btnRemove, "Product Removed Successfully");
							else
								JOptionPane.showMessageDialog(btnRemove, "Could not be removed! Try Again.");
							con.close();
						}catch(Exception ee){
							JOptionPane.showMessageDialog(btnRemove, ee.getMessage());
						}
					}
				}
			});
			btnRemove.setEnabled(false);
			btnRemove.setBounds(251, 268, 89, 23);
			panelProduct.add(btnRemove);
			
			btnReset = new JButton("RESET");
			btnReset.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					textUm_Symbol.setText("");
					textPrice.setText("");
					textVat_Rate.setText("");
					textDescription.setText("");
				}
			});
			btnReset.setEnabled(false);
			btnReset.setBounds(362, 268, 89, 23);
			panelProduct.add(btnReset);

	}}