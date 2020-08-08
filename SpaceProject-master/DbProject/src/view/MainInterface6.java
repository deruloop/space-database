package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;
import controller.Query;
import model.StarInfo;
import javax.swing.border.LineBorder;

public class MainInterface6 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	//private JTextField brillField2;
	//private JTextField branchText6;

	/**
	 * Launch the application.
	 */
	public static void main(Connection con,String user_id,boolean type) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainInterface6 frame = new MainInterface6(con,user_id,type);
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
	public MainInterface6(Connection con,String user_id,boolean type) throws SQLException {
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
		btnPage6.setEnabled(false);
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
		
		
		
		//REQ-FN-9 START
		//GUI ELEMENTS START
		JPanel query9 = new JPanel();
		query9.setBorder(null);
		query9.setBounds(24, 64, 747, 101);
		contentPane.add(query9);
		query9.setLayout(null);
		
		JLabel feedbackquery9 = new JLabel("");
		feedbackquery9.setHorizontalAlignment(SwingConstants.CENTER);
		feedbackquery9.setBounds(490, 54, 224, 40);
		query9.add(feedbackquery9);
		
		JPanel query9DesP = new JPanel();
		query9DesP.setBorder(new LineBorder(Color.LIGHT_GRAY));
		query9DesP.setBounds(6, 6, 734, 40);
		query9.add(query9DesP);
		query9DesP.setLayout(null);
		
		JLabel query9Des = new JLabel("New label");
		query9Des.setText("<html>This function allows you to find from all the stars the ones that fall into a determined filament</html>");
		query9Des.setBounds(6, 6, 728, 27);
		query9DesP.add(query9Des);
		
		JLabel Name9 = new JLabel("Name:");
		Name9.setBounds(16, 64, 45, 16);
		query9.add(Name9);
		
		String[] filamentsNames = Query.getFilNames(con);
		JComboBox<Object> nameBox9 = new JComboBox<Object>();
		nameBox9.setModel(new DefaultComboBoxModel(filamentsNames));
		nameBox9.setSelectedIndex(0);
		nameBox9.setBounds(59, 59, 283, 27);
		query9.add(nameBox9);
		
		JButton btnSubmit9 = new JButton("Submit");
		btnSubmit9.setBounds(360, 53, 128, 41);
		query9.add(btnSubmit9);
		
		JPanel query9ResP = new JPanel();
		query9ResP.setBounds(24, 169, 747, 24);
		contentPane.add(query9ResP);
		query9ResP.setLayout(null);
		
		JLabel feedbackRes9 = new JLabel("");
		feedbackRes9.setHorizontalAlignment(SwingConstants.CENTER);
		feedbackRes9.setBounds(6, 4, 735, 16);
		query9ResP.add(feedbackRes9);
		
		//GUI ELEMENTS END
		//GUI FUNCTIONALITY START
		
		btnSubmit9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nameBox9.getSelectedIndex()!=0 && nameBox9.getSelectedIndex()!=-1) {
					String nameF = (String) nameBox9.getSelectedItem();
					try {
						StarInfo s = new StarInfo();
						s = Query.infoStarsInFilament(con,nameF);
						feedbackRes9.setText("<html>Founded <b><font color = 'red'>" + s.getOccurrence_star() + "</font></b> stars where: <b><font color = 'red'>" + s.getPercentual_prestellar() + "</font>%</b> prestellar, <b><font color = 'red'>"
								+ s.getPercentual_protostellar() + "</font>%</b> protostellar, <b><font color = 'red'>" + s.getPercentual_unbound() + "</font>%</b> unbound</html>");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					feedbackquery9.setText("<html><font color='red'>Choose a filament!</font></html>");
				}
			}
		});
		
		//REQ-FN-9 END
		
		//BACKGROUND
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(MainInterface1.class.getResource("/view/SpaceMain2.jpg")));
		label.setBounds(0, 36, 796, 563);
		contentPane.add(label);
	}
	
}
