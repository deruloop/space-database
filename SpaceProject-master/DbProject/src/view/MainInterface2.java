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
import java.awt.Font;
import controller.Query;
import model.Filament;
import javax.swing.border.LineBorder;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JScrollPane;
import javax.swing.JSlider;

public class MainInterface2 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField ValA7;
	private JTextField ValB7;
	//private JTextField valueField4;
	//private JTextField latField4;
	//private JTextField longField4;

	/**
	 * Launch the application.
	 */
	public static void main(Connection con,String user_id,boolean type) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainInterface2 frame = new MainInterface2(con,user_id,type);
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
	public MainInterface2(Connection con,String user_id,boolean type) throws SQLException {
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
		btnPage2.setEnabled(false);
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
		
		//REQ-FN-7 START
		// GUI ELEMENTS START
		JPanel query7 = new JPanel();
		query7.setBounds(24, 64, 747, 454);
		contentPane.add(query7);
		query7.setLayout(null);
		
		JPanel query7DesP = new JPanel();
		query7DesP.setBounds(6, 6, 734, 47);
		query7DesP.setLayout(null);
		query7DesP.setBorder(new LineBorder(Color.LIGHT_GRAY));
		query7.add(query7DesP);
		
		JLabel query7Des = new JLabel("<html>This function allows you to research a list of filaments that have a number of total segments between a choosen <b>interval</b></html>");
		query7Des.setBounds(10, 6, 713, 38);
		query7DesP.add(query7Des);
		
		JPanel query7InterP = new JPanel();
		query7InterP.setBounds(6, 58, 166, 47);
		query7InterP.setBorder(new LineBorder(Color.LIGHT_GRAY));
		query7.add(query7InterP);
		query7InterP.setLayout(null);
		
		JLabel lblInterval7 = new JLabel("Interval");
		lblInterval7.setBounds(6, 6, 71, 16);
		query7InterP.add(lblInterval7);
		lblInterval7.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		
		JLabel lblFrom7 = new JLabel("from");
		lblFrom7.setBounds(6, 25, 36, 16);
		query7InterP.add(lblFrom7);
		lblFrom7.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		
		JLabel lblTo7 = new JLabel("to");
		lblTo7.setBounds(95, 25, 18, 16);
		query7InterP.add(lblTo7);
		lblTo7.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		
		ValA7 = new JTextField();
		ValA7.setBounds(37, 20, 53, 26);
		query7InterP.add(ValA7);
		ValA7.setColumns(10);
		
		ValB7 = new JTextField();
		ValB7.setColumns(10);
		ValB7.setBounds(109, 20, 53, 26);
		query7InterP.add(ValB7);
		
		JButton btnSubmit7 = new JButton("Submit");
		btnSubmit7.setBounds(184, 65, 148, 37);
		btnSubmit7.setToolTipText("");
		query7.add(btnSubmit7);
		
		JScrollPane query7ResP = new JScrollPane();
		query7ResP.setBounds(6, 150, 735, 264);
		query7.add(query7ResP);
		
		JLabel feedbackRes7 = new JLabel("");
		feedbackRes7.setVerticalAlignment(SwingConstants.TOP);
		query7ResP.setViewportView(feedbackRes7);
		
		JLabel feedbackquery7 = new JLabel("");
		feedbackquery7.setHorizontalAlignment(SwingConstants.LEFT);
		feedbackquery7.setBounds(344, 65, 396, 40);
		query7.add(feedbackquery7);
		
		JLabel feedbackRes7T = new JLabel("");
		feedbackRes7T.setHorizontalAlignment(SwingConstants.CENTER);
		feedbackRes7T.setBounds(6, 112, 734, 32);
		query7.add(feedbackRes7T);
		
		JSlider slider7 = new JSlider();
		slider7.setValue(0);
		slider7.setSnapToTicks(true);
		slider7.setPaintTicks(true);
		slider7.setEnabled(false);
		slider7.setBounds(6, 419, 734, 29);
		query7.add(slider7);
	
		//GUI ELEMENTS END
		//GUI FUNCTIONALITY START
		
		btnSubmit7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				slider7.setValue(0);
				String ValA=ValA7.getText();
				String ValB=ValB7.getText();
				if (checkInt(ValA) && checkInt(ValB)) {
					int ValAInt = Integer.parseInt(ValA);
					int ValBInt = Integer.parseInt(ValB);
					if (ValAInt<ValBInt && ValBInt-ValAInt>2) {
						ArrayList<Filament> resultList = null;
						ArrayList<Filament> resultListP = null;
						int resultCount = 0;
						try {
							resultCount = Query.countFilament(con);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						try {
							resultListP = Query.searchFilamentByRange(con,ValAInt,ValBInt);
							resultList = Query.searchFilamentByRange(con,ValAInt,ValBInt,20,0);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						int i=0;
						feedbackRes7T.setText("<html><i>Founded <b><font color = 'red'>" + resultListP.size() + "</font>/<font color = 'red'>" + resultCount + "</font></b> filaments with a number of segments between <b>" + ValA + "</b> and <b>" + ValB + "</b>:</i></html>");
						String result2 = "<html>";
						int pages = resultListP.size()/20;
						slider7.setMaximum(pages);
						slider7.setMajorTickSpacing(1);
						slider7.setEnabled(true);
						while (i!=(resultList.size())) {
							Filament f = (Filament) resultList.get(i);
							result2 = result2 + f.getName() + "<br>";
							i=i+1;
						}
						if (i==0) {
							result2 = result2 + "None<br>";
						}
						result2 = result2 + "</html>";
						feedbackRes7.setText(result2);
						slider7.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseReleased(MouseEvent e) {
								int x=slider7.getValue();
								ArrayList<Filament> resultList2= null;
								try {
									resultList2 = Query.searchFilamentByRange(con,ValAInt,ValBInt,20,x*20);
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
								int i=0;
								String result2 = "<html>";
								while (i!=(resultList2.size())) {
									Filament f = resultList2.get(i);
									result2 = result2 + f.getName() + "<br>";
									i=i+1;
								}
								if (i==0) {
									result2 = result2 + "None<br>";
								}
								result2 = result2 + "</html>";
								feedbackRes7.setText(result2);
							}
						}); 
					} else {
						feedbackquery7.setText("<html><font color ='red'>Input a valid interval!</font></html>");
						feedbackRes7T.setText("");
						feedbackRes7.setText("");
						btnSubmit7.setEnabled(false);
					}
				} else {
					feedbackquery7.setText("<html><font color ='red'>Input integer values!</font></html>");
					feedbackRes7T.setText("");
					feedbackRes7.setText("");
					btnSubmit7.setEnabled(false);
				}
				
			}
		});
		
		ValA7.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				feedbackquery7.setText("");
				btnSubmit7.setEnabled(true);
			}
		});
		
		ValB7.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				feedbackquery7.setText("");
				btnSubmit7.setEnabled(true);
			}
		});
		
		//GUI FUNCTIONALITY END
	    //REQ-FN-7 END
		
		
		//BACKGROUND
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(MainInterface1.class.getResource("/view/SpaceMain2.jpg")));
		label.setBounds(0, 36, 796, 563);
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
}
