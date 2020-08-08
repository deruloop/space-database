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
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import controller.Query;
import model.Star;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.JSlider;

public class MainInterface4 extends JFrame {

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
					MainInterface4 frame = new MainInterface4(con,user_id,type);
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
	public MainInterface4(Connection con,String user_id,boolean type) throws SQLException {
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
		btnPage3.setEnabled(true);
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
		btnPage4.setEnabled(false);
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
		btnCredits.setBounds(640, 543, 89, 51);
		contentPane.add(btnCredits);

		
		//REQ-FN-12 START
		// GUI ELEMENTS START
		JPanel query12 = new JPanel();
		query12.setBounds(25, 71, 747, 454);
		contentPane.add(query12);
		query12.setLayout(null);
		
		JPanel query12DesP = new JPanel();
		query12DesP.setBounds(6, 6, 734, 65);
		query12DesP.setLayout(null);
		query12DesP.setBorder(new LineBorder(Color.LIGHT_GRAY));
		query12.add(query12DesP);
		
		JLabel query12Des = new JLabel("<html>This function allows you to get the minimum distance from the main branch for all the stars in a filament, ordered by flux or distance in ascended or descended way. Please select the following options:</html>");
		query12Des.setBounds(3, 6, 740, 52);
		query12DesP.add(query12Des);
		
		JPanel query12NameP = new JPanel();
		query12NameP.setBounds(6, 83, 245, 37);
		query12NameP.setBorder(new LineBorder(Color.LIGHT_GRAY));
		query12.add(query12NameP);
		query12NameP.setLayout(null);
		
		JLabel lblBrilliance2 = new JLabel("Name:");
		lblBrilliance2.setBounds(6, 10, 47, 16);
		query12NameP.add(lblBrilliance2);
		lblBrilliance2.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		
		String[] filamentsNames = Query.getFilNames(con);
		JComboBox<?> nameBox12 = new JComboBox<Object>();
		nameBox12.setBounds(50, 6, 189, 27);
		query12NameP.add(nameBox12);
		nameBox12.setModel(new DefaultComboBoxModel(filamentsNames));
		nameBox12.setSelectedIndex(0);
		
		JButton btnSubmit12 = new JButton("Submit");
		btnSubmit12.setBounds(514, 83, 113, 37);
		query12.add(btnSubmit12);
		
		JScrollPane query12ResP = new JScrollPane();
		query12ResP.setBounds(6, 157, 734, 259);
		query12.add(query12ResP);
		
		JLabel feedbackRes12 = new JLabel("");
		query12ResP.setViewportView(feedbackRes12);
		feedbackRes12.setVerticalAlignment(SwingConstants.TOP);
		
		JLabel feedbackRes12T = new JLabel("");
		feedbackRes12T.setHorizontalAlignment(SwingConstants.CENTER);
		feedbackRes12T.setBounds(15, 123, 725, 33);
		query12.add(feedbackRes12T);
		
		JSlider slider12 = new JSlider();
		slider12.setSnapToTicks(true);
		slider12.setValue(0);
		slider12.setPaintTicks(true);
		slider12.setBounds(6, 419, 734, 29);
		slider12.setEnabled(false);
		query12.add(slider12);
		
		JLabel feedbackQuery12 = new JLabel("");
		feedbackQuery12.setHorizontalAlignment(SwingConstants.CENTER);
		feedbackQuery12.setBounds(632, 83, 105, 37);
		query12.add(feedbackQuery12);
		
		JPanel query12OrderP = new JPanel();
		query12OrderP.setBounds(257, 84, 251, 36);
		query12.add(query12OrderP);
		query12OrderP.setLayout(null);
		
		JLabel lblOrderBy12 = new JLabel("Order by:");
		lblOrderBy12.setBounds(6, 9, 61, 16);
		query12OrderP.add(lblOrderBy12);
		
		JComboBox forderBox12 = new JComboBox();
		forderBox12.setBounds(64, 5, 110, 27);
		query12OrderP.add(forderBox12);
		forderBox12.setModel(new DefaultComboBoxModel(new String[] {"", "distance", "flux"}));
		
		JComboBox lorderBox12 = new JComboBox();
		lorderBox12.setBounds(170, 5, 81, 27);
		query12OrderP.add(lorderBox12);
		lorderBox12.setEnabled(false);
		lorderBox12.setModel(new DefaultComboBoxModel(new String[] {"", "asc", "desc"}));
	
		//GUI ELEMENTS END				
		//GUI FUNCTIONALITY START
		
		forderBox12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (forderBox12.getSelectedIndex()!=0 && forderBox12.getSelectedIndex()!=0) {
					lorderBox12.setEnabled(true);
				}
			}
		});
		
		btnSubmit12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				slider12.setValue(0);
				if (nameBox12.getSelectedIndex()!=0 && nameBox12.getSelectedIndex()!=-1) {
					if (forderBox12.getSelectedIndex()!=0 && forderBox12.getSelectedIndex()!=-1 && lorderBox12.getSelectedIndex()!=0 && lorderBox12.getSelectedIndex()!=-1) {
						String name = (String) nameBox12.getSelectedItem();
						String forder= (String) forderBox12.getSelectedItem();
						String lorder= (String) lorderBox12.getSelectedItem();
						ArrayList<Star> resultList = null;
						ArrayList<Star> resultList1 = null;
						try {
							resultList = Query.distanceStarsToMainBranch(con,name);
						} catch (SQLException e2) {
							e2.printStackTrace();
						}
						int fullsize = resultList.size();
						ArrayList<Star> resultListOrd = Query.orderStars(resultList, forder);
						if (lorder.equals("asc")) {
							resultList1 = Query.viewStarsLimit(resultListOrd, 20, 0);
						} else if (lorder.equals("desc")) {
							resultList1 = Query.viewStarsLimit(resultListOrd, 20, fullsize-20);
						}
						int i=0;
						feedbackRes12T.setText("<html><i>The minimum distance from the main branch for all the stars in <b>" + name + "</b>, ordered "
								+ "by </b>"+ forder + "</b> in <b>" + lorder + "ended</b> way, are:" + "</i></html>");
						String result2 = "<html>";
						int pages = fullsize/20;
						slider12.setMaximum(pages);
						slider12.setMajorTickSpacing(1);
						slider12.setEnabled(true);
						while (i!=(resultList1.size())) {
							if (lorder.equals("asc")) {
								Star s = (Star) resultList1.get(i);
								result2 = result2 + s.getName() + "<br>";
								i=i+1;
							} else if (lorder.equals("desc")) {
								int j = resultList1.size()-1-i;
								Star s = (Star) resultList1.get(j);
								result2 = result2 + s.getName() + "<br>";
								i=i+1;
							}
						}
						if (i==0) {
							result2 = result2 + "None<br>";
						}
						result2 = result2 + "</html>";
						feedbackRes12.setText(result2);
						slider12.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseReleased(MouseEvent e) {
								int x=slider12.getValue();
								ArrayList<Star> resultList2= null;
								if (lorder.equals("asc")) {
									resultList2 = Query.viewStarsLimit(resultListOrd, 20, x*20);
								} else if (lorder.equals("desc")) {
									resultList2 = Query.viewStarsLimit(resultListOrd, 20, fullsize-x*20);
								}
								int i=0;
								String result2 = "<html>";
								while (i!=(resultList2.size())) {
									if (lorder.equals("asc")) {
										Star s = (Star) resultList2.get(i);
										result2 = result2 + s.getName() + "<br>";
										i=i+1;
									} else if (lorder.equals("desc")) {
										int j = resultList2.size()-1-i;
										Star s = (Star) resultList2.get(j);
										result2 = result2 + s.getName() + "<br>";
										i=i+1;
									}
								}
								if (i==0) {
									result2 = result2 + "None<br>";
								}
								result2 = result2 + "</html>";
								feedbackRes12.setText(result2);
							}
						}); 
					} else {
						feedbackQuery12.setText("<html><font color ='red'>Choose order!</font></html>");
						feedbackRes12T.setText("");
						feedbackRes12.setText("");
					}
				} else {
					feedbackQuery12.setText("<html><font color ='red'>Choose filament!</font></html>");
					feedbackRes12T.setText("");
					feedbackRes12.setText("");
				}
			}
		});
		//GUI FUNCTIONALITY END
		//REQ-FN-12 END
	
		
		//BACKGROUND
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(MainInterface1.class.getResource("/view/SpaceMain2.jpg")));
		label.setBounds(0, 36, 796, 563);
		contentPane.add(label);
	}
}
