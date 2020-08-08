package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.Query;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.regex.Pattern;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class InsertInterface extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField satName;
	private JTextField insField;
	private JTextField bandField;

	/**
	 * Launch the application.
	 */
	public static void main(Connection con,String user_id,boolean type) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertInterface frame = new InsertInterface(con,user_id,type);
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
	public InsertInterface(Connection con,String user_id,boolean type) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 475, 403);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnClose = new JButton("Back");
		btnClose.setHorizontalAlignment(SwingConstants.RIGHT);
		btnClose.setBounds(-42, 0, 103, 41);
		contentPane.add(btnClose);
		
		JPanel panel = new JPanel();
		panel.setBounds(42, 42, 398, 143);
		contentPane.add(panel);
		panel.setLayout(null);
		
		satName = new JTextField();
		satName.setBounds(90, 46, 152, 26);
		panel.add(satName);
		satName.setColumns(10);
		
		JLabel lblName = new JLabel("Satellite");
		lblName.setBounds(29, 46, 58, 27);
		panel.add(lblName);
		lblName.setForeground(Color.BLACK);
		
		JLabel lblInsertSatelliteInformation = new JLabel("Insert satellite information");
		lblInsertSatelliteInformation.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblInsertSatelliteInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblInsertSatelliteInformation.setBounds(43, 16, 314, 16);
		panel.add(lblInsertSatelliteInformation);
		
		JButton btnInsSat = new JButton("Submit");
		btnInsSat.setBounds(254, 67, 117, 40);
		panel.add(btnInsSat);
		
		JLabel lblFirstObs = new JLabel("First Obs");
		lblFirstObs.setForeground(Color.BLACK);
		lblFirstObs.setBounds(29, 76, 58, 27);
		panel.add(lblFirstObs);
		
		JLabel lblLastObs = new JLabel("Last Obs");
		lblLastObs.setForeground(Color.BLACK);
		lblLastObs.setBounds(29, 107, 58, 27);
		panel.add(lblLastObs);
		
		JLabel feed1 = new JLabel("");
		feed1.setHorizontalAlignment(SwingConstants.CENTER);
		feed1.setBounds(250, 106, 125, 31);
		panel.add(feed1);
		
		JDateChooser dateChooserf = new JDateChooser();
		dateChooserf.setBounds(90, 76, 152, 26);
		panel.add(dateChooserf);
		
		JDateChooser dateChooserl = new JDateChooser();
		dateChooserl.setBounds(90, 107, 152, 26);
		panel.add(dateChooserl);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(42, 197, 398, 178);
		contentPane.add(panel_1);
		
		insField = new JTextField();
		insField.setColumns(10);
		insField.setBounds(125, 56, 82, 26);
		panel_1.add(insField);
		
		JLabel lblInstrument = new JLabel("Instrument:");
		lblInstrument.setForeground(Color.BLACK);
		lblInstrument.setBounds(23, 43, 79, 16);
		panel_1.add(lblInstrument);
		
		JLabel lblInsertInstrumentInformation = new JLabel("Insert instrument information");
		lblInsertInstrumentInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblInsertInstrumentInformation.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblInsertInstrumentInformation.setBounds(49, 12, 314, 16);
		panel_1.add(lblInsertInstrumentInformation);
		
		JButton btnInsIns = new JButton("Submit");
		btnInsIns.setBounds(251, 72, 119, 40);
		panel_1.add(btnInsIns);
		
		@SuppressWarnings("rawtypes")
		JComboBox satBox = new JComboBox();
		satBox.setBounds(18, 57, 111, 27);
		panel_1.add(satBox);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_2.setBounds(16, 39, 193, 45);
		panel_1.add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_3.setBounds(18, 91, 189, 70);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JButton buttonins = new JButton("+");
		buttonins.setBounds(141, 6, 42, 29);
		panel_3.add(buttonins);
		
		bandField = new JTextField();
		bandField.setColumns(10);
		bandField.setBounds(51, 6, 95, 26);
		panel_3.add(bandField);
		
		JLabel lblBands = new JLabel("Bands:");
		lblBands.setBounds(6, 11, 43, 16);
		panel_3.add(lblBands);
		lblBands.setForeground(Color.BLACK);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(6, 35, 177, 29);
		panel_3.add(scrollPane);
		
		JLabel bandlist = new JLabel("");
		scrollPane.setViewportView(bandlist);
		
		JLabel feed2 = new JLabel("");
		feed2.setHorizontalAlignment(SwingConstants.CENTER);
		feed2.setBounds(245, 110, 125, 31);
		panel_1.add(feed2);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(RegisterInterface.class.getResource("/view/SpaceRegist.jpg")));
		label.setBounds(0, 0, 540, 382);
		contentPane.add(label);
		
		satName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				feed1.setText("");
			}
		});
		
		dateChooserf.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				feed1.setText("");
			}
		});
		dateChooserl.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				feed1.setText("");
			}
		});
		
		btnInsSat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String satellite = satName.getText();
				if (checkString(satellite)) {
					try {
						Calendar datef = dateChooserf.getCalendar();
						//String datefTry = datef.toString();
						Calendar datel = dateChooserl.getCalendar();
						//String datelTry = datel.toString();
						Calendar today = Calendar.getInstance();
						if (datel.after(datef)) {
							if (datel.before(today)) {
								int day1 = datef.get(Calendar.DAY_OF_MONTH);
								int month1 =datef.get(Calendar.MONTH) + 1;
								int year1 =datef.get(Calendar.YEAR);
								String dateF=null;
								if (month1<10 && day1<10) {
									dateF = year1 + "-0" + month1 + "-0" + day1;
								} else if(month1<10 && day1>10) {
									dateF = year1 + "-0" + month1 + "-" + day1;
								} else if(day1<10 && month1>10){
									dateF = year1 + "-" + month1 + "-0" + day1;
								} else {
									dateF = year1 + "-" + month1 + "-" + day1;
								}
								int day2 = datel.get(Calendar.DAY_OF_MONTH);
								int month2 =datel.get(Calendar.MONTH) + 1;
								int year2 =datel.get(Calendar.YEAR);
								String dateL=null;
								if (month2<10 && day2<10) {
									dateL = year2 + "-0" + month2 + "-0" + day2;
								} else if(month2<10 && day2>10) {
									dateL = year2 + "-0" + month2 + "-" + day2;
								} else if(day2<10 && month2>10){
									dateL = year2 + "-" + month2 + "-0" + day2;
								} else {
									dateL = year2 + "-" + month2 + "-" + day2;
								}
								String satelliteC = satellite.substring(0, 1).toUpperCase() + satellite.substring(1).toLowerCase();
								try {
									Query.insertSatellite(con, satelliteC, dateF, dateL);
									bandlist.setText("");
									insField.setText("");
									satBox.setSelectedIndex(0);
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
							} else {
								feed1.setText("<html><font color= 'red'>Last obs can't be<br> after today!</font></html>");
							}
						} else {
							feed1.setText("<html><font color= 'red'>Last obs must be<br> after the first one!</font></html>");
						}
						} catch (java.lang.NullPointerException e1) {
							feed1.setText("<html><font color= 'red'>Invalid observation's date!</font></html>");
						}
				} else {
					feed1.setText("<html><font color= 'red'>Invalid satellite name!</font></html>");
				}
				
			}
		});
		
		satBox.addFocusListener(new FocusAdapter() {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public void focusGained(FocusEvent e) {
				String[] satellitesNames = null;
				try {
					satellitesNames = Query.getSatellitesNames(con);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				satBox.setModel(new DefaultComboBoxModel(satellitesNames));
			}
		});
		
		btnInsIns.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (satBox.getSelectedIndex()!=0 && satBox.getSelectedIndex()!=-1) {
					String ins = insField.getText();
					int satId = satBox.getSelectedIndex();
					if (checkString(ins)) {
						String bandls = bandlist.getText();
						if (bandls.equals("")) {
							feed2.setText("<html><font color ='red'>Insert at least one band value!</font></html>");
						} else {
							feed2.setText("");
							String bandL = bandlist.getText();
							String bandList[] = bandL.split(",");
							LinkedList<Double> bandDList = new LinkedList<Double>();
							int i = 0;
							while(i < bandList.length) {
								bandDList.add(Double.parseDouble(bandList[i]));
								i++;
							}
							String INS = ins.toUpperCase();
							try {
								Query.insertInstrument(con, INS, bandDList,satId);
								bandlist.setText("");
								insField.setText("");
								satBox.setSelectedIndex(0);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					} else {
						feed2.setText("<html><font color ='red'>Invalid instrument name!</font></html>");
					}
				} else {
					feed2.setText("<html><font color ='red'>Choose satellite!</font></html>");
				}
			}
		});
		
		buttonins.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String band = bandField.getText();
				if(checkDouble(band)) {
					Double bandD= Double.parseDouble(band);
			        if (bandlist.getText().equals("")) {
			         	bandlist.setText(band + ","); 
			         	return;
			      	}
					String bandSList = bandlist.getText();
					String bandList[] = bandSList.split(",");
					LinkedList<Double> bandDList = new LinkedList<Double>();
					int i = 0;
					while(i < bandList.length) {
						bandDList.add(Double.parseDouble(bandList[i]));
						if (bandD.compareTo(bandDList.get(i))==0) {
							feed2.setText("<html><font color='red'>Value already added!</font></html>");
							return;
						}
						i++;
					}
					bandlist.setText(bandlist.getText() + band + ","); 
					feed2.setText("");
				} else {
					feed2.setText("<html><font color='red'>Invalid band value!</font></html>");
				}
				bandField.setText("");
			}
		});
		
		btnClose.addActionListener(new ActionListener() {
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
	
	private boolean checkString(String name) {
		if (Pattern.matches("[a-zA-Z]+", name)) {
			return true;
		} else {
			return false;
		}
	}
}
