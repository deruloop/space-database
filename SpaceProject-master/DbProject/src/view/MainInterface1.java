package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;
import controller.Query;
import javax.swing.border.LineBorder;
import model.Filament;
import model.Position;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JScrollPane;

public class MainInterface1 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField idField5;

	/**
	 * Launch the application.
	 */
	public static void main(Connection con,String user_id,boolean type) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainInterface1 frame = new MainInterface1(con,user_id,type);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MainInterface1(Connection con,String user_id,boolean type) throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 798, 610);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//If user is an admin show admin functionalities
		if (type==true) {
		JLabel lblRegisterNewUsers = new JLabel("Register new users");
		lblRegisterNewUsers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegisterInterface RegIn=new RegisterInterface(con,user_id,type);
			    RegIn.setVisible(true);
			    RegIn.setResizable(false);
			    dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblRegisterNewUsers.setEnabled(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblRegisterNewUsers.setEnabled(false);
			}
		});
		lblRegisterNewUsers.setEnabled(false);
		lblRegisterNewUsers.setBounds(534, 2, 128, 35);
		contentPane.add(lblRegisterNewUsers);
		
		JLabel lblImportNewFile = new JLabel("Import new file");
		lblImportNewFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ImportInterface ImportIn;
				try {
					ImportIn = new ImportInterface(con,user_id,type);
					ImportIn.setVisible(true);
				    ImportIn.setResizable(false);
				    dispose();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblImportNewFile.setEnabled(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblImportNewFile.setEnabled(false);
			}
		});
		lblImportNewFile.setEnabled(false);
		lblImportNewFile.setBounds(422, 2, 103, 35);
		contentPane.add(lblImportNewFile);
		
		JLabel lblInsertData = new JLabel("Insert Data");
		lblInsertData.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				InsertInterface InsIn=new InsertInterface(con,user_id,type);
			    InsIn.setVisible(true);
			    InsIn.setResizable(false);
			    dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblInsertData.setEnabled(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblInsertData.setEnabled(false);
			}
		});
		lblInsertData.setEnabled(false);
		lblInsertData.setBounds(671, 2, 79, 35);
		contentPane.add(lblInsertData);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(520, 0, 12, 37);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(Color.LIGHT_GRAY);
		separator_1.setBounds(657, 0, 12, 37);
		contentPane.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setForeground(Color.LIGHT_GRAY);
		separator_2.setBounds(409, 0, 12, 37);
		contentPane.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setForeground(Color.LIGHT_GRAY);
		separator_3.setBounds(750, 0, 12, 37);
		contentPane.add(separator_3);
		} else {
			//DON'T CREATE THIS ELEMENTS
		}
		
		JLabel lblWelcome = new JLabel("Welcome");
		lblWelcome.setBounds(6, 11, 61, 16);
		contentPane.add(lblWelcome);
		
		JLabel usernameField = new JLabel("");
		usernameField.setText(user_id);
		usernameField.setForeground(Color.RED);
		usernameField.setBounds(67, 11, 93, 16);
		contentPane.add(usernameField);
		
		JButton btnLogout = new JButton("");
		btnLogout.setHorizontalAlignment(SwingConstants.LEFT);
		btnLogout.setBackground(Color.WHITE);
		btnLogout.setIcon(new ImageIcon(MainInterface1.class.getResource("/view/logout.png")));
		btnLogout.setSelectedIcon(null);
		btnLogout.setBounds(759, 1, 61, 51);
		contentPane.add(btnLogout);
		
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginInterface LoginIn;
				try {
					LoginIn = new LoginInterface();
					LoginIn.setVisible(true);
					LoginIn.setResizable(false);
					dispose();
					con.close();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnPage1 = new JButton("1");
		btnPage1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainInterface1 MainIn;
				try {
					MainIn = new MainInterface1(con,user_id,type);
					MainIn.setVisible(true);
					MainIn.setResizable(false);
					dispose();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnPage1.setBackground(Color.WHITE);
		btnPage1.setBounds(67, 543, 61, 51);
		btnPage1.setEnabled(false);
		contentPane.add(btnPage1);
		
		JButton btnPage2 = new JButton("2");
		btnPage2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainInterface2 MainIn;
				try {
					MainIn = new MainInterface2(con,user_id,type);
					MainIn.setVisible(true);
					MainIn.setResizable(false);
					dispose();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnPage2.setBackground(Color.WHITE);
		btnPage2.setBounds(150, 543, 61, 51);
		contentPane.add(btnPage2);
		
		JButton btnPage3 = new JButton("3");
		btnPage3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainInterface3 MainIn;
				try {
					MainIn = new MainInterface3(con,user_id,type);
					MainIn.setVisible(true);
					MainIn.setResizable(false);
					dispose();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnPage3.setBackground(Color.WHITE);
		btnPage3.setBounds(235, 543, 61, 51);
		contentPane.add(btnPage3);
		
		JButton btnPage4 = new JButton("4");
		btnPage4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainInterface4 MainIn;
				try {
					MainIn = new MainInterface4(con,user_id,type);
					MainIn.setVisible(true);
					MainIn.setResizable(false);
					dispose();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnPage4.setBackground(Color.WHITE);
		btnPage4.setBounds(320, 543, 61, 51);
		btnPage4.setEnabled(true);
		contentPane.add(btnPage4);
		
		JButton btnPage5 = new JButton("5");
		btnPage5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainInterface5 MainIn;
				try {
					MainIn = new MainInterface5(con,user_id,type);
					MainIn.setVisible(true);
					MainIn.setResizable(false);
					dispose();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnPage5.setBackground(Color.WHITE);
		btnPage5.setBounds(405, 543, 61, 51);
		btnPage5.setEnabled(true);
		contentPane.add(btnPage5);
		
		JButton btnPage6 = new JButton("6");
		btnPage6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainInterface6 MainIn;
				try {
					MainIn = new MainInterface6(con,user_id,type);
					MainIn.setVisible(true);
					MainIn.setResizable(false);
					dispose();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnPage6.setBackground(Color.WHITE);
		btnPage6.setBounds(490, 543, 61, 51);
		btnPage6.setEnabled(true);
		contentPane.add(btnPage6);
		
		JButton btnCredits = new JButton("Credits");
		btnCredits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreditInterface CreditIn;
				CreditIn = new CreditInterface(con,user_id,type);
				CreditIn.setVisible(true);
				CreditIn.setResizable(false);
				dispose();
			}
		});
		btnCredits.setBackground(Color.WHITE);
		btnCredits.setBounds(635, 543, 89, 51);
		contentPane.add(btnCredits);
		
		
		
		//REQ-FN-5 START
		//GUI ELEMENTS START
		JPanel query5 = new JPanel();
		query5.setBorder(null);
		query5.setBounds(24, 64, 747, 189);
		contentPane.add(query5);
		query5.setLayout(null);
		
		JPanel query5DesP = new JPanel();
		query5DesP.setBorder(new LineBorder(Color.LIGHT_GRAY));
		query5DesP.setBounds(6, 6, 734, 71);
		query5.add(query5DesP);
		query5DesP.setLayout(null);
		
		JLabel query5Des = new JLabel("New label");
		query5Des.setText("<html>This function allows you to input a filament's <b>ID</b> of a choosen <b>satellite</b> or  the filament’s <b>name</b> and get information about the position of the boundary's centroid with the function <font color='red'>Get Centroid</font>, the boundary's extension with the function <font color='red'>Get Extension</font> and the number of relative segments with the function <font color='red'>Get Segment's Nr</font>. Please select the following options:</html>");
		query5Des.setBounds(6, 4, 728, 65);
		query5DesP.add(query5Des);
		
		JLabel lblSearchBy1 = new JLabel("Search by:");
		lblSearchBy1.setBounds(16, 76, 72, 16);
		query5.add(lblSearchBy1);
		
		JRadioButton OptionId5 = new JRadioButton("ID");
		OptionId5.setBounds(16, 108, 72, 23);
		query5.add(OptionId5);
		
		JRadioButton OptionName5 = new JRadioButton("Name");
		OptionName5.setBounds(16, 155, 72, 23);
		query5.add(OptionName5);
		
		JButton btnSubmit5 = new JButton("Submit");
		btnSubmit5.setBounds(578, 114, 148, 37);
		btnSubmit5.setEnabled(false);
		query5.add(btnSubmit5);
		btnSubmit5.setToolTipText("");
		
		idField5 = new JTextField();
		idField5.setBounds(127, 121, 214, 26);
		query5.add(idField5);
		idField5.setColumns(10);
		idField5.setEnabled(false);

		String[] filamentsNames = Query.getFilNames(con);
		JComboBox<?> nameBox5 = new JComboBox<Object>();
		nameBox5.setModel(new DefaultComboBoxModel(filamentsNames));
		nameBox5.setSelectedIndex(0);
		nameBox5.setBounds(127, 155, 237, 27);
		query5.add(nameBox5);
		nameBox5.setEnabled(false);
	
		String[] satellitesNames = Query.getSatellitesNames(con);
		JComboBox<?> satBox5 = new JComboBox<Object>();
		satBox5.setModel(new DefaultComboBoxModel(satellitesNames));
		satBox5.setSelectedIndex(0);
		satBox5.setBounds(189, 97, 152, 27);
		query5.add(satBox5);
		satBox5.setEnabled(false);
		
		JLabel lblSelectTheFunction1 = new JLabel("Select the function");
		lblSelectTheFunction1.setBounds(410, 112, 118, 16);
		query5.add(lblSelectTheFunction1);
		
		JComboBox<?> functionBox5 = new JComboBox<Object>();
		functionBox5.setModel(new DefaultComboBoxModel(new String[] {"SELECT", "Get Centroid", "Get Extension", "Get Segment’s Nr"}));
		functionBox5.setSelectedIndex(0);
		functionBox5.setEnabled(false);
		functionBox5.setBounds(385, 132, 169, 27);
		query5.add(functionBox5);
		
		ButtonGroup group = new ButtonGroup();
		group.add(OptionId5);
		group.add(OptionName5);
		
		JLabel feedbackquery5 = new JLabel("");
		feedbackquery5.setHorizontalAlignment(SwingConstants.CENTER);
		feedbackquery5.setBounds(410, 162, 107, 16);
		query5.add(feedbackquery5);
		
		JLabel lblSatellite1 = new JLabel("Satellite:");
		lblSatellite1.setBounds(132, 101, 63, 16);
		query5.add(lblSatellite1);
		
		JPanel query5Op1 = new JPanel();
		query5Op1.setBorder(new LineBorder(Color.LIGHT_GRAY));
		query5Op1.setBounds(16, 93, 348, 55);
		query5.add(query5Op1);
		
		JPanel query5Op2 = new JPanel();
		query5Op2.setBorder(new LineBorder(Color.LIGHT_GRAY));
		query5Op2.setBounds(16, 152, 348, 30);
		query5.add(query5Op2);
		
		
		JLabel feedbackRes5 = new JLabel("");
		feedbackRes5.setHorizontalAlignment(SwingConstants.CENTER);
		feedbackRes5.setBounds(30, 256, 734, 37);
		contentPane.add(feedbackRes5);
		
		JPanel query5ResP = new JPanel();
		query5ResP.setBounds(24, 254, 747, 41);
		contentPane.add(query5ResP);
		//GUI ELEMENTS END
		//GUI FUNCTIONALITY START
		OptionId5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (OptionId5.isSelected()) {
				idField5.setEnabled(true);
				satBox5.setEnabled(true);
				functionBox5.setEnabled(true);
				
				nameBox5.setEnabled(false);
				nameBox5.setSelectedIndex(0);
				functionBox5.setSelectedIndex(0);
				btnSubmit5.setEnabled(false);
				}
			}
		});
		
		OptionName5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (OptionName5.isSelected()) {
				nameBox5.setEnabled(true);
				functionBox5.setEnabled(true);
				
				idField5.setEnabled(false);
				idField5.setText("");
				satBox5.setEnabled(false);
				satBox5.setSelectedIndex(0);
				functionBox5.setSelectedIndex(0);
				btnSubmit5.setEnabled(false);
				}
			}
		});
		
		idField5.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				functionBox5.setEnabled(true);
				feedbackquery5.setText("");
			}
		});
		
		satBox5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (satBox5.getSelectedIndex() != 0 && satBox5.getSelectedIndex() != -1) {
					functionBox5.setEnabled(true);
					functionBox5.setSelectedIndex(0);
					feedbackquery5.setText("");
				} 
			}
		});
		
		functionBox5.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (OptionId5.isSelected()) {
					String id = idField5.getText();
					if (checkInt(id)) {
						if (satBox5.getSelectedIndex() != 0 && satBox5.getSelectedIndex() != -1) {
							functionBox5.setSelectedIndex(0);
						} else {
							functionBox5.setEnabled(false);
							functionBox5.setSelectedIndex(0);
							feedbackquery5.setText("<html><font color= 'red'>Choose Satellite!</font></html>");
						} 
					} else {
						functionBox5.setEnabled(false);
						functionBox5.setSelectedIndex(0);
						feedbackquery5.setText("<html><font color= 'red'>Invalid Id!</font></html>");
					}
				} else if(OptionName5.isSelected()){
					if (nameBox5.getSelectedIndex() != 0 && nameBox5.getSelectedIndex() != -1) {
						functionBox5.setSelectedIndex(0);
					} else {
						feedbackquery5.setText("<html><font color= 'red'>Select a name!</font></html>");
						functionBox5.setEnabled(false);
						functionBox5.setSelectedIndex(0);
					}
				}
			}
		});
		
		nameBox5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nameBox5.getSelectedIndex() != 0 && nameBox5.getSelectedIndex() != -1) {
					functionBox5.setEnabled(true);
					functionBox5.setSelectedIndex(0);
					feedbackquery5.setText("");
				}
			}
		});
		
		functionBox5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (functionBox5.getSelectedIndex() == 1 || functionBox5.getSelectedIndex() == 2 || functionBox5.getSelectedIndex() == 3) {
					btnSubmit5.setEnabled(true);
				} else {
					btnSubmit5.setEnabled(false);
				}
			}
		});
		
	
		btnSubmit5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (OptionId5.isSelected()) {
					String idS = idField5.getText();
					int id = Integer.parseInt(idS);
					String satellite = (String) satBox5.getSelectedItem();
					if (functionBox5.getSelectedIndex() == 1) {    //Run Get Centroid
						try {
							Position result = Query.getCentroid(con, id, satellite);
							if (result.getG_lat()==0 && result.getG_lon()==0) {
								feedbackRes5.setText("<html>There is no filament with id <b>" + idS + "</b> for <b>" + satellite + "</b></html>");
							} else {
								feedbackRes5.setText("<html>The position of the boundary's centroid of filament with id <b>" + idS + "</b> for <b>" + satellite + "</b> is <br><p align='center'>(<font color ='red'><b>" + result.getG_lat() + "</b></font> , <font color ='red'><b>" + result.getG_lon() + "</b></font>)</p></html>");
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						} 
					} else if (functionBox5.getSelectedIndex() == 2) {  //Run Get Extension
						try {
							double[] result = new double[2]; 
							result = Query.getBoundaryExtension(con, id, satellite);
							if (result[0] == 0 && result[1] == 0) {
								feedbackRes5.setText("<html>There is no filament with id <b>" + idS + "</b> for <b>" + satellite + "</b></html>");
							} else {
								feedbackRes5.setText("<html>The boundary's extension of filament with id <b>" + idS + "</b> for <b>" + satellite + "</b> is <br> <p align='center'>(<font color ='red'><b>" + result[0] + "</b></font> , <font color ='red'><b>" + result[1] + "</b></font>)</p></html>");							
								}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					} else if (functionBox5.getSelectedIndex() == 3) {   //Run Get Segment's Nr
						try {
							int result = Query.countBranch(con, id, satellite);
							if (result == -1) {
								feedbackRes5.setText("<html>There is no filament with id <b>" + idS + "</b> for <b>" + satellite + "</b></html>");
							} else {
								feedbackRes5.setText("<html>The number of relative segments of filament with id <b>" + idS + "</b> for <b>" + satellite + "</b> is <font color ='red'><b>" + result + "</b></font></html>");
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						} 
					}	
				} else if (OptionName5.isSelected()) {
					String name = (String) nameBox5.getSelectedItem();
					if (functionBox5.getSelectedIndex() == 1) {    //Run Get Centroid
						try {
							Position result = Query.getCentroid(con, name);
							feedbackRes5.setText("<html>The position of the boundary's centroid of filament <b>" + name + "</b> is <br> <p align='center'>(<font color ='red'><b>" + result.getG_lat() + "</b> , <b>" + result.getG_lon() + "</b></font>)</p></html>");
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					} else if (functionBox5.getSelectedIndex() == 2) {  //Run Get Extension
						try {
							double[] result = Query.getBoundaryExtension(con, name);
							feedbackRes5.setText("<html>The boundary's extension of filament <b>" + name + "</b> is <br> <p align='center'>(<font color ='red'><b>" + result[0] + "</b></font> , <font color ='red'><b>" + result[1] + "</b></font>)</p></html>");
						} catch (SQLException e1) {
							e1.printStackTrace();
						} 
					} else if (functionBox5.getSelectedIndex() == 3) {   //Run Get Segment's Nr
						try {
							int result = Query.countBranch(con, name);
							feedbackRes5.setText("<html>The number of relative segments of filament with <b>" + name + "</b> is <font color ='red'><b>" + result + "</font></b></html>");
						} catch (SQLException e1) {
							e1.printStackTrace();
						} 
						
					}
				}
			}
		});
		//GUI FUNCTIONALITY END
		//QUERY1 REQ-FN-5
	

		
		//REQ-FN-8 START
        //GUI ELEMENTS START
		
		JPanel query8 = new JPanel();
		query8.setLayout(null);
		query8.setBorder(null);
		query8.setBounds(24, 305, 747, 228);
		contentPane.add(query8);
		
		JPanel query8DesP = new JPanel();
		query8DesP.setLayout(null);
		query8DesP.setBorder(new LineBorder(Color.LIGHT_GRAY));
		query8DesP.setBounds(6, 6, 734, 71);
		query8.add(query8DesP);
		
		JLabel query8Desc = new JLabel("<html>This function allows you to search a list of filaments that are in a region (which can be a <b>circle</b> or a <b>square</b>) from a determined <b>spacial position</b>. A filament is considered inside a region only if all the boundary’s points are inside the same region. </html>");
		query8Desc.setBounds(6, 4, 728, 65);
		query8DesP.add(query8Desc);
		
		JButton buttonSubmit8 = new JButton("Submit");
		buttonSubmit8.setToolTipText("");
		buttonSubmit8.setBounds(183, 132, 165, 45);
		query8.add(buttonSubmit8);
		
		JPanel query8PosP = new JPanel();
		query8PosP.setBorder(new LineBorder(Color.LIGHT_GRAY));
		query8PosP.setBounds(16, 86, 157, 60);
		query8.add(query8PosP);
		query8PosP.setLayout(null);
		
		JLabel Lat8 = new JLabel("Lat:");
		Lat8.setBounds(6, 9, 46, 16);
		query8PosP.add(Lat8);
		
		JLabel Long8 = new JLabel("Long:");
		Long8.setBounds(6, 35, 46, 16);
		query8PosP.add(Long8);
		
		JTextField latField8 = new JTextField();
		latField8.setColumns(10);
		latField8.setBounds(44, 4, 107, 26);
		query8PosP.add(latField8);
		
		JTextField longField8 = new JTextField();
		longField8.setColumns(10);
		longField8.setBounds(44, 30, 107, 26);
		query8PosP.add(longField8);
		
		JPanel query4TypeP = new JPanel();
		query4TypeP.setBorder(new LineBorder(Color.LIGHT_GRAY));
		query4TypeP.setBounds(16, 152, 157, 65);
		query8.add(query4TypeP);
		query4TypeP.setLayout(null);
		
		JTextField valueField8 = new JTextField();
		valueField8.setEnabled(false);
		valueField8.setBounds(52, 34, 99, 26);
		query4TypeP.add(valueField8);
		valueField8.setColumns(10);
		
		JComboBox typeOption8 = new JComboBox();
		typeOption8.setModel(new DefaultComboBoxModel(new String[] {"", "circle", "square"}));
		typeOption8.setBounds(45, 8, 107, 27);
		query4TypeP.add(typeOption8);
		
		JLabel Type8 = new JLabel("Type:");
		Type8.setBounds(6, 12, 46, 16);
		query4TypeP.add(Type8);
		
		JLabel value8 = new JLabel("");
		value8.setBounds(6, 39, 46, 16);
		query4TypeP.add(value8);
		
		JScrollPane query8ResP = new JScrollPane();
		query8ResP.setBounds(357, 137, 373, 80);
		query8.add(query8ResP);
		
		JLabel feedbackRes8 = new JLabel("");
		feedbackRes8.setVerticalAlignment(SwingConstants.TOP);
		query8ResP.setViewportView(feedbackRes8);
		
		JLabel feedbackquery8 = new JLabel("");
		feedbackquery8.setForeground(Color.RED);
		feedbackquery8.setHorizontalAlignment(SwingConstants.CENTER);
		feedbackquery8.setBounds(183, 177, 165, 40);
		query8.add(feedbackquery8);
		
		JLabel feedbackRes8T = new JLabel("");
		feedbackRes8T.setHorizontalAlignment(SwingConstants.CENTER);
		feedbackRes8T.setBounds(345, 83, 396, 52);
		query8.add(feedbackRes8T);
	
		//GUI ELEMENTS END
		//GUI FUNCTIONALITY START
		
		buttonSubmit8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String glat = latField8.getText(); 
				String glong = longField8.getText(); 
				if (checkDouble(glat) && checkDouble(glong)) {	
					if (typeOption8.getSelectedIndex()==1 || typeOption8.getSelectedIndex()==2) {
						String value = valueField8.getText();
						if (checkDouble(value)) {
							double valueD = Double.parseDouble(value);
							if (valueD>0) {
								//ESEGUI FUNZIONE
								double glatD=Double.parseDouble(glat);
								double glongD=Double.parseDouble(glong);
								Position p_centroid = new Position();
								p_centroid.setG_lat(glatD);
								p_centroid.setG_lon(glongD);
								String typeR4 = (String) typeOption8.getSelectedItem();
								ArrayList<Filament> resultList=null;
								if (typeR4.equals("circle")) {
									try {
										resultList = Query.findFilamentCircle(con, valueD, p_centroid);
									} catch (SQLException e1) {
										e1.printStackTrace();
									}
								}
								if (typeR4.equals("square")) {
									try {
										resultList = Query.findFilamentSquare(con, valueD, p_centroid);
									} catch (SQLException e1) {
										e1.printStackTrace();
									}
								}
								
								int size = resultList.size();
								feedbackRes8T.setText("<html><i>Filaments in a <b>"+ typeR4 +"</b> region with " + value8.getText() + "<b>" 
								+ value + "</b> starting in spacial position <b>( " + glat + " , " + glong + " )</b>" + " are:</i></html>");
								String result2 = "<html>";
								if(size != 0) {
									while (!resultList.isEmpty()) {
										Filament f = resultList.remove(0);
										result2 = result2 + f.getName() + "<br>";
									}
								} else {
									result2 = result2 + "None<br>";
								}
								result2 = result2 + "</html>";
								feedbackRes8.setText(result2);
							} else {
								feedbackquery8.setText("<html>Radius or side value must not be negative!</html>");
								feedbackRes8T.setText("");
								feedbackRes8.setText("");
							}
						} else {
							feedbackquery8.setText("<html>Invalid radius or side value!</html>");
							feedbackRes8T.setText("");
							feedbackRes8.setText("");
						}
					} else {
						feedbackquery8.setText("<html>Select region type!</html>");
						feedbackRes8T.setText("");
						feedbackRes8.setText("");
					}
				} else {
					feedbackquery8.setText("<html>Invalid lat or long value!</html>");
					feedbackRes8T.setText("");
					feedbackRes8.setText("");
				}
			}
		});
		
		latField8.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				feedbackquery8.setText("");
			}
		});
		
		longField8.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				feedbackquery8.setText("");
			}
		});
		
		typeOption8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (typeOption8.getSelectedIndex()==1) {
					valueField8.setEnabled(true);
					value8.setText("radius:");
					feedbackquery8.setText("");
				} else if (typeOption8.getSelectedIndex()==2) {
					valueField8.setEnabled(true);
					value8.setText("side:");
					feedbackquery8.setText("");
				} else {
					valueField8.setEnabled(false);
					value8.setText("");
					feedbackquery8.setText("");
				}
			}
		});
		
		valueField8.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				feedbackquery8.setText("");
				feedbackRes8T.setText("");
				feedbackRes8.setText("");
			}
		});
		
		//GUI FUNCTIONALITY END
		//REQ-FN-8 END
		
		//BACKGROUND
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(MainInterface1.class.getResource("/view/SpaceMain2.jpg")));
		label.setBounds(0, 36, 797, 551);
		contentPane.add(label);
	}
	
	//CHECK 
	private boolean checkInt(String s1) {
		if (Pattern.matches("[0-9]+", s1)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean checkDouble(String s) {
	    try {
	        Double.valueOf(s);
	        return true;
	    } catch (NumberFormatException numberFormatException) {
	        return false;
	    }
	} 
}
