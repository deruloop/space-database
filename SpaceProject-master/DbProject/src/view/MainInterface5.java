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
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import controller.Query;
import model.Position;
import model.StarInfo;
import javax.swing.border.LineBorder;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class MainInterface5 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	//private JTextField brillField2;
	private JTextField branchText11;
	private JTextField basisField10;

	/**
	 * Launch the application.
	 */
	public static void main(Connection con,String user_id,boolean type) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainInterface5 frame = new MainInterface5(con,user_id,type);
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
	public MainInterface5(Connection con,String user_id,boolean type) throws SQLException {
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
		btnPage5.setEnabled(false);
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
		btnCredits.setBounds(640, 543, 89, 51);
		contentPane.add(btnCredits);
		
		
		//REQ-FN-11 START
		//GUI ELEMENTS END
		
		JPanel query11 = new JPanel();
		query11.setLayout(null);
		query11.setBorder(null);
		query11.setBounds(24, 64, 747, 101);
		contentPane.add(query11);
		
		JPanel query11DesP = new JPanel();
		query11DesP.setLayout(null);
		query11DesP.setBorder(new LineBorder(Color.LIGHT_GRAY));
		query11DesP.setBounds(6, 6, 734, 36);
		query11.add(query11DesP);
		
		JLabel query11Des = new JLabel("<html>This function evaluates the minimun distance from the boundary for the two vertices of a selected branch</html>");
		query11Des.setBounds(6, 6, 728, 27);
		query11DesP.add(query11Des);
		
		JLabel idBranch11 = new JLabel("ID branch:");
		idBranch11.setBounds(16, 60, 65, 16);
		query11.add(idBranch11);
		
		branchText11 = new JTextField();
		branchText11.setBounds(84, 56, 130, 26);
		query11.add(branchText11);
		branchText11.setColumns(10);
		
		JButton btnSubmit11 = new JButton("Submit");
		btnSubmit11.setBounds(226, 54, 128, 36);
		query11.add(btnSubmit11);
		
		JLabel feedbackquery11 = new JLabel("");
		feedbackquery11.setHorizontalAlignment(SwingConstants.CENTER);
		feedbackquery11.setBounds(360, 52, 380, 40);
		query11.add(feedbackquery11);
		
		JPanel query11ResP = new JPanel();
		query11ResP.setLayout(null);
		query11ResP.setBounds(24, 170, 747, 51);
		contentPane.add(query11ResP);
		
		JLabel feedbackRes11 = new JLabel("");
		feedbackRes11.setHorizontalAlignment(SwingConstants.CENTER);
		feedbackRes11.setBounds(6, 4, 735, 41);
		query11ResP.add(feedbackRes11);
		
		//GUI ELEMENTS END
		//GUI FUNCTIONALITY START
		
		btnSubmit11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idB = branchText11.getText();
				if (checkInt(idB)) {
					int idBI = Integer.parseInt(idB);
					ArrayList<Double> resultList=null;
					try {
						resultList = Query.distanceVertix(con, idBI);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if(!resultList.isEmpty()) {
						feedbackRes11.setText("<html>The distance of branch with id <b>" + idB + "</b> from the boundary for the first vertex is <b><font color='red'>"
								+ resultList.get(0) + "</font></b> and for the other is <b><font color='red'>" + resultList.get(resultList.size()- 1) + "</font></b></html>");						
					} else {
						feedbackquery11.setText("<html><font color ='red'>Id branch doesn't exist!</font></html>");
					}
				} else {
					feedbackquery11.setText("<html><font color ='red'>Input a valid Id branch!</font></html>");
				}
			}
		});
		
		//GUI FUNCTIONALITY END
		//REQ-FN-11 END
		
		
		//REQ-FN-10 START
        //GUI ELEMENTS START
		
		JPanel query10 = new JPanel();
		query10.setLayout(null);
		query10.setBorder(null);
		query10.setBounds(24, 239, 747, 169);
		contentPane.add(query10);
		
		JPanel query10DesP = new JPanel();
		query10DesP.setLayout(null);
		query10DesP.setBorder(new LineBorder(Color.LIGHT_GRAY));
		query10DesP.setBounds(6, 6, 734, 71);
		query10.add(query10DesP);
		
		JLabel query10Desc = new JLabel("<html>This function allows you to search starts inside of a rectangular sky portion. The function gives you the percentual of starts inside and outside of a filament. Also, for both of the groups(inside and outside), it gives you the relative fraction for each type of star, in percentual values. </html>");
		query10Desc.setBounds(6, 4, 728, 65);
		query10DesP.add(query10Desc);
		
		JButton buttonSubmit10 = new JButton("Submit");
		buttonSubmit10.setToolTipText("");
		buttonSubmit10.setBounds(360, 95, 165, 45);
		query10.add(buttonSubmit10);
		
		JPanel query10PosP = new JPanel();
		query10PosP.setBorder(new LineBorder(Color.LIGHT_GRAY));
		query10PosP.setBounds(16, 89, 157, 60);
		query10.add(query10PosP);
		query10PosP.setLayout(null);
		
		JLabel Lat10 = new JLabel("Lat:");
		Lat10.setBounds(6, 9, 46, 16);
		query10PosP.add(Lat10);
		
		JLabel Long10 = new JLabel("Long:");
		Long10.setBounds(6, 35, 46, 16);
		query10PosP.add(Long10);
		
		JTextField latField10 = new JTextField();
		latField10.setColumns(10);
		latField10.setBounds(44, 4, 107, 26);
		query10PosP.add(latField10);
		
		JTextField longField10 = new JTextField();
		longField10.setColumns(10);
		longField10.setBounds(44, 30, 107, 26);
		query10PosP.add(longField10);
		
		JPanel query10TypeP = new JPanel();
		query10TypeP.setBorder(new LineBorder(Color.LIGHT_GRAY));
		query10TypeP.setBounds(191, 89, 157, 60);
		query10.add(query10TypeP);
		query10TypeP.setLayout(null);
		
		JTextField heightField10 = new JTextField();
		heightField10.setBounds(52, 30, 99, 26);
		query10TypeP.add(heightField10);
		heightField10.setColumns(10);
		
		JLabel height10 = new JLabel("Height:");
		height10.setBounds(6, 35, 46, 16);
		query10TypeP.add(height10);
		
		JLabel base10 = new JLabel("Basis:");
		base10.setBounds(6, 9, 46, 16);
		query10TypeP.add(base10);
		
		basisField10 = new JTextField();
		basisField10.setColumns(10);
		basisField10.setBounds(52, 4, 99, 26);
		query10TypeP.add(basisField10);
		
		JLabel feedbackquery10 = new JLabel("");
		feedbackquery10.setForeground(Color.RED);
		feedbackquery10.setHorizontalAlignment(SwingConstants.CENTER);
		feedbackquery10.setBounds(536, 95, 197, 54);
		query10.add(feedbackquery10);
		
		JPanel panel = new JPanel();
		panel.setBounds(24, 412, 747, 44);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel feedbackRes10 = new JLabel("");
		feedbackRes10.setBounds(6, 6, 735, 32);
		panel.add(feedbackRes10);
		feedbackRes10.setHorizontalAlignment(SwingConstants.CENTER);
	
		//GUI ELEMENTS END
		//GUI FUNCTIONALITY START
		
		buttonSubmit10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String glat = latField10.getText(); 
				String glong = longField10.getText(); 
				if (checkDouble(glat) && checkDouble(glong)) {	
					String basis = basisField10.getText();
					String height = heightField10.getText();
					if (checkDouble(basis) && checkDouble(height)) {
						Double glatD = Double.parseDouble(glat);
						Double glongD = Double.parseDouble(glong);
						Double basisD = Double.parseDouble(basis);
						Double heightD = Double.parseDouble(height);
						if (basisD<0 || heightD<0) {
							feedbackquery10.setText("<html>Basis and/or height must be positive values</html>");
							feedbackRes10.setText("");
						} else {
							try {
								StarInfo[] s = Query.starsInFilament(con, heightD, basisD, new Position(glatD,glongD)); 

								feedbackRes10.setText("<html>" +
								
								"IN: Founded <b><font color = 'red'>" + s[0].getOccurrence_star() + "</font></b> stars where: <b><font color = 'red'>" + s[0].getPercentual_prestellar() + "</font>%</b> prestellar, <b><font color = 'red'>"
										+ s[0].getPercentual_protostellar() + "</font>%</b> protostellar, <b><font color = 'red'>" + s[0].getPercentual_unbound() + "</font>%</b> unbound" +
								
								"<br>" +
								
								"OUT: Founded <b><font color = 'red'>" + s[1].getOccurrence_star() + "</font></b> stars where: <b><font color = 'red'>" + s[1].getPercentual_prestellar() + "</font>%</b> prestellar, <b><font color = 'red'>"
										+ s[1].getPercentual_protostellar() + "</font>%</b> protostellar, <b><font color = 'red'>" + s[1].getPercentual_unbound() + "</font>%</b> unbound" +
								
								"</html>");
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					} else {
						feedbackquery10.setText("<html>Invalid basis or height value!</html>");
						feedbackRes10.setText("");
					}
				} else {
					feedbackquery10.setText("<html>Invalid lat or long value!</html>");
					feedbackRes10.setText("");
				}
			}
		});
		
		latField10.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				feedbackquery10.setText("");
			}
		});
		
		longField10.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				feedbackquery10.setText("");
			}
		});
		
		heightField10.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				feedbackquery10.setText("");
				feedbackRes10.setText("");
			}
		});
		
		//GUI FUNCTIONALITY END
		//REQ-FN-10 END
		
		//BACKGROUND
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(MainInterface1.class.getResource("/view/SpaceMain2.jpg")));
		label.setBounds(0, 36, 796, 563);
		contentPane.add(label);
	}
	
	//CHECK 
	private boolean checkInt(String s) {
		if (Pattern.matches("[0-9]+", s)) {
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
