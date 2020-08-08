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

public class MainInterface3 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(Connection con,String user_id,boolean type) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainInterface3 frame = new MainInterface3(con,user_id,type);
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
	public MainInterface3(Connection con,String user_id,boolean type) throws SQLException {
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
		btnPage3.setEnabled(false);
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
		
		//REQ-FN-6 START
		//GUI ELEMENTS START
		JPanel query6 = new JPanel();
		query6.setBounds(25, 71, 747, 454);
		contentPane.add(query6);
		query6.setLayout(null);
		
		JPanel query6DesP = new JPanel();
		query6DesP.setBounds(6, 6, 734, 65);
		query6DesP.setLayout(null);
		query6DesP.setBorder(new LineBorder(Color.LIGHT_GRAY));
		query6.add(query6DesP);
		
		JLabel query6Des = new JLabel("<html>This function allows you to research a list of filaments that have a bigger <b>brilliance</b> than an established percentage and an <b>ellipticity</b> value included in a sortable range between two and nine. The list can have a max of twenty fila-<br>-ments. Please select the following options:</html>");
		query6Des.setBounds(3, 6, 740, 52);
		query6DesP.add(query6Des);
		
		JPanel query6EllP = new JPanel();
		query6EllP.setBounds(232, 83, 285, 37);
		query6EllP.setBorder(new LineBorder(Color.LIGHT_GRAY));
		query6.add(query6EllP);
		query6EllP.setLayout(null);
		
		JLabel lblEllipticity6 = new JLabel("Ellipticity:");
		lblEllipticity6.setBounds(6, 11, 71, 16);
		query6EllP.add(lblEllipticity6);
		lblEllipticity6.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		
		JLabel lblFrom6 = new JLabel("from");
		lblFrom6.setBounds(80, 11, 36, 16);
		query6EllP.add(lblFrom6);
		lblFrom6.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		
		JLabel lblTo6 = new JLabel("to");
		lblTo6.setBounds(190, 11, 18, 16);
		query6EllP.add(lblTo6);
		lblTo6.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		
		JTextField ellAField6 = new JTextField();
		ellAField6.setBounds(114, 6, 75, 26);
		query6EllP.add(ellAField6);
		ellAField6.setColumns(10);
		
		JTextField ellBField6 = new JTextField();
		ellBField6.setBounds(205, 6, 75, 26);
		query6EllP.add(ellBField6);
		ellBField6.setColumns(10);
		
		JPanel query6BrillP = new JPanel();
		query6BrillP.setBounds(6, 83, 214, 37);
		query6BrillP.setBorder(new LineBorder(Color.LIGHT_GRAY));
		query6.add(query6BrillP);
		query6BrillP.setLayout(null);
		
		JLabel lblBrilliance6 = new JLabel("Brilliance:");
		lblBrilliance6.setBounds(6, 10, 65, 16);
		query6BrillP.add(lblBrilliance6);
		lblBrilliance6.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		
		JLabel perc6 = new JLabel("%");
		perc6.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		perc6.setBounds(184, 10, 24, 16);
		query6BrillP.add(perc6);
		
		JTextField brillField6 = new JTextField();
		brillField6.setHorizontalAlignment(SwingConstants.RIGHT);
		brillField6.setBounds(74, 5, 109, 26);
		query6BrillP.add(brillField6);
		brillField6.setColumns(10);
		
		JButton btnSubmit6 = new JButton("Submit");
		btnSubmit6.setBounds(520, 83, 113, 37);
		btnSubmit6.setEnabled(false);
		query6.add(btnSubmit6);
		
		JScrollPane query6ResP = new JScrollPane();
		query6ResP.setBounds(6, 157, 734, 259);
		query6.add(query6ResP);
		
		JLabel feedbackRes6 = new JLabel("");
		query6ResP.setViewportView(feedbackRes6);
		feedbackRes6.setVerticalAlignment(SwingConstants.TOP);
		
		JLabel feedbackRes6T = new JLabel("");
		feedbackRes6T.setHorizontalAlignment(SwingConstants.CENTER);
		feedbackRes6T.setBounds(15, 123, 725, 33);
		query6.add(feedbackRes6T);
		
		JSlider slider6 = new JSlider();
		slider6.setSnapToTicks(true);
		slider6.setValue(0);
		slider6.setPaintTicks(true);
		slider6.setBounds(6, 419, 734, 29);
		slider6.setEnabled(false);
		query6.add(slider6);
		
		JLabel feedbackQuery2 = new JLabel("");
		feedbackQuery2.setHorizontalAlignment(SwingConstants.CENTER);
		feedbackQuery2.setBounds(636, 83, 105, 37);
		query6.add(feedbackQuery2);
	
		//GUI ELEMENTS END				
		//GUI FUNCTIONALITY START
		
		btnSubmit6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				slider6.setValue(0);
				String brill=brillField6.getText();
				if (checkDouble(brill)==true) {
					double brillDouble = Double.parseDouble(brill);
					if (brillDouble<0) {
						feedbackQuery2.setText("<html><font color ='red'>Brilliance must not be negative!</font></html>");
						feedbackRes6T.setText("");
						feedbackRes6.setText("");
					} else {
						String A=(String) ellAField6.getText();
						String B=(String) ellBField6.getText();
						if(checkDouble(A) && checkDouble(B)) {
							double ADb = Double.parseDouble(A);
							double BDb = Double.parseDouble(B);
							if (ADb<2 || ADb>9 || BDb<2 || BDb>9) {
								feedbackQuery2.setText("<html><font color ='red'>Ellipticity values must be between 2 and 9!</font></html>");
								feedbackRes6T.setText("");
								feedbackRes6.setText("");
								} else if (ADb>BDb) {
									feedbackQuery2.setText("<html><font color ='red'>Invalid ellipticity range!</font></html>");
									feedbackRes6T.setText("");
									feedbackRes6.setText("");
								} else {
									ArrayList<Filament> resultList = null;
									ArrayList<Filament> resultListP = null;
									int resultCount = 0;
									try {
										resultCount = Query.countFilament(con);
									} catch (SQLException e1) {
										e1.printStackTrace();
									}
									try {
										resultListP = Query.searchFilamentByContrastEllipticity(con,brillDouble,ADb,BDb);
										resultList = Query.searchFilamentByContrastEllipticity(con,brillDouble,ADb,BDb,20,0);
									} catch (SQLException e1) {
										e1.printStackTrace();
									}
									int i=0;
									feedbackRes6T.setText("<html><i>Founded <b><font color = 'red'>" + resultListP.size() + "</font>/<font color = 'red'>" + resultCount + "</font></b> filaments with a brillance higher than <b>" + brill + "%</b> in an ellipticity range between <b>" + A + "</b> and <b>" + B + "</b>:</i></html>");
									String result2 = "<html>";
									int pages = resultListP.size()/20;
									slider6.setMaximum(pages);
									slider6.setMajorTickSpacing(1);
									slider6.setEnabled(true);
									while (i!=(resultList.size())) {
										Filament f = (Filament) resultList.get(i);
										result2 = result2 + f.getName() + "<br>";
										i=i+1;
									}
									if (i==0) {
										result2 = result2 + "None<br>";
									}
									result2 = result2 + "</html>";
									feedbackRes6.setText(result2);
									slider6.addMouseListener(new MouseAdapter() {
										@Override
										public void mouseReleased(MouseEvent e) {
											int x=slider6.getValue();
											ArrayList<Filament> resultList2= null;
											try {
												resultList2 = Query.searchFilamentByContrastEllipticity(con,brillDouble,ADb,BDb,20,x*20);
											} catch (SQLException e1) {
												e1.printStackTrace();
											}
											int i=0;
											String result2 = "<html>";
											while (i!=(resultList2.size())) {
												Filament f = (Filament) resultList2.get(i);
												result2 = result2 + f.getName() + "<br>";
												i=i+1;
											}
											if (i==0) {
												result2 = result2 + "None<br>";
											}
											result2 = result2 + "</html>";
											feedbackRes6.setText(result2);
										}
									}); 
								}
						    } else {
						    	    feedbackQuery2.setText("<html><font color ='red'>Invalid ellipticity<br> value!</font></html>");
						    	    feedbackRes6T.setText("");
								feedbackRes6.setText("");
					        }
					} 
				} else {
					feedbackQuery2.setText("<html><font color ='red'>Invalid brilliance value!</font></html>");
					feedbackRes6T.setText("");
					feedbackRes6.setText("");
				}
			}
		});
		
		brillField6.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				btnSubmit6.setEnabled(true);
				feedbackQuery2.setText("");
			}
		});
		
		ellBField6.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				btnSubmit6.setEnabled(true);
				feedbackQuery2.setText("");
			}
		});
		
		ellAField6.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				btnSubmit6.setEnabled(true);
				feedbackQuery2.setText("");
			}
		});
		//GUI FUNCTIONALITY END
		//REQ-FN-6 END
	
		
		
		
		//BACKGROUND
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(MainInterface1.class.getResource("/view/SpaceMain2.jpg")));
		label.setBounds(0, 36, 796, 563);
		contentPane.add(label);
	}
		
	//CHECK 
	private boolean checkDouble(String s) {
	    try {
	        Double.valueOf(s);
	        return true;
	    } catch (NumberFormatException numberFormatException) {
	        return false;
	    }
	} 
}
