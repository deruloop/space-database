package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.DataImport;
import controller.Query;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import java.awt.Color;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JRadioButton;
import java.awt.SystemColor;

public class ImportInterface extends JFrame {

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
					ImportInterface frame = new ImportInterface(con,user_id,type);
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
	public ImportInterface(Connection con,String user_id,boolean type) throws SQLException {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 492, 456);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnClose = new JButton("Back");
		btnClose.setHorizontalAlignment(SwingConstants.RIGHT);
		btnClose.setBounds(-42, 0, 103, 41);
		contentPane.add(btnClose);
		
		JPanel ImportPan = new JPanel();
		ImportPan.setBounds(49, 47, 394, 348);
		contentPane.add(ImportPan);
		ImportPan.setLayout(null);
		
		JLabel feed1 = new JLabel("");
		feed1.setHorizontalAlignment(SwingConstants.CENTER);
		feed1.setBounds(6, 64, 382, 16);
		ImportPan.add(feed1);
		
		JButton btnChoose = new JButton("Choose file");
		btnChoose.setBounds(139, 36, 117, 29);
		ImportPan.add(btnChoose);
		
		JLabel lblChooseACsv = new JLabel("1) Choose a CSV file in your computer to import");
		lblChooseACsv.setBounds(36, 14, 308, 16);
		ImportPan.add(lblChooseACsv);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);
		separator.setBounds(6, 84, 382, 12);
		ImportPan.add(separator);
		
		JLabel lblSpecifyWhat = new JLabel("2) Specify what kind of data are you importing");
		lblSpecifyWhat.setBounds(36, 104, 291, 16);
		ImportPan.add(lblSpecifyWhat);
		
		JRadioButton filData = new JRadioButton("Filaments data");
		filData.setEnabled(false);
		filData.setBounds(140, 128, 134, 23);
		ImportPan.add(filData);
		
		JRadioButton starData = new JRadioButton("Stars data");
		starData.setEnabled(false);
		starData.setBounds(139, 150, 117, 23);
		ImportPan.add(starData);
		
		JRadioButton skeData = new JRadioButton("Skeletons data");
		skeData.setEnabled(false);
		skeData.setBounds(139, 173, 135, 23);
		ImportPan.add(skeData);
		
		JRadioButton bounData = new JRadioButton("Boundaries data");
		bounData.setEnabled(false);
		bounData.setBounds(139, 196, 140, 23);
		ImportPan.add(bounData);
		
		String[] satellitesNames = Query.getSatellitesNames(con);
		JComboBox<?> satBox1 = new JComboBox<Object>();
		satBox1.setModel(new DefaultComboBoxModel(satellitesNames));
		satBox1.setSelectedIndex(0);
		satBox1.setBounds(122, 248, 152, 27);
		ImportPan.add(satBox1);
		satBox1.setEnabled(false);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.GRAY);
		separator_1.setBounds(6, 273, 382, 12);
		ImportPan.add(separator_1);
		
		JLabel lblImport = new JLabel("4) Press import!");
		lblImport.setHorizontalAlignment(SwingConstants.CENTER);
		lblImport.setBounds(53, 281, 291, 16);
		ImportPan.add(lblImport);
		
		JButton btnImport = new JButton("Import");
		btnImport.setEnabled(false);
		btnImport.setBounds(139, 298, 117, 29);
		ImportPan.add(btnImport);
		
		JLabel feed2 = new JLabel("");
		feed2.setHorizontalAlignment(SwingConstants.CENTER);
		feed2.setBounds(6, 326, 382, 16);
		ImportPan.add(feed2);
		
		JLabel absPath = new JLabel("");
		absPath.setForeground(SystemColor.window);
		absPath.setBounds(29, 269, 61, 16);
		ImportPan.add(absPath);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(RegisterInterface.class.getResource("/view/SpaceRegist.jpg")));
		label.setBounds(0, 0, 492, 439);
		contentPane.add(label);
		
		ButtonGroup group = new ButtonGroup();
		group.add(filData);
		group.add(starData);
		group.add(skeData);
		group.add(bounData);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.GRAY);
		separator_2.setBounds(6, 219, 382, 12);
		ImportPan.add(separator_2);
		
		JLabel lblChooseA = new JLabel("3) Choose a satellite");
		lblChooseA.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseA.setBounds(53, 231, 291, 16);
		ImportPan.add(lblChooseA);
		
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				feed2.setText("");
				final JFileChooser jFileChooser = new JFileChooser();
	            int returnVal = jFileChooser.showOpenDialog(ImportInterface.this);
	            if(returnVal == JFileChooser.APPROVE_OPTION){
	                File file = jFileChooser.getSelectedFile();
	                if (file.getName().matches(".*\\.csv")) {
	                		feed1.setText("<html><font color = 'green'>Selected file: " + file.getName() + "</font></html>");
	                		absPath.setText(file.getAbsolutePath());
	                		filData.setEnabled(true);
	                		starData.setEnabled(true);
	                		skeData.setEnabled(true);
	                		bounData.setEnabled(true);
	                } else {
	                		feed1.setText("<html><font color = 'red'>Wrong format file, must be CSV!</font></html>");
	                }
	            }else if(returnVal == JFileChooser.CANCEL_OPTION){
	                feed1.setText("<html><font color = 'red'>Operation cancelled!</font></html>");
	            }else if(returnVal == JFileChooser.ERROR_OPTION){
	                feed1.setText("<html><font color = 'red'>Error!</font></html>");
	            }else{
	                feed1.setText("<html><font color = 'red'>Unknown...</font></html>");
	            }
	            
			}
		});
		
		filData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (filData.isSelected()) {
					satBox1.setEnabled(true);
				}
			}
		});
		
		starData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (starData.isSelected()) {
					satBox1.setSelectedIndex(1);
					satBox1.setEnabled(false);
					btnImport.setEnabled(true);
				}
			}
		});
		
		skeData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (skeData.isSelected()) {
					satBox1.setEnabled(true);
				}
			}
		});
		
		bounData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (bounData.isSelected()) {
					satBox1.setEnabled(true);
				}
			}
		});
		
		satBox1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (satBox1.getSelectedIndex()!=0 && satBox1.getSelectedIndex()!=-1) {
					btnImport.setEnabled(true);
				}
			}
		});
		
		btnImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (satBox1.getSelectedIndex()==0 || satBox1.getSelectedIndex()==-1) {
					feed2.setText("<html><font color ='red'>Choose satellite!</font></html>");
				} else {
					String myPath = absPath.getText();
					String sat = (String) satBox1.getSelectedItem();
					int feed = 0;
					if (filData.isSelected()) {
						feed = DataImport.importFilamentData(con,sat,myPath);
					} else if (starData.isSelected()) {
						feed = DataImport.importStarData(con,myPath);
					} else if (skeData.isSelected()) {
						feed = DataImport.importBranchData(con,sat,myPath);
					} else if (bounData.isSelected()) {
						feed = DataImport.importBoundaryData(con,sat,myPath);
					}
					if (feed == 1) {
						feed2.setText("<html><font color ='red'>Fields don't match! Change CSV file!</font></html>");
						satBox1.setSelectedIndex(0);
						filData.setSelected(false);
						starData.setSelected(false);
						skeData.setSelected(false);
						bounData.setSelected(false);
						filData.setEnabled(false); 
						starData.setEnabled(false); 
						skeData.setEnabled(false);
						bounData.setEnabled(false);
						satBox1.setEnabled(false);
						btnImport.setEnabled(false);
						feed1.setText("<html><font color ='red'>Choose another CSV file!</font></html>");
					} else if (feed == -1) {
						feed2.setText("<html><font color ='blue'>Wrong query! Contact the admins</font></html>");
					} else if (feed == 0) {
						feed2.setText("<html><font color ='green'>Import succeded!</font></html>");
						satBox1.setSelectedIndex(0);
						filData.setSelected(false);
						starData.setSelected(false);
						skeData.setSelected(false);
						bounData.setSelected(false);
						filData.setEnabled(false); 
						starData.setEnabled(false); 
						skeData.setEnabled(false);
						bounData.setEnabled(false);
						satBox1.setEnabled(false);
						btnImport.setEnabled(false);
					}
				}
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

}
